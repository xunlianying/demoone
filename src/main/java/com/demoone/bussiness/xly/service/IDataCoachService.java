package com.demoone.bussiness.xly.service;

import com.demoone.bussiness.xly.entity.DataCoach;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
public interface IDataCoachService extends IService<DataCoach> {

    boolean addCoach(DataCoach dataCoach);
}
