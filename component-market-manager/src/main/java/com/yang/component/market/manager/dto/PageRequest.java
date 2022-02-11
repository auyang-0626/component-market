package com.yang.component.market.manager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2021-12-28
 */
@Data
public class PageRequest implements Serializable {

    private long size = 20;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();

}
