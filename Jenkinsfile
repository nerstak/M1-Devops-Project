pipeline {
	agent {     
		node {       
		    label 'java-docker-slave'         
		}
	}
	tools { 
		maven 'maven'
	}
	stages {
		stage('Checkout') {
			steps {
				slackSend color: 'good', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER}: Checking out project..."
				checkout([
					$class: 'GitSCM', 
					branches: [[name: '*/master']], 
					doGenerateSubmoduleConfigurations: false, 
					extensions: [], 
					submoduleCfg: [], 
					userRemoteConfigs: [[
						credentialsId: 'github', 
						url: 'https://github.com/nerstak/M1-Devops-Project']]
				]) 
			}
		}
		stage('Execute Maven') {
			steps {
				slackSend color: 'good', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER}: Running maven..."
				sh 'mvn package'  
			}
		}
		stage('Deploy') {
			steps {
				slackSend color: 'good', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER}: Deploying..."
				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'deployment_wildfly', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
					sh 'mvn clean package wildfly:deploy -Dhostname=159.65.123.81 -Dhostport=9900 -Dhostusername=$USERNAME -Dhostpassword=$PASSWORD'
				}
			
				//deploy adapters: [tomcat9(url: 'http://192.168.33.10:1080', credentialsId: 'deployer')], war: '**/*.war', contextPath: 'mywebapp'
				 
			}
		}
	}	
	post { 
		failure { 
			slackSend color: 'danger', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER}: Pipeline failed!"
		}
		success {
			slackSend color: 'good', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER}: Pipeline succeed!"
		}
	}
}
    
