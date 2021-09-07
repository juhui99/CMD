CREATE TABLE `users` (
                         `user_index` int NOT NULL AUTO_INCREMENT,
                         `user_id` varchar(20) NOT NULL,
                         `user_pwd` varchar(20) NOT NULL,
                         `user_name` varchar(80) DEFAULT NULL,
                         `user_age` int NOT NULL DEFAULT 0,
                         `user_major` varchar(20) DEFAULT 'non',
                         `user_email` varchar(200) DEFAULT NULL,
                         `user_gender` varchar(20) DEFAULT 'non',
                         `user_profile` varchar(200) DEFAULT 'default.png',
                         `user_manager` int NOT NULL DEFAULT 0,
                         `user_withdrawal` int NOT NULL DEFAULT 0,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `survey` (
                         `survey_index` int NOT NULL AUTO_INCREMENT,
                         `user_index` int NOT NULL,
                         `survey_title` varchar(200) NOT NULL,
                         `survey_content` varchar(1000) DEFAULT NULL,
                         `survey_reg_date` datetime NOT NULL DEFAULT NOW(),
                         `survey_end_date` datetime DEFAULT NULL,
                         `survey_count` int DEFAULT NULL,
                         `survey_duplicate` int DEFAULT NULL,
                         PRIMARY KEY (`survey_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# CREATE TABLE `survey_item` (
#                           `survey_item_index` int NOT NULL AUTO_INCREMENT,
#                           `survey_index` int NOT NULL,
#                           `survey_item_content` varchar(1000) DEFAULT NULL,
#                           PRIMARY KEY (`survey_item_index`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# CREATE TABLE `survey_result` (
#                           `survey_result_index` int NOT NULL AUTO_INCREMENT,
#                           `survey_index` int NOT NULL,
#                           `survey_item_index` int NOT NULL,
#                           `survey_result` varchar(1000) DEFAULT NULL,
#                           `survey_result_reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
#                           PRIMARY KEY (`survey_result_index`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `board` (
     `bno` int NOT NULL AUTO_INCREMENT,
     `subject` varchar(50),
     `content` text,
     `writer` varchar (50) NOT NULL,
     `reg_date` datetime NOT NULL,
     `hit` int NOT NULL DEFAULT 0,
     `notice` varchar(20),
     `kind` varchar(20),
     `realm` varchar(20),
     `recommend` int NOT NULL DEFAULT 0,
     `delete_option` int NOT NULL DEFAULT 0,
      PRIMARY KEY (`bno`)
      );

 CREATE TABLE `board_recommend` (
     `kind` varchar(20),
     `realm` varchar(20),
     `bno` int NOT NULL,
     `user_id` varchar(20),
     FOREIGN KEY(bno) REFERENCES board(bno) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment` (
    `bno` int NOT NULL,
    `c_bno` int NOT NULL AUTO_INCREMENT,
    `c_sequence` int,
    `level` int NOT NULL DEFAULT 0,
    `reg_date` datetime NOT NULL,
    `writer` varchar(20) NOT NULL,
    `content` text,
    `delete_option` int NOT NULL DEFAULT 0,
    FOREIGN KEY(bno) REFERENCES board(bno) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (`c_bno`)

);

CREATE TABLE `file`(
    `f_no` int PRIMARY KEY AUTO_INCREMENT,
    `bno` int NOT NULL,
    `filename` varchar(200),
    `fileOriginName` varchar(300),
    `fileUrl` varchar(500),
    FOREIGN KEY(bno) REFERENCES board(bno) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `friends`(
                       `user_id` varchar(20) NOT NULL,
                       `friend_id` varchar(20) NOT NULL
) ;

CREATE TABLE `chat`(
    `chat_index` int NOT NULL AUTO_INCREMENT,
    `user_id` varchar(20) NOT NULL,
    `friend_id` varchar(20) NOT NULL,
    `chat_content` varchar(500),
    `chat_time` datetime,
    `chat_read` int,
    PRIMARY KEY (`chat_index`)
);

CREATE TABLE `search` (
    `search_index` int NOT NULL AUTO_INCREMENT,
    `keyword` varchar(100),
    `search_date` datetime,
    PRIMARY KEY (`search_index`)
);




