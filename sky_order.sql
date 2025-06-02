/*
 Navicat Premium Dump SQL

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : sky_order

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 01/06/2025 20:15:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orders_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `product_id` int NOT NULL COMMENT '商品id',
  `product_count` int NULL DEFAULT NULL COMMENT '商品数量',
  `actual_cost` double NULL DEFAULT NULL,
  `orders_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cost_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `invoice_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cost_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `place_order_time` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `delivery_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL COMMENT '收货人id',
  `receipt_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '配送时间',
  `receipt_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orders_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 100, 100, '11111111', '支付宝', '不开发票', '2025-05-02 20:16:25', '2025-05-02 20:16:29', '顺丰速运', 1, '地球村', '2025-05-02 20:17:50', '送货上门');
INSERT INTO `orders` VALUES (2, 2, 50, 99, '22222222', '微信', '不开发票', '2025-05-02 20:16:27', '2025-05-02 20:16:36', '京东直达', 2, '月亮海', '2025-05-02 20:17:52', '送货上门');

SET FOREIGN_KEY_CHECKS = 1;
