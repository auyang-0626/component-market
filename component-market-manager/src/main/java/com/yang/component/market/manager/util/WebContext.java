package com.yang.component.market.manager.util;

import com.yang.component.market.manager.dto.LoginUser;
import com.yang.component.market.manager.kind.UserOrigin;

public class WebContext {

    public static LoginUser getLoginUser() {
        return LoginUser.builder()
                .id(1L)
                .userName("mock")
                .userEmail("123@163.com")
                .userOrigin(UserOrigin.ZC)
                .build();
    }
}
