package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpuAttrResultVo implements Serializable {
    private String name;
    private String spuid;
    private List<SpuAttrResultValueVo> values;
}
