package com.demoone.bussiness.xly.vo;

import com.demoone.bussiness.xly.entity.DataStudent;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@ApiModel("学员信息返回实体")
public class DataStudentInfoVo extends DataStudent {
}
