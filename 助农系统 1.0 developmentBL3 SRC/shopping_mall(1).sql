/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : shopping_mall

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 30/05/2024 23:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id\r\n',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人\r\n',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别\r\n',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号\r\n',
  `province_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级区划编号\r\n',
  `province_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级名称\r\n',
  `city_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级区划编号\r\n',
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级名称\r\n',
  `district_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级区划编号\r\n',
  `district_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级名称\r\n',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址\r\n',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签\r\n',
  `is_default` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认 0 否 1是\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `spu_id` int NULL DEFAULT NULL COMMENT 'spuid\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名\r\n',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性值\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attribute
-- ----------------------------
INSERT INTO `attribute` VALUES (1, 2, 'a', '1', NULL);
INSERT INTO `attribute` VALUES (2, 2, 'b', '7', NULL);
INSERT INTO `attribute` VALUES (3, 2, 'a', '2', NULL);
INSERT INTO `attribute` VALUES (4, 2, 'b', '8', NULL);
INSERT INTO `attribute` VALUES (5, 2, 'b', '9', NULL);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `spu_id` int NULL DEFAULT NULL COMMENT 'spu_id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名\r\n',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称\r\n',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码\r\n',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码\r\n',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别\r\n',
  `id_number` int NULL DEFAULT NULL COMMENT '身份证号码\r\n',
  `status` int NULL DEFAULT NULL COMMENT '状态（0禁用1启用）\n',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  `create_user` int NULL DEFAULT NULL COMMENT '创建人id\r\n',
  `update_user` int NULL DEFAULT NULL COMMENT '修改人id\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `spuid` int NULL DEFAULT NULL COMMENT 'spuid\r\n',
  `skuid` int NULL DEFAULT NULL COMMENT 'skuid\r\n',
  `order_id` int NULL DEFAULT NULL COMMENT '订单概况表id\r\n',
  `count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, 1, 2, 1, NULL);

