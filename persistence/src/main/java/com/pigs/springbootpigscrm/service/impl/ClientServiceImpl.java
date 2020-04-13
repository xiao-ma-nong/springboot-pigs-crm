package com.pigs.springbootpigscrm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Client;
import com.pigs.springbootpigscrm.mapper.ClientMapper;
import com.pigs.springbootpigscrm.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 客户 服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-04-03
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<Client> queryClientList(Page page, Map<String, Object> map) {

        IPage<Client> clientIPage = clientMapper.queryClientList(page, map);
        if (clientIPage != null) {
            return clientIPage;
        }

        return null;
    }
}
