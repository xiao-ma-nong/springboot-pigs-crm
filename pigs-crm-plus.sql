/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : pigs-crm-plus

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-04-14 15:27:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(50) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_password` varchar(60) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `is_orders` tinyint(4) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', '我是客户', '0', '0', '2020-04-06 21:36:12', '', '765dc50a48b51a7a7ab50bc1e4cedaa7c62501b01707afef', '0', '18318121633', '我家');
INSERT INTO `client` VALUES ('8', 'a', '0', '0', '2020-04-06 21:48:20', '876ac3e5f1733a700a2033d0e5890abd', '9147dd29fb524a6574a8d61819da4dc0975d8b9757cdfa6d', '1', '15454354354', 'a');
INSERT INTO `client` VALUES ('9', '肥仔', '0', '0', '2020-04-10 16:39:09', '28c503b723c234fde98e406d5a3fd5a2', 'fa45716fb8b6a6b9166f86700773c1e5b254f30652a137e0', '1', '13487648464', '你家\n');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '研发部门', '2020-04-10 16:38:20', '2020-03-17 15:34:17', '0');
INSERT INTO `department` VALUES ('2', '测试部门', '2020-03-26 23:09:06', '2020-03-17 15:34:17', '0');
INSERT INTO `department` VALUES ('3', '销售部门', '2020-03-26 15:49:51', '2020-03-25 15:29:13', '0');
INSERT INTO `department` VALUES ('7', '运维部门', '2020-03-26 20:13:30', '2020-03-26 19:40:11', '0');
INSERT INTO `department` VALUES ('8', '董事会', '2020-03-26 19:47:46', '2020-03-26 19:47:46', '0');
INSERT INTO `department` VALUES ('9', '财务部门', '2020-03-26 20:14:08', '2020-03-26 20:10:19', '0');
INSERT INTO `department` VALUES ('10', '市场部门', '2020-03-26 20:14:29', '2020-03-26 20:10:28', '0');
INSERT INTO `department` VALUES ('12', 's', '2020-03-27 18:30:03', '2020-03-27 18:30:03', '0');

-- ----------------------------
-- Table structure for department_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `department_role_ref`;
CREATE TABLE `department_role_ref` (
  `department_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`,`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of department_role_ref
-- ----------------------------
INSERT INTO `department_role_ref` VALUES ('1', '1', '2020-03-25 10:52:06', '2020-03-25 09:04:33', '0');
INSERT INTO `department_role_ref` VALUES ('2', '2', '2020-03-25 10:52:09', '2020-03-25 00:13:50', '0');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `sex` int(11) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `image` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '猪农aaaaaaaaaaa', '182db214ae867c15066718f1f1f5b342', '0', '18318121630', '2020-03-27 23:56:44', '2020-03-26 00:58:34', '0', '765dc50a48b51a7a7ab50bc1e4cedaa7c62501b01707afef', 'images/userface4.jpg');
INSERT INTO `employee` VALUES ('2', 'pigs', '4559368ea394502b0760a6ab857eec26', '1', '18318121630', '2020-04-06 21:52:41', '2020-04-06 21:36:35', '0', 'c1a71131fa7e4d4d9c7cc5f650f617eb6fbaa6a4b2523508', 'images/userface4.jpg');

