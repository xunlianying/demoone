package com.demoone.bussiness.xly.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 照片信息表
 * </p>
 *
 * @author 华强
 * @since 2019-12-31
 */
@TableName("data_photo_info")
public class DataPhotoInfo extends Model<DataPhotoInfo> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 文件id
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 1学院入营照片 2学员离营照片 3学员合同照片 4教练照片
     */
    private Integer type;

    /**
     * 文件信息id 学员照片存学员id，宿舍照片存宿舍id
     */
    @TableField("info_id")
    private String infoId;

    @TableField("create_time")
    private Date createTime;

    /**
     * 0可用，1不可用
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataPhotoInfo{" +
        "id=" + id +
        ", fileId=" + fileId +
        ", type=" + type +
        ", infoId=" + infoId +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
