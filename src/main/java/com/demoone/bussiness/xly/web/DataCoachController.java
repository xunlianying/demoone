package com.demoone.bussiness.xly.web;


import com.demoone.bussiness.xly.entity.DataCoach;
import com.demoone.bussiness.xly.service.IDataCoachService;
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
@RequestMapping("/coach")
@Api(value = "CoachController", description = "教练信息")
public class DataCoachController {



    @Autowired
    private IDataCoachService iDataCoachService;

    @ApiOperation(value = "批量增加教练信息", notes = "批量增加教练信息")
    @PostMapping("addCoach")
    @ResponseBody
    public OptResult addCoach(@RequestBody DataCoach dataCoach) {
        OptResult result = null;
        if (iDataCoachService.addCoach(dataCoach)){
            result= OptResult.success();
            result.setMsg("添加成功！");
        }else {
            throw new SellException(ErrCode.FAIL,"添加失败！");
        }
        return result;
    }
    @ApiOperation(value = "删除房间信息", notes = "删除房间信息")
    @GetMapping("delete")
    @ResponseBody
    public OptResult queryCompInfoByCondition(int id) {
        OptResult result = null;
        if (iDataCoachService.deleteById(id)){
            result= OptResult.success();
            result.setMsg("删除成功！");
        }else {
            throw new SellException(ErrCode.FAIL,"删除教练失败！");
        }
        return result;
    }


}
