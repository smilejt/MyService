/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云服务器MySQL
Source Server Version : 80019
Source Host           : 111.229.42.161:3306
Source Database       : service_weChat

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-30 20:29:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wechat_message
-- ----------------------------
DROP TABLE IF EXISTS `wechat_message`;
CREATE TABLE `wechat_message` (
  `message_id` varchar(32) NOT NULL COMMENT '主键ID',
  `to_user_name` varchar(50) DEFAULT NULL COMMENT '接收方微信号',
  `from_user_name` varchar(50) DEFAULT NULL COMMENT '发送方微信号',
  `create_time` datetime DEFAULT NULL COMMENT '消息创建时间',
  `msg_type` varchar(10) DEFAULT NULL COMMENT '消息类型',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文本消息内容',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '图片消息链接地址',
  `media_id` varchar(255) DEFAULT NULL COMMENT '媒体消息ID',
  `format` varchar(10) DEFAULT NULL COMMENT '语音消息格式',
  `recognition` text COMMENT '语音消息识别结果',
  `thumb_media_id` varchar(255) DEFAULT NULL COMMENT '视频消息缩略图ID',
  `location_X` decimal(50,6) DEFAULT NULL COMMENT '位置消息纬度',
  `location_Y` decimal(50,6) DEFAULT NULL COMMENT '位置消息经度',
  `scale` bigint DEFAULT NULL COMMENT '地图缩放大小',
  `label` varchar(255) DEFAULT NULL COMMENT '地理位置信息',
  `title` varchar(255) DEFAULT NULL COMMENT '链接消息标题',
  `description` text COMMENT '链接消息描述',
  `url` varchar(500) DEFAULT NULL COMMENT '链接消息URL',
  `msg_id` bigint DEFAULT NULL COMMENT '消息ID(微信给的)',
  `write_time` datetime DEFAULT NULL COMMENT '写入时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接收微信消息表';
