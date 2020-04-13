package com.pigs.springbootpigscrm.entity.vo;

import com.pigs.springbootpigscrm.entity.CommonalityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/24 10:57
 * @effect :
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class QueryEmployeeListVo extends CommonalityEntity {

    /**
     * 用户名
     */
    private String name;

    /**
     * id
     */
    private Integer id;

    /**
     * 角色
     */
    private String role;
    /**
     * 性别
     */
    private String sex;

    /**
     * 员工头像
     */
    private String image;

    /**
     * 名称
     */
    private String departmentName;


    /**
     * 手机号码
     */
    private String phone;

    @Override
    public String toString() {
        return "QueryEmployeeListVo{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", createTime='" + super.getCreateTime() + '\'' +
                ", updateTime='" + super.getUpdateTime() + '\'' +
                ", state='" + super.getState() + '\'' +
                '}';
    }
}
