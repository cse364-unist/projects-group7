# Use Ubuntu 22.04 as base image
FROM ubuntu:22.04

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
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN add-apt-repository universe
RUN apt-get update && apt-get install -y python3-pip

# Set python3.8 as the default python
RUN update-alternatives --install /usr/bin/python python /usr/bin/python3.10 1

# Create project directory
RUN mkdir -p /root/project
WORKDIR /root/project

# Add the run script and make it executable
ADD run.sh /root/project/run.sh
RUN chmod +x /root/project/run.sh

# Expose port 8888 for service accessibility
EXPOSE 8888

# Define default command to run the script
#CMD ["./run.sh"]
