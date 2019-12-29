package com.demoone.bussiness.xly.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.StudentInfoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
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

public interface DataStudentDao extends BaseMapper<Student> {

    /*
     * 停止学员周期
     */
    @Update("update data_student set state='1' where sid=#{sid}")
    boolean  tingZhouQi(String  sid);

    /*
     * 减学员天数
     */
    @Update("update data_student set surplus_day=surplus_day-1 where  state='0'  ")
    boolean jianTianShu();

    /*
     * 今日总在营人数
     */
    @Select("select count(*) from data_student where state='0'")
    String zongZaiYing();

    /*
     * 今日出营人数
     */
    @Select("select count(1) from data_student where state='1' and leave_time=now() ")
    String jinChuYing();

    /*
     * 今日入营人数
     */
    @Select("select count(1) from data_student where  join_time=now() ")
    String jinRuYing();


    /*
      * 获取教练下拉框数据
      */
    @Select("select c.cid,c.name from data_coach c")
    List<DataCoach> coachDropDown();

    /*
  * 获取教练下拉框数据
  */
    @Select("select r.rid,r.no from data_room  r")
    List<DataRoom> roomDropDown();

    List<StudentInfoVo> queryStudentInfo(Page page,QueryStudentInfoVo queryStudentInfoVo);

    @Delete("delete  from  data_student  where sid=#{sid} ")
    boolean deleteStudent(String sid);


    @Select("select * from data_room  where rid=#{roomNo}")
    DataRoom queryRoomFullNum(String roomNo);

    @Select("select * from data_student where room_no=#{roomNo} ")
    List<Student> queryRoomStudentList(String roomNo);
}
