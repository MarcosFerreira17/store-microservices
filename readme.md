# GeekStore - project under development

See the overall picture of **implementations on microservices with Sprinboot tools** on real-world **e-commerce microservices** project;

![microservices_remastered](/images/project-arch.png)

There is a couple of microservices which implemented **e-commerce** modules over **Catalog, Inventory, Notification** and **Ordering** microservices with **NoSQL (MongoDB)** and **Relational databases (MySQL)** with communicating over **RabbitMQ Event Driven Communication** and using ** API Gateway**.


## Run The Project
You will need the following tools:

* [InteliJ]()
* [Java 17 or later]()
* [Docker Desktop](https://www.docker.com/products/docker-desktop)

### Installing
Follow these steps to get your development environment set up: (Before Run Start the Docker Desktop)
1. Clone the repository
2. Once Docker for Windows is installed, go to the **Settings > Advanced option**, from the Docker icon in the system tray, to configure the minimum amount of memory and CPU like so:
* **Memory: 4 GB**
* CPU: 2
3. At the root directory which include **docker-compose.yml** files, run below command:
```csharp
docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d
```
3. Wait for docker compose all microservices. That’s it! (some microservices need extra time to work so please wait if not worked in first shut)

4. You can **launch microservices** as below urls:

* **Catalog API -> http://localhost:8000/swagger/index.html**
* **Inventory API -> http://localhost:8001/swagger/index.html**
* **Notification API -> http://localhost:8002/swagger/index.html**
* **Ordering API -> http://localhost:8004/swagger/index.html**
* **API Gateway -> http://localhost:8010/**
* **Rabbit Management Dashboard -> http://localhost:15672**   -- guest/guest
* **Portainer -> http://localhost:9000**   -- admin/admin1234
* **Elasticsearch -> http://localhost:9200**
* **Kibana -> http://localhost:5601**
* **Web Frontend -> http://localhost:8006**

1. Launch http://localhost:8007 in your browser to view the Web Status. Make sure that every microservices are healthy.
2. Launch http://localhost:8006 in your browser to view the Web UI. You can use Web project in order to **call microservices over API Gateway**. When you **checkout the basket** you can follow **queue record on RabbitMQ dashboard**.