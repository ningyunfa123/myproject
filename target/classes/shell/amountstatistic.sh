#!/bin/bash
port = $1
service iptables stop
iptables -I INPUT -p tcp --dport $port -m connlimit --connlimit-above 5 -j DROP
iptables -I OUTPUT -p tcp --dport $port -m connlimit --connlimit-above 5 -j DROP
service iptables save
service iptables start