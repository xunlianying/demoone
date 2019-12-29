package com.demoone.bussiness.xly.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demoone.bussiness.xly.entity.Student;
import com.demoone.bussiness.xly.vo.QueryRoomInfoVo;
import com.demoone.bussiness.xly.vo.RoomInfoVo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
public interface DataRoomDao extends BaseMapper<DataRoom> {


    List<RoomInfoVo> queryRoomInfo(Page<RoomInfoVo> page, QueryRoomInfoVo queryRoomInfoVo);

    List<Student> queryRoomNo(DataRoom dataRoom);

    @Delete("delete  from  data_room  where rid=#{rid} ")
    boolean deleteRoom(String rid);

    List<DataRoom> queryRoomNoList(DataRoom dataRoom);
}