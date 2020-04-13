package com.pigs.springbootpigscrm.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/14 0:04
 */
@Controller
public class HomeController {

    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    /**
     * 进入 main 页面
     *
     * @return
     */
    @GetMapping("/page/main.html")
    public String main() {

        return "page/main";
    }


    /**
     * 进入当前登录员工信息页面
     *
     * @return
     */
    @GetMapping("page/user/userInfo.html")
    public String userInfo() {

        return "page/user/userInfo";
    }

    /**
     * 员工管理
     * page/admin/adminList.html
     */
    @GetMapping("/page/employee/employeeList.html")
    public String employeeList() {
        return "page/employee/employeeList";
    }


    /**
     * 员工添加
     */
    @GetMapping("/page/employee/employeeSave.html")
    public String employeeSave() {
        return "page/employee/employeeSave";
    }

    /**
     * 员工修改
     */
    @GetMapping("/page/employee/employeeUpdate.html")
    public String employeeUpdate() {
        return "/page/employee/employeeUpdate";
    }

    /**
     * 部门信息
     */
    @GetMapping("/page/employee/department/departmentList.html")
    public String departmentList() {
        return "/page/employee/department/departmentList";
    }

    /**
     * 部门修改
     */
    @GetMapping("/page/employee/department/departmentUpdate.html")
    public String departmentUpdate() {
        return "/page/employee/department/departmentUpdate";
    }


    /**
     * 部门添加
     */
    @GetMapping("/page/employee/department/departmentSave.html")
    public String departmentSave() {
        return "page/employee/department/departmentSave";
    }


    /**
     * 角色信息
     */
    @GetMapping("/page/employee/role/roleList.html")
    public String roleList() {
        return "/page/employee/role/roleList";
    }

    /**
     * 角色修改
     */
    @GetMapping("/page/employee/role/roleUpdate.html")
    public String roleUpdate() {
        return "/page/employee/role/roleUpdate";
    }


    /**
     * 角色添加
     */
    @GetMapping("/page/employee/role/roleSave.html")
    public String roleSave() {
        return "page/employee/role/roleSave";
    }



    /**
     * 权限 信息
     */
    @GetMapping("page/employee/permission/permissionList.html")
    public String permissionList() {
        return "/page/employee/permission/permissionList";
    }

    /**
     * 权限 修改
     */
    @GetMapping("/page/employee/permission/permissionUpdate.html")
    public String permissionUpdate() {
        return "/page/employee/permission/permissionUpdate";
    }


    /**
     * 权限 添加
     */
    @GetMapping("/page/employee/permission/permissionSave.html")
    public String permissionSave() {
        return "page/employee/permission/permissionSave";
    }



    /**
     * 系统基本信息
     * /page/links/linksList.html
     */
    @GetMapping("page/systemParameter/systemParameter.html")
    public String systemParameter() {
        return "page/systemParameter/systemParameter";
    }

    /**
     * 403 页面
     *
     */
    @GetMapping("page/403.html")
    public String no404() {
        return "page/403";
    }

    /**
     * 登录
     * page/login/login.html
     */
    @GetMapping("page/login/login.html")
    public String login() {
        return "page/login/login";
    }


    /**
     * 今日收入
     * page/user/allUsers.html
     */
    @GetMapping("page/user/allUsers.html")
    public String todayIncome() {
        return "page/user/allUsers";
    }


    /**
     * 新增留言
     * page/message/message.html
     */
    @GetMapping("page/message/message.html")
    public String message() {
        return "page/message/message";
    }


    /**
     * 回复留言留言
     * page/message/messageReply.html
     */
    @GetMapping("page/message/messageReply.html")
    public String messageReply() {
        return "page/message/messageReply";
    }

    /**
     * 回复留言留言
     * page/news/newsAdd.html
     */
    @GetMapping("page/news/newsAdd.html")
    public String newsAdd() {
        return "page/news/newsAdd";
    }

    /**
     * 管理员查看个人信息
     */
    @GetMapping("page/admin/adminInfo.html")
    public String adminInfo() {
        return "page/admin/adminInfo";
    }

    /**
     * 管理员修改登录密码
     */
    @GetMapping("page/admin/changePwd.html")
    public String changePwd() {
        return "page/admin/changePwd";
    }
}
