SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `admin_menu` VALUES (192, 13, '合同作废', 'discard', 3, 1, 1, NULL);

ALTER TABLE `admin_fieldv` ADD INDEX `batch_id`(`batch_id`) USING BTREE;

ALTER TABLE `admin_file` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `size`;

ALTER TABLE `admin_record` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `update_time`;

ALTER TABLE `admin_user_role` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '用户ID' AFTER `id`;

ALTER TABLE `crm_action_record` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '操作人ID' AFTER `id`;

ALTER TABLE `crm_business` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_business` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_business_change` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人' AFTER `create_time`;

ALTER TABLE `crm_business_type` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `dept_ids`;

ALTER TABLE `crm_contacts` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_contacts` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_contract` MODIFY COLUMN `check_status` int(4) NULL DEFAULT 0 COMMENT '0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回 5草稿 6 作废' AFTER `business_id`;

ALTER TABLE `crm_contract` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `order_date`;

ALTER TABLE `crm_contract` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

UPDATE `crm_customer` SET `deal_status` = 1 WHERE `deal_status` = '已成交';

UPDATE `crm_customer` SET `deal_status` = 0 WHERE `deal_status` = '未成交';

ALTER TABLE `crm_customer` MODIFY COLUMN `deal_status` int(4) NOT NULL DEFAULT 0 COMMENT '成交状态 0未成交 1已成交' AFTER `next_time`;

ALTER TABLE `crm_customer` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_customer` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_customer_stats` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '用户id' AFTER `id`;

ALTER TABLE `crm_leads` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_leads` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_owner_record` MODIFY COLUMN `pre_owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '上一负责人' AFTER `type`;

ALTER TABLE `crm_owner_record` MODIFY COLUMN `post_owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接手负责人' AFTER `pre_owner_user_id`;

ALTER TABLE `crm_product` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `description`;

ALTER TABLE `crm_product` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_receivables` MODIFY COLUMN `check_status` int(4) NULL DEFAULT NULL COMMENT '0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回' AFTER `contract_id`;

ALTER TABLE `crm_receivables` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_receivables` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `crm_receivables_plan` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `remark`;

ALTER TABLE `crm_receivables_plan` MODIFY COLUMN `owner_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `oa_action_record` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '操作人ID' AFTER `log_id`;

ALTER TABLE `oa_announcement` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `content`;

ALTER TABLE `oa_event` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `end_time`;

ALTER TABLE `oa_examine` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `duration`;

ALTER TABLE `oa_examine_category` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `type`;

ALTER TABLE `oa_examine_category` MODIFY COLUMN `delete_user_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人ID' AFTER `delete_time`;

ALTER TABLE `oa_examine_log` MODIFY COLUMN `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人' AFTER `examine_status`;

ALTER TABLE `oa_log` MODIFY COLUMN `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID' AFTER `question`;

ALTER TABLE `task` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `name`;

ALTER TABLE `task` MODIFY COLUMN `main_user_id` bigint(20) NULL DEFAULT NULL COMMENT '负责人ID' AFTER `create_user_id`;

ALTER TABLE `task_comment` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '评论人ID' AFTER `comment_id`;

ALTER TABLE `task_comment` MODIFY COLUMN `pid` bigint(20) NULL DEFAULT 0 COMMENT '回复对象ID' AFTER `main_id`;

ALTER TABLE `work_task_class` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `create_time`;

ALTER TABLE `work_task_label` MODIFY COLUMN `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID' AFTER `create_time`;

ALTER TABLE `work_task_log` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '操作人ID' AFTER `log_id`;

ALTER TABLE `work_user` MODIFY COLUMN `user_id` bigint(20) NOT NULL COMMENT '成员ID' AFTER `work_id`;

SET FOREIGN_KEY_CHECKS=1;
