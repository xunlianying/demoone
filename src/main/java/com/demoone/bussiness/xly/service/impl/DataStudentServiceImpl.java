package com.demoone.bussiness.xly.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.DataStudent;
import com.demoone.bussiness.xly.mapper.DataStudentDao;
import com.demoone.bussiness.xly.service.IDataStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.DataStudentInfoVo;
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
public class DataStudentServiceImpl extends ServiceImpl<DataStudentDao, DataStudent> implements IDataStudentService {

    /**.
     * 停止学员周期
     * @param sid 学员的编号
     * @return
     */
    @Override
    public boolean tingZhouQi(String sid) {
        return baseMapper.tingZhouQi(sid);
    }
    /**.
     *  减学员天数
     * @param
     * @return
     */
    @Override
    public boolean jianTianShu() {
        return baseMapper.jianTianShu();
    }
    /**.
     *  今日总在营人数
     * @param
     * @return 今日总在营人数
     */
    public String zongZaiYing() {
        return baseMapper.zongZaiYing();
    }

    /**.
     * 今日出营人数
     * @return  今日出营人数
     */
    @Override
    public String jinChuYing() {
        return baseMapper.jinChuYing();
    }
    /**.
     *  今日入营人数
     * @return  今日入营人数
     */
    @Override
    public String jinRuYing() {
        return baseMapper.jinRuYing();
    }

    @Override
    public boolean addStudent(DataStudent dataStudent) {
        if (StringUtils.isNotBlank(dataStudent.getIdNo())){
//            Wrapper<Student> ew = new EntityWrapper();
//            ew.eq("sid",student.getSid());
//            List<Student> list = selectList(ew);
//            if (list!=null && list.size()>0){
//                throw new BusinessException(ErrCode.FAIL,"该学员信息已存在！");
//            }
            Wrapper<DataStudent> ew2 = new EntityWrapper();
            ew2.eq("id_no", dataStudent.getIdNo());
            List<DataStudent> list2 = selectList(ew2);
            if (list2!=null && list2.size()>0){
                throw new BusinessException(ErrCode.FAIL,"该学员信息已存在！");
            }
        }else{
            throw  new BusinessException(ErrCode.FAIL,"身份证号不能为空！");
        }

        dataStudent.setCreateTime(new Date());
        dataStudent.setModifyTime(new Date());
        dataStudent.setDeleteState(0);
        dataStudent.setSid("S"+StringUtils.getRandomNumber(6));
        for(int i=0;i<-1;i++){
            Wrapper<DataStudent> ew = new EntityWrapper();
            ew.eq("sid", dataStudent.getSid());
            List<DataStudent> list = selectList(ew);
            if (list==null || list.size()<1){
                break;
            }
        }
        DataRoom dataRoom = baseMapper.queryRoomFullNum(dataStudent.getRoomNo());
        List<DataStudent> listDataStudentNo =  baseMapper.queryRoomStudentList(dataStudent.getRoomNo());

        if (dataRoom.getFullNum()<= listDataStudentNo.size()){
            throw new BusinessException(ErrCode.FAIL,"该房间已住满！");
        }
        if (dataStudent.getIdNo().length()!=18||dataStudent.getIdNo()==null){
            throw new BusinessException(ErrCode.FAIL,"身份证不合法！");
        }else {
            int sex = Integer.parseInt(dataStudent.getIdNo().substring(16, 17));
            if (sex % 2 > 0) {
                dataStudent.setGender(1);
            } else {
                dataStudent.setGender(2);
            }
        }
        return insert(dataStudent);
    }

    /**.
     *  获取教练下拉框数据
     * @return  教练下拉框数据
     */
    @Override
    public List<DataCoach> coachDropDown() {
        return baseMapper.coachDropDown();
    }
    /**.
     *  获取房间下拉框数据
     * @return  房间下拉框数据
     */
    @Override
    public List<DataRoom> roomDropDown() {
        return  baseMapper.roomDropDown();
    }


    /**.
     *  根据条件查询学员信息
     * @param
     * @return  学员的信息
     */
    @Override
    public Page<DataStudentInfoVo> queryStudentInfo(QueryStudentInfoVo queryStudentInfoVo) {
        Page<DataStudentInfoVo> page = new Page<>(queryStudentInfoVo.getPage(),queryStudentInfoVo.getSize());
        page.setRecords(baseMapper.queryStudentInfo(page,queryStudentInfoVo));
        return page;
    }
    /**.
     *  修改学员信息
     * @param
     * @return
     */
    @Override
    public boolean updateStudent(DataStudent dataStudent) {
        Wrapper<DataStudent> ew = new EntityWrapper();
        ew.eq("sid", dataStudent.getSid());
        dataStudent.setModifyTime(new Date());
        return update(dataStudent,ew);
    }

    @Override
    public boolean deleteStudent(String sid) {
        return baseMapper.deleteStudent(sid);
    }
}
