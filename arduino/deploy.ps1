# jar包变化
ssh -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/arduino;rm *.jar"
scp -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem .\target\*.jar root@boer.ink:/root/arduino
ssh -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/arduino; docker compose build"

# 重启镜像
ssh -i C:\Users\shiwenbo\.ssh\ubuntu20_server_ali.pem root@boer.ink " cd /root/arduino;docker compose down; docker compose up -d"
