package com.yang.component.market.manager.kind;

import java.util.stream.Stream;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ModelKind {

    ENTITY("entity", "数据库实体"),
    ENUM("enum", "枚举"),
    DTO("dto", "传输实体"),
    ;

    @EnumValue
    private final String code;
    private final String desc;

    ModelKind(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @JsonCreator
    public static ModelKind decode(final String code) {
        return Stream.of(ModelKind.values())
                .filter(targetEnum -> targetEnum.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
