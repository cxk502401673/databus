#!/bin/bash
LOGFILE="/var/log/keepalived-haproxy.log"
config="/usr/local/haproxy/haproxy.cfg"
exec="/usr/local/haproxy/sbin/haproxy"
PID="/usr/local/haproxy/haproxy.pid"
#定时检查haproxy进程的脚本

date >> $LOGFILE
if [ `ps -C haproxy --no-header |wc -l` -eq 0 ];then
    echo "haproxy is dead, begin restart haproxy" >> $LOGFILE
    $exec -D -f $config -p $PID
    sleep 2

    if [ `ps -C haproxy --no-header |wc -l` -eq 0 ];then
        echo "fail: haproxy can not start,stop keepalived " >> $LOGFILE
        service keepalived stop
    fi
else
    echo "success: check_haproxy status" >> $LOGFILE
fi