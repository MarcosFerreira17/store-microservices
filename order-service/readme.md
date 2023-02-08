# **Orders API MS**

## **Introduction**

This is a REST API built with Java and integrated with a MySQL database. The API is containerized using Docker for deployment and testing purposes. The API is based on the OpenAPI specification and has a single endpoint for placing an order.

## **API Endpoint**

The API has a single endpoint **`/api/v1/order`** that accepts a **`POST`** request with the order details in JSON format. The order details should contain an array of **`OrderLineItemsDto`** objects, which have the following properties:

- **`id`** (integer)
- **`skuCode`** (string)
- **`price`** (number)
- **`quantity`** (integer)

The API returns a **`201`** status code on successful creation of an order.

## **Testing**

You can use the API endpoint to place an order by sending a **`POST`** request to **`http://localhost:8081/api/v1/order`**. The request body should contain an array of **`OrderLineItemsDto`** objects in JSON format.

## **Further Documentation**

You can view the API documentation generated from the OpenAPI specification at **`http://localhost:8081/v3/api-docs`**.