server {
        listen 80;
        server_name api.prayful.app www.api.prayful.app;
        return 301 https://$host$request_uri;
}

server {
        listen 443 ssl;
        server_name api.prayful.app www.api.prayful.app;

        location / {
                proxy_pass http://localhost:8000;
        }

        ssl_certificate /etc/letsencrypt/live/prayful.app/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/prayful.app/privkey.pem;

        access_log /var/log/nginx/prayful-access.log;
        error_log /var/log/nginx/prayful-error.log;
}