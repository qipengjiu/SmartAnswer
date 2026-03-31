package com.chd.ragSmartAnswer.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 文档上传入参类
*/
@Data
public class DocumentForm {

    /**
     * 文档文件(支持PDF/WORD/MD//TXT)
     */
    private MultipartFile file;

    /**
     * 文件类型（0-项目资料, 1-开发规范, 2-新人培训，3-问题排查，4-其他资料）
     */

    private Integer docType;

    /**
     * 文件描述
     */
    private String description;
}