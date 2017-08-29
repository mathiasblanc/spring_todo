use cdtilde;

grant all privileges on cdtilde.* to 'test'@'localhost' with grant option;

create table todo (
	id bigint not null auto_increment,
    name nvarchar(50) not null,
    description nvarchar(1000),
    created_at timestamp not null,
    done_at timestamp null,
	primary key (id)
);

desc todo;




