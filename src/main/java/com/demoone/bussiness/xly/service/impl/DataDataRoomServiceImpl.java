package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.mapper.DataRoomDao;
import com.demoone.bussiness.xly.service.IDataRoomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demoone.support.exception.SellException;
import com.demoone.support.sys.ErrCode;
import com.demoone.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
@Service
public class DataDataRoomServiceImpl extends ServiceImpl<DataRoomDao, DataRoom> implements IDataRoomService {

        @Override
        public boolean addRoom(DataRoom dataRoom) {
                if (StringUtils.isNotBlank(dataRoom.getNo())){
                        Wrapper<DataRoom> ew = new EntityWrapper();
                        ew.eq("no", dataRoom.getNo());
                        List<DataRoom> list = selectList(ew);
                        if (list!=null && list.size()>0){
                                throw new SellException(ErrCode.FAIL,"该房间信息已存在！");
                        }
                }
                dataRoom.setCreateTime(new Date());
                return insert(dataRoom);
        }
}