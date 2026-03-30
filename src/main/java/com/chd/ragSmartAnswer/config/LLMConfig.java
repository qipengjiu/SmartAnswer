package com.chd.ragSmartAnswer.config;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Value("${DASH_SCOPE_API_KEY}")
    private String dashScopeApiKey;

    @Bean
    public QwenChatModel qwenChatModel(){
        return QwenChatModel.builder()
                .apiKey(dashScopeApiKey)
                .modelName("qwen-plus")
                // 温度参数，和配置文件一致
                .temperature(0.1F)
                // 最大生成 Token 数
                .maxTokens(2048)
                .build();
    }
}
