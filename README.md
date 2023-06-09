
# Introduction : About the Project 
The Twitter CRUD Application is an app that allows users to search, post, and delete tweets via official Twitter REST APIs on a Twitter Developer Account. The app uses the Model-View-Controller (MVC) architectural pattern, which includes models (DTO), service layer, controller layer, and data access (DAO) layer. Each layer of the application was tested using both JUnit and Mockito frameworks. Additionally, the app uses the Spring framework to manage dependencies by implementing the Inversion of Control (IoC) principle. The application was also dockerized and deployed on the Docker Hub Registry.

## Technologies Used:
- Core Java
- Oauth and HttpClient
- Maven
- Twitter REST APIs
- Postman
- HTTP Clients & JSON libraries
- Spring
- JUnit & Mockito
- Docker
- IntelliJ 

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

# Project Structure and Design 
This app was designed with the MVC (model-view-controller) architecture; however, view (front-end) will develop as a separate project using React.js (Client-side-rendering).

## UML Diagram

![screenshot5](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot5.jpg)

There is 4 layers to the application and Spring framework will be used to manage all the dependencies. 
### Utility
  - `JsonUtil.java` : String to object, and object to String
  - `TweetUtil.java` : TweetUtil creates tweet entity with given text, longitude, and latitude
### Models
  -  tweet, entities, coordinate, user-mention, and hashtag object.
### Data Access Layer (DAO)
  - `TwitterHttpHelper.java` : manage Oauth and communicate with TwitterAPIs it has post/get call methods
  - `TwitterDao.java` : construct uri with entity parameters then calls `TwitterHelper` `post/get` methods with the uri.
    - Uses http response from `TwitterHelper` to validate the response ( HTTP code :200, 404, 403 and etc) 
    - Convert the http response to Tweet object

### Service Layer
  - ex. like a business layer, it gives restriction on the data 
    1. check if the tweet text exceeds 280 characters
    2. if lon/lat is range from -90 to 90 for latitude and -180 to 180 for longitude.
    3. When you search for a Tweet, you need to check if user input IDs are in the correct format.

### Controller Layer
- ex. handles user input/arguments and pass the refined request to service layer to get results.

### Main App Layer



## Object Models

Generated by [Markdown Table Editor](https://tableconvert.com/markdown-generator)

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

[Entities Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/entities)

| Attribute     | Type                     | Descriptions                                                      | Examples                                                                                                                                    |
|---------------|--------------------------|-------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| hashtags      | Array of Hashtag Objects | Represents hashtags which have been parsed out of the Tweet text. | `{ "hashtags": [ { "indices": [ 32, 38 ], "text": "nodejs" } ] }`                                                                           |
| user_mentions | Array of Mention Objects | Represents other Twitter users mentioned in the text of the Tweet | `{ "user_mentions": [ { "name": "Twitter API", "indices": [ 4, 15 ], "screen_name": "twitterapi", "id": 6253282, "id_str": "6253282" } ] }` |

[User Mention Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/entities#mentions)

| Attribute   | Type         | Description                                                                                                 | Examples                  |
|-------------|--------------|-------------------------------------------------------------------------------------------------------------|---------------------------|
| id          | Int64        | ID of the mentioned user, as an integer                                                                     | "id" : 625382             |
| id_str      | String       | If of the mentioned user, as a string.                                                                      | "id_str":"6253282"        |
| indices     | Array of Int | An array of integers representing the offsets within the Tweet text where the user reference begins and ends. | "indices":[4,15]          |
| name        | String       | Display name of the referenced user.                                                                        | "name":"Twitter API"      |
| screen_name | String       | Screen name of the referenced user.                                                                         | "screen_name":"twitterapi" |

[coordinates](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/geo#coordinates)

| Attribute   | Type                             | Description                                                                                                                                                                                 | Example                                                                                                                               |
|-------------|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| coordinates | Array of Array of Array of Float | A series of longitude and latitude points, defining a box which will contain the Place entity this bounding box is related to. Each point is an array in the form of [longitude, latitude]. | `{ "coordinates": [ [ [ -74.026675, 40.683935 ], [ -74.026675, 40.877483 ], [ -73.910408, 40.877483 ], [ -73.910408, 40.3935 ] ] ] }` |
| type        | String                           | The type of data encoded in the coordinates property.                                                                                                                                       | "type":"Polygon"                                                                                                                      |

[Hashtag Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/entities#hashtags)

| Attribute | Type         | Description                                                                                                                                                                                                                                                                                                                                                                                                 | Example           |
|-----------|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|
| indices   | Array of Int | An array of integers indicating the offsets within the Tweet text where the hashtag begins and ends. The first integer represents the location of the # character in the Tweet text string. The second integer represents the location of the first character after the hashtag. Therefore the difference between the two numbers will be the length of the hashtag name plus one (for the ‘#’ character).  | "indices":[32,38] |
| text      | String       | Name of the hashtag, minus the leading ‘#’ character.                                                                                                                                                                                                                                                                                                                                                       | "text":"nodejs"   |


# Unit Test (Junit4 / Mockito)

## DAO layer test

[TwitterHttpHelperTest.java](https://github.com/binyoon99/twitter-crud-app/blob/main/src/test/java/com/binyoon/apps/twitter/dao/TwitterHttpHelperTest.java)
- [x] httpPost() 
- [x] httpGet()

[TwitterDaoIntTest.java](https://github.com/binyoon99/twitter-crud-app/blob/main/src/test/java/com/binyoon/apps/twitter/dao/TwitterDaoIntTest.java)
- [x] create()
  - hashtag is not recognizable in the api call, perhaps it is deprecated in v1.1 api .
- [x] deleteById()
  - There was HTTP 403 bug, took me 2 hours to find out I was doing Get method instead of Post method. 

[TwitterDaoUnitTest.java](https://github.com/binyoon99/twitter-crud-app/blob/main/src/test/java/com/binyoon/apps/twitter/dao/TwitterDaoUnitTest.java)
- Used Mockito framework : mock `TwitterHttpHelper` and injectmock `TwitterDao`
- [X] showTweet()
- [ ] postTweet()
- [ ] deleteTweet()

## Service layer test

## Controller layer test


# RoadMap
- [] Support Multiple - languages 
- [] Develop Front-end
- [] Integrate to Twitter API v2

# License

# Contact
Heather(Hyebin) Yoon - binyoon99@gmail.com
Project Link: https://github.com/binyoon99/twitter-crud-app

# Acknowledgments
[Spring initializer](https://start.spring.io/)
[Postman](https://www.postman.com/)
[Twitter v1.1 API Doc](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview)
[Jackson JsonParser](https://fasterxml.github.io/jackson-core/javadoc/2.8/com/fasterxml/jackson/core/JsonParser.html)
[PercentEscaper](https://guava.dev/releases/19.0/api/docs/com/google/common/net/PercentEscaper.html)
[DockerHub](https://hub.docker.com/)
[Markdown Table Editor](https://tableconvert.com/markdown-generator)
[Github Markdown Document](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
[draw.io](https://app.diagrams.net)