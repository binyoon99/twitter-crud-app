# twitter-crud-app
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
Before running the Twitter CLI app, you need to create a Twitter Developer account with OAuth 1.0 and Twitter API 1.1.
Enabling location information on your Twitter account is also a must.
You should then acquire and record the following four keys and tokens:


Use [Spring initializer](https://start.spring.io/) to generate spring boot project and IntelliJ IDEA for IDE.
Download and use  [Postman](https://www.postman.com/) to test Twitter APIs.

# Twitter API Standard v1.1
## [Post, retrieve, and engage with Tweets](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview)

### CREATE: [POST statuses/update](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update)
Updates the authenticating user's current status, also known as Tweeting
Resource URL : `https://api.twitter.com/1.1/statuses/update.json`

Limit : 300 tweets or retweets per 3hours

### READ: [GET statuses/show/:id](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/get-statuses-show-id)
Returns a single Tweet, specified by the id parameter.

Resource URL : `https://api.twitter.com/1.1/statuses/show.json`

![screenshot1](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot1.png)

### UPDATE : Disabled

### DELETE: [POST statuses/destroy/:id](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-destroy-id)
Destroys the status specified by the required ID parameter. The authenticating user must be the author of the specified status. Returns the destroyed status if successful.

Resource URL : `https://api.twitter.com/1.1/statuses/destroy/:id.json`

![screenshot2](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot2.png)
![screenshot3](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot3.png)
![screenshot4](https://github.com/binyoon99/twitter-crud-app/blob/main/assets/screenshot4.png)


# Object Model
## [Tweet Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/tweet)
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

## [Entities Object](https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/object-model/entities)
"hashtags",
"user_mentions"

## [User Mention Object]
"id",
"id_str",
"indices",
"name",
"screen_name"

## [coordinates]
"coordinates",
"type"

## [Hashtag Object]
"text",
"indices"

