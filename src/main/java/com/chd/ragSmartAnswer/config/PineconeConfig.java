package com.chd.ragSmartAnswer.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Pinecone向量存储配置
 */
@Configuration
public class PineconeConfig {

    @Value("${pinecone.api-key}")
    private String pineconeApiKey;

    @Value("${pinecone.index}")
    private String pineconeIndex;

    @Value("${pinecone.region}")
    private String pineconeRegion;

    @Value("${pinecone.namespace}")
    private String pineconeNamespace;
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(EmbeddingModel embeddingModel) {
        return PineconeEmbeddingStore.builder()
                .apiKey(pineconeApiKey)
                .index(pineconeIndex)
                .nameSpace(pineconeNamespace)
                .createIndex(PineconeServerlessIndexConfig.builder()
                        .dimension(embeddingModel.dimension())
                        .cloud("AWS")
                        .region(pineconeRegion)
                        .build())
                .build();

    }
}
