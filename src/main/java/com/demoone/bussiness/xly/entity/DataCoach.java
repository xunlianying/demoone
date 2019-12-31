package com.demoone.bussiness.xly.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
@TableName("data_coach")
@ApiModel("教练类")
@Data
@ToString
public class DataCoach extends Model<DataCoach> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("教练编号")
    private String cid;

    @ApiModelProperty("教练姓名")
    private String name;

    @TableField("id_no")
    @ApiModelProperty("身份证")
    private String idNo;

    @ApiModelProperty("性别 1男 2女")
    private Integer sex;

    @ApiModelProperty("职位")
    private String position;

    @ApiModelProperty("教练状态")
    private Integer state;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private Date modifyTime;

    @ApiModelProperty("教练联系方式")
    private String phone;

    @ApiModelProperty("删除状态，0，显示，1 删除")
    private Integer deleteState;

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }

}
