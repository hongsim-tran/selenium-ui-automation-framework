#!/bin/bash

# Let's print what we have received
echo "-------------------------------------------"
echo "HUB_HOST      : ${HUB_HOST}"
echo "GRID_ENABLED  : ${GRID_ENABLED}"
echo "THREAD_COUNT  : ${THREAD_COUNT}"
echo "MAVEN_PROFILE : ${MAVEN_PROFILE}"
echo "-------------------------------------------"

# Do not start the tests immediately. Hub has to be ready with browser nodes
echo "Checking if hub is ready..!"
count=0
while [ "$( curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 30 ]
  then
      echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
      exit 1
  fi
  sleep 1
done

# At this point, selenium grid should be up!
echo "Selenium Grid is up and running. Running the test...."

# Start the java command
./mvnw install -P${MAVEN_PROFILE} -Dgrid-enabled=${GRID_ENABLED}
