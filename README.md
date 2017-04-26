k8s-test
===

This repo is used to demonstrate the memory required to run a small Java program in Kubernetes compared to the memory required to run the same image directly in Docker.

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

#### Run the image on Docker:

1. Set the database connection environment variables in settings.env

2. Run the image with a 30M memory limit 

`docker run -m 30m --env-file=settings.env jre247/k8s-test`


3. Run `docker ps` and `docker logs <image_id> to check the status of the container. In our testing, the container will not crash.





#### (Optional) Building the image

docker build .

The steps below are optional and provided in case you want to modify the docker Image.

`docker build .`

`docker tag jre247/k8s-test`

`docker push jre247/k8s-test`
