---
title: TwoBrains API Reference

language_tabs:
  - code

toc_footers:
  - <a href='mailto:development@twobrains.tempestasludi.com'>Contact the developers</a>

includes:

search: true
---

# Introduction

Welcome to the documentation for the TwoBrains Web API! You can use our API to create a fully functional TwoBrains application. 

While building this API we followed the RESTful guidelines strictly. This resulted in a straight forward web API that uses standard HTTP messages. Because of this, our API is completely platform independent. You are not limited in any way in creating your TwoBrains application.

# General

A client can get information from the server by doing requests on the different endpoints. The server then responds with the requested information in JSON format. JSON is the only dataformat used. 

We'll cover all the possible requests a client can do and all different objects a client can receive and is expected to send, but first we'll deal with the topic of authorization.

# HTTP requests

All requests should be done on the same server where this html page is hosted, on port 80.

## Authorization

Every single http request must use Basic Authentication. This means that every http message must contain a Authorization request-header field.

From <a href=https://en.wikipedia.org/wiki/Basic_access_authentication#Client_side>Wikipedia</a>:

The Authorization field is constructed as follows:
  1.  Username and password are combined into a string "username:password".
  2.  The resulting string is then encoded using the RFC2045-MIME variant of Base64, except not limited to 76 char/line.
  3.  The authorization method and a space i.e. "Basic " is then put before the encoded string.

This may look as follows:

`Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==`

If the Authorization field is correct, the server will start looking at the request itself and respond with either the requested data or a 400 Bad Request.

> A http request containing an Authorization field may look like

```code
GET /user HTTP/1.1
Authorization: Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==
Content-Length: 0
Content-Type: text/json
Date: Wed Jan 13 22:18:32 CET 2016
```

> When the Authorization field is not supplied, the server responds with a message similar to

```code
HTTP/1.1 401 UNAUTHORIZED
Date: Mon, 18 Jan 2016 20:14:15 GMT
Content-Length: 0
WWW-Authenticate: Basic realm="Fake Realm"
Access-Control-Allow-Credentials: true
```

> When the Authorization field is supplied, but the username is not known or the password is incorrect

```code
HTTP/1.1 401 UNAUTHORIZED
Date: Mon, 18 Jan 2016 20:19:15 GMT
Content-Length: 0
WWW-Authenticate: Basic realm="Fake Realm"
Access-Control-Allow-Credentials: true
```

## Signing up a new user

To sign up a new user, one has to do a PUT request on /user. The body of the request should contain a JSON representation of a user. Also in this first message, Basic Authentication is used.

> A http request to register a new user may look like

```code
PUT /user HTTP/1.1
Authorization: Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==
Content-Length: 162
Content-Type: text/json
Date: Wed Jan 13 22:28:26 CET 2016

{"gradeList":[{"grade":"9","courseId":"TI1216"},{"grade":"10","courseId":"TI1206"}],"postalCode":"AndyAntwerpen","description":"slimme man","username":"azaidman"}
```

If registering a user was succesful, the server will respond with a statuscode 200 and a JSON object in the body with a boolean field 'succes'. If not, registering was not succesful.

> A response after success may look like

```code
HTTP/1.1 200 OK
Date: Mon, 18 Jan 2016 20:27:33 GMT
Content-Length: 0
Access-Control-Allow-Credentials: true
```

> A response after failure may look like

```code
HTTP/1.1 301 MOVED PERMANENTLY
Location: http://api.tempestasludi.com/user
```

## Updating user information and settings

Updating user information goes in the same way as signing up a new user: one does a PUT request on /user. The body of the message should again contain a JSON representation of a user.

> A http request changing user information may look like

```code
PUT /user HTTP/1.1
Authorization: Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==
Content-Length: 162
Content-Type: text/json
Date: Wed Jan 13 22:28:26 CET 2016

{"gradeList":[{"grade":"9","courseId":"TI1216"},{"grade":"10","courseId":"TI1206"}],"postalCode":"AndyAntwerpen","description":"slimme man","username":"azaidman"}
```

## 'Logging in', or checking a users credentials

As this API is RESTful, the server doesn't know the state of it's clients and treats all message individually. You can still ask the server to check the correctness of credentials, however. To do so, one simply sends a request to any endpoint, such as /user. The server will then check the credentials. If the statuscode of the response is 401 - Unauthorized, the credentials are incorrect. If not, the server responds with the requested content. The client can ofcourse simply drop the body of the received message, as the contents of the message are irrelevant.

> A http request checking if a user can log in may look like

```code
GET /user HTTP/1.1
Authorization: Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==
Content-Length: 0
Content-Type: text/json
Date: Wed Jan 13 22:18:32 CET 2016
```

## Requesting chatmessages

To request chatmessages a client does a GET request on /chat. The server will then send a JSON representation of all chatmessages to client. 

> A http request requesting new chatmessages may look like

```code
GET /chat HTTP/1.1
Authorization: Basic dXNlcm5hbWUxOnBhc3N3b3Jk
Content-Length: 0
Content-Type: text/json
Date: Mon Jan 18 21:38:40 CET 2016
```

A client will probably want to check for new chatmessages regularly, so some form of polling is a logical thing to do.

> A http response containing chatmessages may look like

