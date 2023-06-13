create table User
(
    user_pk    MEDIUMINT     NOT NULL AUTO_INCREMENT,
    first_name varchar(1000) NOT NULL,
    last_name  varchar(1000) NOT NULL,
    image_path varchar(4000),
    about      varchar(300),
    is_active  boolean default true,
    PRIMARY KEY (user_pk)
);



create table Role
(
    role_pk   MEDIUMINT    NOT NULL AUTO_INCREMENT,
    role_name varchar(100) NOT NULL,
    PRIMARY KEY (role_pk)
);


create table User_Role
(
    user_role_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    user_fk      MEDIUMINT,
    role_fk      MEDIUMINT,
    PRIMARY KEY (user_role_pk),
    FOREIGN KEY (user_fk) REFERENCES User (user_pk) ON DELETE CASCADE,
    FOREIGN KEY (role_fk) REFERENCES Role (role_pk) ON DELETE CASCADE
);

create table Account
(
    account_pk    MEDIUMINT    NOT NULL AUTO_INCREMENT,
    email         varchar(200) NOT NULL,
    phone_number  varchar(50)  NOT NULL,
    password_hash varchar(1000),
    user_id_fk    MEDIUMINT,
    is_Active     boolean   DEFAULT true,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UC_Account UNIQUE (email, phone_number),
    PRIMARY KEY (account_pk),
    FOREIGN KEY (user_id_fk) REFERENCES User (user_pk) ON DELETE CASCADE
);



create table Car
(
    car_pk        MEDIUMINT NOT NULL AUTO_INCREMENT,
    color         varchar(1000),
    make          varchar(1000),
    model         varchar(1000),
    model_year    integer,
    license_plate varchar(30),
    user_id_fk    MEDIUMINT,
    CONSTRAINT UC_Car UNIQUE (license_plate),
    PRIMARY KEY (car_pk),
    FOREIGN KEY (user_id_fk) REFERENCES User (user_pk) ON DELETE CASCADE
);

create table Car_Images
(
    id_pk      MEDIUMINT NOT NULL AUTO_INCREMENT,
    image_path varchar(4000),
    car_fk     MEDIUMINT,
    PRIMARY KEY (id_pk),
    FOREIGN KEY (car_fk) REFERENCES Car (car_pk) ON DELETE CASCADE
);

create table Travel_Preference
(
    id_pk       MEDIUMINT NOT NULL AUTO_INCREMENT,
    description varchar(4000),
    PRIMARY KEY (id_pk)
);


create table User_Preference
(
    id_pk                MEDIUMINT NOT NULL AUTO_INCREMENT,
    user_id_fk           MEDIUMINT,
    travel_preference_fk MEDIUMINT,
    PRIMARY KEY (id_pk),
    FOREIGN KEY (user_id_fk) REFERENCES User (user_pk) ON DELETE CASCADE,
    FOREIGN KEY (travel_preference_fk) REFERENCES Travel_Preference (id_pk) ON DELETE CASCADE

);

create table Page
(
    page_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (page_pk)
);

create table Contact_Us
(
    id_pk       MEDIUMINT NOT NULL AUTO_INCREMENT,
    email       varchar(4000),
    description varchar(4000),
    PRIMARY KEY (id_pk)
);

create table Ride
(
    ride_pk MEDIUMINT    NOT NULL AUTO_INCREMENT,
    user_fk MEDIUMINT,
    `from`  varchar(768) NOT NULL,
    `to`    varchar(768) NOT NULL,
    `date`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    seats   INT          NOT NULL,
    cost    FLOAT        NOT NULL,
    FOREIGN KEY (user_fk) REFERENCES User (user_pk) ON DELETE CASCADE,
    PRIMARY KEY (ride_pk)
);

create table Report
(
    report_pk     MEDIUMINT     NOT NULL AUTO_INCREMENT,
    ride_fk       MEDIUMINT,
    title         varchar(250)  NOT NULL,
    `description` varchar(1000) NOT NULL,
    `date`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_fk       MEDIUMINT,
    is_solved     boolean   default false,

    FOREIGN KEY (ride_fk) REFERENCES Ride (ride_pk) ON DELETE CASCADE,
    FOREIGN KEY (user_fk) REFERENCES User (user_pk) ON DELETE CASCADE,
    PRIMARY KEY (report_pk)
);

create table Passenger
(
    passenger_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    user_fk      MEDIUMINT,
    ride_fk      MEDIUMINT,
    FOREIGN KEY (ride_fk)
        REFERENCES Ride (ride_pk)
        ON DELETE CASCADE,
    FOREIGN KEY (user_fk)
        REFERENCES User (user_pk)
        ON DELETE CASCADE,
    PRIMARY KEY (passenger_pk)
);

