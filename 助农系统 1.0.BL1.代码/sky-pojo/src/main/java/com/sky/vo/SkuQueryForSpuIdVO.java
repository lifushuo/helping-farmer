package com.sky.vo;


import com.sky.entity.Records;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuQueryForSpuIdVO {
    private List<Records> records;
    private long total;
}