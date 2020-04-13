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
 * 权限 角色 关系模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("权限角色关系模型")
public class PermissionRoleRef extends CommonalityEntity{

    @ApiModelProperty(value = "角色id",required = true)
    private Integer roleId;

    @ApiModelProperty(value = "权限id",required = true)
    private Integer permissionId;

    @Override
    public String toString() {
        return "PermissionRoleRef{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", createTime=" + super.getCreateTime() +
                ", updateTime=" + super.getUpdateTime() +
                ", state=" + super.getState() +
                '}';
    }
}
