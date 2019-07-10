#!/bin/bash
#####################################################Environment Setting#######################################################

#程序代码数组
APPS=(sustain-gateway-0.0.1-SNAPSHOT sustain-auth-0.0.1-SNAPSHOT rabbitmq-server-0.0.1-SNAPSHOT. sustain-percept-customer-1.0-SNAPSHOT sustain-percept-provider-1.0-SNAPSHOT sustain-rabbitm-provide-0.0.1-SNAPSHOT sustain-redis-service-1.0-SNAPSHOT sustain-user-service-0.0.1-SNAPSHOT)

#程序名称数组

NAMES=(sustain-gateway-0.0.1-SNAPSHOT sustain-auth-0.0.1-SNAPSHOT rabbitmq-server-0.0.1-SNAPSHOT. sustain-percept-customer-1.0-SNAPSHOT sustain-percept-provider-1.0-SNAPSHOT sustain-rabbitm-provide-0.0.1-SNAPSHOT sustain-redis-service-1.0-SNAPSHOT sustain-user-service-0.0.1-SNAPSHOT)

#jar包数组

JARS=(sustain-gateway-0.0.1-SNAPSHOT.jar sustain-auth-0.0.1-SNAPSHOT.jar rabbitmq-server-0.0.1-SNAPSHOT.jar sustain-percept-customer-1.0-SNAPSHOT.jar sustain-percept-provider-1.0-SNAPSHOT.jar sustain-rabbitm-provide-0.0.1-SNAPSHOT.jar sustain-redis-service-1.0-SNAPSHOT.jar sustain-user-service-0.0.1-SNAPSHOT.jar)

#jar包路径数组

