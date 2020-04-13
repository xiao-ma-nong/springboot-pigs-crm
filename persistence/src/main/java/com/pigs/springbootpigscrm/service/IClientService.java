package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  客户 服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-03
 */
public interface IClientService extends IService<Client> {

    /**
     * 查询 全部 客户 并分页 携带条件查询
     * @param map
     * @param page
     * @return
     */
    IPage<Client> queryClientList(Page page,Map<String, Object> map);



}
