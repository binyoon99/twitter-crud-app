
# Introduction
The Twitter CRUD Application is an app that allows users to search, post, and delete tweets via official Twitter REST APIs on a Twitter Developer Account. The app uses the Model-View-Controller (MVC) architectural pattern, which includes models (DTO), service layer, controller layer, and data access (DAO) layer. Each layer of the application was tested using both JUnit and Mockito frameworks. Additionally, the app uses the Spring framework to manage dependencies by implementing the Inversion of Control (IoC) principle. The application was also dockerized and deployed on the Docker Hub Registry.

### Technologies Used:
- Core Java
- JDBC
- Maven
- Twitter REST APIs
- Postman
- HTTP Clients & JSON libraries
- Spring
- JUnit & Mockito
- Docker

# Quick Start
## Setups & Environment Variables
Before running the Twitter CLI app, you need to create a Twitter Developer account with OAuth 1.0 and Twitter API 1.1. Enabling location information on your Twitter account to use Geo location. 

You should then acquire and record the following four keys and tokens:

1. API key : consumerKey
2. API key secret : consumerSecret
3. Access Token : accessToken
4. Access Token Secret : tokenSecret


Use [Spring initializer](https://start.spring.io/) to generate spring boot project and IntelliJ IDEA for IDE.
Download and use  [Postman](https://www.postman.com/) to test Twitter APIs. 

# Twitter API Standard v1.1
## Post, retrieve, and engage with Tweets

[Twitter v1.1 API Doc](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview)
### CREATE

- [POST statuses/update](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update): updates the authenticating user's current status, also known as Tweeting.

  - Resource URL : `https://api.twitter.com/1.1/statuses/update.json`

  - Limit : 300 tweets or retweets per 3hours

### READ

- [GET statuses/show/:id](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/get-statuses-show-id) : returns a single Tweet, specified by the id parameter.
  - Resource URL : `https://api.twitter.com/1.1/statuses/show.json`
![screenshot1](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot1.png)

### UPDATE 
- Twitter doesn't allow developers to update posted tweets

### DELETE

- [POST statuses/destroy/:id](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id) : destroys the status specified by the required ID parameter. The authenticating user must be the author of the specified status. Returns the destroyed status if successful.
  - Resource URL : `https://api.twitter.com/1.1/statuses/destroy/:id.json`

![screenshot2](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot2.png)
![screenshot3](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot3.png)
![screenshot4](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot4.png)


# Concepts
- Serialization: to convert its state to a byte stream so that the byte stream can be reverted into a copy of the object.
    - Ex. Saving Java Object to a JSON file
- Deserialization: the process of converting the serialized form of an object bak into a copy of the object
  - Ex. Read a JSON file and convert to an Object
  - used [Jackson JsonParser](https://fasterxml.github.io/jackson-core/javadoc/2.8/com/fasterxml/jackson/core/JsonParser.html)
- Parsing : the process of analyzing a string of symbols, either in natural language, computer  languages or data structures, conforming to the rule of a formal grammar.
    - Ex. used [PercentEscaper](https://guava.dev/releases/19.0/api/docs/com/google/common/net/PercentEscaper.html)
- Marshal/Unmarshal
    - the process of transforming the memory representation of an object to a data format suitable for storage or transmission, and it is typically used when data must be moved between different parts of a computer program or from one program to another.

# Design
This app was designed with the MVC (model-view-controller) architecture; however, view (front-end) will develop as a separate project using React.js (Client-side-rendering).

### UML Diagram

![screenshot5](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot5.jpg)

There is 4 layers to the application and Spring framework will be used to manage all the dependencies.
- Models
  - ex. tweet object, entities object, and etc
- Controller Layer
  - ex. handles user input/arguments and pass the refined request to service layer to get results.
- Service Layer
  - ex. like a business layer, it gives restriction on the data (tweet needs to be less than 100 characters)
- Data Access Layer (DAO)
  - ex. dao layer handles the `Model` objects and perform save/show/delete on the tweets.
    - depends on HTTPClient layer


# Object Models

[Tweet Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/tweet)

| Attribute      | Type        | Descriptions                                                                                    | Example                                                                                                                |
|----------------|-------------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| created_at     | String      | UTC time when this Tweet was Created                                                            | "created_at": "Wed Oct 10 20:19:24 +0000 2018"                                                                         |
| id             | Int64       | The integer representation of the unique identifier for this Tweet.                             | "id":1050118621198921728                                                                                               |
| id_str         | String      | The string representation of the unique identifier for this Tweet.                              | "id_str":"1050118621198921728"                                                                                         |
| text           | String      | The actual UTF-8 text of the status update                                                      | "text":"Hello World, Bin"                                                                                              |
| entities       | Entities    | Entities which have been parsed out of the text of the Tweet.                                   | `"entities":{"hashtags":[],"urls":[],"user_mentions":[],"media":[],"symbols":[]"polls":[]}` |
| coordinates | Coordidnates | Represents the geographic location of this Tweet as reported by the user or client application | `"coordinates":{"coordinates":[-75.14310264,40.05701649],"type":"Point"}` |
| retweet_count  | Int         | Number of times this Tweet has been retweeted. Example:                                         | "retweet_count":314                                                                                                     |
| favourite_count | Integer     |  Indicates approximately how many times this Tweet has been liked by Twitter users.             | "favorite_count":200                                                                                                    |
| favorited      | Boolean     |  Indicates whether this Tweet has been liked by the authenticating user                         | "favorited":true                                                                                                        |
| retweeted      | Boolean     | Indicates whether this Tweet has been Retweeted by the authenticating user                      | "retweeted":false                                                                                                       |
                                                                                                                       
 Generated by [Markdown Table Editor](https://tableconvert.com/markdown-generator)

[Entities Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/entities)
"hashtags",
"user_mentions"

[User Mention Object]
"id",
"id_str",
"indices",
"name",
"screen_name"

[coordinates]
"coordinates",
"type"

[Hashtag Object]
"text",
"indices"


# Unit Test (Junit4 / Mockito)

## DAO layer test

[TwitterHttpHelperTest.java](https://github.com/binyoon99/twitter-crud-app/blob/main/src/test/java/com/binyoon/apps/twitter/dao/TwitterHttpHelperTest.java)
- [x] httpPost() 
- [x] httpGet() 

