set SCRIPT_DIR=%~dp0
java %SBT_OPTS% -Dfile.encoding=UTF-8 -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar "%SCRIPT_DIR%\sbtwrapper\sbt-launch-0.13.5.jar" %*
