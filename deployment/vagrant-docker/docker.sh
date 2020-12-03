#!/usr/bin/env bash
 
echo "Installing dependencies"
sudo apt install -y apt-transport-https ca-certificates curl gnupg-agent software-properties-common
 
echo "Importing keys"
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
 
echo "Add docker apt repository"
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
 
echo "Updating apt"
sudo apt -qq update
 
echo "Install latest docker version"
sudo apt install -y docker-ce docker-ce-cli containerd.io

echo "Adding vagrant user to docker group"
sudo usermod -aG docker vagrant

echo "Exposing Docker Deamon"
sudo mkdir /etc/systemd/system/docker.service.d/

sudo wget https://gist.githubusercontent.com/Roguyt/6eb92f06e0865b54ea8f3dbdf958e0f0/raw/d6da7dec86f07d835de97c1fcc3b54b9f60966d0/gistfile1.txt -O /etc/systemd/system/docker.service.d/override.conf

sudo systemctl daemon-reload
sudo systemctl restart docker.service

