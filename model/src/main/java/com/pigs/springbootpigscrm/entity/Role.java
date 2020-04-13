package com.pigs.springbootpigscrm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author Y.yang
 * @since 2020-03-23
 * <p>
 * 角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ApiModel("角色模型")
public class Role extends CommonalityEntity {

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色名称", required = true)
    private String role;

    /**
     * 描述
     */
    @ApiModelProperty(value = "角色描述", required = true)
    private String description;

    /**
     * 权限 模型
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "权限模型", required = true)
    private List<Permission> permission;

    /**
     * 权限 模型
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "部门模型", required = true)
    private List<Department> departments;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + super.getId() + '\'' +
                ",role='" + role + '\'' +
                ", description='" + description + '\'' +
                ",createTime=" + super.getCreateTime() + '\'' +
                ",state=" + super.getState() + '\'' +
                ",updateTime=" + super.getUpdateTime() + '\'' +
                '}';
    }
}
