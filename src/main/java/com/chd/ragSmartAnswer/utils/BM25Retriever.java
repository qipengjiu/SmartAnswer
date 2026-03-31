package com.chd.ragSmartAnswer.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dev.langchain4j.data.segment.TextSegment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * BM25关键词检索工具：
 * 基于词频的精确检索，弥补向量检索专业术语、代码命令检索的不足
 * 与向量检索结合实现混合检索，提升团队知识库检索准确性
 */
@Component  
@Slf4j
@RequiredArgsConstructor
public class BM25Retriever {
    

    // BM25返回前K条
    @Value("${rag.hybrid-retrieval.bm25-top-k}")
    private Integer bm25TopK;


    /**
     * BM25检索
     * @param query 用户查询问题
     * @return
     */
    public List<TextSegment> retrieve(String query) {
        // TODO: 实现BM25检索逻辑
        return null;
    }
}
