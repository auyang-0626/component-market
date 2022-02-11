package com.yang.component.market.manager.kind;

import java.util.stream.Stream;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppKind {

    SPRING_BOOT("spring_boot", "SpringBoot应用");

    @EnumValue
    private final String code;
    private final String desc;

    AppKind(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static AppKind decode(final String code) {
        return Stream.of(AppKind.values())
                .filter(targetEnum -> targetEnum.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
