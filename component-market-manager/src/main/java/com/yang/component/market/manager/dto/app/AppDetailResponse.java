package com.yang.component.market.manager.dto.app;

import org.springframework.beans.BeanUtils;

import com.yang.component.market.manager.entity.app.AppPojo;

/**
 * @author yangguangyue
 * Created on 2021-12-30
 */
public class AppDetailResponse extends AppPojo {


    public static AppDetailResponse build(AppPojo app) {
        AppDetailResponse response = new AppDetailResponse();
        BeanUtils.copyProperties(app, response);
        return response;
    }
}
