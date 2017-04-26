# k8s-test

Running docker container manually:

Step 1: Fill in the following values in the file settings.env

    a) <jdbc-url> - This will be the jdbc url for the database you want to read data from

    b) <username> - This will be the username for connecting to the database you want to read data from
  
    c) <password> - This will be the password for connecting to the database you want to read data from
  
    d) <table-name> - This will be the database table name that you want to read data from

    
Step 2: Run this command to build the docker container:
    
    a) docker build .

Step 3: Run this command to run the docker container:
    
    a) docker run -m 30m --env-file=settings.env <image-id>
      
      i) Make sure to fill in <image-id> to be the actual image id of the built container from the previous step

Deploying job to kubernetes:

Step 1: Fill in the following values in the file job.yaml
  
    a) <jdbc-url> - This will be the jdbc url for the database you want to read data from
  
    b) <username> - This will be the username for connecting to the database you want to read data from
  
    c) <password> - This will be the password for connecting to the database you want to read data from
  
    d) <table-name> - This will be the database table name that you want to read data from

Note: Step 2 and 3 below are optional, only if you want to push a new container image to either the provided docker repo or another docker repo of your choosing. Note that we have already provided a ready-to-go image at this docker repo: jre247/k8s-test. If you want to use a different docker repo than the one provided then you will need to follow steps 2 and 3 below.

Step 2: Run this command to tag docker container image
  
    a) docker tag <image-id> jre247/k8s-test - You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test

Step 3: Run this command to push the container image to a docker repo:
  
    a) docker push jre247/k8s-test - You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test

Step 4: Run this command to deploy the job: - kubectl create -f ./job.yaml
      
    a) You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test     
    b) If you'd like to use a different docker repo then change the value of "image" within job.yaml to be another value
