create table loginlist(
	id serial,
	email varchar(256) not null,
	pass varchar(64) not null,
	name varchar(50) not null
);

insert into loginlist(email,pass,name)
values("yuichi.sudo@ssie.jp","714a6a422683de8c4f467d101273704e39447f557df6c62baed72426e3417a70","須藤雄一");