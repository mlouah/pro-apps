-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: my-pro-apps-prod
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `client_logo` longblob,
  `client_logo_content_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `date_creation` datetime(6),
  `date_modify` datetime(6),
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'S2M (INTERNAL CLIENT)','S2M',_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0)\0\0\0\Ã$Œ\æ\0\0\0sRGB\0®\Î\é\0\0\ÍIDATx^\í›	t\Í\×ö\Ç?¹ó½%BJbx\æ\ÆkQ­¹Š\Zš\Õ<\ÕPj–\ÖX35¯h‰ª¢¨§­ò”¶<j(\Ú\Æ‰„$’9¹óğ_\çDbˆ!õ^\×{kı³\×\Ê\ÊZ÷³Ï¹û»‡\ïŞ¿{\İ\\.—‹ùŸ±€[	 ÿ3XÈ‹R(ÿ=`\Ü\Ü\Ü\n/‰ÿ…\'‹`(\0¥@ş,PŒ+d\Ù\ìOJ\ãV…~•\ÊPF§)Æ®\ç[R!O±›\Å\á\à»\Ät>‹M\æ@J&N‡“\æe}˜[;˜\Æ\Ş\Ïgñg\ì*\ä	ú!9ƒUW“ø\év:‡TJP¸\ÕN€^Ã¤\ZAŒ®ZRñ¦GÌ™aµ1?\êŸ\Æ&“g±JŠGŒnw\È]İ‚J3³vEjû¸?‹Éˆ\Íj\Æ\Ã\Û÷™\à•\0ò€‰®\å˜yŸ’\ÒA©„§y¿\Ó6Õ¼\r\Ìy1˜ü‹;3-•¨SG8ºk\Ù™ô;™º[=”@\î™\'&\×DŸÑœº“	j<\Ğ\È%\"m	\Ü\\ ¸—¾\Ä\ë6¥‚‰5‚«ñ*%ñ\ÑQİ½s\Çwñf£‡J— \ß\äh\Õs\ĞA)09ô9Í·q© U5–Í§FÍ€2dYml»y»\Ãj%ˆ>\Îî”€u¯@x½*XOcá¸\ÄD\İ\Ä7@F§CÚ”kB¥\Õ\Ò\'l†M,\ì7<ğb\Ì\Í!\æB$‰1—É¸›\n¸ğòñ#0\ä/T®ó2Ş¾¥‹~Æb/üNò\ÍX²3\Ó\Å6|\Ê\\3”ª¡\rP<š§…$\Å_\'#5	•ZƒO\é2”y!¤Xyø\Æ\åsXŒ¹”¬€ù\nOİ³\æ\ÚmFG^\Ã)RÔƒ‘!F{võ|½˜]§\"\í\Êù\Ê ù<6™ğ\è.e\æİ¯1b­\ÍÎ«¥ˆx\å/ø%D³z\Ö\Î;„ÁCB¡’\0˜FJ-Cf¯¤m\ß\áE\îõ‡\09õ\Ów\ì\İğ1\×\ÎFb\Ê\Í\Ä\é\Ì\×\'l©Ñ»R+”n\ïM¥~\Ëöôów_²{]8·®^Áj6\Ê}\âş\"ò½|\ËR¯\å›ô3À\à*\í3\æd³r\âPN:€ÁS‡·¡M[\Òq\Èx\ÊVx20?n_\Ï\Ö%s°\ÙÌ”ò/Ã›}‡\ÑnÀ¨Ç‚b¶\ÒşH‘i\Ùù©ª@$\Â\ëı	¯[‰ ƒö¡»\Å\æYXx)7R°”bµS½”_6\r¥š9\Õ\ÓGrl÷\×\è\İ5(”\Ê|PòŒ¸ûø1i\ÍW„6jù\Şb\"\íß²–M\Ã\ÈI\ÏE«W Pº\álÃ•o\\ñg5C`¥@&­\ÙI—_“İ½ƒUaƒ\É\Í\ÌC¥\Égn\nP©…\Ç(°Y¬˜Œğ\×\æM¿b\ËCŞœ\Ã\Ì>­H¸vÁ\r»Í…\Õõ[·büŠ\Í\ÒØJl\Ô\æ~‹”¸[h\rnXL.t\îZ\Æ,\ßHÓoYÿÏ¤4:¿Œ]¼Sb\æm·3°rYVşµ2^\0uı\âi\\UC\ë#üqSl2³/\Æ—k¾W{\0‹Z¾\î\ìlYj¶l–†\r\å\èw;0xhq»—	ò2M\ÔiÜ˜\Ö\î\Â\Ë\×\ï?(\Æ\è\ä\â‰\Ã|4¢9i<õ8lVl6\îŞ¥\Ğhucn¦ôŸ2™~“b\ÊË‘\Æ9ùı!<|@\ç\á\Î\à‰\Ó\á 7\ëJ¥µV‹\Ãn\'7\ÓJ—÷&0xú\Ò\Â\Üz)ò8wÀb\ÌA©\Î\ïV&£Á3\Óyx\ØCv:D\ÌËˆ\Õx–\ÒKû\nG\É\Í4Ñ¨c\'&®ú\nõ==\\ºÅ´s7ò\ëA\Ø´)WŠ­¯V\ÇWs¿¦İ³ƒ\Ï\çLÂ…“\ÓÓ¬So¹#2=‡\Ñgb8‘’u«FeJ±§y(ÊŒ–\î\ÅÙ£‡p÷\ĞI\àv;V«ƒ\ÖĞ¦\Ïı\Ôõ\Ìq8¬›1’½\Ö\á\é+À°\ã\é[šv\ÆPë•¦Ò \ÂÀ™wR¸üûq~şf/·h\É\ÈE¤&\Æ3¥\ËkxxúÓ¢G?Bj\Õ\Å\İ\Óa¸¨_²\'b9wQ©\ÕXL&‚ª\Ödö¶Qªt¾\çû\ç–\é…Z£–\Ñ$\Å\rŒ\Ù&j\Ôo\È\Ô\Ïö\àõ@\ÍJ¸~…9ıZ“œˆFŸ_H…˜óLò\ì©öP:0\è!ÇŸaEt\â}@œNü4jö7©E=?\ÏÂµÿ±‘õ3\Ça\ÌÎ’)Z©\Ö\Ñağhº¿7woŒf†ıv}‰i÷@)\Ã\ÎøšdÊ‹½pšC;’’„V—J^–Q¦\ë°5;\Ğ{\äŸõL@D^4¼3Q¿Á\ài /\ÛH“N=™´j{‘ğ/\ÄG_$#\å6u›¶\âö\Íşf3-ºô¥l…JEÖ¯Ÿ==\ëWbp\×a³Yññ/\Ãô\Ï÷\\#T®İµn)_\ÌCg\Ğ\çG‡\İ.\ë€\Ó\å\Ä\årc\Âß·ñj\Û.…zw®^È—‹¦¢5è¤“ˆ\\€\í\Ì\äu»©™¯»@&‰%üJ\Â}@lú„°¥aõ\Â5‘‡÷³|t/ò23\Ñ{p\Úd¥[¨\ßú\r\Æ.û¿²\å\ä\ÚD£…¾\'£9œ’‘ŠÃ‰F\ÅO\ÍjS\Ï×“­\á³Ø¹r¶tQKl3\Şş\Ì\Üô#ª\Õ* 9™\é|4¼39‚»§›\ÅBÀÁt\Z1™\ZõP>XÒºÇ‰ıË Dş]ø_¯^Œ\ŞC\'/\ç_>˜Y[‚\'\0Û»ae! Z½v›»ÍŠ1\ÇLó®½»|³4|vF:sû·!\æü\ïhõ:\Ôzw™Zv›\Û\à\éC\Ø\'_S\ç\Õ\æ]uÉ•\Ş?{‡“µõª0¬r \\—™v‡ùƒ\Úù\î\Ş\\N¦<Íºôa\à\Ô%ø–\É_W \ç3ò\èp,Š[FK~cisVó‡†Í¬~­\ÈLID¥©Ú†R­er\ÄnB½^<@„§}6w<ß¬ú/_\rJ•J\ZO‹€*S>¤\nAUkQ%´>/6j‰W©û\ê±(\İ{1\ê\ÔQV…\r\äNB<\Z†\ìt¯ux‹°Ov\È</\Î\rÓ—\ã{¶K·Û­4hÓ¬»\ÉDÿzTG\ÏR¥™±ùBj„rø›-¬ş`0¸h´\ê·\é\Â\Å_“~—Ë‰›B\Éø•\Ûiøf§‡®µ\ïvº,\êñ\0R!\ÓU‹{\Ã\Ã\ï¿X\Åú™£QkKRHG¨÷F[Æ¯ÜŠ‡·\Ïc?\â‚K7™v!.ŸF\Ú\í¼^Î—\ï›\ÖAc³0DW~?°½‡A:ŒR¥!l\í.^jš\ßÁ?3e‰E¢óüû„\\ş5Ôšü†\Èns\àô\Ä%h¯\àšµxk\Ø\Zwxû±MOÁ\íŞµ•­K¦q\'1Nzsn–™\Ò\å\Ê3qõVj¿\ÒT.‘¸`\Ø[œ=üƒ\\c1š\é9a&\n•š¯\Âg R©°XlôŸ²˜.#\ÂX2²;\Çv\ïD­q#0¤\Z=\Ç\Í\æ\Ë\Åp÷v‚Ls\"ª\Ş]´7z<\Ü%\'›¬t<\Åoi9’¿\ëT\n~lV‹&~Şˆ_:²;\'¿ß…\ŞS\Õl\Â/°\"S\"vÉšô$¹šc¤ù\á‹\ÜQ\ârQ\ÏÏ‹ı\Í_\ÄO­`\íôQ\ìûb\rzOv‹½—36\í§jz\ÅD\æ\ÇØ«\ì\ßü)§ş‘;	±²Pº\ÄT\Z­§Ó‰)Ï‡\'£G\Ğ\äo=‹\Ü7/;‹o\×.e\ÏúeXM&\Ô:5\Æ,kVcÈ¬¿óR³Ö…{r³2˜7¨-WOŸB£\Õb6Y2k5\Z4aNŸ70\çeK–Ò õ\ß\è:j:\á£{p7ñ&6›‹V½\Óù\İ\É,|§)ñ\×P©4X\ÌL[F§aŠ\Ükñ•[L>‹K¡D\éÿxµ:ƒJK¢2op;bÎ–Qj1™\é2j\nı\Ş_ğ´\à\Çhw\Ğ\íøeDô	y5À›š½ˆ§>™6’6}ZX«Kr²o\ßüYX±\"\äÁ\ÓE§-r\á­kQÜˆ:Gô\é_¸›/CZHv†‰—Z´`Êºo1x\Şfpój[O\ã\äş\ï\Ğh@\Ñ 6lÛ…¾\ï\Ï\'¨òı\"*ô¤§&3w\à›Ü¼rNv\é6³•¡sWÑº÷p¦vkÌµ³§d”øR»\ç~ş§¬\"jÇ¯\Ü\ÂK-\Ú3¥KC’b®Hjn\Ê3\Óc\ìtzOš[Ä˜©f+\íDsx7[’€©µ*0ÿ\Å`2’™3¨-q—.È¬ w÷fê†½T{©\áSqº\\Œøı:×“$-\ì}$8-f\æ\ïÌ™\í—\0\çe›\é;y6=Fx¿*\Î#\\ÁnD\íxT\ì6©	qlœ?‰Èƒ{dq7M†T•”4¨r5¹\åÂ‰CD|8†Ø‹1x¨°Z\ìø—\âoC\'ñf\ß\á\Ò`JJB<³û¶&õ\æ5”*56«•aóVófß‘|6w{\Ö-Gï¡—%ş\nÁ¦,øUdöÖƒxúø2­{#nE_’\éÔ˜c\â­eŸó8Ù“˜F\ÏW0Yl„xs\äõº¬&\æ¾Ó‘sGJª[©\Î\Ë\Ì\Üü£\Ôı4±9]ô;y…¯®%V\Åg\rª1¸RYnF_dNÿ6d¥¥`5;¨X½:S\Ö\ïyhBQ¬9¾w\'&c\r[w\ÂÃ»T‘»\Ïÿz\Õt\î:\Ìyf\ÊW©&=©\\pe.G`\Å\Ø>$\Å\ÜÀà¥‘Æ«Û´-¦.¡|¥¿<ñs%\Æ\\aV\ß7\ÈLM–\Å\Ô\ép1lÁ:9)ıõÀ–\ìŠ7Y\Ü]N\'nJ%y\Ù&šuy›	o#7\ã.³ú¶\âF\ÔY´z½|¯\í€aŸÿ)ŠG\'¹÷n±\è\ÒM¦œ»764¬Æ 2lœÆ®OE³\n5^iÌ‡÷¡s÷x* yc\"c8r;\n¥<\ØŞ°\Zş\Z%[–L\å\ëU¥.\ÑÇŒY¶‘&N\í\Åä«•³ùr\É^lÔ”\Ú\r›Q¶b^Ş’¶%\Ä\\\á\àöÙŒ)\Õ*\Ù¿üz+¦®ß\Õj‘=Ì™C‡ğò\ÕK\æ¤\Ò\èhĞ¦3å‚«`\ÊÍ‘M¢ÌN—Lc\r\Ût¢\æ+Íˆ¿r9Ú–*Ì›RÅ¨%büÑ“´”$\æ\rl\'S¦`+¢pŠ(v:]›·†Ö½†’“‘\Îü!¸\Zy»œL#¯w\ï\Íè¥Ÿ\Ëø8±:L>\ÇòñT÷ó\ä`Ë¿b»ô\Ó¶\';-À`fn9H`Å¢=Õƒú_K¶9È¶;ñR©T»qõ\ì¯,~·+)q	¨´\nzOœK·÷¦!?\Å\ä\Ğ×›	¯?\ÂvJYÄµ²fZŒbF¥”¯‰¢\çp¸µ$‚V=‡púğ~iR¥D©#qU7ùô\Ìbræ®\ï‰ 6»®#\'\ËğÑ«\Æ\ìyia\ÄQK7Ñ¨]W©fû\Ê9lşh&>Z\n%£Q²«7\í—}’\èŸ\æl\'I»§»œ¥5\íÜƒ1\Ë6\É\éÂ“D€2\å|\á\â\è_½_Ô¯\Â\Æ£ø\Ç:ÁŒò\" |v~<_FŠH•ŠxE«R rsCL\é­.7â®œ\çú¹K¸{k\é9\îCºŒø@•br\'\é&\á£{s\æğqtPŠr\â&üVüSH/†x\n•\íf\à\ÔE².D\ŞÇ¼\íp\Ø\î\í¹wzÁ”¸\à2\"„\í¶ü±È¤5›iŞ¹/ù,«=gŸLN«W1qõ6^k\×Mnµ\ë£\áİ‰:ù;z=X\Ì\Ğm\Ì8†|¸\\¾/z¥\Õc\ï\Æ\ÍxxA^64\é\Ô^ö9:½\á©)G\å£K·˜u!qu«ğ\Ş\Äò\áoqñ\ÔE\ê·nÂ iá¬6“û£C„¢˜<¬__Z\éóş<ùù$\ÅDls¢«pşØ¿\È\ÎH‘\Ã1qŠ\È\ïzOo‚ª†Ò²G?šv\ì]Ø¹›rsù\æ\Ó\Åü²÷[\\NK\á¤óñ^¡¦n³–¼=Nx}~Ñ¼rú_­˜/\ÏF2k)u›Ü§\Æbòº=|ñ—\Ï\ã\å\ç\Ç\èe®V§P}Âµ\Ël]1›¸‹gewır\Ë6¼3+ü™€(\Ø‡}·3S§\n†s‡Y4ª/I±·\é?u2¯÷\Ä\çs\'‘{\rµ\æA\Â#\ĞD£ ô]²Ÿ}‹ˆüJO\é_d\ê.\Ë*¸ `U·ã®’{Ü¬t™ÿ\r^…P®be<\Ã>\Ä\é)‰²~y,ú\02¢yóñÌŸ?= bbœ“™&#\Ñ\Ë\×­t\Éûb6æ‘ŠZ«§”\Ù\"gµ˜ew£1Œ|ğ\ëšO\rñx\Ã\áD¯»RÁ/{w°úı‘¤§¦1bÁRZõzGÎµ\Ä3…ü tŞ8ó>\"ùFw›|®”õ¬KÿzÿÔ»ødò»¤&$\Óc\ì\ê¼\ÖR¦l\áHv«\èƒl\Ô~­>\Å!•\0òğ\è3\'‰˜1³GN\á\îj­B’ñ„ ı wy{\Ül¼ıŠ~¥8Gÿ¡”U…ÿ_\Öd¤Ş–\ãô\Û#\È\Ër\äGqÒ¦\Ï;hóyN)\ä9\rWP€\ì\ØÀ™ŸOı}>ş<ªK\0y«ı‰{J\0ùû<ªK\0y«ı‰{J\0ùû<ªHÁ·5GaÉ\Ï%¿1ü÷\ì÷§\î.ù\áŸj\Ş?®üÿ\0V\à\Ìş¡‡<ª\0\0\0\0IEND®B`‚','image/png',NULL,'2023-05-25 15:32:03.719000','2023-05-25 15:32:32.926237','admin-louah','admin-louah',NULL),(2,'ABAY BANK  S.C','ABAY ',NULL,NULL,'https://abaybank.com.et/','2023-05-25 15:34:54.113331','2023-05-25 15:34:54.113331','admin-louah','admin-louah',NULL),(3,'Groupe CIH BANK ','CIH',NULL,NULL,'https://www.cihbank.ma/','2023-06-28 15:15:15.818000','2023-06-28 15:52:31.077660','admin-louah','admin-louah',NULL),(4,'ONDA','ONDA',NULL,NULL,NULL,'2023-06-28 15:17:42.784790','2023-06-28 15:17:42.784790','admin-louah','admin-louah',NULL),(5,'CDG CAPITAL','CDG CAPITAL',NULL,NULL,NULL,'2023-06-28 15:43:02.987103','2023-06-28 15:43:02.987103','admin-louah','admin-louah',NULL),(6,'ONCF','ONCF',NULL,NULL,NULL,'2023-06-28 15:51:30.177770','2023-06-28 15:51:30.177770','admin-louah','admin-louah',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 18:21:45
