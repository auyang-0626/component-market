package com.yang.component.market.manager.dto;

import java.io.Serializable;

import com.yang.component.market.manager.kind.UserOrigin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUser implements Serializable {

    private Long id;

    /**
     * 来源, KS(快手),ZC(注册)
     */
    private UserOrigin userOrigin;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String userEmail;
}
