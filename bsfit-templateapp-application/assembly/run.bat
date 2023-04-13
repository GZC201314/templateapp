@echo off
for /r ./ %%i in (*.jar) do java -XX:MaxNewSize=256m -XX:MaxPermSize=256m -Xms1G -Xmx6G -XX:+UseG1GC -Dloader.path=libs -jar %%i