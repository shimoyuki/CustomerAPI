-- MySQL dump 10.13  Distrib 5.7.3-m13, for Win64 (x86_64)
--
-- Host: localhost    Database: customerapi
-- ------------------------------------------------------
-- Server version	5.7.3-m13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `city_data`
--

DROP TABLE IF EXISTS `city_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(30) NOT NULL,
  `initial` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_UNIQUE` (`city`),
  UNIQUE KEY `initial_UNIQUE` (`initial`)
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_data`
--

LOCK TABLES `city_data` WRITE;
/*!40000 ALTER TABLE `city_data` DISABLE KEYS */;
INSERT INTO `city_data` VALUES (1,'﻿北京市','BEIJING'),(2,'天津市','TIANJIN'),(3,'上海市','SHANGHAI'),(4,'重庆市','CHONGQING'),(5,'邯郸市','HANDAN'),(6,'石家庄市','SHIJIAZHUANG'),(7,'保定市','BAODING'),(8,'张家口市','ZHANGJIAKOU'),(9,'承德市','CHENGDE'),(10,'唐山市','TANGSHAN'),(11,'廊坊市','LANGFANG'),(12,'沧州市','CANGZHOU'),(13,'衡水市','HENGSHUI'),(14,'邢台市','XINGTAI'),(15,'秦皇岛市','QINHUANGDAO'),(16,'朔州市','SHUOZHOU'),(17,'忻州市','XINZHOU'),(18,'太原市','TAIYUAN'),(19,'大同市','DATONG'),(20,'阳泉市','YANGQUAN'),(21,'晋中市','JINZHONG'),(22,'长治市','CHANGZHI'),(23,'晋城市','JINCHENG'),(24,'临汾市','LINFEN'),(25,'吕梁市','LVLIANG'),(26,'运城市','YUNCHENG'),(27,'沈阳市','SHENYANG'),(28,'铁岭市','TIELING'),(29,'大连市','DALIAN'),(30,'鞍山市','ANSHAN'),(31,'抚顺市','FUSHUN'),(32,'本溪市','BENXI'),(33,'丹东市','DANDONG'),(34,'锦州市','JINZHOU'),(35,'营口市','YINGKOU'),(36,'阜新市','FUXIN'),(37,'辽阳市','LIAOYANG'),(38,'朝阳市','CHAOYANG'),(39,'盘锦市','PANJIN'),(40,'葫芦岛市','HULUDAO'),(41,'长春市','CHANGCHUN'),(42,'吉林市','JILIN'),(43,'延边朝鲜族自治州','YANBIAN'),(44,'四平市','SIPING'),(45,'通化市','TONGHUA'),(46,'白城市','BAICHENG'),(47,'辽源市','LIAOYUAN'),(48,'松原市','SONGYUAN'),(49,'白山市','BAISHAN'),(50,'哈尔滨市','HAERBIN'),(51,'齐齐哈尔市','QIQIHAER'),(52,'鸡西市','JIXI'),(53,'牡丹江市','MUDANJIANG'),(54,'七台河市','QITAIHE'),(55,'佳木斯市','JIAMUSI'),(56,'鹤岗市','HEGANGSHI'),(57,'双鸭山市','SHUANGYASHAN'),(58,'绥化市','SUIHUASHI'),(59,'黑河市','HEIHESHI'),(60,'大兴安岭地区','DAXINGANLINGDIQU'),(61,'伊春市','YICHUN1'),(62,'大庆市','DAQING'),(63,'南京市','NANJING'),(64,'无锡市','WUXI'),(65,'镇江市','ZHENJIANG'),(66,'苏州市','SUZHOU'),(67,'南通市','NANTONG'),(68,'扬州市','YANGZHOU'),(69,'盐城市','YANCHENG'),(70,'徐州市','XUZHOU'),(71,'淮安市','HUAIAN'),(72,'连云港市','LIANYUNGANG'),(73,'常州市','CHANGZHOU'),(74,'泰州市','TAIZHOU'),(75,'宿迁市','SUQIAN'),(76,'舟山市','ZHOUSHAN'),(77,'衢州市','QUZHOU'),(78,'杭州市','HANGZHOU'),(79,'湖州市','HUZHOU'),(80,'嘉兴市','JIAXING'),(81,'宁波市','NINGBO'),(82,'绍兴市','SHAOXING'),(83,'温州市','WENZHOU'),(84,'丽水市','LISHUI'),(85,'金华市','JINHUA'),(86,'台州市','TAIZHOU1'),(87,'合肥市','HEFEI'),(88,'芜湖市','WUHU'),(89,'蚌埠市','BENGBU'),(90,'淮南市','HUAINAN'),(91,'马鞍山市','MAANSHAN'),(92,'淮北市','HUAIBEI'),(93,'铜陵市','TONGLING'),(94,'安庆市','ANQING'),(95,'黄山市','HUANGSHAN'),(96,'滁州市','CHUZHOU'),(97,'阜阳市','FUYANG'),(98,'宿州市','SUZHOU1'),(99,'巢湖市','CHAOHU'),(100,'六安市','LIUAN'),(101,'亳州市','BOZHOU'),(102,'池州市','CHIZHOU'),(103,'宣城市','XUANCHENG'),(104,'福州市','FUZHOU'),(105,'厦门市','XIAMEN'),(106,'宁德市','NINGDE'),(107,'莆田市','PUTIAN'),(108,'泉州市','QUANZHOU'),(109,'漳州市','ZHANGZHOU'),(110,'龙岩市','LONGYAN'),(111,'三明市','SANMING'),(112,'南平市','NANPING'),(113,'鹰潭市','YINGTAN'),(114,'新余市','XINYU'),(115,'南昌市','NANCHANG'),(116,'九江市','JIUJIANG'),(117,'上饶市','SHANGRAO'),(118,'抚州市','FUZHOU1'),(119,'宜春市','YICHUN'),(120,'吉安市','JIAN'),(121,'赣州市','GANZHOU'),(122,'景德镇市','JINGDEZHEN'),(123,'萍乡市','PINGXIANG'),(124,'菏泽市','HEZE'),(125,'济南市','JINAN'),(126,'青岛市','QINGDAO'),(127,'淄博市','ZIBO'),(128,'德州市','DEZHOU'),(129,'烟台市','YANTAI'),(130,'潍坊市','WEIFANG'),(131,'济宁市','JINING'),(132,'泰安市','TAIAN'),(133,'临沂市','LINYI'),(134,'滨州市','BINZHOU'),(135,'东营市','DONGYING'),(136,'威海市','WEIHAI'),(137,'枣庄市','ZAOZHUANG'),(138,'日照市','RIZHAO'),(139,'莱芜市','LAIWU'),(140,'聊城市','LIAOCHENG'),(141,'商丘市','SHANGQIU'),(142,'郑州市','ZHENGZHOU'),(143,'安阳市','ANYANG'),(144,'新乡市','XINXIANG'),(145,'许昌市','XUCHANG'),(146,'平顶山市','PINGDINGSHAN'),(147,'信阳市','XINYANG'),(148,'南阳市','NANYANG'),(149,'开封市','KAIFENG'),(150,'洛阳市','LUOYANG'),(151,'济源市','JIYUAN'),(152,'焦作市','JIAOZUO'),(153,'鹤壁市','HEBI'),(154,'濮阳市','PUYANG'),(155,'周口市','ZHOUKOU'),(156,'漯河市','LUOHE'),(157,'驻马店市','ZHUMADIAN'),(158,'三门峡市','SANMENXIA'),(159,'武汉市','WUHAN'),(160,'襄樊市','XIANGFAN'),(161,'鄂州市','EZHOU'),(162,'孝感市','XIAOGANSHI'),(163,'黄冈市','HUANGGANG'),(164,'黄石市','HUANGSHI'),(165,'咸宁市','XIANNING'),(166,'荆州市','JINGZHOU'),(167,'宜昌市','YICHANG'),(168,'恩施土家族苗族自治州','ENSHI'),(169,'神农架林区','SHENNONGJIALINQU'),(170,'十堰市','SHIYAN'),(171,'随州市','SUIZHOU'),(172,'荆门市','JINGMEN'),(173,'仙桃市','XIANTAO'),(174,'天门市','TIANMEN'),(175,'潜江市','QIANJIANGSHI'),(176,'岳阳市','YUEYANG'),(177,'长沙市','CHANGSHA'),(178,'湘潭市','XIANGTAN'),(179,'株洲市','ZHUZHOU'),(180,'衡阳市','HENGYANG'),(181,'郴州市','CHENZHOU'),(182,'常德市','CHANGDE'),(183,'益阳市','YIYANG'),(184,'娄底市','LOUDI'),(185,'邵阳市','SHAOYANG'),(186,'湘西土家族苗族自治州','XIANGXITUJIAZUMIAOZUZIZHIZHOU'),(187,'张家界市','ZHANGJIAJIE'),(188,'怀化市','HUAIHUA'),(189,'永州市','YONGZHOU'),(190,'广州市','GUANGZHOU'),(191,'汕尾市','SHANWEISHI'),(192,'阳江市','YANGJIANG'),(193,'揭阳市','JIEYANGSHI'),(194,'茂名市','MAOMING'),(195,'惠州市','HUIZHOU'),(196,'江门市','JIANGMEN'),(197,'韶关市','SHAOGUAN'),(198,'梅州市','MEIZHOU'),(199,'汕头市','SHANTOU'),(200,'深圳市','SHENZHEN'),(201,'珠海市','ZHUHAI'),(202,'佛山市','FOSHAN'),(203,'肇庆市','ZHAOQING'),(204,'湛江市','ZHANJIANG'),(205,'中山市','ZHONGSHAN'),(206,'河源市','HEYUAN'),(207,'清远市','QINGYUAN'),(208,'云浮市','YUNFUSHI'),(209,'潮州市','CHAOZHOU'),(210,'东莞市','DONGGUAN'),(211,'兰州市','LANZHOU'),(212,'金昌市','JINCHANGSHI'),(213,'白银市','BAIYIN'),(214,'天水市','TIANSHUI'),(215,'嘉峪关市','JIAYUGUANSHI'),(216,'武威市','WUWEI'),(217,'张掖市','ZHANGYE'),(218,'平凉市','PINGLIANG'),(219,'酒泉市','JIUQUAN'),(220,'庆阳市','QINGYANG'),(221,'定西市','DINGXISHI'),(222,'陇南市','LONGNANSHI'),(223,'临夏回族自治州','LINXIA'),(224,'甘南藏族自治州','GANNANZANGZUZIZHIZHOU'),(225,'成都市','CHENGDU'),(226,'攀枝花市','PANZHIHUA'),(227,'自贡市','ZIGONG'),(228,'绵阳市','MIANYANG'),(229,'南充市','NANCHONG'),(230,'达州市','DAZHOU'),(231,'遂宁市','SUINING'),(232,'广安市','GUANGAN'),(233,'巴中市','BAZHONG'),(234,'泸州市','LUZHOU'),(235,'宜宾市','YIBIN'),(236,'资阳市','ZIYANG'),(237,'内江市','NEIJIANG'),(238,'乐山市','LESHAN'),(239,'眉山市','MEISHAN'),(240,'凉山彝族自治州','LIANGSHANYIZUZIZHIZHOU'),(241,'雅安市','YAAN'),(242,'甘孜藏族自治州','GANZIZANGZUZIZHIZHOU'),(243,'阿坝藏族羌族自治州','ABAZANGZUQIANGZUZIZHIZHOU'),(244,'德阳市','DEYANG'),(245,'广元市','GUANGYUAN'),(246,'贵阳市','GUIYANG'),(247,'遵义市','ZUNYI'),(248,'安顺市','ANSHUN'),(249,'黔南布依族苗族自治州','QIANNAN'),(250,'黔东南苗族侗族自治州','QIANDONGNANMIAOZUDONGZUZIZHIZHOU'),(251,'铜仁地区','TONGREN'),(252,'毕节地区','BIJIEDIQU'),(253,'六盘水市','LIUPANSHUI'),(254,'黔西南布依族苗族自治州','QIANXINANBUYIZUMIAOZUZIZHIZHOU'),(255,'海口市','HAIKOU'),(256,'三亚市','SANYA'),(257,'五指山市','WUZHISHANSHI'),(258,'琼海市','QIONGHAI'),(259,'儋州市','DANZHOUSHI'),(260,'文昌市','WENCHANGSHI'),(261,'万宁市','WANNINGSHI'),(262,'东方市','DONGFANGSHI'),(263,'澄迈县','CHENGMAIXIAN'),(264,'定安县','DINGANXIAN'),(265,'屯昌县','TUNCHANGXIAN'),(266,'临高县','LINGAOXIAN'),(267,'白沙黎族自治县','BAISHALIZUZIZHIXIAN'),(268,'昌江黎族自治县','CHANGJIANGLIZUZIZHIXIAN'),(269,'乐东黎族自治县','LEDONGLIZUZIZHIXIAN'),(270,'陵水黎族自治县','LINGSHUILIZUZIZHIXIAN'),(271,'保亭黎族苗族自治县','BAOTINGLIZUMIAOZUZIZHIXIAN'),(272,'琼中黎族苗族自治县','QIONGZHONGLIZUMIAOZUZIZHIXIAN'),(273,'西双版纳傣族自治州','XISHUANGBANNADAIZUZIZHIZHOU'),(274,'德宏傣族景颇族自治州','DEHONGDAIZUJINGPOZUZIZHIZHOU'),(275,'昭通市','ZHAOTONG'),(276,'昆明市','KUNMING'),(277,'大理白族自治州','DALI'),(278,'红河哈尼族彝族自治州','HONGHEHANIZUYIZUZIZHIZHOU'),(279,'曲靖市','QUJING'),(280,'保山市','BAOSHAN'),(281,'文山壮族苗族自治州','WENSHANZHUANGZUMIAOZUZIZHIZHOU'),(282,'玉溪市','YUXI'),(283,'楚雄彝族自治州','CHUXIONG'),(284,'普洱市','PUERSHI'),(285,'临沧市','LINCANG'),(286,'怒江傈傈族自治州','NUJIANGLILIZUZIZHIZHOU'),(287,'迪庆藏族自治州','DIQINGZANGZUZIZHIZHOU'),(288,'丽江市','LIJIANG'),(289,'海北藏族自治州','HAIBEIZANGZUZIZHIZHOU'),(290,'西宁市','XINING'),(291,'海东地区','HAIDONGDIQU'),(292,'黄南藏族自治州','HUANGNANZANGZUZIZHIZHOU'),(293,'海南藏族自治州','HAINANZANGZUZIZHIZHOU'),(294,'果洛藏族自治州','GUOLUOZANGZUZIZHIZHOU'),(295,'玉树藏族自治州','YUSHUZANGZUZIZHIZHOU'),(296,'海西蒙古族藏族自治州','HAIXIMENGGUZUZANGZUZIZHIZHOU'),(297,'西安市','XIAN'),(298,'咸阳市','XIANYANG'),(299,'延安市','YANAN'),(300,'榆林市','YULIN1'),(301,'渭南市','WEINAN'),(302,'商洛市','SHANGLUO'),(303,'安康市','ANKANG'),(304,'汉中市','HANZHONG'),(305,'宝鸡市','BAOJI'),(306,'铜川市','TONGCHUAN'),(307,'防城港市','FANGCHENGGANG'),(308,'南宁市','NANNING'),(309,'崇左市','CHONGZUO'),(310,'来宾市','LAIBIN'),(311,'柳州市','LIUZHOU'),(312,'桂林市','GUILIN'),(313,'梧州市','WUZHOU'),(314,'贺州市','HEZHOUSHI'),(315,'贵港市','GUIGANG'),(316,'玉林市','YULIN'),(317,'百色市','BAISESHI'),(318,'钦州市','QINZHOU'),(319,'河池市','HECHISHI'),(320,'北海市','BEIHAI'),(321,'拉萨市','LASA'),(322,'日喀则地区','RIKAZE'),(323,'山南地区','SHANNANDIQU'),(324,'林芝地区','LINZHIDIQU'),(325,'昌都地区','CHANGDUDIQU'),(326,'那曲地区','NAQUDIQU'),(327,'阿里地区','ALIDIQU'),(328,'银川市','YINCHUAN'),(329,'石嘴山市','SHIZUISHANSHI'),(330,'吴忠市','WUZHONGSHI'),(331,'固原市','GUYUAN'),(332,'中卫市','ZHONGWEISHI'),(333,'塔城地区','TACHENG'),(334,'哈密地区','HAMI'),(335,'和田地区','HETIAN'),(336,'阿勒泰地区','ALETAIDIQU'),(337,'克孜勒苏柯尔克孜自治州','KEZILESUKEERKEZIZIZHIZHOU'),(338,'博尔塔拉蒙古自治州','BOERTALAMENGGUZIZHIZHOU'),(339,'克拉玛依市','KELAMAYI'),(340,'乌鲁木齐市','WULUMUQI'),(341,'石河子市','SHIHEZI'),(342,'昌吉回族自治州','CHANGJI'),(343,'五家渠市','WUJIAQUSHI'),(344,'吐鲁番地区','TULUFAN'),(345,'巴音郭楞蒙古自治州','BAYINGUOLENGMENGGUZIZHIZHOU'),(346,'阿克苏地区','AKESU'),(347,'阿拉尔市','ALAERSHI'),(348,'喀什地区','KASHI'),(349,'图木舒克市','TUMUSHUKESHI'),(350,'伊犁哈萨克自治州','YILI'),(351,'呼伦贝尔市','HULUNBEIERSHI'),(352,'呼和浩特市','HUHEHAOTE'),(353,'包头市','BAOTOU'),(354,'乌海市','WUHAI'),(355,'乌兰察布市','WULANCHABUSHI'),(356,'通辽市','TONGLIAO'),(357,'赤峰市','CHIFENG'),(358,'鄂尔多斯市','EERDUOSI'),(359,'巴彦淖尔市','BAYANNAOERSHI'),(360,'锡林郭勒盟','XILINGUOLEMENG'),(361,'兴安盟','XINGANMENG'),(362,'阿拉善盟','ALASHANMENG'),(363,'台北市','TAIBEISHI'),(364,'高雄市','GAOXIONGSHI'),(365,'基隆市','JILONGSHI'),(366,'台中市','TAIZHONGSHI'),(367,'台南市','TAINANSHI'),(368,'新竹市','XINZHUSHI'),(369,'嘉义市','JIAYISHI'),(370,'澳门特别行政区','AOMENTEBIEXINGZHENGQU'),(371,'香港特别行政区','XIANGGANGTEBIEXINGZHENGQU');
/*!40000 ALTER TABLE `city_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 14:39:24
