package com.pigs.springbootpigscrm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Y.yang
 * @since 2020-03-23
 * 权限 模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@TableName("permission")
@ApiModel("权限模型")
public class Permission extends CommonalityEntity {

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限名称",required = true)
    private String permission;

    /**
     * 名称
     */
    @ApiModelProperty(value = "权限描述",required = true)
    private String name;

    /**
     * url
     */
    @ApiModelProperty(value = "权限url",required = true)
    private String url;

    /**
     * 角色 模型
     */
    @ApiModelProperty(value = "角色模型",required = true)
    @TableField(exist = false)
    private Role role;


    @Override
    public String toString() {
        return "Permission{" +
                "permission='" + permission +
                ", name='" + name +
                ", url='" + url +
                ",createTime=" + getCreateTime() +
                ",updateTime=" + getUpdateTime() +
                ",state=" + getState() +
                ", role=" + role +
                '}';
    }
}
