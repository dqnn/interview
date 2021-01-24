# Overview
* The page is to demostrate How we design a online chat Application, from simple to sophsicated

# Requirements
* Support Individual chat, group chat, include send/recieve/view individual and group msg, also highlight unread msgs
* support link preview
* support emoj, msg reply, multimedia msg
* msg notification

# Interview breakdown
* 10m use case talk
* 10m constrains
* 10 mins: Architecture
* 10 mins: API Design
* 10 mins: DB choice
* 10 mins: Data model

## Use Case talk
* User can see their msg last 3 days, if more, he can retrieve from remote servers. also would see the chat sessions, group chat and individual chat are the same.
* User see group msgs by msg timestamp
* User can also CRUD groups, friends, msgs
* User can register/authentication/
* User can search msgs
* User can use emoj to other msgs. like thumbs up, sad, etc
## Constrains, main is DB read/write
* DAU: 10M active users 
* peak msgs count: 10M * 100 msgs/person * 8.64(peak factor)/86400 = 10K msgs/s,
* one msg size: 1 KB
* storage:  1K * 10K  = 10 M/s, one year is 10 * 86400 * 365 = 315T
* througput:   10M/s, notes: some addtional capacity is needed
* group member limit: < 1000 memembers
* Latency: 
* CPU, RAM, Disk < 50%, avg shoud be 30% ~ 40% 
## Scale
 * QPS, > 1000, should have more than 2 hosts? why? 
 * Data size, rentation policy, how longer we need to keep? 
# Arch (breakdown into microservices)
![Arch](https://github.com/dqnn/interview/blob/master/doc/system-design-questions/designSlacks.png)
 
 
 
