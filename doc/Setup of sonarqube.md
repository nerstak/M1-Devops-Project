# Setup of SonarQube

## Installation

Run the following command in the root folder to start SonarQube and its database: `docker-compose up -d`. Note that these images are using a lot of memory.

## Set up of SonarQube

- Go to [localhost:9000](http://localhost:9000)

- Log in with `admin`:`admin`

- Add a new project, and give it a name (it as no impact on the configuration)

- Generate a token

- Select Java Project, with Maven

- Run the given Maven command inside the project

It may take a while as it is reviewing the whole code base. However, once it is finished, you can see the analysis.
