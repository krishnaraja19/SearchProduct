# docker-product
Please follow the below procedure
Step 1: Extract product-docker.tar file. Please run below command.
docker image ls
Step 2: Please check, product image file is available or not.
Step 3: If it is available, Please run below command. Ant it is not available, Please contact Krishna raja.
docker container run -p 8080:8080 product

Step 4:
http://localhost:8080/api/product?type=phone
http://localhost:8080/api/product?type=subscription
http://localhost:8080/api/product?type=
http://localhost:8080/api/product
http://localhost:8080/api/product?type=phone&properties=color:rosa
http://localhost:8080/api/product?type=subscription&properties=gb_limit:50
http://localhost:8080/api/product?type=subscription&properties=


