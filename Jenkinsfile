def hostAddress = "159.65.123.81"
def portAdmin = "9990"
def portWeb = "8080"
def pathAddress = "M1-JEE-Project-1.0-SNAPSHOT"

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
				slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Checking out project..."
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
		stage('UnitTests') {
			steps {
				script {
					slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Unit testing..."
					sh 'mvn --version'
					sh 'mvn --batch-mode resources:testResources compiler:testCompile surefire:test'
					}
			}
			post {
				always {
					junit testResults: "target/surefire-reports/*.xml"
					slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Unit tests available in 'target/surefire-reports/*.xml'."
				}
			}
		}
		stage('Execute Maven') {
			steps {
				slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Running maven..."
				sh 'mvn clean package'  
			}
		}
		stage('Deploy') {
			steps {
				slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Deploying..."
				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'deployment_wildfly', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
					sh 'mvn package wildfly:deploy -Dhostname=159.65.123.81 -Dhostport=9990 -Dhostusername=$USERNAME -Dhostpassword=$PASSWORD'
				}
			
				//deploy adapters: [tomcat9(url: 'http://192.168.33.10:1080', credentialsId: 'deployer')], war: '**/*.war', contextPath: 'mywebapp'
				 
			}
		}
	}	
	post { 
		failure { 
			slackSend color: 'danger', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Pipeline failed!"
		}
		success {
			slackSend color: 'good', message: ":robot_face: ${env.JOB_NAME} - ${env.BUILD_NUMBER}: Pipeline succeed! - Check it out: http://${hostAddress}:${portWeb}/${pathAddress}"
		}
	}
}
    
