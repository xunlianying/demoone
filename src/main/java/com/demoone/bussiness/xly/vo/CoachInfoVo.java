package com.demoone.bussiness.xly.vo;

import com.demoone.bussiness.xly.entity.DataCoach;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@ApiModel("教练信息返回实体")
public class CoachInfoVo extends DataCoach {
}
