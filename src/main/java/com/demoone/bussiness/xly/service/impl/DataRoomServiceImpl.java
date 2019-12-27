package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.Student;
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
                Wrapper<DataRoom> ew = new EntityWrapper();
                ew.eq("rid",dataRoom.getRid());
                List<DataRoom> list = selectList(ew);
                if (list!=null && list.size()>0){
                        throw new BusinessException(ErrCode.FAIL,"该房间信息已存在！");
                }
                dataRoom.setCreateTime(new Date());
                dataRoom.setRid("R"+StringUtils.getRandomNumber(6));
                for(int i=0;i<-1;i++){
                        Wrapper<DataRoom> ew1 = new EntityWrapper();
                        ew1.eq("rid", dataRoom.getRid());
                        List<DataRoom> list1 = selectList(ew1);
                        if (list1==null || list1.size()<1){
                                break;
                        }
                }
              String rid=dataRoom.getRid();
                List<Student> listStudent = baseMapper.queryRoomNo(rid);
                if (listStudent==null || listStudent.size()<1){
                        dataRoom.setExistingNum(0);
                        dataRoom.setState("1");//未住满
                }else if (listStudent.size()<dataRoom.getFullNum()){
                        dataRoom.setExistingNum(listStudent.size());
                        dataRoom.setState("1");//未住满
                }else if (listStudent.size()==dataRoom.getFullNum()){
                        throw new BusinessException(ErrCode.FAIL,"该房间已经住满！");
                }
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
        public boolean deleteRoom(String rid) {
                return  baseMapper.deleteRoom(rid);
        }
}
