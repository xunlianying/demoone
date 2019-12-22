package com.demoone.bussiness.xly.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("基地")
public class Base {


    @ApiModelProperty("基地编号")
    private String sid;


    @ApiModelProperty("基地名称")
    private String name;


}
