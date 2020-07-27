INSERT INTO EMPLOYEE_ENTITY(first_name, last_name, email)
values
    ('Jane', 'Doe', 'some@mail.com'),
    ('John', 'Doe', 'other@email.com');

INSERT INTO `authority`(`name`, `id`)
VALUES
    ('ROLE_USER', 1),
    ('ROLE_ADMIN', 2);

INSERT INTO `users` (`id`, `username`, `password`)
VALUES
    (1,'user','{noop}password'),
    (2,'admin','{bcrypt}$2a$10$4.jV3xcv1tORCN9szDekKuJcQkJO.8zxti2lIJmoeSKxJRJ.sfd1i');

INSERT INTO `users_authority`(`authority_id`, `user_id`)
VALUES
    (1, 1),
    (2, 2);