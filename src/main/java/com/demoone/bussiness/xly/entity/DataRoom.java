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
@TableName("data_room")
@Data
@ToString
@ApiModel("房间类")
public class DataRoom extends Model<DataRoom> {

    private static final long serialVersionUID = 1L;

    private String rid;

    @ApiModelProperty("房间号")
    private String no;

    @ApiModelProperty("房间状态,1 未住满 2 已住满")
    private String state;

    @ApiModelProperty("满员人数")
    @TableField("full_num")
    private Integer fullNum;

    @ApiModelProperty("已经入住人数")
    @TableField("existing_num")
    private Integer existingNum;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private Date modifyTime;

    @ApiModelProperty("删除状态，0，显示，1 删除")
    @TableField("delete_state")
    private Integer deleteState;

    @Override
    protected Serializable pkVal() {
        return this.rid;
    }


}
