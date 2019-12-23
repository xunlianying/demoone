package com.demoone.bussiness.xly.vo;

import com.demoone.bussiness.xly.entity.DataRoom;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@ApiModel("房间信息返回实体")
public class RoomInfoVo extends DataRoom{
}
