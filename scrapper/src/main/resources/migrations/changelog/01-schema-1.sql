create type status as enum('registered', 'deleted');

create table tg_chat
(
    chat_id        bigint    not null,
    tg_chat_status status    not null default 'registered',
    created_at     timestamp not null default now(),
    deleted_at     timestamp,

    constraint chat_id primary key (chat_id)
);


create table link
(
    link_id     bigint    not null,
    tg_chat_id  bigint    not null,
    url         text      not null,
    link_status status    not null default 'registered',
    created_at  timestamp not null default now(),
    deleted_at  timestamp,

    constraint link_id primary key (link_id),
    constraint fk_tg_chat foreign key (tg_chat_id) references tg_chat (chat_id)
);
