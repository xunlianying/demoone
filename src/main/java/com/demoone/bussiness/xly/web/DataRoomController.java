package com.demoone.bussiness.xly.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demoone.bussiness.xly.entity.DataRoom;
import com.demoone.bussiness.xly.service.IDataRoomService;
import com.demoone.support.exception.BusinessException;
import com.demoone.bussiness.xly.vo.QueryRoomInfoVo;
import com.demoone.support.sys.ErrCode;
import com.demoone.support.sys.OptResult;
import com.demoone.utils.string.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/api/xly/room")
@Api(value = "RoomController", description = "房间信息")
public class DataRoomController {



    @Autowired
    private IDataRoomService iDataRoomService;

    @ApiOperation(value = "增加房间信息", notes = "增加房间信息")
    @PostMapping("addRoom")
    public OptResult addRoom(@RequestBody DataRoom dataRoom) {
        OptResult result = null;
        if (iDataRoomService.addRoom(dataRoom)){
            result= OptResult.success();
            result.setMsg("添加成功！");
        }else {
            result= OptResult.fail();
            result.setMsg("添加失败，请稍后再试!");
        }
        return result;
    }
    @ApiOperation(value = "删除房间信息", notes = "删除房间信息")
    @GetMapping("delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rid", value = "房间id", paramType = "query"),
	})
    public OptResult delete(String rid) {
        OptResult result = null;
        if (iDataRoomService.deleteRoom(rid)){
            result= OptResult.success();
            result.setMsg("删除成功！");
        }else {
            throw new BusinessException(ErrCode.FAIL,"删除房间失败！");
        }
        return result;
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query", required = false,defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", required = false,defaultValue = "10")
    })
    @GetMapping("list")
    public OptResult list( Integer page, Integer size,String roomNo) {
        OptResult result = null;
        if (page ==null){
            page=1;
        }
        if (size ==null){
            size=10;
        }
        EntityWrapper<DataRoom> ew = new EntityWrapper();
        if (StringUtils.isNotBlank(roomNo)){
            ew.eq("room_id",roomNo);
        }
        Page<DataRoom> pageRoom = new Page<>(page,size);

        result.setData(iDataRoomService.selectPage(pageRoom,ew));
        return result;
    }


    @ApiOperation(value = "房间信息查询", notes = "房间信息查询")
    @PostMapping("queryRoom")
    public OptResult queryRoomInfo(@RequestBody QueryRoomInfoVo queryRoomInfoVo) {
        OptResult result = OptResult.success();
        result.setData(iDataRoomService.queryRoomInfo(queryRoomInfoVo));
        return result;
    }


    @ApiOperation(value = "修改房间信息", notes = "修改房间信息")
    @PostMapping("updateRoom")
    public OptResult updateRoom(@RequestBody DataRoom dataRoom) {
        OptResult result = null;
        if (iDataRoomService.updateRoom(dataRoom)){
            result= OptResult.success();
            result.setMsg("修改成功!");
        }else {
            result= OptResult.fail();
            result.setMsg("修改失败，请稍后再试!");
        }
        return result;
    }
}
