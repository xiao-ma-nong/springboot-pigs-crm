package com.pigs.springbootpigscrm.shiro;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.pigs.springbootpigscrm.entity.Employee;
import com.pigs.springbootpigscrm.entity.Permission;
import com.pigs.springbootpigscrm.entity.Role;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.mapper.EmployeeMapper;
import com.pigs.springbootpigscrm.service.IEmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/18 22:25
 * @effect
 */
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(MyRealm.class);


    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private IEmployeeService employeeService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        AbstractWrapper roleWrapper = new QueryWrapper<Role>();
        AbstractWrapper permissionWrapper = new QueryWrapper<Permission>();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();


        logger.info("userName={}", employee.getName());

        if (employee.getName() != null) {

            /**
             * 循环 赋值 角色 权限url
             *
             */
            for (Role role : employee.getRole()) {
                authorizationInfo.addRole(role.getRole());
                for (Permission permission : role.getPermission()) {
                    authorizationInfo.addStringPermission(permission.getPermission());
                }

            }
        }


        return authorizationInfo;
    }


    /**
     * 认证信息
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AbstractWrapper wrapper = new QueryWrapper<Employee>();
        String userName = (String) authenticationToken.getPrincipal();

        wrapper.eq("name", userName);
        Employee employee = employeeMapper.selectOne(wrapper);

        if (employee == null) {
            logger.info("用户不存在");
            return null;
        }

        /**
         * 返回用户名 密码 加密后的加盐 realm 名称
         */
        return new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes(employee.getSalt()), getName());
    }
}
