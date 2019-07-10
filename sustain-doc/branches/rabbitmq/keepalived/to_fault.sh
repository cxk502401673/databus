#keepalived进入故障fault状态时执行的命令，可做报警使用 , eth0 为我的网卡名称，按具体情况修改


#!/bin/bash
Date=$(date +%F" "%T)
IP=$(ifconfig eth0 |grep -w "inet" |awk '{print $2}')
echo "$Date $IP change to fault." >>/var/log/keepalived-haproxy-status.log

