delete from user_role;
delete from usr;

insert into usr (id, username, password, active)
    values (1,'biba', '123', true), (2,'boba', '123', true);

insert into user_role (user_id, roles)
    values (1,'USER'), (1,'ADMIN'), (2,'USER');
