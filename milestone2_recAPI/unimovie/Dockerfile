# Use Ubuntu 22.04 as base image
FROM ubuntu:22.04

# Set non-interactive installation
ENV DEBIAN_FRONTEND=noninteractive

# Install prerequisites and clean up in one step
RUN apt-get update && apt-get install -y \
    wget \
    gnupg2 \
    vim \
    openjdk-17-jdk \
    maven \
    git \
    python3.10 \
    software-properties-common \
    curl \
    && apt-get clean \
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

RUN add-apt-repository universe
RUN apt-get update && apt-get install -y python3-pip

# Set python3.10 as the default python
RUN update-alternatives --install /usr/bin/python python /usr/bin/python3.10 1

# Install Python packages
RUN pip install pandas joblib scikit-learn

# Create project directory
RUN mkdir -p /root/project
WORKDIR /root/project

ADD milestone2 /root/project/milestone2

# Add the run script and make it executable
ADD run.sh /root/project/run.sh
RUN chmod +x /root/project/run.sh

# Expose port 8080 for service accessibility
EXPOSE 8080

RUN chmod +x /root/project/run.sh
CMD ["/bin/bash"]
