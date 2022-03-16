CREATE TABLE `t_job`  (
  `id` char(32) NOT NULL,
  `area_id` varchar(10) NOT NULL,
  `build_id` varchar(10) NOT NULL,
  `project_id` varchar(50) NOT NULL,
  `room_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE `t_user`  (
  `id` char(32) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `job_id` char(32) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin;

ALTER TABLE `t_user` ADD FOREIGN KEY (`job_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

