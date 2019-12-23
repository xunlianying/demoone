package com.demoone.bussiness.xly.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ApiModel("学员信息参数类")
@NoArgsConstructor
@ToString
public class QueryStudentInfoVo {

    @ApiModelProperty("页数 默认1")
    private Integer page=1;

    @ApiModelProperty("每页条数 默认10")
    private Integer size=10;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别 1男 2女")
    private Integer gender;

    @ApiModelProperty("所属基地")
    private String base;

    @ApiModelProperty("加入时间")
    @TableField("join_time")
    private Date joinTime;

    @ApiModelProperty("离营时间")
    @TableField("leave_time")
    private Date leaveTime;

    @ApiModelProperty("销售（谁招收的学员）")
    private String sales;

    @ApiModelProperty("房间编号")
    @TableField("room_no")
    private String roomNo;

    @ApiModelProperty("所属教练")
    @TableField("coach_id")
    private String coachId;

    @ApiModelProperty("学员状态")
    private Integer state;

}
