#!/bin/sh
function checkIsDead(){
   tpid=$(cat "tpid")
   running=$(ps -ef |awk '{print $2}'|grep -w $tpid)
   if [ "$running" ];
    then echo "0"
   else echo "1"
   fi
} 
if [ ! -f "tpid" ];
then
    echo "File \"tpid\" does NOT existed."
else
    tpid=$(cat "tpid")
    running=$(ps -ef |awk '{print $2}'|grep -w $tpid)
    if [ "$running" ];
    then
        echo "Process(pid:$tpid) will be killed. Please wait."
        kill $tpid
        flag=true
        declare -i i=1 
        while(($i<=15));
        do
          sleep 1s
          signal=$(checkIsDead)
          if [ $signal -eq 1 ];
            then 
                 flag=false
                 echo "Process(pid:$tpid) is killed."
                 break
          else echo "."
          fi
          let ++i 
        done
        if [ "$flag" = true ];
         then
         echo "kill failed in 15 seconds,please manually kill -9 $tpid."
        fi  
    else
        echo "Process(pid:$tpid) is NOT running."
    fi
fi
