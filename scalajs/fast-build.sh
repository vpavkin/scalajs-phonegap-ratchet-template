#!/usr/bin/env bash
NAME="app-client"
BUILD_DIR="./build"
BUILD_WWW_DIR="${BUILD_DIR}/www"
PHONEGAP_DIR="../phonegap"

rm -rf ${BUILD_DIR}
mkdir ${BUILD_DIR} -p
cp -a ./target/scala-2.11/classes/www ${BUILD_WWW_DIR}
mkdir ${BUILD_WWW_DIR}/js -p
cp ./target/scala-2.11/${NAME}-jsdeps.js ${BUILD_WWW_DIR}/js/deps.js
cp ./target/scala-2.11/${NAME}-fastopt.js ${BUILD_WWW_DIR}/js/app.js
#cp ./target/scala-2.11/${NAME}-fastopt.js.map ${BUILD_WWW_DIR}/js/

rm -rf ${PHONEGAP_DIR}/www
mkdir -p ${PHONEGAP_DIR}/www
mkdir -p ${PHONEGAP_DIR}/www/js
cp -R ./build/* ${PHONEGAP_DIR}
