create table mainlist(
	id serial,
	title varchar(100) not null,
	details text,
	value int default '1',
	limitdate date,
	did  varchar(1) not null default '1',
	personal_id int default null
);

insert into mainlist(title,details,value,limitdate,personal_id)
values
('テスト1',null,'1','2019/07/01','1'),
('文字数100まで','詳細欄','3','2019/06/27','1'),
('てすと2','00000000','1','2019/06/27','1');