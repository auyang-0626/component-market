package com.yang.component.market.manager.kind;

import java.util.stream.Stream;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AttrKind {

    BASIC("basic", "基本类型"),
    ENUM("enum", "枚举"),
    ;

    @EnumValue
    private final String code;
    private final String desc;

    AttrKind(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static AttrKind decode(final String code) {
        return Stream.of(AttrKind.values())
                .filter(targetEnum -> targetEnum.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
