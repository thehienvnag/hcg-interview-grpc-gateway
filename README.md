## Postman collection
Please use the below postman script to execute API:
- Contains the Test script that automatically set accessToken header for all request after `Login`

[POSTMAN_SCRIPT](https://github.com/thehienvnag/hcg-interview-grpc-gateway/blob/master/HCG_Interview_PostmanScript.postman_collection.json)

## How to Install

To run the project using Docker Compose with `1 single command` to run, please follow the steps below:

### Prerequisites
Make sure you have the following software installed on your machine:
- Docker: [Installation Guide](https://docs.docker.com/get-docker/)
- Docker Compose: [Installation Guide](https://docs.docker.com/compose/install/)

### Steps
1. Clone the repository to your local machine:
```sh
git clone https://github.com/thehienvnag/hcg-interview-grpc-gateway
```
2. Navigate to the project directory:
```sh
cd <repository_directory>
```
3. Open the terminal or command prompt and run the following command to start the services using Docker Compose:
```sh
docker-compose --env-file docker.env up --build
```
This command will download the necessary Docker images (if not already available) and start the containers in detached mode.
4. Wait for the services to start up. You can check the logs to monitor the progress:
```sh
docker-compose --env-file docker.env logs -f
```
5. Once the services are up and running, you can access them using the following URLs:
- Gateway service: [http://localhost:8088](http://localhost:8088)
6. You can now use tools like Postman to interact with the APIs. Import the provided Postman collection file to get started with the available API endpoints.
7. To stop the services and remove the containers, run the following command in the project directory:
```sh
docker-compose --env-file docker.env down
```
This command will gracefully stop the running containers and remove them.
