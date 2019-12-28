FROM nginx:1.15.0-alpine

RUN apk add --no-cache nginx-mod-http-lua

RUN apk add --update openssl && \
    rm -rf /var/cache/apk/*

RUN mkdir -p /run/nginx

COPY default.conf /etc/nginx/conf.d/default.conf
COPY content_analyzer.lua /var/lib/nginx/
COPY rsa.lua /usr/share/lua/5.1/resty/
COPY decoder.lua /usr/share/lua/5.1/base64/

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]
