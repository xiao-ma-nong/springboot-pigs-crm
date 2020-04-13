package com.pigs.springbootpigscrm.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/19 19:09
 * @effect :
 * 权限 角色 用户名vo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class NameRolePermissionVo {

    /** 用户名
     *
     */
    private String name;

    /**
     * 角色
     */
    private String role;

    /**
     * 权限
     */
    private String permission;

    /**
     * 盐
     */
    private String salt;


    /**
     * url
     */
    private String url;

    /**
     * 员工头像
     */
    private String image;

    /**
     * 图标
     */
    private String icon;
}
