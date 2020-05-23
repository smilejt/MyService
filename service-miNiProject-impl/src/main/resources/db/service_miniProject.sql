USE `service_miniproject`;
DROP TABLE IF EXISTS `service_miniproject`.`mini_project_banner`;
CREATE TABLE `service_miniproject`.`mini_project_banner`
(
    `system_id` VARCHAR(32) NOT NULL COMMENT '系统ID',
    `banner_name` VARCHAR(255) NULL COMMENT 'Banner名称',
    `banner_url` VARCHAR(500) NULL COMMENT 'Banner的URL',
    `enable_mark` BIGINT(1) NULL COMMENT '启用标识:1-启用，2-禁用',
    `create_user` VARCHAR(50) NULL COMMENT '创建人',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `delete_mark` BIGINT(1) NULL COMMENT '逻辑删除标识:0-删除，1-启用',
    PRIMARY KEY (`system_id`)
) COMMENT = '微信小程序Banner表';