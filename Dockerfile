FROM bellsoft/liberica-openjdk-alpine:21
LABEL authors="simtran"

# Install curl, jq
RUN apk add curl jq

# Set working directory
WORKDIR home/web-test

# Copy project files
ADD . .

# Run the tests
ENTRYPOINT sh runner.sh
#ENTRYPOINT ["./mvnw", "install", "-Ptestng-prod", "-Dgrid-enabled=true"]