server {
        listen 80;
        server_name stage.api.prayful.app www.stage.api.prayful.app;
        return 301 https://$host$request_uri;
}

server {
        listen 443 ssl;
        server_name stage.api.prayful.app www.stage.api.prayful.app;

        location / {
                proxy_pass http://localhost:8001;
        }

        ssl_certificate /etc/letsencrypt/live/prayful.app/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/prayful.app/privkey.pem;

        access_log /var/log/nginx/prayful-access.log;
        error_log /var/log/nginx/prayful-error.log;
}