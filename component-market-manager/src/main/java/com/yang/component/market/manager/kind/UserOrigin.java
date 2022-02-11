package com.yang.component.market.manager.kind;

public enum UserOrigin {

    // 注册
    ZC("zhu_ce"),
    // 快手
    KS("kuai_shou");

    private String value;

    UserOrigin(String value) {
        this.value = value;
    }

}
