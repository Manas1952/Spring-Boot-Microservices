cd api-gateway
docker-compose up -d

cd ../inventory-service
docker-compose up -d

cd ../notification-service

cd ../order-service
docker-compose up -d

cd ../product-service
docker-compose up -d

Start-Sleep -Seconds 10

Write-Output "Starting API Gateway..."

# Start API Gateway
cd ../api-gateway
Start-Process "mvn" "spring-boot:run"

Write-Output "Starting Notification Service..."

# Start Notification Service
cd ../notification-service
Start-Process "mvn" "spring-boot:run"

Write-Output "Starting Inventory Service..."

# Start Inventory Service
cd ../inventory-service
Start-Process "mvn" "spring-boot:run"

Write-Output "Starting Order Service..."

# Start Order Service
cd ../order-service
Start-Process "mvn" "spring-boot:run"

Write-Output "Starting Product Service..."

# Start Product Service
cd ../product-service
Start-Process "mvn" "spring-boot:run"
