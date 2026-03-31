package com.chd.ragSmartAnswer.entity;

import lombok.Data;


/**
 * RAG智能问答入参类
 */
@Data
public class RagQo {

    /**
     * 用户查询问题
     */
    private String question;
    
    /**
     * 对话ID（可选，用于聊天记忆）
     */
    private Long memoryId;
    
}
