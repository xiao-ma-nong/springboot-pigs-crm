package com.pigs.springbootpigscrm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @author PIGS
 * @since 2020-03-24
 *  员工 角色关系模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("员工角色关系模型")
public class EmployeeRoleRef extends CommonalityEntity{

    @ApiModelProperty(value = "员工id",required = true)
    private Integer employeeId;

    @ApiModelProperty(value = "角色id",required = true)
    private Integer roleId;

    @Override
    public String toString() {
        return "EmployeeRoleRef{" +
                "employeeId=" + employeeId +
                ", roleId=" + roleId +
                ", createTime=" + super.getCreateTime() +
                ", updateTime=" + super.getUpdateTime() +
                ", state=" + super.getState() +
                '}';
    }
}
