# Vehicle Inventory Service
A simple vehicle inventory service that performs basic CRUD operation.

### Installation
- Java 11
- Git
- Gradle

### Development

1. Clone [vehicle-inventory-service](https://github.com/alvindan/inventory-service.git) repository
2. Checkout to ***master*** branch
3. Then create your own branch (derived from ***master*** branch)
5. Open your Terminal and navigate to your project folder and run this command:

Running the project
sh
$`./gradlew bootRun`

* *Note: add `--console plain` for neater console logs

### History
- Initialize project
- Added basic CRUD operation for vehicle inventory

### CRUD Operation

#### 1. Create
- **Method:** POST
- **URL:** http://localhost:8082/inventory-service/vehicle
- **Header:** Content-Type: application/json
- **Request Body:** {"inventoryCode":"","name":"","model":"","color":""}
- **cURL:** curl --location --request POST 'http://localhost:8082/inventory-service/vehicle' --header 'Content-Type: application/json' --data-raw '{"id":1,"inventoryCode":"IC00001","name":"CR-V","model":"2022-AT","color":"red"}'
	
#### 2. Update
- **Method:** PUT
- **URL:** http://localhost:8082/inventory-service/vehicle/inventory-code/{inventoryCode}
- **Path Param:** inventoryCode
- **Header**: Content-Type: application/json
- **Request Body:** {"name":"","model":"","color":""}
- **cURL:** curl --location --request PUT 'http://localhost:8082/inventory-service/vehicle/inventory-code/IC00001' --header 'Content-Type: application/json' --data-raw '{"name":"ADV","model":"2022-M","color":"blue"}'

#### 3. Pagination

- **Method:** GET
- **URL:** http://localhost:8082/inventory-service/vehicle
- **Request Param:** sortProperties, sortDir, pageIndex, pageSize
- **cURL:** curl --location --request GET 'http://localhost:8082/inventory-service/vehicle/pagination?sortProperties=id,name&sorDir=ASC&pageIndex=0&pageSize=5'

#### 4. Delete

- **Method:** DELETE
- **URL:** http://localhost:8082/inventory-service/vehicle/{id}
- **Path Param:** id
- **cURL:** curl --location --request DELETE 'http://localhost:8082/inventory-service/vehicle/1'


