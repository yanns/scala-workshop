#! /usr/bin/env sh

if test "$1" = "debug"; then
  JPDA_PORT="9999"
  shift
fi

if [ -z "${JPDA_PORT}" ]; then
  DEBUG_PARAM=""
else
  DEBUG_PARAM="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${JPDA_PORT}"
fi

java ${DEBUG_PARAM} -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -Dhttp.proxyHost=cache.epost.de -Dhttp.proxyPort=8080 -jar `dirname $0`/sbtwrapper/sbt-launch.jar "$@"
