#!/bin/sh
 
echo "Pilot(7200) Stopping..."
 
. ./bin/setDomainEnv.sh
 
java weblogic.Admin -url t3://1.1.1.1:7200 -username pilot -password 1234 FORCESHUTDOWN
