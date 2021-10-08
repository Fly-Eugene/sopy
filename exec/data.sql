USE ssafy_db;
INSERT INTO authority (authority_name) values("ROLE_ADMIN");
INSERT INTO authority (authority_name) values("ROLE_USER");

INSERT INTO ssafy_db.image (dtype,image_name,image_org_name,`path`,thumbnail) VALUES
	 ('book','95b56614-5e65-4ca0-b79e-d71f41830c9f.jpg','죽고 싶지만 떡볶이는 먹고 싶어.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_95b56614-5e65-4ca0-b79e-d71f41830c9f.jpg'),
	 ('book','e6f2d61d-406c-4cc8-bbcb-48cdcd8d6daf.jpg','라이언, 내 곁에 있어줘_표지.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_e6f2d61d-406c-4cc8-bbcb-48cdcd8d6daf.jpg'),
	 ('book','ef3be20b-c564-41a0-8e53-68c0dfe8ecf0.jpg','1cm.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_ef3be20b-c564-41a0-8e53-68c0dfe8ecf0.jpg'),
	 ('book','23d0e0b5-5d33-4eeb-a237-330290a911eb.png','너의 이름은.png','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_23d0e0b5-5d33-4eeb-a237-330290a911eb.png'),
	 ('book','e6875c14-61bc-448c-b430-ba735b852615.jpg','도깨비.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_e6875c14-61bc-448c-b430-ba735b852615.jpg'),
	 ('book','e8d41b97-e24a-4ac8-93f2-226b935797f7.jpg','미스터 선샤인.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_e8d41b97-e24a-4ac8-93f2-226b935797f7.jpg'),
	 ('book','cbc510cb-b5c5-47ae-b7a9-e786df3f7444.png','응답하라 1994.png','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_cbc510cb-b5c5-47ae-b7a9-e786df3f7444.png'),
	 ('book','5bc32178-c638-4457-883e-4a76dae00b02.png','비타민 한국어 1.png','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_5bc32178-c638-4457-883e-4a76dae00b02.png'),
	 ('book','807d723f-efbc-4c56-be11-9a44529297fa.jpg','비타민 한국어 2.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_807d723f-efbc-4c56-be11-9a44529297fa.jpg'),
	 ('book','fe1bf705-f6d0-4664-97c7-4f7f3882f3ac.jpg','비타민 한국어 3.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_fe1bf705-f6d0-4664-97c7-4f7f3882f3ac.jpg');
INSERT INTO ssafy_db.image (dtype,image_name,image_org_name,`path`,thumbnail) VALUES
	 ('book','27e28b9f-0ce9-45b3-bff4-573ba9186f24.jpg','도깨비.jpg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C07/','thumb_27e28b9f-0ce9-45b3-bff4-573ba9186f24.jpg'),
	 ('book','7696578f-edd9-4da0-8907-aefdc559837e.png','토끼전.png','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C08/','thumb_7696578f-edd9-4da0-8907-aefdc559837e.png'),
	 ('user','3aaa278c-b01f-4339-891f-6b421b845f96.jpeg','test.jpeg','https://s3.ap-northeast-2.amazonaws.com/sopy/c%3A%5Csopy%5Cupload%5C2021%5C10%5C08/','thumb_3aaa278c-b01f-4339-891f-6b421b845f96.jpeg');

INSERT INTO ssafy_db.book_image (image_id) VALUES
	 (1),
	 (2),
	 (3),
	 (4),
	 (5),
	 (6),
	 (7),
	 (8),
	 (9),
	 (10);
INSERT INTO ssafy_db.book_image (image_id) VALUES
	 (11),
	 (12);

