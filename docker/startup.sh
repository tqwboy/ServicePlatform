#!/bin/bash
docker stop service-platform

docker rm service-platform

docker run --name service-platform --privileged=true --restart=always \
 -p 18080:18080 \
 -e TZ="Asia/Shanghai" \
 -v /etc/localtime:/etc/localtime \
 -v /home/orange/logs/luckydraw:/var/log/LuckyDrawService \
 -itd service-platform:latest

docker rmi $(docker images | awk '/<none>/ { print $3 }') || true