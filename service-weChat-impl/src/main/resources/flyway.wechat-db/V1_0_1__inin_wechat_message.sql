DROP TABLE IF EXISTS `wechat_message`;
CREATE TABLE `wechat_message` (
    `message_id` VARCHAR(32) NOT NULL COMMENT '主键ID',
    `to_user_name` VARCHAR(50) NULL COMMENT '接收方微信号',
    `from_user_name` VARCHAR(50) NULL COMMENT '发送方微信号',
    `create_time` DATETIME NULL COMMENT '消息创建时间',
    `msg_type` VARCHAR(10) NULL COMMENT '消息类型',
    `content` TEXT NULL COMMENT '文本消息内容',
    `pic_url` VARCHAR(500) NULL COMMENT '图片消息链接地址',
    `media_id` VARCHAR(255) NULL COMMENT '媒体消息ID',
    `format` VARCHAR(10) NULL COMMENT '语音消息格式',
    `recognition` TEXT NULL COMMENT '语音消息识别结果',
    `thumb_media_id` VARCHAR(255) NULL COMMENT '视频消息缩略图ID',
    `location_X` DECIMAL(50,6) NULL COMMENT '位置消息纬度',
    `location_Y` DECIMAL(50,6) NULL COMMENT '位置消息经度',
    `scale` BIGINT(5) NULL COMMENT '地图缩放大小',
    `label` VARCHAR(255) NULL COMMENT '地理位置信息',
    `title` VARCHAR(255) NULL COMMENT '链接消息标题',
    `description` TEXT NULL COMMENT '链接消息描述',
    `url` VARCHAR(500) NULL COMMENT '链接消息URL',
    `msg_id` BIGINT(20) NULL COMMENT '消息ID(微信给的)',
    `write_time` DATETIME NULL COMMENT '写入时间',
    primary key (`message_id`)
) COMMENT = '接收微信消息表';