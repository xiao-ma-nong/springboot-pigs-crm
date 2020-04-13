package com.pigs.springbootpigscrm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author PIGS
 * @since 2020-04-03
 *
 * 员工客户关系模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("员工客户关系模型")
@TableName("employee_client_ref")
public class EmployeeClientRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工id",required = true)
    private Integer employeeId;

    @ApiModelProperty(value = "客户id",required = true)
    private Integer clientId;

    @ApiModelProperty(value = "创建时间",required = true)
    private String createTime;

    @ApiModelProperty(value = "修改时间",required = true)
    private String updateTime;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;


}
