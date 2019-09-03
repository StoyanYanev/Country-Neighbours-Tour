# Country Neighbours Tour
***
### What is Country Neighbours Tour?

Country Neighbours Tour is REST API which can calculate how many times the user can go through all neighbor countries within his total budget.
The API calculates the budget for each country in its respected currencies. If the exchange rate is missing it returns the amount in the original currency. The potential leftover amount from the total budget also is returned in the original currency. 

##### The REST API accepts the following request parameters:

- Starting (home) country 
- Budget per country (equal for all neighbor countries) 
- Тotal budget 
- Input Currency


### How to run the application with Docker:

- Open terminal and enter: 
- 
    ```sh
    $ docker run -p 8080:8080 stoyany/tour:1.0
    ```
    
- After Docker image is running open Postman ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/postman.png){:height="50px" width="50px"}

- With Postman you can send two types of request **POST** and **GET**:
    1. To send **Post** request copy API url http://localhost:8080/tour and paste it in the Postman endpoint bar.
        
          ![](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/url.png)
           
          ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/Post.png)
        
        
        After that you must add **request body**: click on Body tab then click on raw and select format type JSON. This endpoint expects a Json body which contains the details for new trip plan. Below is a sample Json body.
           
           ```
            {	
        		"startCountry": "Bulgaria",
        		"budgetPerCountry": "100",
        		"totalBudget": "1200",
        		"currencyCode": "EUR"
        	}
            ```
            
          ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/Body.png)
            
            
    2. To send **Get** request with **path variables** use the following url(you can make your own trip plan by changing the path variables): http://localhost:8080/tour/Bulgaria/100/1200/EUR
   
   ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/PathVariables.png)
    
    
    3. To send **Get** request with **request parameters** use the following ur(you can make your own trip plan by changing the request parameters): http://localhost:8080/tour/v2country=Bulgaria&budgetPerCountry=100.0&totalBudget=1200.0&currency=EUR
   
   ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/%20RequestParameters.png)


## Diagram of the Application
    
   ![alt text](https://github.com/StoyanYanev/Country-Neighbours-Tour/blob/master/imgs/CountryNeighboursTourDiagram.jpg)

## How to run the application with Kubernetes:
1. Download helm chart from [here](https://drive.google.com/open?id=1IMQK8NE31m8V948mw6-fkjHBPhPdv-7S)
2. Extract it.
3. Open terminal and enter:
    ```sh
    $ cd tour
    $ helm install . -n tour
    $ kubectl port-forward <pod name> 8080:8080
    ```
5. Open Postman and repeat the steps **above**.
