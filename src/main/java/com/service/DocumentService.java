package com.service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chd.ragSmartAnswer.utils.SemanticSplitter;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {


    private final SemanticSplitter semanticSplitter;

    private final EmbeddingModel embeddingModel;

    private final PineconeEmbeddingStore pineconeEmbeddingStore;

    /**
     * 文档上传并入库向量库
     * @param file 文档文件
     * @param docType 文档类型
     * @return 入库结果
     */
    public String uploadAndStore(MultipartFile file, Integer docType) {
        try {
            String fileName = file.getOriginalFilename();
            if(fileName == null || fileName.isEmpty()){
                return "error: filename is empty";
            }

            log.info("开始处理文档：{}，类型：{}", fileName, docType);
            
            // 创建临时文件用于解析
            File tempFile;
            tempFile = File.createTempFile(UUID.randomUUID().toString(), fileName.substring(fileName.lastIndexOf(".")));
            file.transferTo(tempFile);

            // 将文件解析为文档对象
            Document document;
            if (fileName.endsWith(".pdf")) {
                document = FileSystemDocumentLoader.loadDocument(tempFile.getAbsolutePath(), new ApachePdfBoxDocumentParser());
            } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
                document = FileSystemDocumentLoader.loadDocument(tempFile.getAbsolutePath(), new ApachePoiDocumentParser());
            } else {
                // MD/TXT/HTML使用默认解析器
                document = FileSystemDocumentLoader.loadDocument(tempFile.getAbsolutePath());
            }
            // 为文档添加标签
            document.metadata().put("name", fileName);
            document.metadata().put("type", docType);
            document.metadata().put("size", file.getSize()+"B");

            // 语义分块
            List<TextSegment> segments = semanticSplitter.split(document);

            // 向量化并存入向量数据库
            for (TextSegment segment : segments) {
                pineconeEmbeddingStore.add(embeddingModel.embed(segment.text()).content(), segment);
            }

            // 删除临时文件
            Files.deleteIfExists(tempFile.toPath());

            log.info("文档[{}]处理完成，成功入库{}个语义块", fileName, segments.size());
            return String.format("文档[%s]上传并入库成功，生成%d个语义块", fileName, segments.size());
        } catch (Exception e) {
            log.error("文档处理失败", e);
            return "文档处理失败：" + e.getMessage();
        }
    }
    
}
