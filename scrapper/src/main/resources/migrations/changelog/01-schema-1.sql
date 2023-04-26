create table tg_chat
(
    chat_id    bigint      not null,
    created_at timestamptz not null default now(),

    constraint chat_id primary key (chat_id)
);


create table link
(
    link_id    serial      not null,
    tg_chat_id bigint      not null,
    url        text        not null,
    created_at timestamptz not null default now(),

    constraint link_id primary key (link_id),
    constraint fk_tg_chat foreign key (tg_chat_id) references tg_chat (chat_id) on delete cascade on update cascade
);
