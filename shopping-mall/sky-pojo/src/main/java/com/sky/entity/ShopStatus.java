package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (ShopStatus)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:11:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopStatus implements Serializable {
    private static final long serialVersionUID = -84527590220079433L;
/**
     * 商店营业状态
     */
    private String status;




}

