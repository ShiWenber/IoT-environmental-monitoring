# dist包变化
ssh -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/arduino-web;rm -r dist"
scp -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem -r ./dist root@boer.ink:/root/arduino-web 

# 重启镜像
ssh -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/arduino-web;docker compose down;docker compose build; docker compose up -d"