package com.demoone.bussiness.xly.vo;

import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("学员下拉框")
public class StudentDropDown {

    @ApiModelProperty("所属基地下拉框")
    private List<Base> basedropdown;


    @ApiModelProperty("教练下拉框")
    private List<DataCoach> coachdropdown;

    @ApiModelProperty("房间下拉框")
    private List<DataRoom> roomdropdown;



}
