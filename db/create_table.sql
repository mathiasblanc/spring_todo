use cdtilde;

grant all privileges on cdtilde.* to 'test'@'localhost' with grant option;
drop table todo;

create table todo (
	id bigint not null auto_increment,
    description nvarchar(120),
    created_at timestamp not null,
    done_at timestamp null,
	primary key (id)
);

desc todo;

select * from todo;

truncate table todo;

delete from todo where id in (5,6,7);

insert into todo(description) values ('Factures ....');







