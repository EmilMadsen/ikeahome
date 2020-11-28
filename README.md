# ikeahome
Spring Boot web service to control traadfri ikea lights via http.   
Using https://github.com/StijnGroenen/ikea-tradfri-api library to handle CoAP requests.

endpoints for:

   - get/set all lights.
   - get/set all lights in grp by name
   - get/set single light by id

### docker pi
build image for pi:  
```docker build -t emilmadsen/ikeahome-api:rpi -f DockerfilePi ```  
push to dockerhub   
```docker push emilmadsen/ikeahome-api:rpi```   
run on pi:  
```docker run -e "secretip=192.168.1.19" -e "secretcode=xxxx" -p 8080:8080 emilmadsen/ikeahome:rpi```
