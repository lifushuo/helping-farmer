package com.sky.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * (SecondaryClassification)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("secondary_classification")
public class SecondaryClassification implements Serializable {
    private static final long serialVersionUID = -12821464710977119L;
    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 二级商品名
     */
    private String name;
    /**
     * 父类id
     */
    private String parentId;
    /**
     * 父类名
     */
    private String parentName;
    /**
     * 是否有效（1有效0无效）
     */
    private String isEffective;
    /**
     * 是否删除（1删除0未删除）
     */
    private String isDeleted;

}

