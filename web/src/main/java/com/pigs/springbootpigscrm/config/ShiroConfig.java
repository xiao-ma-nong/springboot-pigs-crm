package com.pigs.springbootpigscrm.config;


import com.pigs.springbootpigscrm.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/18 22:47
 * @effect shiro 配置类
 */
@Configuration
public class ShiroConfig {


    /**
     * 过滤器工厂
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("启动 ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 拦截器 map 集合.
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        /**
         * 配置不会被拦截的链接 顺序判断
         * anon 匿名 访问
         */
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/ico/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/json/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/employee/navs", "anon");

        filterChainDefinitionMap.put("/page/login/login.html", "anon");
        filterChainDefinitionMap.put("/employee/login", "anon");

        /**
         * 配置退出 过滤器,其中的具体的退出代码Shiro已经实现了
         */
        filterChainDefinitionMap.put("/logout", "logout");


        /**
         * authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
         * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
         */

        /**
         * 员工过滤链
         */
        filterChainDefinitionMap.put("/employee/saveEmployeeList", "perms[employee:saveEmployeeList]");
        filterChainDefinitionMap.put("/employee/queryEmployeeList", "perms[employee:queryEmployeeList]");
        filterChainDefinitionMap.put("/employee/updateEmployeeImage", "perms[employee:updateEmployeeImage]");
        filterChainDefinitionMap.put("/employee/queryEmployeeInfo", "perms[employee:queryEmployeeInfo]");
        filterChainDefinitionMap.put("/employee/updateEmployeeState", "perms[employee:updateEmployeeState]");
        filterChainDefinitionMap.put("/employee/updateEmployeeList", "perms[employee:updateEmployeeList]");


        /**
         * 客户过率链
         */
        filterChainDefinitionMap.put("/client/updateClientState", "perms[client:updateClientState]");
        filterChainDefinitionMap.put("/client/clientSave", "perms[client:clientSave]");
        filterChainDefinitionMap.put("/client/clientUpdate", "perms[client:clientUpdate]");
        filterChainDefinitionMap.put("/client/queryClientList", "perms[client:queryClientList]");


        /**
         * 部门过率链
         */
        filterChainDefinitionMap.put("/department/queryDepartmentList", "perms[department:queryDepartmentList]");
        filterChainDefinitionMap.put("/department/queryDepartmentName", "perms[department:queryDepartmentName]");
        filterChainDefinitionMap.put("/department/updateDepartmentState", "perms[department:updateDepartmentState]");
        filterChainDefinitionMap.put("/department/updateDepartment", "perms[department:updateDepartment]");
        filterChainDefinitionMap.put("/department/saveDepartment", "perms[department:saveDepartment]");


        /**
         * 权限过滤链
         */
        filterChainDefinitionMap.put("/permission/queryPermissionList", "perms[permission:queryPermissionList]");
        filterChainDefinitionMap.put("/permission/updatePermissionState", "perms[permission:updatePermissionState]");
        filterChainDefinitionMap.put("/permission/savePermission", "perms[permission:savePermission]");
        filterChainDefinitionMap.put("/permission/updatePermission", "perms[permission:updatePermission]");
        filterChainDefinitionMap.put("/permission/updatePermissionState", "perms[permission:updatePermissionState]");


        /**
         * 角色过滤链
         */
        filterChainDefinitionMap.put("/role/queryRoleList", "perms[role:queryRoleList]");
        filterChainDefinitionMap.put("/role/saveRole", "perms[role:saveRole]");
        filterChainDefinitionMap.put("/role/updateRoleState", "perms[role:updateRoleState]");
        filterChainDefinitionMap.put("/role/updateRole", "perms[role:updateRole]");
        filterChainDefinitionMap.put("/role/queryRole", "perms[role:queryRole]");

        filterChainDefinitionMap.put("/swagger-ui.html#", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");

        filterChainDefinitionMap.put("/**", "authc");

        /**
         * 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
         */
        shiroFilterFactoryBean.setLoginUrl("/page/login/login.html");

        /**
         *
         * 登录成功后要跳转的链接
         * shiroFilterFactoryBean.setSuccessUrl("/index.html");
         */

        /**
         * 未授权界面
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/403.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 身份认证realm; (账号密码校验；权限等)
     *
     * @return MyShiroRealm
     */
    @Bean
    public MyRealm myShiroRealm() {
        MyRealm myShiroRealm = new MyRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    /**
     * 凭证匹配器
     * （密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 修改下doGetAuthenticationInfo中的代码;
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        /**
         * 散列算法:使用MD5算法
         * 加密一次
         */
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    /**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 顾问自动代理创建者
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 授权属性来源顾问
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


}
