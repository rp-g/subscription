# Subscription

## Architecture

#### Public Service: 
validate data entry and call Subscription Service. The call is synchronous. Wait for the response of the post and return valid if the operation was successful.
Data entry: email, firstName, gender, dateOfBith, flag for consent and the newsletter Id corresponding to the campaign. Only gender and firstName are optional values.

#### Subscription Service: 
The subscription service persists the subscription and returns the ID of the subscription created to the public service. Before returning the id, make an asynchronous call to the Email Service

Below the MySQL subscription table:

#### Email Service: 
Send an email to the email received at the data entry.

## How to build/run/use the app

First to use the microservices. Modify the properties files

subscriptions\subscription-service\src\main\resources\aplication.properties
Complete:
```
spring.datasource.url=jdbc:mysql://localhost:3306/schema
spring.datasource.username=username
spring.datasource.password=password
```
subscriptions\email-service\src\main\resources\aplication.properties
```
spring.mail.username=email@gmail.com
spring.mail.password=password
```

Consider
- gmail permissions
- disable antivirus

### Build app:

To build and package the applications into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.

mvn package

### Run app:
To run the applications from a command line in a Terminal window you can you the java -jar command. 

java -jar target/subscription-0.0.1-SNAPSHOT.jar

*Do this (Build and Run) for the three microservices. One by one.


### Use app:
Example:
The application should only be accessible by the public service but for now it is possible to test all three:

### Public Service:
Post: localhost:8080/subscription
JSON(application/json)
```
{
	"email":"mail@outlook.es",
	"firstName":"as",
	"gender":"MALE",
	"dateOfBith":"2000-04-28",
	"flagNewsletter": true,
	"idCampaign":1
}
```
Return: Valid

### Subscription Service:
Post: localhost:8001/subscription
JSON(application/json)
```
{
	"email":"ph",
	"firstName":"as",
	"gender":"MALE",
	"flagNewsletter": true,
"dateOfBith":"2000-04-28",
	"idCampaign":1
}
```
Return: 16  -> number id in database

### Email Service:
Post: localhost:8002/sendemail?email=mail@outlook.es
Return: 
{
   "message": "Request is under process"
}

## Future tasks

#### Public Service:
- Data validation: create a specific class for validation.
#### Subscription Service:
- Check Email: if it already exists.
#### Email Service:
- Email Format: A template should be made for the mail to be sent. 

#### Generic:
- Security: SSL certificate
- Interconnect microservices: Eureka Server
- Docker-compose
- Exceptions
