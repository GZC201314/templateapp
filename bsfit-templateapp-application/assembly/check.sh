#!/bin/sh

if [ ! -f "tpid" ];
then
    echo "File \"tpid\" does NOT existed."
else
    tpid=$(cat "tpid")
    running=$(ps -ef |awk '{print $2}'|grep -w $tpid)
    if [ "$running" ];
    then
        echo "Process(pid:$tpid) is running."
    else
        echo "Process(pid:$tpid) is NOT running."
    fi
fi