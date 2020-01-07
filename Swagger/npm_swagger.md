docker pull swaggerapi/swagger-ui
docker run -p 80:8080 swaggerapi/swagger-ui
http://192.168.99.100:80

docker exec -it 1b6c4fe79242 /bin/bash


# 윈도우 기준
npm install -g swagger

# 특정 폴더로 이동하기
cd C:\Study
swagger project create swagger
# [Express] 프로젝트 설정하기
cd swagger
swagger project start


git clone https://github.com/swagger-api/swagger-ui.git
cd swagger-ui
npm install
npm run dev
Wait a bit
Open http://localhost:3200/
Bonus points



git clone https://  swagger-edit-doc

npm install -g swagger

npm install --save swagger-ui

npm install --save swagger-ui-dist


https://app.swaggerhub.com/apis/jmryu94/API_Example/1.0.0#/default/get_todos__id_



```yaml 
openapi: 3.0.0
info:
  version: '1.0.0'
  title: 'API_Example'
  description: 'Swagger실슬읍위한API Example'
#paths: {}
# Added by API Auto Mocking Plugin
servers:
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/jmryu94/API_Example/1.0.0
  - description: SwaggerHub API Auto Mocking
    url: https://jsonplaceholder.typicode.com
paths:
 /todos/{id}:
  get:
    summary: Returns a user by ID
    parameters:
     - name: id 
       in: path
       required: true
       description: The ID of the user to return
       schema:
        type: integer
    responses:
      '200':
        description: OK
        content:
          application/json:
            schema:
              type: object
              properties:
                userId:
                  type: integer
                id:
                  type: integer
                title:
                  type: string
                completed:
                  type: boolean
```                
              
