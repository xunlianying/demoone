package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.mapper.DataCoachDao;
import com.demoone.bussiness.xly.service.IDataCoachService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class DataCoachServiceImpl extends ServiceImpl<DataCoachDao, DataCoach> implements IDataCoachService {

    @Override
    public boolean addCoach(DataCoach dataCoach) {
        dataCoach.setCreateTime(new Date());
        dataCoach.setCid("C"+StringUtils.getRandomNumber(6));
        for(int i=0;i<-1;i++){
            Wrapper<DataCoach> ew = new EntityWrapper();
            ew.eq("cid", dataCoach.getCid());
            List<DataCoach> list = selectList(ew);
            if (list==null || list.size()<1){
                break;
            }
        }
        return insert(dataCoach);
    }
}
