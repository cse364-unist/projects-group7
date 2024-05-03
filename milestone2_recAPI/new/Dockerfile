# Use Ubuntu 22.04 as base image
FROM ubuntu:22.04

# Install prerequisites
RUN apt-get update \
    && apt-get install -y wget gnupg2 \
    && rm -rf /var/lib/apt/lists/*

# Import MongoDB public GPG key
RUN wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | apt-key add -

# Add MongoDB repository to sources list
RUN echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/6.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-6.0.list

# Update package list and install MongoDB
RUN apt-get update \
    && apt-get install -y mongodb-org \
    && rm -rf /var/lib/apt/lists/*


# Create necessary directories
RUN mkdir -p /data/db /data/configdb

# Change ownership of directories
RUN chown -R mongodb:mongodb /data/db /data/configdb

# Add your stuff below:


# Install all necessary packages to run your program, such as vim, java 17, maven, etc.
RUN apt-get update && \
    apt-get install -y vim openjdk-17-jdk maven curl && \
    rm -rf /var/lib/apt/lists/*

#Create /root/project directory and set it as WORKDIR.
WORKDIR /root/project

#Add your ‘milestone1 directory’ under WORKDIR.
ADD milestone2 /root/project/milestone2

#Add your run.sh file under WORKDIR.
ADD run.sh /root/project/run.sh

#A container should execute a bash shell by default when the built image is launched.
RUN chmod +x /root/project/run.sh
CMD ["/bin/bash"]