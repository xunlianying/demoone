package com.demoone.bussiness.xly.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demoone.bussiness.xly.vo.CoachInfoVo;
import com.demoone.bussiness.xly.vo.QueryCoachInfoVo;

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
}
