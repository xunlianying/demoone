package com.demoone.bussiness.xly.web;


import com.demoone.bussiness.xly.entity.Student;
import com.demoone.bussiness.xly.service.IDataStudentService;
import com.demoone.bussiness.xly.vo.QueryStudentInfoVo;
import com.demoone.bussiness.xly.vo.StudentDropDown;
import com.demoone.bussiness.xly.vo.StudentManagerHeadVo;
import com.demoone.support.exception.SellException;
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
	@PostMapping("addStudent")
	public OptResult queryStudentInfo(@RequestBody QueryStudentInfoVo queryStudentInfoVo) {
		OptResult result = null;
		result.setData(iDataStudentService.queryStudentInfo(queryStudentInfoVo));
		return result;
	}

	@ApiOperation(value = "删除学员信息", notes = "删除学员信息")
	@GetMapping("delete")
	public OptResult delete(int id) {
		OptResult result = null;
		if (iDataStudentService.deleteById(id)){
			result= OptResult.success();
			result.setMsg("删除成功！");
		}else {
			throw new SellException(ErrCode.FAIL,"删除学员失败！");
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
	public OptResult tingZhouQi(int id) {
		OptResult result = null;
		if (iDataStudentService.tingZhouQi(id)){
			result= OptResult.success();
			result.setMsg("停止学员周期成功！");
		}else {
			throw new SellException(ErrCode.FAIL,"停止学员周期失败！");
		}
		return result;
	}


	@ApiOperation(value = "减学员天数", notes = "减学员天数")
	@GetMapping("jiantianshu")
	public OptResult jianTianShu(int id) {
		OptResult result = null;
		if (iDataStudentService.jianTianShu(id)){
			result= OptResult.success();
			result.setMsg("减学员天数成功！");
		}else {
			throw new SellException(ErrCode.FAIL,"减学员天数失败！");
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



	@ApiOperation(value = "获取学员的下拉框", notes = "获取学员的下拉框",response = StudentDropDown.class)
	@GetMapping("studentdropdown")
	public OptResult StudentDropDown() {
		OptResult result =OptResult.success();
		StudentDropDown studentDropDown = new StudentDropDown();
		studentDropDown.setBasedropdown(iDataStudentService.baseDropDown());
		studentDropDown.setCoachdropdown(iDataStudentService.coachDropDown());
		studentDropDown.setRoomdropdown(iDataStudentService.roomDropDown());
		result.setData(studentDropDown);
		return result;
	}
}
