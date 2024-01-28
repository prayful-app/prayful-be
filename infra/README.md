# Deployment

## Pre-requisites
### Install
```bash
sudo apt install unzip
sudo apt install zip
```

## Java
### Install
```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 21.0.1-amzn
sdk install maven 3.9.6
```

## PostgreSQL
### Install
```bash
sudo apt-get install postgresql
```

### Users

Connect with superadmin

```bash
sudo -u postgres psql
```

#### Create user and database

[How to Create a Postgres User | phoenixNAP KB](https://phoenixnap.com/kb/postgres-create-user)

[PostgreSQL - How to grant access to users? | TablePlus](https://tableplus.com/blog/2018/04/postgresql-how-to-grant-access-to-users.html)

```bash
CREATE USER "<name>" WITH PASSWORD '<password>';
ALTER USER username CREATEDB;
CREATE DATABASE "prayful-db" WITH ENCODING "UTF-8";
```

### Login

[Change PostgreSQL Authentication Method From Peer To MD5 (kompulsa.com)](https://www.kompulsa.com/change-postgresql-authentication-method-from-peer-to-md5/)

```bash
sudo vim /etc/postgresql/14/main/pg_hba.conf
sudo service postgresql restart
```

## NGINX
### Install
```bash
sudo apt install nginx
```

### Configuration
#### Create SSL certificate
```bash
sudo apt install certbot
sudo systemctl stop nginx
sudo certbot certonly --standalone --post-hook "systemctl restart nginx"
sudo systemctl reload nginx
```
#### Create sites-available file
Create a symlink to the sites-available directory
```bash
sudo ln -s <path_to_api.prayful.app> /etc/nginx/sites-available/api.prayful.app
sudo ln -s /etc/nginx/sites-available/api.prayful.app /etc/nginx/sites-enabled/api.prayful.app
sudo systemctl restart nginx
```

## Systemd
### Configuration
```bash
sudo ln -s <source> /etc/systemd/system/<>.service # Source must be absolute
sudo systemctl enable prayful-api-prod.service
sudo systemctl start prayful-api-prod.service
```
