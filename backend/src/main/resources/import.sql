insert into tb_users (first_name) values ('Pedro Lima');
insert into tb_users (first_name) values ('Augusto dos Anjos');
insert into tb_users (first_name) values ('Mario Andreazza');
insert into tb_users (first_name) values ('Alirio Sampaio');

insert into tb_events (name, vacancies, begin_date_time, end_date_time) values ('Evento 1', 10, '2021-09-15 10:00:00', '2021-09-19 10:00:00');
insert into tb_events (name, vacancies, begin_date_time, end_date_time) values  ('Evento 2', 10, '2021-10-20 10:00:00', '2021-10-30 10:00:00');
insert into tb_events (name, vacancies, begin_date_time, end_date_time) values ('Evento 3', 0, '2021-10-20 10:00:00', '2021-10-30 10:00:00');

insert into tb_subscriptions (event_id, user_id, moment, status, is_checked_in) values (1, 1, '2021-09-16 10:00:00', 1, false);
insert into tb_subscriptions (event_id, user_id, moment, status, is_checked_in) values (2, 2, '2021-10-21 10:00:00', 1, false);
insert into tb_subscriptions (event_id, user_id, moment, status, is_checked_in) values (3, 3, '2021-10-23 10:00:00', 1, false);