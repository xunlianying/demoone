package com.demoone.bussiness.xly.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
@ApiModel("学员类")
@TableName("data_student")
@Data
@ToString
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("学员编号")
    private String sid;


    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别 1男 2女")
    private Integer gender;

    @TableField("id_no")
    @ApiModelProperty("身份证")
    private String idNo;

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

    @ApiModelProperty("总天数")
    @TableField("total_day")
    private Integer totalDay;

    @ApiModelProperty("剩余天数")
    @TableField("surplus_day")
    private Integer surplusDay;

    @ApiModelProperty("房间编号")
    @TableField("room_no")
    private String roomNo;

    @ApiModelProperty("所属教练")
    @TableField("coach_id")
    private String coachId;

    @ApiModelProperty("所交费用")
    private BigDecimal cost;

    @ApiModelProperty("入营体重")
    @TableField("join_weight")
    private Double joinWeight;

    @ApiModelProperty("离营体重")
    @TableField("leave_weight")
    private Double leaveWeight;

    @ApiModelProperty("学员状态，0，在营，1 离营")
    private Integer state;


    @ApiModelProperty("删除状态，0，显示，1 删除")
    private Integer deleteState;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private Date modifyTime;

    @ApiModelProperty("学员联系方式")
    private String phone;

    @Override
    protected Serializable pkVal() {
        return this.sid;
    }

}
