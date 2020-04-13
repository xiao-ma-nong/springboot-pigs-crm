package com.pigs.springbootpigscrm.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/25 11:41
 * @effect :
 */
@SpringBootTest
public class SecurityUtilsTest {

    @Test
    public void encryptPassword() {
        System.out.println(SecurityUtils.encryptPassword("1003","765dc50a48b51a7a7ab50bc1e4cedaa7c62501b01707afef"));
    }

    @Test
    public void randomSalt() {
        System.out.println(SecurityUtils.randomSalt());
    }
}