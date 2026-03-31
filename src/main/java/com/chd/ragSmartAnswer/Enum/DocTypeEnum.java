package com.chd.ragSmartAnswer.Enum;

import lombok.Getter;

@Getter
public enum DocTypeEnum {
    PROJECT_MATERIALS(0, "项目资料"),

    DEVELOPMENT_GUIDELINES(1, "开发规范"),

    NEW_TRAINING(2, "新人培训"),

    PROBLEM_ANALYSIS(3, "问题排查"),

    OTHER_MATERIALS(4, "其他资料");

    int code;
    String desc;
    
    DocTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public DocTypeEnum findByCode(){
        for (DocTypeEnum docTypeEnum : DocTypeEnum.values()) {
            if (docTypeEnum.getCode() == code) {
                return docTypeEnum;
            }

        }
        return null;
    }
}