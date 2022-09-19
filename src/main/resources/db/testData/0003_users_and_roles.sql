insert into app_user (email, password,enabled,locked)
values ('admin@example.com', '{noop}adminpass',true,false),
       ('user@example.com', '{noop}userpass',true,false),
       ('moderator@example.com', '{noop}moderatorpass',true,false);

insert into app_user_role (name, description)
values ('ADMIN', 'pełne uprawnienia'),
       ('USER', 'podstawowe uprawnienia, możliwośc oddawania głosów'),
       ('MODERATOR', 'podstawowe uprawnienia + możliwość zarządzania treściami');

insert into app_user_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);