create table Rate
(
    rate_pk    MEDIUMINT NOT NULL AUTO_INCREMENT,
    date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rater_fk   MEDIUMINT,
    subject_fk MEDIUMINT,
    rate       int,
    FOREIGN KEY (rater_fk)
        REFERENCES User (user_pk)
        ON DELETE CASCADE,
    FOREIGN KEY (subject_fk)
        REFERENCES User (user_pk)
        ON DELETE CASCADE,
    PRIMARY KEY (rate_pk)

);

create table Page_Content
(
    page_content_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    page_fk         MEDIUMINT,
    content         varchar(4000),
    FOREIGN KEY (page_fk) REFERENCES Page (page_pk) ON DELETE CASCADE,
    PRIMARY KEY (page_content_pk)

);

create table Page_Video
(
    page_video_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    page_fk       MEDIUMINT,
    video_path    varchar(4000),
    FOREIGN KEY (page_fk) REFERENCES Page (page_pk) ON DELETE CASCADE,
    PRIMARY KEY (page_video_pk)
);

create table Page_Image
(
    page_image_pk MEDIUMINT NOT NULL AUTO_INCREMENT,
    image_path    varchar(4000),
    page_fk       MEDIUMINT,
    PRIMARY KEY (page_image_pk),
    FOREIGN KEY (page_fk) REFERENCES Page (page_pk) ON DELETE CASCADE
);


#-----------------RESTING AUTO_INCREMENT FOR ALL TABLES-----------------#
alter table Account
    AUTO_INCREMENT = 1;
alter table Car
    AUTO_INCREMENT = 1;
alter table Car_Images
    AUTO_INCREMENT = 1;
alter table Contact_Us
    AUTO_INCREMENT = 1;

alter table Page
    AUTO_INCREMENT = 1;
alter table Page_Content
    AUTO_INCREMENT = 1;
alter table Page_Image
    AUTO_INCREMENT = 1;
alter table Page_Video
    AUTO_INCREMENT = 1;

alter table Passenger
    AUTO_INCREMENT = 1;
alter table Rate
    AUTO_INCREMENT = 1;
alter table Report
    AUTO_INCREMENT = 1;
alter table Ride
    AUTO_INCREMENT = 1;

alter table Travel_Preference
    AUTO_INCREMENT = 1;
alter table User
    AUTO_INCREMENT = 1;
alter table User_Preference
    AUTO_INCREMENT = 1;
alter table User_Role
    AUTO_INCREMENT = 1;

alter table Role
    AUTO_INCREMENT = 1;
#-----------------END-----------------#


#-----------------DROPPING ALL TABLES-----------------#
drop table Account;
drop table Car;
drop table Car_Images;
drop table Contact_Us;

drop table Page;
drop table Page_Content;
drop table Page_Image;
drop table Page_Video;


drop table Passenger;
drop table Rate;
drop table Report;
drop table Ride;

drop table Role;
drop table Travel_Preference;
drop table User;
drop table User_Preference;

drop table User_Role;
#-----------------END-----------------#


#-----------------INDEXING FOR TABLES-----------------#
#ACCOUNT TABLE
CREATE INDEX idx_email ON Account (email);
CREATE INDEX idx_phone_number ON Account (phone_number);

#RIDE TABLE
CREATE INDEX idx_user_fk ON Ride (user_fk);
CREATE INDEX idx_from ON Ride (`from`);
CREATE INDEX idx_to ON Ride (`to`);
CREATE INDEX idx_date ON Ride (`date`);

#REPORT TABLE
CREATE INDEX idx_ride_fk ON Report (ride_fk);
CREATE INDEX idx_user_fk ON Report (user_fk);

#RATER TABLE
CREATE INDEX idx_rater_fk ON Rate (rater_fk);
CREATE INDEX idx_subject_fk ON Rate (subject_fk);
#-----------------END-----------------#

#-----------------Tables Alteration-----------------#
ALTER TABLE Ride
    ADD max_two_in_the_back boolean DEFAULT FALSE;
ALTER TABLE Ride
    ADD is_instant_booking boolean DEFAULT FALSE;
ALTER TABLE Passenger
    ADD is_Accepted boolean DEFAULT TRUE;

ALTER TABLE User
    ADD gender varchar(50);


ALTER TABLE Ride
    ADD status ENUM (''ACTIVE'', ''CANCELED'', ''COMPLETED'') DEFAULT ''active'';

ALTER TABLE Ride
    ADD car_fk MEDIUMINT;

ALTER TABLE Ride
    ADD arrivalTime TIME;

ALTER TABLE Ride
    ADD departureTime TIME;


ALTER TABLE Ride
    ADD CONSTRAINT ride_car_fk
        FOREIGN KEY (car_fk)
            REFERENCES Car (car_pk);
#-----------------END-----------------#