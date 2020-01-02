package com.demoone.bussiness.xly.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.DataStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.DataStudentInfoVo;
import org.apache.ibatis.annotations.Param;
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

public interface DataStudentDao extends BaseMapper<DataStudent> {

    /*
     * 停止学员周期
     */
    @Update("update data_student set state=1 where sid=#{sid} and delete_state=0")
    boolean  tingZhouQi(String  sid);

    /*
     * 减学员天数
     */
    @Update("update data_student set surplus_day=surplus_day-1 where  state=0 and delete_state=0 ")
    boolean jianTianShu();

    /*
     * 今日总在营人数
     */
    @Select("select count(*) from data_student where state=0  and delete_state=0 ")
    String zongZaiYing();

    /*
     * 今日出营人数
     */
    @Select("select count(1) from data_student where state=1 and leave_time=now() and delete_state=0 ")
    String jinChuYing();

    /*
     * 今日入营人数
     */
    @Select("select count(1) from data_student where  join_time=now() and delete_state=0 ")
    String jinRuYing();


    /*
      * 获取教练下拉框数据
      */
    @Select("select c.cid,c.name from data_coach c where  c.delete_state=0 ")
    List<DataCoach> coachDropDown();

    /*
  * 获取教练下拉框数据
  */
    @Select("select r.rid,r.no from data_room  r where  r.delete_state=0 and state=1")
    List<DataRoom> roomDropDown();

    List<DataStudentInfoVo> queryStudentInfo(Page page, QueryStudentInfoVo queryStudentInfoVo);

    //@Update(" update data_student set delete_state=1  where sid=#{sid} ")
    int  deleteStudent(@Param("sid") List<String> sid);


    @Select("select * from data_room  where rid=#{roomNo} and delete_state=0 ")
    DataRoom queryRoomFullNum(String roomNo);

    @Select("select * from data_student where room_no=#{roomNo}  and delete_state=0 ")
    List<DataStudent> queryRoomStudentList(String roomNo);

    @Update("update data_student set state=0 where sid=#{sid} and delete_state=0")
    boolean jiHuoZhouQi(String sid);
}
