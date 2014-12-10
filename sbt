#!/bin/sh

if test "$1" = "debug"; then
    JAVA_DEBUG_PORT="9998"
    shift
fi

if [ -z "${JAVA_DEBUG_PORT}" ]; then
    DEBUG_PARAMETERS=""
else
    DEBUG_PARAMETERS="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${JAVA_DEBUG_PORT}"
fi

SBT_LAUNCH_JAR=`dirname $0`/sbtwrapper/sbt-launch-0.13.7.jar

java ${DEBUG_PARAMETERS} -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar $SBT_LAUNCH_JAR "$@"
