create table mainlist(
	id serial,
	title varchar(100) not null,
	details text,
	value int default '1',
	limitdate date,
	did  varchar(1) not null default '1'
);

insert into mainlist(title,details,value,limitdate)
values
('テスト1',null,'1','2019/07/01'),
('文字数100まで','詳細欄','3','2019/06/27'),
('てすと2','00000000','1','2019/06/27');