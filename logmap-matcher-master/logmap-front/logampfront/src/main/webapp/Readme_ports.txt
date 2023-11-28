Redirection of ports: (easier than running on port 80) http://www.klawitter.de/tomcat80.html
iptables -t nat -A OUTPUT -d localhost -p tcp --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -A OUTPUT -d your hostname -p tcp --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -A PREROUTING -d your hostname -p tcp --dport 80 -j REDIRECT --to-ports 8080


-A OUTPUT -d localhost -p tcp --dport 80 -j REDIRECT --to-ports 8080
-A OUTPUT -d csu6325.cs.ox.ac.uk -p tcp --dport 80 -j REDIRECT --to-ports 8080
-A PREROUTING -d csu6325.cs.ox.ac.uk -p tcp --dport 80 -j REDIRECT --to-ports 8080




Proxy:
<Connector
  className="org.apache.catalina.connector.http.HttpConnector"
  port="8080"
  proxyPort="80"
>




See if it is running: ps -ef | grep tomcat


Open firewall:
sudo system-config-firewall

1. ACCEPT HTTP and add ports 80 and 8080
(also can bedone in iptables)

To redirect:
sudo iptables -t nat -A OUTPUT -d localhost -p tcp --dport 80 -j REDIRECT --to-ports 8080
sudo iptables -t nat -A OUTPUT -d csu6325.cs.ox.ac.uk -p tcp --dport 80 -j REDIRECT --to-ports 8080
sudo iptables -t nat -A PREROUTING -d csu6325.cs.ox.ac.uk -p tcp --dport 80 -j REDIRECT --to-ports 8080
