#!/bin/sh

#JAVA_HOME=

FFA_HOME=$(dirname $0)/..

FFA_HOME=`cd "$FFA_HOME"; pwd`

. "$FFA_HOME/bin/env.sh"

FFA_JAVA_OPTS="$FFA_JAVA_OPTS -Dspring.profiles.active=$PROFILE"

FFA_CLASSPATH="$CLASSPATH"

FFA_PIDFILE="$FFA_HOME/bin/ffa.pid"

FFA_CONSOLEOUT="$FFA_HOME/logs/console.out"

case `uname` in
    CYGWIN*)
        FFA_CLASSPATH=`cygpath -p -w "$FFA_CLASSPATH"`
        FFA_HOME=`cygpath -p -w "$FFA_HOME"`
    ;;
esac

case $1 in
start)
    echo  -n "Starting ffa ... "
    if [ -f "$FFA_PIDFILE" ]; then
      if kill -0 `cat "$FFA_PIDFILE"` > /dev/null 2>&1; then
         echo $command already running as process `cat "$FFA_PIDFILE"`. 
         exit 0
      fi
    fi
    nohup "$JAVA" $JAVA_OPTS $FFA_JAVA_OPTS -cp "$FFA_CLASSPATH" \
        com.sinnren.ffa.Application >> "$FFA_CONSOLEOUT" 2>&1 < /dev/null &
    if [ $? -eq 0 ]
    then
      if /bin/echo -n $! > "$FFA_PIDFILE"
      then
        sleep 1
        echo STARTED
      else
        echo FAILED TO WRITE PID
        exit 1
      fi
    else
      echo SERVER DID NOT START
      exit 1
    fi
    ;;
stop)
    echo -n "Stopping ffa"
    if [ ! -f "$FFA_PIDFILE" ]
    then
      echo "no ffa to stop (could not find file $FFA_PIDFILE)"
    else
      stopprocess $(cat "$FFA_PIDFILE")
      rm "$FFA_PIDFILE"
    fi
    exit 0
    ;;
restart)
    shift
    "$0" stop ${@}
    sleep 5
    "$0" start ${@}
    ;;
*)
    echo "Usage: $0 {start|stop|restart}" >&2

esac
