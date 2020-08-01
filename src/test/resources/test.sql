insert into user (created_at, email, uuid, password, status, username) values (now(), 'user1@as.as', '82532435-c58e-4917-adba-6f45e88546f1', 'as', 'ACTIVE', 'user1');
insert into user (created_at, email, uuid, password, status, username) values (now(), 'user2@as.as', '82532435-c58e-4917-adba-6f45e88546f2', 'as', 'ACTIVE', 'user2');
insert into user (created_at, email, uuid, password, status, username) values (now(), 'user3@as.as', '82532435-c58e-4917-adba-6f45e88546f3', 'as', 'ACTIVE', 'user3');

insert into server (domain, port, name, owner_id, secret_key, server_version) values ('kingcjy.oa.to', 25565, 'KingCjy 서버', 1, 'secretKey', '1.15.2');
