package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (SpuPicture)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:38
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpuPicture implements Serializable {
    private static final long serialVersionUID = 359013093441404672L;
/**
     * 主键
     */
    private Integer id;
/**
     * spu_id
     */
    private Integer spuId;
/**
     * 图片地址
     */
    private String pictureUrl;




}

