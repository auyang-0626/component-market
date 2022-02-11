package com.yang.component.market.manager.kind;

import java.util.stream.Stream;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationKind {

    ONE_TO_MANY("one-to-many", "1对多"),
    ONE_TO_ONE("one-to-one", "1对1"),
    ;

    @EnumValue
    private final String code;
    private final String desc;

    RelationKind(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static RelationKind decode(final String code) {
        return Stream.of(RelationKind.values())
                .filter(targetEnum -> targetEnum.code.equals(code))
                .findFirst()
                .orElse(null);
    }

}
