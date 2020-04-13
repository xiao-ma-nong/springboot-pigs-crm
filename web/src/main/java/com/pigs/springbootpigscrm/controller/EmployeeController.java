package com.pigs.springbootpigscrm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.*;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import com.pigs.springbootpigscrm.mapper.EmployeeMapper;
import com.pigs.springbootpigscrm.service.*;
import com.pigs.springbootpigscrm.util.ResultPagingUtil;
import com.pigs.springbootpigscrm.util.ResultUtil;
import com.pigs.springbootpigscrm.util.TimeUtitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 * <p>
 * 员工控制器
 */
@RestController
@RequestMapping("/employee/")
@Api(value = "/employee", description = "员工前端控制器")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private IEmployeeRoleRefService employeeRoleRefService;

    @Autowired
    private EmployeeRoleRef employeeRoleRef;


    /**
     * 员工登录
     *
     * @param employeeName
     * @param employeePwd
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "员工登录")
    public ResultFormat login(String employeeName, String employeePwd) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(employeeName, employeePwd);
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                logger.info("登录成功");
                return ResultUtil.success();
            }
        } catch (UnknownAccountException e) {
            logger.info("账号不存在：");
            return ResultUtil.error(404, "账号不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.info("密码错误：");
            return ResultUtil.error(100, "密码输入错误");
        } catch (Exception e) {
            logger.info("发生错误={}", e.getMessage());
        }

        return ResultUtil.error(500, "稍后重试！");
    }

    /**
     * 员工 查询信息
     * 从shiro中获取
     * @return
     */
    @PostMapping("info")
    @ApiOperation(value = "查询员工信息")
    public ResultFormat info() {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        logger.info("employee ={}", employee);
        if (employee != null) {
            return ResultUtil.success(employee);
        }

        return ResultUtil.error(500, "稍后重试！");
    }


    /**
     * 员工权限 菜单
     *
     * @param session
     * @return "title": "员工列表",
     * "icon": "&#xe630;",
     * "href": "",
     * "spread": false,
     * "children": [
     * {
     * "title": "404页面",
     * "icon": "&#xe61c;",
     * "href": "page/404.html",
     * "spread": false
     * },
     * {
     * "title": "登录",
     * "icon": "&#xe609;",
     * "href": "page/login/login.html",
     * "spread": false,
     * "target": "_blank"
     * }
     */
    @GetMapping("navs")
    public Map<String, Object> navs(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        String employeeName = (String) session.getAttribute("employeeName");
        List<NameRolePermissionVo> nameRolePermissionVos = employeeService.queryNavs(employeeName);
        if (nameRolePermissionVos != null) {
            map2.put("title", "员工菜单");
            map2.put("icon", "&#xe630;");
            map2.put("href", "");
            map2.put("spread", "false");
            List<String> navUrl = nameRolePermissionVos.stream().map(NameRolePermissionVo::getUrl).collect(Collectors.toList());
            List<String> navName = nameRolePermissionVos.stream().map(NameRolePermissionVo::getName).collect(Collectors.toList());
            List<String> navIcon = nameRolePermissionVos.stream().map(NameRolePermissionVo::getIcon).collect(Collectors.toList());

            map.put("href", navUrl);
            map.put("spread", "false");
            map.put("title", navName);
            map.put("icon", navIcon);
            map2.put("children", map);

            logger.info("map2={}", map2);
            return map2;
        }

        return map;
    }

    /**
     * 查询 全部员工
     */
    @GetMapping("queryEmployeeList")
    @ApiOperation(value = "查询员工信息列表")
    public ResultFormatPaging queryEmployeeList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (page != null && limit != null) {

            Page<QueryEmployeeListVo> iPage = new Page<QueryEmployeeListVo>(page, limit);

            IPage<QueryEmployeeListVo> mapIPage = employeeService.queryEmployeeList(iPage, map);

            List<QueryEmployeeListVo> queryEmployeeListVos = iPage.getRecords();
            for (QueryEmployeeListVo queryEmployeeListVo : queryEmployeeListVos) {
                logger.info("queryEmployeeListVo={}", queryEmployeeListVo);
            }

            return ResultPagingUtil.pagingSuccess(0, (int) iPage.getTotal(), queryEmployeeListVos);
        }
        return ResultPagingUtil.pagingSuccess(0, 0, "分页失败");

    }

    /**
     * 修改员工状态
     */
    @PutMapping("updateEmployeeState")
    @ApiOperation(value = "修改员工状态")
    public ResultFormat updateEmployeeState(Integer employeeState, Integer employeeId) {
        Employee employee = new Employee();
        logger.info("employeeState,employeeId={}", employeeState + " -- " + employeeId);
        if (employeeState != null && employeeId != null) {
            employee.setId(employeeId);
            employee.setState(employeeState);
            int update = employeeMapper.updateById(employee);
            logger.info("update={}", update);
            return ResultUtil.success(update);
        }
        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 通过id查询 员工信息
     */
    @PutMapping("queryEmployeeInfo")
    @ApiOperation(value = "通过id修改员工信息")
    public ResultFormat updateEmployeeList(Integer employeeId) {
        logger.info("employeeId={}", employeeId);

        Map<String, Object> map = new HashMap<String, Object>();

        if (employeeId != null) {
            Page<QueryEmployeeListVo> iPage = new Page<QueryEmployeeListVo>(1, 5);
            System.out.println(iPage);
            map.put("employeeId", 1);
            map.put("employeeId", employeeId);
            IPage<QueryEmployeeListVo> mapIPage = employeeService.queryEmployeeList(iPage, map);
            List<QueryEmployeeListVo> queryEmployeeListVoList = iPage.getRecords();

            logger.info("queryEmployeeListVoIPage ={}", queryEmployeeListVoList);
            if (queryEmployeeListVoList != null) {
                return ResultUtil.success(queryEmployeeListVoList);
            }

        }

        return ResultUtil.error(100, "修改失败");
    }


    /**
     * 修改员工头像
     *
     * @param
     * @return
     */
    @PutMapping("/updateEmployeeImage")
    @ApiOperation(value = "修改员工头像")
    public ResultFormat updateAdminInfoImage(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        long startTime = System.currentTimeMillis();
        Employee employee = new Employee();
        AbstractWrapper wrapper = new QueryWrapper();
        /**
         *  图片前后缀
         */
        String prefix = "";
        String dateStr = "";

        /**
         * 保存上传
         */
        String strTime = Long.toString(startTime);
        OutputStream out = null;
        InputStream fileInput = null;
        String filepath = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                System.out.println("prefix--" + prefix);
                System.out.println("dateStr--" + dateStr);
                System.out.println("originalName--" + originalName);

                System.out.println("文件原名称--" + file.getOriginalFilename());
                System.out.println("文件大小--" + file.getSize());
                System.out.println("文件类型--" + file.getContentType());

                filepath = request.getServletContext().getRealPath("/") + "uploadImage/";

                filepath = filepath.replace("\\", "/");

                Long size = file.getSize();
                String strSize = Long.toString(size);

                System.out.println("filepath-- " + filepath);
                File files = new File(filepath + strTime + "." + prefix);

                //打印查看上传路径
                System.out.println("files-- " + files);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);

            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }

        String employeeName = (String) session.getAttribute("employeeName");
        logger.info("employeeName={}", employeeName);

        if (employeeName != null) {
            wrapper.eq("name", employeeName);
            employee.setImage("uploadImage/" + strTime + "." + prefix);
            int flag = employeeMapper.update(employee, wrapper);
            logger.info("flag = {}", flag);
            if (flag > 0) {

                return ResultUtil.success(flag);
            }
        }

        return ResultUtil.error(104, "更新失败..");
    }

    /**
     * 添加员工
     *
     * @param
     * @return
     */
    @PostMapping("/saveEmployeeList")
    @ApiOperation(value = "添加员工")
    public ResultFormat saveEmployeetList(@RequestBody HashMap<String, String> hashMap) {
        Employee employee = new Employee();
        AbstractWrapper wrapper = new QueryWrapper();

        logger.info("hashMap={}", hashMap);
        wrapper.eq("name", hashMap.get("employeeName"));
        List list = employeeService.list(wrapper);
        logger.info("list={}", list);
        if (list.size() > 0) {
            return ResultUtil.error(100, "用户名已存在");
        }

        employee.setName(hashMap.get("employeeName"));
        Integer sex = Integer.parseInt(hashMap.get("sex"));
        employee.setSex(sex);
        employee.setImage("images/userface4.jpg");
        employee.setPhone(hashMap.get("phone"));
        employee.setUpdateTime(TimeUtitl.dateTime());
        employee.setSalt(com.pigs.springbootpigscrm.util.SecurityUtils.randomSalt());
        String password = com.pigs.springbootpigscrm.util.SecurityUtils.encryptPassword(hashMap.get("employeePassword"), employee.getSalt());
        employee.setPassword(password);
        employee.setState(0);
        boolean saveEmployee = employeeService.save(employee);

        employeeRoleRef.setEmployeeId(employee.getId());
        employeeRoleRef.setRoleId(Integer.valueOf(hashMap.get("roleId")));
        employeeRoleRef.setUpdateTime(TimeUtitl.dateTime());
        employeeRoleRef.setState(0);

        boolean saveEmployeeRoleRef = employeeRoleRefService.save(employeeRoleRef);

        logger.info("saveEmployee,saveDepartmentRoleRef,saveEmployeeRoleRef", saveEmployee + " -- " + " -- " + saveEmployeeRoleRef);

        if (saveEmployeeRoleRef && saveEmployee) {
            return ResultUtil.success();
        }

        return ResultUtil.error(100, "添加失败..");
    }


    /**
     * 修改员工信息
     *
     * @param
     * @return
     */
    @PostMapping("/updateEmployeeList")
    @ApiOperation(value = "修改员工信息")
    public ResultFormat updateEmployeetList(@RequestBody HashMap<String, String> hashMap) {
        Employee employee = new Employee();

        AbstractWrapper wrapper = new QueryWrapper<Employee>();
        AbstractWrapper employeeRoleRefQueryWrapper = new QueryWrapper<EmployeeRoleRef>();

        logger.info("hashMap={}", hashMap);

        Integer sex = Integer.parseInt(hashMap.get("sex"));

        if (hashMap.get("employeeName") != null) {
            wrapper.eq("name", hashMap.get("employeeName"));

            Employee employeeList = employeeMapper.selectOne(wrapper);
            if (employeeList == null) {
                return ResultUtil.error(100, "用户名不存在！修改失败..");
            }

            if (sex != null) {
                employee.setSex(sex);
            }

            if (hashMap.get("phone") != null) {
                employee.setPhone(hashMap.get("phone"));
            }

            employee.setUpdateTime(TimeUtitl.dateTime());

            String password = hashMap.get("employeePassword");

            if (password != null) {
                employee.setPassword(com.pigs.springbootpigscrm.util.SecurityUtils.encryptPassword(password, employeeList.getSalt()));
            }

            employee.setId(employeeList.getId());


            employeeRoleRef.setRoleId(Integer.valueOf(hashMap.get("roleId")));
            employeeRoleRef.setUpdateTime(TimeUtitl.dateTime());

            employeeRoleRefQueryWrapper.eq("employee_id", employee.getId());

            boolean updateEmployeeRoleRef = employeeRoleRefService.update(employeeRoleRef, employeeRoleRefQueryWrapper);
            boolean updateEmployee = employeeService.updateById(employee);

            logger.info("updateEmployee,saveEmployeeRoleRef", updateEmployee + " -- " + updateEmployeeRoleRef);

            if (updateEmployee && updateEmployeeRoleRef) {
                return ResultUtil.success();
            }
        }
        return ResultUtil.error(100, "修改失败..");
    }

}


