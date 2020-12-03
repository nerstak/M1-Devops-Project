# M1-DevOps-Project - Proof of Concept

## Table of contents

- [Informations](#Informations)

- [Features](#Features)

- [Installation](#Installation)

## Informations

### Description

Proof of concept of CI/CD, for a Java Web Application.

### Technologies/framework used

Original java project used: 

- [M1-JEE-Project](https://github.com/nerstak/M1-JEE-Project)

Automation server: 

- Jenkins

Application server:

- Wildfly

Container & Virtualization:

- Docker

- Vagrant

Chat:

- Slack

## Features

This project enables project to build, run test on follower on remote machine, deploy on distant server, and send notifications using Slack.

## Installation

### Deployment server

Upload the `docker-compose.yml` located in `deployment/vps-deploy` to your server, and run `docker-compose up -d --build`.

If you are using a different server from ours, put your`DEPLOYMENT_IP` in the stage `Deploy`, in the argument `-Dhostname`.

### Docker builder

In `deployment/vagrant-docker`, run `vagrant up`.

This machine will be used to summon followers to build our project.

### Jenkins

#### Machine setup

In `deployment/vagrant-jenkins`, run `vagrant up`. In case the installation script fails, you will have to connect to the machine and install vagrant manually.

Connect to [localhost:8080](localhost:8080) and install Jenkins.

#### Plugins

If they are not installed, install:

- Docker

- Maven

- Github

- Slack

#### Setup

##### Maven

In Tools, add Maven, name it `maven`, install automatically and select version 3.6.0.

##### Docker

In Configure Clouds, add a Docker. Name it `docker`, put `tcp://192.168.33.10` in Docker Host URI (IP of our Vagrant machine).

In Docker Agent templates, name it and label it `java-docker-slave`, **enable it**, connect with SSH and configure your credentials (by default, jenkins/vagrant or jenkins/jenkins).

##### Credentials

In credentials manager, add your GitHub credentials and put `github` as its ID. This step is necessary if this repository is private.

Create another credential for Wildfly, with adm/adm and put `deployment_wildfly` as its ID.

#### Slack

Install the following plugin in your Slack channel: [Jenkins Slack](https://my.slack.com/services/new/jenkins-ci).

In Jenkins Credentials, add your Teams domain, the Integration Token and the Project Channel. Get the Teams domain and Token from here [Jenkins CI](https://slack.com/apps/A0F7VRFKN-jenkins-ci).

#### Pipeline

Create a new pipeline, add your SCM repository (the link of this repository), select your credentials if needed and select Jenkinsfile from repository. 
