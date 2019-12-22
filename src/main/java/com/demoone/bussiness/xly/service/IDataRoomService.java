package com.demoone.bussiness.xly.service;

import com.demoone.bussiness.xly.entity.DataRoom;
import com.baomidou.mybatisplus.service.IService;

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
}
