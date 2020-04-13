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
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Y.yang
 * @since 2020-03-23
 * <p>
 * 员工 模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("员工模型")
public class Employee extends CommonalityEntity {

    /**
     * 名称
     */
    @ApiModelProperty(value = "员工名称", required = true)
    private String name;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "员工登录密码", required = true)
    private String password;


    /**
     * 性别 0女 1男
     */
    @ApiModelProperty(value = "员工性别", required = true)
    private Integer sex;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "员工手机号码", required = true)
    private String phone;

    /**
     * 盐
     */
    @ApiModelProperty(value = "盐", required = true)
    private String salt;

    /**
     * 员工头像
     */
    @ApiModelProperty(value = "员工头像", required = true)
    private String image;


    /**
     * 角色 模型
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色模型", required = true)
    private List<Role> role;



}
