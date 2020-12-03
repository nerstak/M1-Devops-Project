#!/bin/bash
echo "Adding apt-keys"
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
echo deb https://pkg.jenkins.io/debian-stable binary/ | sudo tee -a /etc/apt/sources.list

echo "Updating apt-get"
sudo apt-get update
sudo apt-get install --fix-missing

echo "Installing default-java"
sudo apt-get -y install default-jre
sudo apt-get -y install default-jdk

echo "Installing git"
sudo apt-get -y install git

echo "Installing git-ftp"
sudo apt-get -y install git-ftp

echo "Installing jenkins"
sudo apt-get -y install jenkins

sleep 1m

echo "Add rights to access the directory to Vagrant user"
sudo chmod a+r /var/lib/jenkins/secrets
sudo chmod a+X /var/lib/jenkins/secrets

echo "Installing Jenkins Plugins"
JENKINSPWD=$(sudo cat /var/lib/jenkins/secrets/initialAdminPassword)
echo $JENKINSPWD