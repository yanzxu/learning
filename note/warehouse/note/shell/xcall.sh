#! /bin/bash
for i in vm01 vm02 vm03
do
   echo "============== $i ========="
   ssh $i "$*" 
done
