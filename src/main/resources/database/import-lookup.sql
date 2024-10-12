insert into lk_authentication_type (id, key, value)
values (1, 'email', 'E-Posta');
insert into lk_authentication_type (id, key, value)
values (2, 'sms', 'Sms');
insert into lk_authentication_type (id, key, value)
values (3, 'sms_email', 'Sms ve E-Posta');

insert into lk_authentication_reason (id, key, value)
values (1, 'login', 'Giriş');
insert into lk_authentication_reason (id, key, value)
values (2, 'register', 'Kayıt');
insert into lk_authentication_reason (id, key, value)
values (3, 'forgot-password', 'Şifremi Unuttum');

insert into lk_corporate_type (id, key, value)
values (1, 'insurance', 'Insurance');
insert into lk_corporate_type (id, key, value)
values (2, 'reinsurance', 'Reinsurance');

insert into lk_advert_type (id, key, value)
values (1, 'car', 'Car');
insert into lk_advert_type (id, key, value)
values (2, 'health', 'Health');
insert into lk_advert_type (id, key, value)
values (3, 'other', 'Other');

insert into lk_advert_status (id, key, value)
values (1, 'active', 'Active');
insert into lk_advert_status (id, key, value)
values (2, 'passive', 'Passive');
insert into lk_advert_status (id, key, value)
values (3, 'pending', 'Pending');

insert into lk_advert_insured_type (id, key, value)
values (1, 'individual', 'Individual');
insert into lk_advert_insured_type (id, key, value)
values (2, 'corporate', 'Corporate');