package com.sky.vo;


import com.sky.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsPageVO {
    /**
     * 总条数，这个一定要有
     */
    private Integer counts;
    /**
     * 当前页数
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer pageSize;
    private List<Item> items;
}

