package com.demoone.bussiness.xly.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.baomidou.mybatisplus.service.IService;
import com.demoone.bussiness.xly.vo.QueryRoomInfoVo;
import com.demoone.bussiness.xly.vo.RoomInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
public interface IDataRoomService extends IService<DataRoom> {

    boolean addRoom(DataRoom dataRoom);

    Page<RoomInfoVo> queryRoomInfo(QueryRoomInfoVo queryRoomInfoVo);
}