```code
HTTP/1.1 200 OK
Date: Mon, 18 Jan 2016 20:27:33 GMT
Content-Length: 247
Content-type: text\json
Access-Control-Allow-Credentials: true

{"messages":[{ "receiver":"Stefan","sender":"Andy","message":"Hoe heeft A1-2 het gedaan?",{"receiver":"Stefan","sender":"Andy","message":"Hoe heeft A1-2 het gedaan?" },{"receiver":"Stefan","sender":"Andy","message":"Hoe heeft A1-2 het gedaan?" }]}
```

> A http response containing no new chatmessages may look like

```code
HTTP/1.1 200 OK
Date: Mon, 18 Jan 2016 20:27:33 GMT
Content-Length: 0
Content-type: text\json
Access-Control-Allow-Credentials: true
```

## Sending chatmessages

To send a chatmessage a client does a PUT request on /chat. The server will then expect a JSON representation of a chatmessage in the body of the message.

> A http request sending a chatmessage may look like

```code
PUT /chat HTTP/1.1
Authorization: Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==
Content-Length: 64
Content-Type: text/json
Date: Wed Jan 13 22:34:29 CET 2016

{"receiver":"Inhoud","sender":"Verzender","message":"Ontvanger"}
```

## Requesting matches

To request a match which is being made on the server, a client does a GET request on /match. The server then responds with a JSON representation of matches.

> A http request requesting new matches may look like

```code
GET /match HTTP/1.1
Authorization: Basic dXNlcm5hbWUxOnBhc3N3b3Jk
Content-Length: 0
Content-Type: text/json
Date: Mon Jan 18 21:41:26 CET 2016
```

Requesting new matches may also be done regularly, similar to requesting new chatmessages.

> A http response sending new matches may look like

```code
HTTP/1.1 200 OK
Date: Mon, 18 Jan 2016 20:27:33 GMT
Content-Length: 301
Content-type: text\json
Access-Control-Allow-Credentials: true

[{"approved":false,"matchUsername":"matchUsername","id":45631912,"seen":false,"username":"username"},{"approved":false,"matchUsername":"matchUsername","id":45631912,"seen":false,"username":"username"},{"approved":false,"matchUsername":"matchUsername","id":45631912,"seen":false,"username":"username"}]
```

> A http response containing no new matches may look like

```code
HTTP/1.1 200 OK
Date: Mon, 18 Jan 2016 20:27:33 GMT
Content-Length: 0
Content-type: text\json
Access-Control-Allow-Credentials: true
```

## Responding to matches

//TODO

## Possible error codes

The error codes used with this API are standard http error codes. The ones used most are

Error Code | Meaning
---------- | -------
400 | Bad Request -- Your request sucks
401 | Unauthorized -- Basic Authentication field is not supplied or is wrong
403 | Forbidden -- The endpoint requested is not available/used
404 | Not Found -- The requested resource could not be found
405 | Method Not Allowed -- You tried to access an invalid method
406 | Not Acceptable -- You requested a format that isn't json
410 | Gone -- The item requested has been removed from our servers
418 | I'm a teapot
429 | Too Many Requests -- You're doing too many requests.
500 | Internal Server Error -- We had a problem with our server. Please try again later.
503 | Service Unavailable -- We're temporarially offline for maintanance. Please try again later.

# Data objects

All objects are represented in JSON and are sent in the body of a http response. Always check the received status code first, then try to read the possible JSON data.
Whitespace is used only to make the JSON more readable to the programmer. Normally, whitespace is omitted.

## User

A user object is used to register a new user on the server and to send/receive preferences and grades for courses.

> A user object represented in JSON may look like

```code
{ "gradeList":  [ { "grade":"9",
                    "courseId":"TI1216" },
                  { "grade":"10",
                    "courseId":"TI1206" } ],
  "postalCode": "1234AB",
  "description":"This is my bio.",
  "username":   "azaidman" }
```

## Chatmessage

> A chatmessage object represented in JSON may look like

```code
{ "receiver":"Stefan",
  "sender":"Andy",
  "message":"Hoe heeft A1-2 het gedaan?" }
```

## Match

> A match object represented in JSON may look like

```code
{ "approved":false,
  "matchUsername":"matchUsername",
  "id":45631912,
  "seen":false,
  "username":"username" }
```

## Course

> A course object represented in JSON may look like

```code
{ "name":"OOP Project",
  "id":"TI1216",
  "program":"TI" }
```

## Grade

> A grade object represented in JSON may look like

```code
{ "grade":"8",
  "courseId":"TI1216" }
```

## Program

> A program object represented in JSON may look like

```code
{ "courses":  [ { "name":"OOP Project",
                  "id":"TI1216",
                  "program":"TI" } ],
  "name":"Technische Informatica",
  "id":"TI",
  "faculty":"EWI" }
```

## Faculty

> A faculty object represented in JSON may look like

```code
{ "name":"Electrical Engineering, Mathematics and Computer Science",
  "id":"EWI",
  "programs": [ { "courses":[ { "name":"OOP Project",
                                "id":"TI1216",
                                "program":"TI" },
                              { "name":"OOP",
                                "id":"TI1206",
                                "program":"TI" } ],
                  "name":"Technische Informatica",
                  "id":"TI",
                  "faculty":"EWI" } ] }
```