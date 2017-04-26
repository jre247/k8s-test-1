k8s-test
===

This repo is used to demonstrate the memory required to run a small Java program in Kubernetes compared to the memory required to run the same image in Kubernetes.

#### Run the image on Kubernetes:

1. Set the database connection environment variables in job.yaml
```
<jdbc-url> - jdbc url to connect to
<username> - username to connect to database with
<password> - password to connect to database with
<table-name> - database table name to read from
```

2. Deploy the job with a 32Mi resource request for memory:
`kubectl create -f ./job.yaml`


3. View the job and pods. In our testing, the pods for the Job would terminate due to OOMKilled:
```
State:		Terminated
      Reason:		OOMKilled
      Exit Code:	137
      Started:		Wed, 26 Apr 2017 11:04:48 -0400
      Finished:		Wed, 26 Apr 2017 11:08:44 -0400
```

#### Run the image on Docker

1. Set the database connection environment variables in settings.env

2. Run the image with a 30M memory limit 

`docker run -m 30m --env-file=settings.env jre247/k8s-test`

Build the image (optional)

docker build .

Note: Step 2 and 3 below are optional, only if you want to push a new container image to either the provided docker repo or another docker repo of your choosing. Note that we have already provided a ready-to-go image at this docker repo: jre247/k8s-test. If you want to use a different docker repo than the one provided then you will need to follow steps 2 and 3 below.

Run this command to tag docker container image

a) docker tag jre247/k8s-test - You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test

Run this command to push the container image to a docker repo:

a) docker push jre247/k8s-test - You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test

Run this command to deploy the job: - kubectl create -f ./job.yaml

a) You can optionally use a different docker repo, but I've provided this one: jre247/k8s-test
b) If you'd like to use a different docker repo then change the value of "image" within job.yaml to be another value
