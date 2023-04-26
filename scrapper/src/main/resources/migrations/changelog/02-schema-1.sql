alter table link
    add column updated_at timestamptz not null default now();


create table github_link_info
(
    id                serial      not null,
    link_id           bigint      not null,
    name              text        not null,
    owner_name        text        not null,
    fork              boolean     not null,
    forks_count       bigint      not null,
    watchers_count    bigint      not null,
    open_issues_count bigint      not null,
    pushed_at         timestamptz,
    created_at        timestamptz not null,

    constraint github_link_info_id primary key (id),
    constraint fk_link foreign key (link_id) references link (link_id) on delete cascade on update cascade
);


create table stackoverflow_link_info
(
    id            serial      not null,
    question_id   bigint      not null,
    link_id       bigint      not null,
    tags          text[],
    is_answered   boolean     not null,
    view_count    bigint      not null,
    answer_count  bigint      not null,
    score         int         not null,
    creation_date timestamptz not null,
    title         text        not null,

    constraint stackoverflow_link_info_id primary key (id),
    constraint fk_link foreign key (link_id) references link (link_id) on delete cascade on update cascade
);