##PATHS=(/application/provider/pushcode /application/provider/thirdpayment /application/provider/security /application/provider/redis /application/provider/commservice /application/provider/wechat /application/provider/point /application/provider/useraccount  /application/provider/user /application/provider/message/send /application/provider/payment /application/provider/sms /application/provider/deal  /application/provider/message/dealload /application/provider/message/recv /application/provider/innermsg /application/provider/reward /application/provider/finacial /application/provider/pushmsg /application/provider/esign /application/provider/crmda)
##### 命令 demo:
#  service.sh start all  （启动所有jar）
#  service.sh start  sustain-gateway-0.0.1-SNAPSHOT (单独启动 sustain-gateway-0.0.1-SNAPSHOT.jar)
#  service.sh stop all （停止所有jar，除了 sustain-config-server-0.0.1-SNAPSHOT.jar 和sustain-server-0.0.1-SNAPSHOT.jar）
#  service.sh stop  sustain-gateway-0.0.1-SNAPSHOT (单独关闭 sustain-gateway-0.0.1-SNAPSHOT.jar)
# ##################
start(){
local APPNAME=
local NAME=
local CLASSNAME=
local PROJECTDIR=
local command="sh service.sh start"
local cmd2="$1"
local cmd2ok=0
local cnt=0
local okcnt=0
#local C_PID="0"
#for i in `seq 0 22`
echo "---------------------------开始启动服务..."


# 优先启动 service 和 config
JARPATH=/usr/local/jar
SERVICEJAR=sustain-server-0.0.1-SNAPSHOT.jar
CONFIGJAR=sustain-config-server-0.0.1-SNAPSHOT.jar
#启动 service
PID=`ps -ef |grep $(echo $SERVICEJAR |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
#do
#C_PID=$(ps --no-heading $pid | wc -l)
#if [ "$C_PID" == "1" ]; then
if [ -n "$PID" ]
then
echo "sustain-server-0.0.1-SNAPSHOT.jar 己经运行,PID=$PID"
#okcnt=$(($okcnt+1))
else
command="nohup java -jar $JARPATH/$SERVICEJAR"
exec $command >>$JARPATH/$SERVICEJAR.out &
sleep 7s
fi
# 启动config
PID=`ps -ef |grep $(echo $CONFIGJAR |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
if [ -n "$PID" ]
then
echo "sustain-config-server-0.0.1-SNAPSHOT.jar 己经运行,PID=$PID"
#okcnt=$(($okcnt+1))
else
command="nohup java -jar $JARPATH/$CONFIGJAR"
exec $command >>$JARPATH/$CONFIGJAR.out &
sleep 7s
fi
for(( i=0;i<${#APPS[@]};i++))
do
APPNAME=${APPS[$i]}
NAME=${NAMES[$i]}
CLASSNAME=${JARS[$i]}
#PROJECTDIR=${PATHS[$i]}
PROJECTDIR=/usr/local/jar/2019-06-03
if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
cmd2ok=1
C_PID="0"
cnt=0
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
#do
#C_PID=$(ps --no-heading $pid | wc -l)
#if [ "$C_PID" == "1" ]; then
if [ -n "$PID" ]
then
echo "$APPNAME---$NAME:己经运行,PID=$PID"
#okcnt=$(($okcnt+1))
else
cd $PROJECTDIR
#rm -f $PROJECTDIR/nohup.out
command="nohup java -jar $CLASSNAME"
exec $command >> $PROJECTDIR/$CLASSNAME.out &
echo "启动 $APPNAME---$NAME 。。。。"
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#do
# C_PID=$(ps --no-heading $pid | wc -l)
#done
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
cnt=0
#while (("$C_PID" == "0"))
while [ -z "$PID" ]
do
if (($cnt==30))
then
echo "$APPNAME---$NAME:$cnt秒内未启动，请检查！"
break
fi
cnt=$(($cnt+1))
sleep 1s
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#do
# C_PID=$(ps --no-heading $pid | wc -l)
#done
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
done
okcnt=$(($okcnt+1))
echo "$APPNAME---$NAME:己经成功启动,PID=$PID"
fi
#done
fi
done
if (($cmd2ok==0))
then
echo "command2: all|pushcode|thirdpayment|security|redis|commservice|wechat|point|useraccount|coupon|interest|experience|dealaccount|user|send|payment|sms|deal|dispatch|dealload|recv|innermsg|reward|finacial|debt"
else
echo "---------------------------本次启动:$okcnt个服务"
fi
}

stop(){
local APPNAME=
local CLASSNAME=
local PROJECTDIR=
local command="sh service.sh stop"
local cmd2="$1"
local cmd2ok=0
#local C_PID="0"
local okcnt=0
echo "---------------------------开始停止服务..."
for(( i=0;i<${#APPS[@]};i++))
do
APPNAME=${APPS[$i]}
NAME=${NAMES[$i]}
CLASSNAME=${JARS[$i]}
PROJECTDIR=${PATHS[$i]}
if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
cmd2ok=1
#ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read PID
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
#do
#C_PID=$(ps --no-heading $PID | wc -l)
#if [ "$C_PID" == "1" ]; then
if [ -n "$PID" ]
then
echo "$NAME:PID=$PID准备结束"
kill $PID
#C_PID=$(ps --no-heading $PID | wc -l)
#while (("$C_PID" == "1"))
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
while [ -n "$PID" ]
do
sleep 1s
#C_PID=$(ps --no-heading $PID | wc -l)
PID=`ps -ef |grep $(echo $CLASSNAME |awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
done
echo "$NAME:成功结束"
okcnt=$(($okcnt+1))
else
echo "$NAME:未运行"
fi
#done
fi
done
if (($cmd2ok==0))
then
echo "sustain-gateway-0.0.1-SNAPSHOT sustain-auth-0.0.1-SNAPSHOT rabbitmq-server-0.0.1-SNAPSHOT. sustain-percept-customer-1.0-SNAPSHOT sustain-percept-provider-1.0-SNAPSHOT sustain-rabbitm-provide-0.0.1-SNAPSHOT sustain-redis-service-1.0-SNAPSHOT sustain-user-service-0.0.1-SNAPSHOT"
else
echo "---------------------------本次共停止:$okcnt 个服务"
fi
}

case "$1" in
start)
start "$2"
exit 1
;;
stop)
stop "$2"
;;
restart)
stop "$2"
start "$2"
;;
*)
echo "command1: start|stop|restart"
exit 1
;;
esac