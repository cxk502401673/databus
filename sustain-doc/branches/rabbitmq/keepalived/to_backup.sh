#!/bin/bash
#keepalived切换为备用backup状态时执行的命令，可做报警使用, eth0 为我的网卡名称，按具体情况修改
Date=$(date +%F" "%T)
IP=$(ifconfig eth0 |grep -w "inet" |awk '{print $2}')
echo "$Date $IP change to backup." >> /var/log/keepalived-haproxy-status.log


