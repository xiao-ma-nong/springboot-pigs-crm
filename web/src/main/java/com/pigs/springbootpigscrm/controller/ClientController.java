package com.pigs.springbootpigscrm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.*;
import com.pigs.springbootpigscrm.mapper.EmployeeMapper;
import com.pigs.springbootpigscrm.service.IClientService;
import com.pigs.springbootpigscrm.service.IEmployeeClientRefService;
import com.pigs.springbootpigscrm.service.IEmployeeService;
import com.pigs.springbootpigscrm.util.ResultPagingUtil;
import com.pigs.springbootpigscrm.util.ResultUtil;
import com.pigs.springbootpigscrm.util.SecurityUtils;
import com.pigs.springbootpigscrm.util.TimeUtitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户 前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-04-03
 */
@RestController
@Api(value = "/client", description = "客户前端控制器")
@RequestMapping("/client/")
public class ClientController {

    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private IEmployeeClientRefService employeeClientRefService;


    /**
     * 查询客户列表并分页
     */
    @GetMapping("queryClientList")
    @ApiOperation(value = "查询客户列表并分页")
    public ResultFormatPaging queryClientList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, Map<String, Object> map) {

        if (page != null && limit != null) {

            Page<Client> iPage = new Page<Client>(page, limit);

            IPage<Client> mapIPage = clientService.queryClientList(iPage, map);

            List<Client> clientList = iPage.getRecords();

            for (Client client : clientList) {
                logger.info("client= {}", client);
            }

            return ResultPagingUtil.pagingSuccess(0, (int) iPage.getTotal(), clientList);
        }

        return ResultPagingUtil.pagingError(500, 0, "查询失败");
    }

    /**
     * 修改 客户 状态
     */
    @PutMapping("updateClientState")
    @ApiOperation(value = "修改客户状态")
    public ResultFormat updateDepartmentState(Integer clientState, Integer clientId) {
        Client client = new Client();
        logger.info("clientState,clientId={}", clientState + " -- " + clientId);

        if (clientState != null && clientId != null) {
            client.setId(clientId);
            client.setStatus(clientState);
            boolean update = clientService.updateById(client);
            logger.info("update={}", update);
            return ResultUtil.success(update);
        }

        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 添加客户
     */
    @PostMapping("clientSave")
    @ApiOperation(value = "添加客户")
    public ResultFormat clientSave(@RequestBody Client client, HttpSession session) {

        logger.info("client={}", client);
        String salt = SecurityUtils.randomSalt();
        client.setSalt(salt);
        String password = SecurityUtils.encryptPassword(client.getUserPassword(), salt);
        client.setUserPassword(password);
        client.setStatus(0);

        boolean clientSave = clientService.save(client);
        logger.info("clientSave={} ", clientSave);
        String employeeName = (String) session.getAttribute("employeeName");

        if (!employeeName.isEmpty()) {
            logger.info("employeeName", employeeName);
            AbstractWrapper wrapper = new QueryWrapper<Employee>();
            wrapper.eq("name", employeeName);
            Employee employee = employeeMapper.selectOne(wrapper);
            EmployeeClientRef employeeClientRef = new EmployeeClientRef();
            employeeClientRef.setClientId(client.getId());
            employeeClientRef.setEmployeeId(employee.getId());
            employeeClientRef.setUpdateTime(TimeUtitl.dateTime());
            employeeClientRef.setState(0);

            boolean employeeClientRefSave = employeeClientRefService.save(employeeClientRef);
            logger.info("employeeClientRefSave={} ", employeeClientRefSave);

            if (clientSave && employeeClientRefSave) {
                return ResultUtil.success();
            }

        }


        return ResultUtil.error(100, "添加失败");
    }

    /**
     * 修改客户信息
     */
    @PutMapping("clientUpdate")
    @ApiOperation(value = "修改客户信息")
    public ResultFormat clientUpdate(@RequestBody Client client) {

        logger.info("client={}", client);

        boolean clientUpdate = clientService.updateById(client);
        if (clientUpdate) {
            return ResultUtil.success(clientUpdate);
        }

        return ResultUtil.error(100, "修改失败");
    }


}
