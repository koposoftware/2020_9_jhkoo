--testing query
insert into member(id, name, password, phone, email, address, age_group, gender, property_status, job_key)
  		values('test', 'test', '1234', '010-1111-2222', 'abc@abc.com', 'aassdd', '10대', '여', '5천만원 미만', '주부');
SELECT * FROM `member`;
DROP TABLE `missiondb`.`member`;
--------------------member table -----------------------
CREATE TABLE `missiondb`.`member` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `age_group` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `property_status` VARCHAR(45) NOT NULL,
  `job_key` VARCHAR(45) NOT NULL,
  `cash` INT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- 'type' varchar

------------------cash_manage table-----------------------

CREATE TABLE `missiondb`.`cash_manage` (
  `no` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `cash` INT NOT NULL,
  `log_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



------------------user_challenge table--------------------

CREATE TABLE `missiondb`.`user_challenge` (
  `challenge_pk` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `challenge_name` VARCHAR(45) NOT NULL,
  `challenge_end_date` VARCHAR(45) NOT NULL,
  `challenge_type` VARCHAR(45) NOT NULL,
  `target_amount` INT NOT NULL,
  `nowBalanceByType` INT,
  PRIMARY KEY (`challenge_pk`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_as_ci;


------------------favorite_account table-------------------
CREATE TABLE `missiondb`.`favorite_account` (
  `no` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `to_account_number` VARCHAR(45) NOT NULL,
  `to_name` VARCHAR(45) NOT NULL,
  `favorite_flag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-------------------friend_request table--------------------
CREATE TABLE `missiondb`.`friend_request` (
  `no` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `friend_id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `reg_date` VARCHAR(45) NOT NULL,
  `agree_flag` VARCHAR(45) NOT NULL,
  `friend_name` VARCHAR(45) NOT NULL,
  `expenditure_this_month` INT NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


---------------------mail table-------------------------------
CREATE TABLE `missiondb`.`mail` (
  `no` INT NOT NULL, --uncertain, github imgには存在 > VOには無い 
  `id` VARCHAR(45) NOT NULL,
  `to_mail` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;




----------------------dw_account-------------------------
CREATE TABLE `missiondb`.`dw_account` (
  `account_number` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `account_password` VARCHAR(45) NOT NULL,
  `balance` INT NOT NULL,
  `bank_book_key` VARCHAR(45) NOT NULL,
  `nick_name` VARCHAR(45) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL,
  `main_account` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
-----------------------------------------------


---------------savings_account-----------------

CREATE TABLE `missiondb`.`savings_account` (
  `account_number` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `account_password` VARCHAR(45) NOT NULL,
  `balance` INT NOT NULL,
  `bank_book_key` VARCHAR(45) NOT NULL,
  `nick_name` VARCHAR(45) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL,
  `saving_day` INT NOT NULL,
  `saving_date` VARCHAR(45) NOT NULL,
  `auto_saving` VARCHAR(45) NOT NULL,
  `auto_saving_bool` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`account_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
-----------------------------------------------

---------------auto_transfer-------------------
CREATE TABLE `missiondb`.`auto_transfer` (
  `log_pk` INT NOT NULL,          //uncertain
  `account_number` VARCHAR(45) NOT NULL,
  `my_name` VARCHAR(45) NOT NULL,
  `to_type` VARCHAR(45) NOT NULL,
  `to_name` VARCHAR(45) NOT NULL,
  `to_account_number` VARCHAR(45) NOT NULL,
  `to_amount` INT NOT NULL,
  `auto_trans_day` INT NOT NULL,
  PRIMARY KEY (`log_pk`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
-----------------------------------------------

---------------dw_account_log------------------
CREATE TABLE `missiondb`.`dw_account_log` (
  `log_pk` INT NOT NULL,
  `account_number` VARCHAR(45) NOT NULL,
  `log_date` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  `log_type_key` VARCHAR(45) NOT NULL,
  `to_account_number` VARCHAR(45) NOT NULL,
  `to_name` VARCHAR(45) NOT NULL,
  `card_number` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`log_pk`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-----------------------------------------------

---savings_account_log-------------------------
CREATE TABLE `missiondb`.`savings_account_log` (
  `log_pk` INT NOT NULL,
  `account_number` VARCHAR(45) NOT NULL,
  `log_date` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`log_pk`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
-----------------------------------------------








