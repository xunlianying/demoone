package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.mapper.DataCoachDao;
import com.demoone.bussiness.xly.service.IDataCoachService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demoone.bussiness.xly.vo.CoachInfoVo;
import com.demoone.bussiness.xly.vo.QueryCoachInfoVo;
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
public class DataCoachServiceImpl extends ServiceImpl<DataCoachDao, DataCoach> implements IDataCoachService {

    @Override
    public boolean addCoach(DataCoach dataCoach) {
        Wrapper<DataCoach> ew = new EntityWrapper();
        ew.eq("name",dataCoach.getName());
        List<DataCoach> list = selectList(ew);
        if (list!=null && list.size()>0){
            throw new BusinessException(ErrCode.FAIL,"该教练信息已存在！");
        }
        dataCoach.setCreateTime(new Date());
        dataCoach.setModifyTime(new Date());
        dataCoach.setDeleteState(0);
        dataCoach.setCid("C"+StringUtils.getRandomNumber(6));
        for(int i=0;i<-1;i++){
            Wrapper<DataCoach> ew1 = new EntityWrapper();
            ew1.eq("cid", dataCoach.getCid());
            List<DataCoach> list1 = selectList(ew1);
            if (list1==null || list1.size()<1){
                break;
            }
        }
        return insert(dataCoach);
    }

    @Override
    public Page<CoachInfoVo> queryCoachInfo(QueryCoachInfoVo queryCoachInfoVo) {
        Page<CoachInfoVo> page = new Page<>(queryCoachInfoVo.getPage(),queryCoachInfoVo.getSize());
        page.setRecords(baseMapper.queryCoachInfo(page,queryCoachInfoVo));
        return page;
    }

    @Override
    public boolean updateCoach(DataCoach dataCoach) {
        Wrapper<DataCoach> ew = new EntityWrapper();
        ew.eq("cid",dataCoach.getCid());
		DataCoach dataCoach1 = selectOne(ew);
		if (dataCoach1==null){
			throw new BusinessException(ErrCode.FAIL,"该教练信息不存在！");
		}
		dataCoach1.setModifyTime(new Date());
		dataCoach1.setName(dataCoach.getName());
		dataCoach1.setPhone(dataCoach.getPhone());
		dataCoach1.setPosition(dataCoach.getPosition());
		dataCoach1.setSex(dataCoach.getSex());
        return updateById(dataCoach);
    }

    @Override
    public boolean deleteCoach(String cid) {
        return baseMapper.deleteCoach(cid);
    }
}
