DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`(
	`id` int(11) NOT NULL auto_increment,
	`username` varchar(32) NOT NULL COMMENT '用户名称',
	`birthday` datetime default NULL COMMENT '生日',
	`sex` char(1) default NULL COMMENT '性别',
	`address` varchar(256) default NULL COMMENT '地址',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `username`, `birthday`, `sex`, `address`) VALUES (41, '王二', '2020-03-18 09:54:08', '男', '珠海市香洲区'),(33, '张三', '2020-03-18 07:00:08', '男', '北京市海淀区'),(18, '二柱子', '2010-03-18 09:54:08', '女', '上海市闵行区'),(62, '阿豪', '1960-03-18 09:54:08', '男', '河南省开封市'),(17, '王二', '2013-03-18 09:54:08', '男', '陕西省洛阳市')