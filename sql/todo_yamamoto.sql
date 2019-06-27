create table todo_yamamoto(
	id serial,
	title varchar(100) not null,
	details text,
	value int default '1',
	limitdate date
);

insert into todo_yamamoto(title,details,value,limitdate) 
values 
('ƒeƒXƒg1',null,'1','2019/07/01'),
('•¶š”100‚Ü‚Å','Ú×—“','3','2019/06/27'),
('‚Ä‚·‚Æ2','00000000','1','2019/06/27');