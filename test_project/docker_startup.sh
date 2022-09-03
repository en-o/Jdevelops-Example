#!/bin/bash
echo .
echo ===========================
echo start up pdap
echo ============================

IMAGES=${3}
PROJECT_PORT=${2}
PROJECT_PROPERTIES=${1}
# 设置默认值
if [ ! $IMAGES ]; then
	IMAGES="tannnn/test_project:0.1"
fi

if [ ! $PROJECT_PORT ]; then
	PROJECT_PORT="9003"
fi

if [ ! $PROJECT_PROPERTIES ]; then
	PROJECT_PROPERTIES="prod"
fi

#echo run -d -p $PROJECT_PORT:9003 --name pdap -v  $PWD/logs:/logs --restart=unless-stopped -e ACTIVE=$PROJECT_PROPERTIES $IMAGES

docker stop pdap
docker rm pdap
docker run -d -p $PROJECT_PORT:9003 --name pdap -v  $PWD/logs:/logs --restart=unless-stopped -e ACTIVE=$PROJECT_PROPERTIES $IMAGES

