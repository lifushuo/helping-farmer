package com.sky.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecondaryCategoryAddDto {
    Long id;
    Long category1Id;
    String name;


}
