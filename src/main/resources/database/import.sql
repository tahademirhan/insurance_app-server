insert into configuration (vkey, vvalue,  description)
values ('email_key_expire_time', '120',  'email ile gönderilen doğrulama anahtarı geçerlilik süresi');
insert into configuration (vkey, vvalue,  description)
values ('email_key_char_set', '0123456789',  'email ile gönderilen doğrulama karakter seti');
insert into configuration (vkey, vvalue, description)
values ('email_key_length', '6',  'email ileğ gönderilen doğrulama anahtarı uzunluğu');
insert into configuration (vkey, vvalue, description)
values ('email_authentication_subject', 'E-Posta Doğrulama Anahtarınız',
        'doğrulama anahtarı gönderim konu başlığı');
insert into configuration (vkey, vvalue, description)
values ('email_authentication_content',
        'Merhaba,<br/>E-Posta doğrulama anahtarınız: <b>{key}</b><br/> İyi Günler',
        'doğrulama anahtarı gönderim içeriği');
insert into configuration (vkey, vvalue,  description)
values ('email_from', '[YOUR_YANDEX_ACCOUNT]',  'email gönderim domaini');
insert into configuration (vkey, vvalue,  description)
values ('email_from_password', '[YOUR_APP_PASSWORD]',  'email gönderim domain şifresi');