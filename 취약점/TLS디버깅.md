
#  TLS ssl 디버깅 
-Djavax.net.debug=all

java -Djava.security.debug=all -Djavax.net.debug=all Hc https://api-air-lottejtb.tour.wonders.app/air/wmp.jsp
java -Djava.security.debug=all -Djavax.net.debug=all Hc https://api-air-lottejtb.tour.wonders.app/air/wmp.jsp > a.txt
