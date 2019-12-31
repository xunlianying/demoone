package com.demoone.bussiness.xly.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demoone.bussiness.xly.vo.CoachInfoVo;
import com.demoone.bussiness.xly.vo.QueryCoachInfoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
public interface DataCoachDao extends BaseMapper<DataCoach> {

    List<CoachInfoVo> queryCoachInfo(Page<CoachInfoVo> page, QueryCoachInfoVo queryCoachInfoVo);

    @Update("update   data_coach set delete_state=1 where cid=#{cid} ")
    boolean deleteCoach(String rid);
}
