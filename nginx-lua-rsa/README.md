## Description

- This service is used to encrypt path with RSA;
- This service can only encrypt path with public key and decrypt path by public key;
- This service is running with docker by docker file;
- This service contains nginx, lua and java;


### Steps

#### Step1
 - Replace the public key and private in RSAConstants.java with yours;
 - Replace the path in RsaTest.java's main method.
 - Run main method in RsaTest.java to get encrypt path;
 
#### Step2
- Replace the private key and host in content_analyzer.lua;
- Replace the server_name with yours in default.conf;

#### Step3
- Build docker image with the given DockerFile;
- Run a container with the image you have build;

#### Step4
- Send request with the path you have encrypt and nginx will decrypt it and redirect to the target URL;