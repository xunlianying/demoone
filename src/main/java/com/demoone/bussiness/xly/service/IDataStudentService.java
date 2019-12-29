package com.demoone.bussiness.xly.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.Student;
import com.baomidou.mybatisplus.service.IService;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.StudentInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
public interface IDataStudentService extends IService<Student> {
    /**
     *  停止学员周期
     */
    boolean tingZhouQi(String sid);
    /**
     *  减学员天数
     */
    boolean jianTianShu();
    /**
     *  今日总在营人数
     */
    String zongZaiYing();
    /**
     *  今日出营人数
     */
    String jinChuYing();
    /**
     *  今日入营人数
     */
    String jinRuYing();
    /**
     *  添加学员信息
     */
    boolean addStudent(Student student);
    /**
     *  获取教练下拉框
     */
    List<DataCoach> coachDropDown();
    /**
     *  获取房间下拉框
     */
    List<DataRoom> roomDropDown();

    Page<StudentInfoVo> queryStudentInfo(QueryStudentInfoVo queryStudentInfoVo);

    boolean updateStudent(Student student);

    boolean deleteStudent(String sid);
}
