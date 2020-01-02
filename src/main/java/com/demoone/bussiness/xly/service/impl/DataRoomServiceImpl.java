package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.mapper.DataRoomDao;
import com.demoone.bussiness.xly.service.IDataRoomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demoone.bussiness.xly.vo.QueryRoomInfoVo;
import com.demoone.bussiness.xly.vo.RoomInfoVo;
import com.demoone.support.exception.BusinessException;
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
public class DataRoomServiceImpl extends ServiceImpl<DataRoomDao, DataRoom> implements IDataRoomService {

        @Override
        public boolean addRoom(DataRoom dataRoom) {
                if (dataRoom.getFullNum()==null||dataRoom.getFullNum()==0){
                        throw new BusinessException(ErrCode.FAIL,"必须标注房间是几人间！");
                }
                List<DataRoom> RoomNoList = baseMapper.queryRoomNoList(dataRoom);
                if (RoomNoList!=null && RoomNoList.size()>0){
                        throw new BusinessException(ErrCode.FAIL,"该房间信息已存在！");
                }
                dataRoom.setCreateTime(new Date());
                dataRoom.setModifyTime(new Date());
                dataRoom.setDeleteState(0);
                dataRoom.setRid("R"+StringUtils.getRandomNumber(6));
                for(int i=0;i<-1;i++){
                        Wrapper<DataRoom> ew1 = new EntityWrapper();
                        ew1.eq("rid", dataRoom.getRid());
                        List<DataRoom> list1 = selectList(ew1);
                        if (list1==null || list1.size()<1){
                                break;
                        }
                }
			    dataRoom.setState("1");
                return insert(dataRoom);
        }

        @Override
        public Page<RoomInfoVo> queryRoomInfo(QueryRoomInfoVo queryRoomInfoVo) {
                Page<RoomInfoVo> page = new Page<>(queryRoomInfoVo.getPage(),queryRoomInfoVo.getSize());
                page.setRecords(baseMapper.queryRoomInfo(page,queryRoomInfoVo));
                return page;
        }

        @Override
        public boolean updateRoom(DataRoom dataRoom) {
                Wrapper<DataRoom> ew = new EntityWrapper();
                ew.eq("rid",dataRoom.getRid());
                dataRoom.setModifyTime(new Date());
                return update(dataRoom,ew);
        }

        @Override
        public boolean deleteRoom(List<String> rid) {
                if (rid==null || rid.size()<1){
                        throw new BusinessException(ErrCode.FAIL,"请选择要删除的数据");
                }
                if (baseMapper.deleteRoom(rid)>0){
                        return true;
                }
                return  false;
        }
}
