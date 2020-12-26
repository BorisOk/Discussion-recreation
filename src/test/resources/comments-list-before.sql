delete from comment;

insert into comment (id, country, text, user_id)
    values
    (1, 'Armenia', 'text about Armenia', 1),
    (2, 'Belarus', 'text about Belarus', 1),
    (3, 'China', 'first text about China', 1),
    (4, 'China', 'second text about China', 1);

alter sequence hibernate_sequence restart with 7;