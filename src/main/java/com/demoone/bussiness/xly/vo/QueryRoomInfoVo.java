package com.demoone.bussiness.xly.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ApiModel("房间信息参数类")
@NoArgsConstructor
@ToString
public class QueryRoomInfoVo {

    @ApiModelProperty("页数 默认1")
    private Integer page=1;

    @ApiModelProperty("每页条数 默认10")
    private Integer size=10;

    @ApiModelProperty("房间号")
    private String no;

    @ApiModelProperty("房间状态")
    private String state;
}
