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
 *
 * 部门 角色 关系模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("部门 角色 模型")
public class DepartmentRoleRef extends CommonalityEntity{

    @ApiModelProperty(value = "部门id",required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "角色id",required = true)
    private Integer roleId;

    @Override
    public String toString() {
        return "DepartmentRoleRef{" +
                "departmentId=" + departmentId +
                ", roleId=" + roleId +
                ", createTime=" + super.getCreateTime() +
                ", updateTime=" + super.getUpdateTime() +
                ", state=" + super.getState() +
                '}';
    }
}
