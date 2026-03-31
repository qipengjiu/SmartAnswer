package com.chd.ragSmartAnswer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chd.ragSmartAnswer.Result.Result;
import com.chd.ragSmartAnswer.entity.DocumentForm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 文档管理相关接口
 */
@Tag(name = "文档管理", description = "团队RAG知识库文档上传接口")
@RestController
@RequestMapping("/v1/api/document")
@RequiredArgsConstructor
public class DocumentController {
    

    @Operation(summary = "文档上传并入库向量库")
    @PostMapping("/upload")
    public Result<String> uploadDocument(DocumentForm form) {
        // TODO: 实现文档上传逻辑   
        return Result.success("文档上传成功");
    }
}