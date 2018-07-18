#!/bin/bash
port = $1
amount = $2
service iptables stop
iptables -N TRAFFIC_QUOTA
iptables -A OUTPUT -p tcp --sport $port -g TRAFFIC_QUOTA
iptables -A OUTPUT -p udp --sport $port -g TRAFFIC_QUOTA
iptables -A TRAFFIC_QUOTA -m quota --quota $amount -j ACCEPT
iptables -A TRAFFIC_QUOTA -j DROP
service iptables save
service iptables start
#重启防火墙使配置文件生效
systemctl restart iptables.service