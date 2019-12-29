package com.demoone.bussiness.xly.web;


import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.entity.Student;
import com.demoone.bussiness.xly.service.IDataStudentService;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.StudentManagerHeadVo;
import com.demoone.support.exception.BusinessException;
import com.demoone.support.sys.ErrCode;
import com.demoone.support.sys.OptResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/xly/base/student")
@Api(value = "StudentController", description = "学员信息")
public class DataStudentController {


	@Autowired
	private IDataStudentService iDataStudentService;

	@ApiOperation(value = "增加学员信息", notes = "增加学员信息")
	@PostMapping("addStudent")
	public OptResult addStudent(@RequestBody Student student) {
		OptResult result = null;
		if (iDataStudentService.addStudent(student)){
			result= OptResult.success();
			result.setMsg("添加成功!");
		}else {
			result= OptResult.fail();
			result.setMsg("添加失败，请稍后再试!");
		}
		return result;
	}

	@ApiOperation(value = "学员信息查询", notes = "学员信息查询")
	@PostMapping("queryStudentInfo")
	public OptResult queryStudentInfo(@RequestBody QueryStudentInfoVo queryStudentInfoVo) {
		OptResult result = OptResult.success();
		result.setData(iDataStudentService.queryStudentInfo(queryStudentInfoVo));
		return result;
	}

	@ApiOperation(value = "删除学员信息", notes = "删除学员信息")
	@GetMapping("delete")
	public OptResult delete(String sid) {
		OptResult result = null;
		if (iDataStudentService.deleteStudent(sid)){
			result= OptResult.success();
			result.setMsg("删除成功！");
		}else {
			throw new BusinessException(ErrCode.FAIL,"删除学员失败！");
		}
		return result;
	}
//	@ApiOperation(value = "先获取学员信息", notes = "先获取学员信息",response = Student.class)
//	@GetMapping("updateid")
//	@ApiImplicitParams(
//			@ApiImplicitParam(name = "id",value = "学院id",paramType = "query",required = true)
//	)
//	public OptResult updateId(int id) {
//		OptResult result =OptResult.success();
//		Student student=new Student();
//		student.setId(id);
//		Wrapper<Student> ew = new EntityWrapper<>(student);
//		result.setData(iStudentService.selectList(ew));
//		return result;
//	}


	@ApiOperation(value = "停止学员周期", notes = "停止学员周期")
	@GetMapping("tingzhouqi")
	public OptResult tingZhouQi(String sid) {
		OptResult result = null;
		if (iDataStudentService.tingZhouQi(sid)){
			result= OptResult.success();
			result.setMsg("停止学员周期成功！");
		}else {
			throw new BusinessException(ErrCode.FAIL,"停止学员周期失败！");
		}
		return result;
	}


	@ApiOperation(value = "减学员天数", notes = "减学员天数")
	@GetMapping("jiantianshu")
	public OptResult jianTianShu() {
		OptResult result = null;
		if (iDataStudentService.jianTianShu()){
			result= OptResult.success();
			result.setMsg("减学员天数成功！");
		}else {
			throw new BusinessException(ErrCode.FAIL,"减学员天数失败！");
		}
		return result;
	}

	@ApiOperation(value = "获取今日在营人数", notes = "获取今日在营人数",response = StudentManagerHeadVo.class)
	@GetMapping("zongzaiying")
	public OptResult zongZaiYing() {
		OptResult result =OptResult.success();
		StudentManagerHeadVo studentManagerHeadVo = new StudentManagerHeadVo();
		studentManagerHeadVo.setZongzaiying(iDataStudentService.zongZaiYing());
		studentManagerHeadVo.setJinchuying(iDataStudentService.jinChuYing());
		studentManagerHeadVo.setJinruying(iDataStudentService.jinRuYing());
		result.setData(studentManagerHeadVo);
		return result;
	}



	@ApiOperation(value = "获取教练的下拉框", notes = "获取教练的下拉框",response = DataCoach.class)
	@GetMapping("coachdropdown")
	public OptResult coachDropDown() {
		OptResult result =OptResult.success();
		result.setData(iDataStudentService.coachDropDown());
		return result;
	}


	@ApiOperation(value = "获取房间的下拉框", notes = "获取房间的下拉框",response = DataRoom.class)
	@GetMapping("roomdropdown")
	public OptResult roomDropDown() {
		OptResult result =OptResult.success();
		result.setData(iDataStudentService.roomDropDown());
		return result;
	}

	@ApiOperation(value = "修改学员信息", notes = "修改学员信息")
	@PostMapping("updateStudent")
	public OptResult updateStudent(@RequestBody Student student) {
		OptResult result = null;
		if (iDataStudentService.updateStudent(student)){
			result= OptResult.success();
			result.setMsg("修改成功!");
		}else {
			result= OptResult.fail();
			result.setMsg("修改失败，请稍后再试!");
		}
		return result;
	}

}
