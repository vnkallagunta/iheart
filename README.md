
# About Project
This project provide rest api end points to perform following operations for advertisor resource.
* Create
* Get
* Update
* Delete

# Notes
* Could not implement get credit limit as this could not be represented as a rest resource. Get Advertiser api call fetches   credit limit as part of advertiser details. Inorder to retrieve only credit limit information GET advertiser api could be parameterized perhaps by passing a query parameter specifying the details to filter.

# How to use it
* git clone https://github.com/vnkallagunta/iheart.git
* mvn clean spring-boot:run

# Operations
## Create Advertiser
POST
http://localhost:8080/advertisers

### Sample Request
{

	"name": "iHeart Media",
	"contactName": "iHeart",
	"creditLimit": 99999.99
}

### Sample Response
{

    "id": "639ac1e7-327f-4fb4-8908-e0a448d47567",
    "name": "iHeart Media",
    "contactName": "iHeart",
    "creditLimit": 99999.99
}

* 201 - Successfully Created
* 400 - Bad Request
* 500 - Internal Server Error.

## Update Advertiser
PUT
http://localhost:8080/advertisers

### Sample Request
{

	"name": "IHeartMedia",
	"creditLimit": 999.99
}

### Sample Response
{

    "id": "639ac1e7-327f-4fb4-8908-e0a448d47567",
    "name": "IHeartMedia",
    "contactName": "iHeart",
    "creditLimit": 999.99
}
* 200 - Successfully Created
* 400 - Bad Request
* 404 - No Advertiser found with given id.
* 500 - Internal Server Error.

## Get Advertiser
GET
http://localhost:8080/advertisers/{advertiserId}

### Sample Request
N/A

### Sample Response
{

    "id": "639ac1e7-327f-4fb4-8908-e0a448d47567",
    "name": "IHeartMedia",
    "contactName": "iHeart",
    "creditLimit": 999.99
}

* 200 - Success
* 404 - No Advertiser Found with given Id.
* 500 - Internal Server Error.

## Delete
DELETE
http://localhost:8080/advertisers/{advertiserId}

### Sample REquest
N/A

### Sample Response
Empty

* 201 - No Content. Advertiser successfully deleted. This is hard delete.
* 500 - Internal Server Error.