-- ----------------------------
-- Table structure for order_summary
-- ----------------------------
DROP TABLE IF EXISTS `order_summary`;
CREATE TABLE `order_summary`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT '订单状态 1待付款 2待接单 3已接单 \r\n4派送中 5已完成 6已取消 7退款 8已收货',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id\r\n',
  `address_book_id` int NULL DEFAULT NULL COMMENT '地址Id',
  `pay_method` int NULL DEFAULT NULL COMMENT '支付方式 1微信,2支付宝',
  `pay_id` int NULL DEFAULT NULL COMMENT '支付编号',
  `pay_status` int NULL DEFAULT NULL COMMENT '支付状态 0未支付 1已支付 2退款\'\r\n',
  `sum_money` double NULL DEFAULT NULL COMMENT '合计总价\r\n',
  `discount_money` double NULL DEFAULT NULL COMMENT '优惠金额\r\n',
  `pay_money` double NULL DEFAULT NULL COMMENT '实付款\r\n',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注\r\n',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码\r\n',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址信息\r\n',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称\r\n',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人\r\n',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单取消原因\r\n',
  `rejection_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家拒单原因\r\n',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '订单取消时间\r\n',
  `estimated_delivery_time` datetime NULL DEFAULT NULL COMMENT '送达时间待定\r\n',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '开始配送时间\r\n',
  `checkout_time` datetime NULL DEFAULT NULL COMMENT '结账时间\r\n',
  `pay_amount` double NULL DEFAULT NULL COMMENT '运费\n',
  `is_deleted` int NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_summary
-- ----------------------------
INSERT INTO `order_summary` VALUES (1, 1, 2, 3, 4, 5, 6, 0, 1, 1, 1, '1', '1', '1', '1', 'a', 'b', 'c', '2024-05-29 17:00:49', '2024-05-29 17:00:53', '2024-05-29 17:00:56', '2024-05-29 17:00:58', '2024-05-29 17:01:00', 5, 0);
INSERT INTO `order_summary` VALUES (2, 2, 3, 4, 5, 6, 7, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_summary` VALUES (3, 3, 4, 5, 6, 7, 8, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `order_summary` VALUES (4, 4, 2, 6, 7, 8, 9, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for primary_classification
-- ----------------------------
DROP TABLE IF EXISTS `primary_classification`;
CREATE TABLE `primary_classification`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '一级分类名\r\n',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标地址\r\n',
  `is_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效（1有效0无效）\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of primary_classification
-- ----------------------------

-- ----------------------------
-- Table structure for secondary_classification
-- ----------------------------
DROP TABLE IF EXISTS `secondary_classification`;
CREATE TABLE `secondary_classification`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '二级商品名\r\n',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父类id\r\n',
  `parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父类名\r\n',
  `is_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效（1有效0无效）\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of secondary_classification
-- ----------------------------

-- ----------------------------
-- Table structure for shop_status
-- ----------------------------
DROP TABLE IF EXISTS `shop_status`;
CREATE TABLE `shop_status`  (
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商店营业状态'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_status
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sku_id` int NULL DEFAULT NULL COMMENT 'sku_id',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `is_selected` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否选中',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_cart_view
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart_view`;
CREATE TABLE `shopping_cart_view`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `sku_id` int NULL DEFAULT NULL COMMENT 'skuid\r\n',
  `spu_id` int NULL DEFAULT NULL COMMENT 'spuid\r\n',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称\r\n',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id\r\n',
  `is_selected` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否选中\r\n',
  `count` int NULL DEFAULT NULL COMMENT '数量\r\n',
  `discount` double NULL DEFAULT NULL COMMENT '折扣(比例)\r\n',
  `old_price` double NULL DEFAULT NULL COMMENT '原价\r\n',
  `price` double NULL DEFAULT NULL COMMENT '具体价格\r\n',
  `sku_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体属性值（字符数组）\r\n',
  `is_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效（1有效0无效）\r\n',
  `sku_store_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'sku库存\r\n',
  `is_collected` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否收藏\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart_view
-- ----------------------------

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'sku名字\r\n',
  `spu_id` int NULL DEFAULT NULL COMMENT 'skuid\r\n',
  `price` double NULL DEFAULT NULL COMMENT '具体价格\r\n',
  `old_price` double NULL DEFAULT NULL COMMENT '原价\r\n',
  `store_count` int NULL DEFAULT NULL COMMENT '库存\r\n',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片\r\n',
  `is_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效（1有效0无效）\r\n',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (2, '斗往参办发包', 1, 8, 66, 77659, 'http://dummyimage.com/400x400', '0', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sku_attri
-- ----------------------------
DROP TABLE IF EXISTS `sku_attri`;
CREATE TABLE `sku_attri`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `sku_id` int NULL DEFAULT NULL COMMENT 'skuid\r\n',
  `arri_id` int NULL DEFAULT NULL COMMENT '属性id\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku_attri
-- ----------------------------
INSERT INTO `sku_attri` VALUES (6, 2, 1);
INSERT INTO `sku_attri` VALUES (7, 2, 2);
INSERT INTO `sku_attri` VALUES (8, 2, 3);
INSERT INTO `sku_attri` VALUES (9, 2, 4);
INSERT INTO `sku_attri` VALUES (10, 2, 5);
INSERT INTO `sku_attri` VALUES (11, 2, 57);
INSERT INTO `sku_attri` VALUES (12, 2, 35);

-- ----------------------------
-- Table structure for specification
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `spu_id` int NULL DEFAULT NULL COMMENT 'spuid\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格名称\r\n',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数值\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）默认是0\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specification
-- ----------------------------
INSERT INTO `specification` VALUES (1, 1, '前样系受', 'ad', '0');
INSERT INTO `specification` VALUES (2, 1, '织存通什放很', 'eiusmod sit', '0');
INSERT INTO `specification` VALUES (3, 1, '马它集己算', 'do quis', '0');
INSERT INTO `specification` VALUES (4, 1, '听酸点展', 'et Ut sit enim', '0');
INSERT INTO `specification` VALUES (5, 1, '史家展色打利', 'ad veniam', '0');
INSERT INTO `specification` VALUES (6, 1, '米区题严', 'qui consectetur cupidatat est ex', '0');
INSERT INTO `specification` VALUES (7, 2, '前样系受', 'ad', '0');
INSERT INTO `specification` VALUES (8, 2, '织存通什放很', 'eiusmod sit', '0');
INSERT INTO `specification` VALUES (9, 2, '马它集己算', 'do quis', '0');
INSERT INTO `specification` VALUES (10, 2, '听酸点展', 'et Ut sit enim', '0');
INSERT INTO `specification` VALUES (11, 2, '史家展色打利', 'ad veniam', '0');
INSERT INTO `specification` VALUES (12, 2, '米区题严', 'qui consectetur cupidatat est ex', '0');
INSERT INTO `specification` VALUES (13, 3, '化家美复', 'laboris consectetur', '0');
INSERT INTO `specification` VALUES (14, 3, '置矿务', 'veniam proident in elit', '0');
INSERT INTO `specification` VALUES (15, 3, '明合从眼外却低', 'aliqua non', '0');
INSERT INTO `specification` VALUES (16, 3, '区议知用', 'dolore esse elit', '0');
INSERT INTO `specification` VALUES (17, 3, '接养并再义都', 'nulla fugiat', '0');
INSERT INTO `specification` VALUES (18, 3, '白关常口步进', 'pariatur incididunt in id', '0');
INSERT INTO `specification` VALUES (19, 4, '育代白市求象', 'ipsum mollit consectetur voluptate', '0');
INSERT INTO `specification` VALUES (20, 4, '因林细', 'cillum Duis eiusmod adipisicing', '0');
INSERT INTO `specification` VALUES (21, 4, '增上花共个', 'adipisicing', '0');
INSERT INTO `specification` VALUES (22, 4, '机后名', 'eu occaecat est nulla consectetur', '0');
INSERT INTO `specification` VALUES (23, 4, '海难边从共或', 'occaecat ullamco ut', '0');
INSERT INTO `specification` VALUES (24, 5, '给验面眼极', 'irure', '0');
INSERT INTO `specification` VALUES (25, 5, '极深应都二理即', 'irure labore Ut aliquip sint', '0');
INSERT INTO `specification` VALUES (26, 5, '并例认派', 'ex esse culpa fugiat', '0');
INSERT INTO `specification` VALUES (27, 5, '定门员干别', 'cupidatat proident', '0');
INSERT INTO `specification` VALUES (28, 5, '加上飞量切', 'ut cillum Duis', '0');
INSERT INTO `specification` VALUES (29, 5, '每天完又', 'Duis culpa Ut ea cupidatat', '0');
INSERT INTO `specification` VALUES (30, 6, '拉元圆带件', 'laborum', '0');
INSERT INTO `specification` VALUES (31, 6, '许流青', 'velit consequat adipisicing', '0');
INSERT INTO `specification` VALUES (32, 6, '志因作委提技而', 'amet dolore nisi', '0');
INSERT INTO `specification` VALUES (33, 6, '电己证五统', 'aliquip do dolor culpa nulla', '0');
INSERT INTO `specification` VALUES (34, 6, '称美无', 'sunt ea dolore qui veniam', '0');

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `spu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'spu名称\r\n',
  `price` double NULL DEFAULT NULL COMMENT '原价\r\n',
  `discount` double NULL DEFAULT NULL COMMENT '折扣（比例）\r\n',
  `store_count` int NULL DEFAULT NULL COMMENT '库存\r\n',
  `sales_count` int NULL DEFAULT NULL COMMENT '销量 默认为0 \r\n',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片集合待定？\r\n',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品详情描述\r\n',
  `is_effective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效（1有效0无效）默认是有效\r\n',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间\r\n',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  `category2_id` int NULL DEFAULT NULL COMMENT '二级分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES (1, '或数门天成力群', 28, 30, 49, 0, NULL, '然还形确观道们党向向立过题制。候称制步例民照象用回花九。制不子济造研精南内将很省。入体过点引说去素路究变年身达王。识成真王起认持来到发完东有该。主共色却体技易如起维包图表制总人压。', '1', NULL, NULL, '0', 11);
INSERT INTO `spu` VALUES (2, '或数门天成力群', 28, 30, 49, 0, NULL, '然还形确观道们党向向立过题制。候称制步例民照象用回花九。制不子济造研精南内将很省。入体过点引说去素路究变年身达王。识成真王起认持来到发完东有该。主共色却体技易如起维包图表制总人压。', '1', NULL, NULL, '0', 11);
INSERT INTO `spu` VALUES (3, '程商没集只', 98, 25, 44, 0, NULL, '什那律世律研听农改指于知性。受斯单又量清好容些天记干从能。示那已队求制细高议支传北道始党教。至调数引少界给会作快南入机林间。反资才离出步书须提将非快其收次公。产王九式合角地到习记出每风周术长使心。', '1', NULL, NULL, '0', 55);
INSERT INTO `spu` VALUES (4, '同青七县际做展', 44, 13, 49, 0, NULL, '她展明受型类内交候易被率持般手日再。国形团之极型给引电部林志向至。金金花认代利县七周统体七相由还。世低产受能内总低阶少素强连。', '1', NULL, NULL, '0', 72);
INSERT INTO `spu` VALUES (5, '将极参研阶', 45, 75, 88, 0, NULL, '快支动海织清条达治段识次教己。没研权情式安在想风力除包切红断色众向。音响八江验接构别很红业外学影果力。据系被反该光委节与国光东对。是生安影影类列长家等许风第着天速。如较风万又事成然千过流单并将除改需。标也义率如指此布热马强加界历科员。', '1', NULL, NULL, '0', 12);
INSERT INTO `spu` VALUES (6, '当养争确队细', 92, 23, 84, 0, NULL, '厂话说广文见之安儿期开水接与际。话位铁放部劳度书海引件院引。压正者她重历它济线示近再派利带农。究东号个究难速公活际阶分力。看观究住接九高相事命好们教严外较。实非间就再层展标织状组个院十严运不。', '1', NULL, NULL, '0', 86);

-- ----------------------------
-- Table structure for spu_picture
-- ----------------------------
DROP TABLE IF EXISTS `spu_picture`;
CREATE TABLE `spu_picture`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `spu_id` int NULL DEFAULT NULL COMMENT 'spu_id',
  `picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spu_picture
-- ----------------------------
INSERT INTO `spu_picture` VALUES (1, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (2, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (3, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (4, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (5, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (6, 1, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (7, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (8, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (9, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (10, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (11, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (12, 2, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (13, 3, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (14, 3, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (15, 3, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (16, 3, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (17, 3, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (18, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (19, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (20, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (21, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (22, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (23, 4, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (24, 5, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (25, 5, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (26, 5, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (27, 5, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (28, 5, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (29, 6, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (30, 6, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (31, 6, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (32, 6, 'http://dummyimage.com/400x400');
INSERT INTO `spu_picture` VALUES (33, 6, 'http://dummyimage.com/400x400');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信用户唯一标识\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名\r\n',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号\r\n',
  `sex` int NULL DEFAULT NULL COMMENT '性别\r\n',
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号\r\n',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像\r\n',
  `is_deleted` int NULL DEFAULT NULL COMMENT '是否删除（1删除0未删除）\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
