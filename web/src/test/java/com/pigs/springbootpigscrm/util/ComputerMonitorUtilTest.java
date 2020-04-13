package com.pigs.springbootpigscrm.util;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/4/13 0:03
 * @effect :
 */
@SpringBootTest
public class ComputerMonitorUtilTest {

    /**
     * 获取cpu的使用率
     */
    @Test
    public void getCpuUsage() {
        double cpuUsage = ComputerMonitorUtil.getCpuUsage();
        System.out.println(cpuUsage);
    }


    /**
     * 获取内存的使用率
     */
    @Test
    public void getMemUsage() {
        double memUsage = ComputerMonitorUtil.getMemUsage();
        System.out.println(memUsage);
    }

    /**
     * 获取硬盘的使用率
     */
    @Test
    public void getDiskUsage() {
        try {
            double diskUsage = ComputerMonitorUtil.getDiskUsage();
            System.out.println(diskUsage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}