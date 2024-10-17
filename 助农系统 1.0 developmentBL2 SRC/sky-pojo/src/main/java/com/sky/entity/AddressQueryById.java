package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressQueryById {
    private Integer userId;
    private Integer addressId;

    private String receiver;
    private String contact;
    private Integer provinceCode;
    private Integer cityCode;
    private Integer districtCode;
    private String fullLocation;
    private String address;


}
