package com.chd.ragSmartAnswer.config;



import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RAG流式大模型配置,为问答服务提供基础能力
 */

@Configuration
public class RAGConfig {

    @Value("${langchain4j.community.dashscope.streaming-chat-model.api-key}")
    private String dashScopeApiKey;

    @Value("${langchain4j.community.dashscope.streaming-chat-model.model-name}")
    private String chatModelName;

    @Value("${langchain4j.community.dashscope.streaming-chat-model.temperature}")
    private Float temperature;

    
    /**
     * 初始化流式大模型，用于最终答案生成，降低温度系数减少幻觉
     */
    @Bean
    public QwenStreamingChatModel streamingChatModel() {
        return QwenStreamingChatModel.builder()
                .apiKey(dashScopeApiKey)
                .modelName(chatModelName)
                .temperature(temperature)
                .build();
    }
}
