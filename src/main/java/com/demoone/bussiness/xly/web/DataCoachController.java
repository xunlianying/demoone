package com.demoone.bussiness.xly.web;


import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.service.IDataCoachService;
import com.demoone.support.exception.BusinessException;
import com.demoone.bussiness.xly.vo.QueryCoachInfoVo;
import com.demoone.support.sys.ErrCode;
import com.demoone.support.sys.OptResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 华强
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/api/xly/coach")
@Api(value = "CoachController", description = "教练信息")
public class DataCoachController {



    @Autowired
    private IDataCoachService iDataCoachService;

    @ApiOperation(value = "增加教练信息", notes = "增加教练信息")
    @PostMapping("addCoach")
    public OptResult addCoach(@RequestBody DataCoach dataCoach) {
        OptResult result = null;
        if (iDataCoachService.addCoach(dataCoach)){
            result= OptResult.success();
            result.setMsg("添加成功！");
        }else {
            throw new BusinessException(ErrCode.FAIL,"添加失败！");
        }
        return result;
    }
    @ApiOperation(value = "删除教练信息", notes = "删除删除信息")
    @GetMapping("delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cid", value = "教练id", paramType = "query"),
	})
    public OptResult delete(List<String> cid) {
        OptResult result = null;
        if (iDataCoachService.deleteCoach(cid)){
            result= OptResult.success();
            result.setMsg("删除成功！");
        }else {
            throw new BusinessException(ErrCode.FAIL,"删除教练失败！");
        }
        return result;
    }
    @ApiOperation(value = "教练信息查询", notes = "教练信息查询")
    @PostMapping("queryCoach")
    public OptResult queryCoachInfo(@RequestBody QueryCoachInfoVo queryCoachInfoVo) {
        OptResult result = OptResult.success();
        result.setData(iDataCoachService.queryCoachInfo(queryCoachInfoVo));
        return result;
    }


    @ApiOperation(value = "修改教练信息", notes = "修改教练信息")
    @PostMapping("updateCoach")
    public OptResult updateCoach(@RequestBody DataCoach dataCoach) {
        OptResult result = null;
        if (iDataCoachService.updateCoach(dataCoach)){
            result= OptResult.success();
            result.setMsg("修改成功!");
        }else {
            result= OptResult.fail();
            result.setMsg("修改失败，请稍后再试!");
        }
        return result;
    }

}
