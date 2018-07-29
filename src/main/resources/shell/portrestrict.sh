#!/bin/bash
port=$1
iptables -A INPUT -p udp -m state --state NEW -m udp --dport ${port} -j ACCEPT
iptables -A INPUT -p tcp -m state --state NEW -m tcp --dport ${port} -j ACCEPT
service iptables save
service iptables restart