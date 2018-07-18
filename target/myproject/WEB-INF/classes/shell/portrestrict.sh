#!/bin/bash
port = $1
service iptables stop
iptables -A INPUT -p tcp --dport $port -j ACCEPT
iptables -A OUTPUT -p tcp --sport $port -j ACCEPT
iptables -I INPUT -p tcp --dport $port -m connlimit --connlimit-above 3 -j DROP
#iptables -I OUTPUT -p tcp --dport $port -m connlimit --connlimit-above 3 -j DROP
service iptables save
service iptables start
#重启防火墙使配置文件生效
systemctl restart iptables.service