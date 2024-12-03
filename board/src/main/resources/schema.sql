CREATE TABLE member
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '회원 ID',
    `name`  VARCHAR(64)  NOT NULL COMMENT '이름',
    `age`   INT          NOT NULL COMMENT '나이',
    `email` VARCHAR(512) NOT NULL COMMENT '이메일'
);