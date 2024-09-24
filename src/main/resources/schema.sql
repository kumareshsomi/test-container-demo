create table customers
(
    id    number(19)     GENERATED ALWAYS AS IDENTITY,
    name  varchar2(4000) not null,
    email varchar2(4000) not null,
    primary key (id),
    UNIQUE (email)
);