-- ----------------------------
-- Table structure for employee_client_ref
-- ----------------------------
DROP TABLE IF EXISTS `employee_client_ref`;
CREATE TABLE `employee_client_ref` (
  `employee_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`employee_id`,`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of employee_client_ref
-- ----------------------------
INSERT INTO `employee_client_ref` VALUES ('1', '1', '2020-04-04 07:58:23', '2020-04-03 16:02:55', '0');
INSERT INTO `employee_client_ref` VALUES ('1', '6', '2020-04-05 23:30:40', '2020-04-05 23:30:40', '0');
INSERT INTO `employee_client_ref` VALUES ('1', '7', '2020-04-06 17:36:23', '2020-04-06 17:36:23', '0');
INSERT INTO `employee_client_ref` VALUES ('1', '8', '2020-04-06 21:48:20', '2020-04-06 21:48:20', '0');
INSERT INTO `employee_client_ref` VALUES ('1', '9', '2020-04-10 16:39:09', '2020-04-10 16:39:09', '0');

-- ----------------------------
-- Table structure for employee_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `employee_role_ref`;
CREATE TABLE `employee_role_ref` (
  `employee_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`,`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of employee_role_ref
-- ----------------------------
INSERT INTO `employee_role_ref` VALUES ('1', '1', '2020-03-26 00:58:34', '2020-03-26 00:58:34', '0');
INSERT INTO `employee_role_ref` VALUES ('2', '1', '2020-03-25 08:34:31', '2020-03-25 08:34:29', '0');
INSERT INTO `employee_role_ref` VALUES ('3', '1', '2020-03-25 09:06:13', '2020-03-25 09:06:10', '0');
INSERT INTO `employee_role_ref` VALUES ('5', '1', '2020-03-25 09:07:53', '2020-03-25 09:07:51', '0');
INSERT INTO `employee_role_ref` VALUES ('6', '1', '2020-03-25 09:11:07', '2020-03-25 09:11:05', '0');
INSERT INTO `employee_role_ref` VALUES ('8', '1', '2020-03-25 10:49:50', '2020-03-25 10:49:48', '0');
INSERT INTO `employee_role_ref` VALUES ('27', '1', '2020-03-25 10:53:43', '2020-03-25 10:53:43', '0');
INSERT INTO `employee_role_ref` VALUES ('28', '1', '2020-03-25 10:54:54', '2020-03-25 10:54:54', '0');
INSERT INTO `employee_role_ref` VALUES ('31', '1', '2020-03-25 11:40:01', '2020-03-25 11:40:01', '0');
INSERT INTO `employee_role_ref` VALUES ('33', '1', '2020-03-25 19:08:20', '2020-03-25 19:08:20', '0');
INSERT INTO `employee_role_ref` VALUES ('35', '1', '2020-03-26 20:10:56', '2020-03-26 20:10:56', '0');
INSERT INTO `employee_role_ref` VALUES ('36', '1', '2020-03-27 23:56:07', '2020-03-27 23:56:07', '0');
INSERT INTO `employee_role_ref` VALUES ('37', '1', '2020-04-01 20:41:39', '2020-04-01 20:41:39', '0');
INSERT INTO `employee_role_ref` VALUES ('4', '2', '2020-03-25 09:07:08', '2020-03-25 09:07:04', '0');
INSERT INTO `employee_role_ref` VALUES ('7', '2', '2020-03-25 09:36:21', '2020-03-25 09:36:18', '0');
INSERT INTO `employee_role_ref` VALUES ('29', '2', '2020-03-25 10:55:19', '2020-03-25 10:55:19', '0');
INSERT INTO `employee_role_ref` VALUES ('30', '2', '2020-03-25 11:34:27', '2020-03-25 11:34:27', '0');
INSERT INTO `employee_role_ref` VALUES ('32', '2', '2020-03-26 00:54:38', '2020-03-26 00:54:38', '0');
INSERT INTO `employee_role_ref` VALUES ('34', '2', '2020-03-26 19:56:42', '2020-03-26 19:56:42', '0');
INSERT INTO `employee_role_ref` VALUES ('38', '2', '2020-04-06 21:36:35', '2020-04-06 21:36:35', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `permission` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '员工添加', 'employee:saveEmployeeList', 'saveEmployeeList', '2020-04-06 21:35:57', '2020-04-06 21:35:57', '0');
INSERT INTO `permission` VALUES ('2', ' 查询全部员工', 'employee:queryEmployeeList', 'queryEmployeeList', '2020-04-06 20:53:28', '2020-03-29 23:29:47', '0');
INSERT INTO `permission` VALUES ('3', '员工查询个人信息', 'employee:queryEmployeeInfo', 'queryEmployeeInfo', '2020-03-29 23:11:37', '2020-03-29 23:11:37', '0');
INSERT INTO `permission` VALUES ('4', '修改员工信息', 'employee:updateEmployeeList', 'updateEmployeeList', '2020-04-06 21:02:19', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('5', '修改客户状态', 'client:updateClientState', 'updateClientState', '2020-04-06 20:47:33', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('6', '添加客户', 'client:clientSave', 'clientSave', '2020-04-06 20:48:30', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('7', '修改客户信息', 'client:clientUpdate', 'clientUpdate', '2020-04-06 20:47:33', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('8', '查询部门', 'department:queryDepartmentList', 'queryDepartmentList', '2020-04-06 20:47:33', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('9', '通过名字查询部门信息回显', 'department:queryDepartmentName', 'queryDepartmentName', '2020-04-06 20:50:29', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('10', '修改部门状态', 'department:updateDepartmentState', 'updateDepartmentState', '2020-04-06 20:51:07', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('11', '修改 部门信息', 'department:updateDepartment', 'updateDepartment', '2020-04-06 20:51:44', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('12', '添加部门', 'department:saveDepartment', 'saveDepartment', '2020-04-06 20:52:08', '2020-04-06 20:47:30', '0');
INSERT INTO `permission` VALUES ('13', '修改员工头像', 'employee:updateEmployeeImage', 'updateEmployeeImage', '2020-04-06 21:02:19', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('14', '修改员工状态', 'employee:updateEmployeeState', 'updateEmployeeState', '2020-04-06 21:04:09', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('15', '查询权限列表', 'permission:queryPermissionList', 'queryPermissionList', '2020-04-06 21:04:09', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('16', '添加权限', 'permission:savePermission', 'savePermission', '2020-04-06 21:06:03', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('17', '修改权限状态', 'permission:updatePermissionState', 'updatePermissionState', '2020-04-06 21:06:20', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('18', '修改权限信息', 'permission:updatePermission', 'updatePermission', '2020-04-06 21:07:13', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('19', '修改权限状态', 'permission:updatePermissionState', 'updatePermissionState', '2020-04-06 21:04:09', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('20', '查询角色列表', 'role:queryRoleList', 'queryRoleList', '2020-04-06 21:07:37', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('21', '添加角色', 'role:saveRole', 'saveRole', '2020-04-06 21:10:15', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('22', '修改角色状态', 'role:updateRoleState', 'updateRoleState', '2020-04-06 21:07:37', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('23', '修改角色信息', 'role:updateRole', 'updateRole', '2020-04-06 21:07:37', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('24', '查询角色 role 回显数据', 'role:queryRole', 'queryRole', '2020-04-06 21:11:27', '2020-03-29 23:40:44', '0');
INSERT INTO `permission` VALUES ('25', '添加客户', 'client:queryClientList', 'queryClientList', '2020-04-06 20:48:30', '2020-04-06 20:47:30', '0');

-- ----------------------------
-- Table structure for permission_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `permission_role_ref`;
CREATE TABLE `permission_role_ref` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permission_role_ref
-- ----------------------------
INSERT INTO `permission_role_ref` VALUES ('1', '1', '2020-04-06 21:35:57', '2020-04-06 21:35:57', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '2', '2020-04-06 21:31:08', '2020-03-29 23:29:47', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '3', '2020-04-06 21:31:12', '2020-03-29 16:28:22', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '4', '2020-04-06 21:31:40', '2020-03-29 23:38:03', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '5', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '6', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '7', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '8', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '9', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '10', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '11', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '12', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '13', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '14', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '15', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '16', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '17', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '18', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '19', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '20', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '21', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '22', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '23', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '24', '2020-04-06 21:31:43', '2020-03-29 23:40:44', '0');
INSERT INTO `permission_role_ref` VALUES ('1', '25', '2020-04-06 21:45:59', '2020-04-06 21:45:58', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员', '2020-03-29 17:26:18', '2020-03-17 15:34:17', '0');
INSERT INTO `role` VALUES ('2', 'common', '普通管理员', '2020-02-25 15:34:14', '2020-03-17 15:34:17', '0');
INSERT INTO `role` VALUES ('3', '销售员', '卖东西', '2020-04-01 20:41:12', '2020-03-27 17:00:59', '0');
