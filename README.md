You are working at a very successful e-commerce and your team is asked to build a microservice that will calculate real-time statistics of item sales on the platform. This microservice will feed data to a dashboard installed in a business team’s room.
The microservice shall have a REST interface with two endpoints. The first endpoint will be called by the checkout service whenever a new payment is received and the second endpoint will provide statistics about the total order amount and average amount per order for the last 60 seconds. (Orders between t and t - 60 sec, given t = request time)

Specifications for the requested endpoints are as follows:

Transactions
URL: /sales
Method: POST
Content-Type: application/x-www-form-urlencoded
Parameters:
Name                  Required           Type
sales_amount            Yes               Number String (e.g. “10.00”)
Return HTTP Code: 202 Accepted
Return Body: blank


Statistics
URL: /statistics
Method: GET
Parameters: none
Return HTTP Code: 200 OK
Return Body:
```
{
total_sales_amount: “1000000.00”,
average_amount_per_order: “45.04”
}
```

Facts
This might seem to be an easy task from functional perspective, but like the most of engineering problems you would face at the company, there are constraints you should keep in account:

* Business people are only interested in seeing the statistics for the last minute. They are not interested in seeing historical data, since they use this service only to create a real-time dashboard. The dashboard is updated once every second.
* In the shop, around 250.000 items are sold each minute. So, your service must expect a high amount of transaction data per minute and on several TCP connections in parallel.
* For your coded solution, you don’t need to worry about the service running on several nodes. You can keep and process data in main memory. You are not allowed to use persistent storage due to internal INFOSEC policies. If the service should be restarted, losing this in-memory data is not a big problem since the risk is only to lose sales statistics for a couple of seconds, then new orders will arrive and your service will again show statistics.
* The SiteOps team allocated limited cloud resources for this project. Use the main memory wisely. Note that, a high quantity of sales numbers will be delivered to your endpoint and you will need to think about a way to operate over this data with minimum memory.
* Your engineering manager wants the statistics endpoint to return results very fast. Since your CPU will be mainly busy with handling transactions, you should use also your CPU wisely while calculating the statistics. Think about ways of delivering the expected result with low time complexity. How would you design and implement an efficient solution for these requirements?

Using the Java programming language, create the project from scratch. Decisions about choosing a build tool, a REST framework, testing approach etc. are left to you, but be prepared to defend these decisions during your in-person interviews.


# Sales Dashboard

- Simply open the project on your IDE and run DashboardApplication

- The endpoints are accessible at http://localhost:4567/

## Endpoints available

- GET /statistics
- POST /sales
    - params: sales_amount=10.00
