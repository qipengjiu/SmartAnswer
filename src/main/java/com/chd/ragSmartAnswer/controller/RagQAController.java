package com.chd.ragSmartAnswer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chd.ragSmartAnswer.entity.RagQo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * RAG问答接口：混合检索+大模型问答
 */
@Tag(name = "RAG问答", description = "团队RAG知识库混合检索问答接口(流式输出)")
@RestController
@RequestMapping("/api/rag")
@RequiredArgsConstructor
public class RagQAController {



    @Operation(summary = "RAG混合检索问答（流式输出）")
    @PostMapping(value = "/qa", produces = MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=utf-8")
    public Flux<String> qa(@RequestBody RagQo qo) {
        
        // TODO: 实现RAG混合检索问答逻辑
        return Flux.just("Hello, world!");
    }
}
