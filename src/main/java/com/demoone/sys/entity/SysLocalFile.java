package com.demoone.sys.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 华强
 * @since 2019-12-30
 */
@TableName("sys_local_file")
public class SysLocalFile extends Model<SysLocalFile> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 备注
     */
    private String description;

    /**
     * 类型
     */
    private String type;

    /**
     * 路径分隔符
     */
    private String volume;

    /**
     * 路径
     */
    private String path;

    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLocalFile{" +
        "id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", type=" + type +
        ", volume=" + volume +
        ", path=" + path +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        "}";
    }
}
