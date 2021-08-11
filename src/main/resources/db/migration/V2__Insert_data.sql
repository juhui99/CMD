insert into users ( `user_id`,
                    `user_pwd`,
                    `user_name`,
                    `user_age`,
                    `user_major`,
                    `user_email`,
                    `user_gender`,
                    `user_profile`,
                    `user_manager`)
values('aaaa', '0a0a','이주희', '23', 'DBA', 'aaaa@gmail.com', 'W', 'profile',1);

insert into users ( `user_id`,
                    `user_pwd`,
                    `user_name`,
                    `user_age`,
                    `user_major`,
                    `user_email`,
                    `user_gender`,
                    `user_profile`,
                    `user_manager`)
values('ehfehfdl0927', '0927','김주희', '23', 'Backend', 'bbbb@gmail.com', 'W', 'profile',1);


insert into survey (`user_index`,
                    `survey_title`,
                    `survey_duplicate`)
values(0, '많이 사용하는 언어',2);

insert into board       (`subject`,
                       `content`,
                       `writer`,
                       `reg_date`,
                       `hit`,
                       `notice`,
                       `kind`,
                       `realm`,
                       `recommend`,
                       `delete_option`)
values('글제목','글내용1입니다.','ehfehfdl0927',now(),0,0,'자유게시판','Backend',0,0);
insert into board       (`subject`,
                       `content`,
                       `writer`,
                       `reg_date`,
                       `hit`,
                       `notice`,
                       `kind`,
                       `realm`,
                       `recommend`,
                       `delete_option`)
values('글제목2','글내용2입니다.안녕하세요.','aaaa',now(),0,0,'정보팁게시판','Frontend',0,0);













-- insert into survey_item ( `survey_item_index`,
--                     `survey_index`,
--                     `survey_item_content`)
--
-- insert into survey_result ( `survey_result_index`,
--                     `survey_index`,
--                     `survey_item_index`,
--                     `survey_result`,
--                     `survey_result_reg_date`)