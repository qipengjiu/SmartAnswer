package com.chd.ragSmartAnswer.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 语义分块工具：
 * 基于余弦相似度的动态分块，解决固定分块语义断裂问题
 * 
 * 步骤：
 * 1.初步按句子/段落切分 
 * 2.向量化计算相似度 
 * 3.高于阈值则合并 
 * 4.最终生成语义完整的块
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SemanticSplitter {

    private final EmbeddingModel embeddingModel;
    
    private final Tokenizer tokenizer = new HuggingFaceTokenizer();

    // 语义分块相似度阈值
    @Value("${rag.semantic-splitter.similarity-threshold}")
    private Double similarityThreshold;
    
    // 单块最大token数
    @Value("${rag.semantic-splitter.max-tokens}")
    private Integer maxTokens;

    // 块间重叠token
    @Value("${rag.semantic-splitter.overlap-tokens}")
    private Integer overlapTokens;


    /**
     * 对文档进行语义分块
     * @param document
     * @return
     */
    public List<TextSegment> split(Document document) {

        // TODO: 实现语义分块逻辑
        return null;
    }
}