INSERT INTO ssafy_db.book (author,dir_path,genre,introduce,page_size,published_date,publisher,title,translator,image_id) VALUES
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a','철학','소개',11,NULL,'출판사','죽고 싶지만 떡볶이는 먹고 싶어','번역가
',1),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997','철학','소개',12,NULL,'출판사','라이언, 내 곁에 있어줘','번역가
',2),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c','소설','소개',10,NULL,'출판사','1cm','번역가
',3),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9','소설','소개',10,NULL,'출판사','너의 이름은','번역가
',4),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507','소설','소개',9,NULL,'출판사','도깨비','번역가
',5),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99','소설','소개',11,NULL,'출판사','미스터 선샤인','번역가
',6),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540','소설','소개',6,NULL,'출판사','응답하라 1994','번역가
',7),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150','역사','소개',10,NULL,'출판사','비타민 한국어 1','번역가
',8),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\07\\8527061d-03c9-489e-8899-78a36970fc83','역사','소개',1,NULL,'출판사','비타민 한국어 2','번역가
',9),
	 (NULL,'c:\\sopy\\upload\\2021\\10\\08\\1a63197f-50f6-4c63-b577-a82061e7da65','소설','소개',11,NULL,'출판사','토끼전','번역가
',12);


INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (271472,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.008000000','1.png',1),
	 (6555220,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.019000000','10.png',1),
	 (6117034,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.025000000','11.png',1),
	 (528163,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.028000000','2.png',1),
	 (5487182,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.031000000','3.png',1),
	 (639260,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.033000000','4.png',1),
	 (1100291,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.034000000','5.png',1),
	 (4470966,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.038000000','6.png',1),
	 (6377958,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.040000000','7.png',1),
	 (6914816,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.045000000','8.png',1);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (5790448,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\8f98ac8a-bfae-47ba-b254-84910700196a\\img','2021-10-07 20:08:57.050000000','9.png',1),
	 (159641,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.182000000','1.png',2),
	 (84224,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.186000000','10.png',2),
	 (312839,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.187000000','11.png',2),
	 (567917,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.188000000','12.png',2),
	 (433160,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.189000000','2.png',2),
	 (202769,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.191000000','3.png',2),
	 (31809,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.192000000','4.png',2),
	 (318155,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.195000000','5.png',2),
	 (28403,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.197000000','6.png',2);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (300084,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.199000000','7.png',2),
	 (30122,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.200000000','8.png',2),
	 (295654,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\06a59b46-b484-4920-94a9-095259f75997\\img','2021-10-07 20:57:44.200000000','9.png',2),
	 (8292902,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.099000000','1.png',3),
	 (2049533,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.103000000','10.png',3),
	 (7084750,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.106000000','2.png',3),
	 (2046287,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.108000000','3.png',3),
	 (4323278,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.110000000','4.png',3),
	 (1773728,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.114000000','5.png',3),
	 (1229993,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.115000000','6.png',3);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (6774369,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.116000000','7.png',3),
	 (7104880,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.119000000','8.png',3),
	 (4942356,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\e120f34b-5dca-46e4-8e94-962cfa46dd9c\\img','2021-10-07 21:28:55.121000000','9.png',3),
	 (777313,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.798000000','1.png',4),
	 (1045704,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.802000000','10.png',4),
	 (578344,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.804000000','2.png',4),
	 (5668693,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.805000000','3.png',4),
	 (851246,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.806000000','4.png',4),
	 (899766,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.807000000','5.png',4),
	 (1020015,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.808000000','6.png',4);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (1010893,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.809000000','7.png',4),
	 (1075094,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.811000000','8.png',4),
	 (1037670,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\58354a2d-eb7a-4338-a3d8-5a19cdd310a9\\img','2021-10-07 21:37:48.814000000','9.png',4),
	 (3276202,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.572000000','1.png',5),
	 (7656698,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.584000000','2.png',5),
	 (11027563,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.586000000','3.png',5),
	 (10986780,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.588000000','4.png',5),
	 (11235027,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.590000000','5.png',5),
	 (11878241,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.591000000','6.png',5),
	 (10350022,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.593000000','7.png',5);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (11370564,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.596000000','8.png',5),
	 (10489565,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\40b6a8ee-9ef3-4190-8bf4-5874e2269507\\img','2021-10-07 21:53:38.599000000','9.png',5),
	 (1721192,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.841000000','1.png',6),
	 (2231745,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.847000000','10.png',6),
	 (3012133,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.850000000','11.png',6),
	 (3242966,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.852000000','2.png',6),
	 (2463652,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.854000000','3.png',6),
	 (2794500,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.857000000','4.png',6),
	 (1872351,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.860000000','5.png',6),
	 (1488242,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.862000000','6.png',6);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (2932127,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.864000000','7.png',6),
	 (3331041,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.868000000','8.png',6),
	 (2792741,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d764da49-edb0-4050-8895-600b0dcb8b99\\img','2021-10-07 22:15:37.871000000','9.png',6),
	 (2926806,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.903000000','1.png',7),
	 (3402040,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.908000000','2.png',7),
	 (4011644,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.909000000','3.png',7),
	 (3598625,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.911000000','4.png',7),
	 (4129819,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.912000000','5.png',7),
	 (1308350,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\d6e6d79a-8485-487b-b8b2-e529bde6d540\\img','2021-10-07 22:35:34.914000000','6.png',7),
	 (399554,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.656000000','1.png',8);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (108803,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.662000000','10.png',8),
	 (1003547,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.664000000','2.png',8),
	 (1105738,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.665000000','3.png',8),
	 (664568,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.665000000','4.png',8),
	 (2136140,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.667000000','5.png',8),
	 (378831,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.670000000','6.png',8),
	 (553999,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.672000000','7.png',8),
	 (492473,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.674000000','8.png',8),
	 (186995,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\1677a313-9825-432a-bc3b-657b09847150\\img','2021-10-07 22:45:24.676000000','9.png',8),
	 (547179,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.666000000','1.png',9);
INSERT INTO ssafy_db.files (file_size,file_type,org_name,`path`,reg_time,sys_name,book_id) VALUES
	 (910125,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.672000000','10.png',9),
	 (820784,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.674000000','2.png',9),
	 (897932,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.675000000','3.png',9),
	 (628614,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.675000000','4.png',9),
	 (1030607,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.676000000','5.png',9),
	 (818350,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.677000000','6.png',9),
	 (280312,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.678000000','7.png',9),
	 (628144,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.678000000','8.png',9),
	 (1536787,'.png',NULL,'c:\\sopy\\upload\\2021\\10\\07\\a552f27f-ebd6-4827-a03f-cbba3071fb38\\img','2021-10-07 23:35:08.679000000','9.png',9);

INSERT INTO ssafy_db.user_image (image_id) VALUES
	 (13);

INSERT INTO ssafy_db.users (age,department,email,password,username,image_id) VALUES
	 (40,'ssafy','test@naver.com','$2a$10$P/O9D8whl4w19a/RdjV1r./vkXiBrTpj18ZNXQpLQJ4ZKN/P/pcH2','김유저',13);

INSERT INTO ssafy_db.user_authority (user_id,authority_name) VALUES
	 (1,'ROLE_USER');

INSERT INTO ssafy_db.comment (content,book_id,user_id) VALUES
	 ('안녕하세요',1,1),
	 ('오늘',1,1),
	 ('처음왔어요',1,1);
