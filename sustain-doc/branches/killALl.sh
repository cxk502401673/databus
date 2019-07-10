#!/bin/bash
#根据端口kill 进程
#端口数组
PORTS=(6868 6671 2004 8761 9181 8112 8812 8073 8061 9877 8001 9001)
for(( i=0;i<${#PORTS[@]};i++))
do
port=${PORTS[$i]}
#echo $port
pid=$(netstat -nlp |grep :$port |awk '{print $7}' |awk -F"/" '{print $1}')
if [ -z "$pid"]; then
        echo $pid
   echo "no port $port "
else
echo "kill pid=$(pid) , port=$(port) ..."

   kill -9 $pid

fi
done
