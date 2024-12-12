CREATE TABLE IF NOT EXISTS member
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '회원 ID',
    `name`  VARCHAR(64)  NOT NULL COMMENT '이름',
    `age`   INT          NOT NULL COMMENT '나이',
    `email` VARCHAR(512) NOT NULL COMMENT '이메일'
);

CREATE TABLE IF NOT EXISTS board
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '게시판 ID',
    `board_name` VARCHAR(100) NOT NULL COMMENT '게시판 이름',
    `status`     VARCHAR(50)  NOT NULL COMMENT '게시판 상태'
);

CREATE TABLE IF NOT EXISTS post
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 ID',
    `board_id`  VARCHAR(64)  NOT NULL COMMENT '게시판 ID',
    `member_name` VARCHAR(50) NOT NULL COMMENT '작성자 이름',
    `password` VARCHAR(4) NOT NULL COMMENT '게시글 비밀번호',
    `email` VARCHAR(100) NOT NULL COMMENT '작성자 이메일',
    `status` VARCHAR(50) NOT NULL COMMENT '게시글 상태',
    `title` VARCHAR(100) NOT NULL COMMENT '게시글 제목',
    `content` TEXT NULL COMMENT '게시글 내용',
    `posted_at` TIMESTAMP NOT NULL COMMENT '게시글 생성 시간'
);

CREATE TABLE IF NOT EXISTS reply
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 ID',
    `post_id`  BIGINT  NOT NULL COMMENT '게시글 ID',
    `user_name`  VARCHAR(50)  NOT NULL COMMENT '작성자 이름',
    `password`  VARCHAR(4)  NOT NULL COMMENT '댓글 비밀번호',
    `status`  VARCHAR(50)  NOT NULL COMMENT '댓글 상태',
    `title`  VARCHAR(100)  NOT NULL COMMENT '댓글 제목',
    `content`  TEXT NOT NULL COMMENT '댓글 내용',
    `replied_at`  TIMESTAMP  NOT NULL COMMENT '댓글 생성 시간'
);
