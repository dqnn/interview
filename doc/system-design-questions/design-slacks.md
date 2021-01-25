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
## Channel Service (Session Service)
* serve all request from user, more like a API Gateway
### APIs(To be continued...)
- GetChannels(UserId)
  - store last viewed channel; 
  - store visible channels;recent direct msgs, memberships
  - show unread msgs/last msg preview; denormalized data
- SendMessage(SenderId, revciever_Id, message_type, Message, mediaId)
  - message_type is enum[individual message, group message]
  - create_timestamp, updated_timestamp, expire_timestamp, STATE will be appended by channel service
  - state will enum(recieved, sent, viewed, notification_sent, deleted), the multimedia content needs to be processed, it would need sometime between recieved and sent
- updateMessage(message_Id, message)/delete

![Arch](https://github.com/dqnn/interview/blob/master/doc/system-design-questions/designSlacks.png)
### Internal APIs
- Image APIs
  - processMedia(mediaId)
     - replica media
     - postprocess: generate thumbail/preview
     - distribute to CDN
- Notification APIs
  - notifyUser(userId, send_id, message_Id, msg_preview)
    - either use db or kafka
    - exactly once is difficult, requiring two phrase commit(decrease perf), see XA protocol, https://en.wikipedia.org/wiki/X/Open_XA, thining about this case: one user installed slacks on phone and desktop, then we should the notification to phone and desktop(ready), if he saw the message on phone then on desktop, we should not send this notification or it should mute this notification immediately,(commit), also see https://www.educative.io/edpresso/what-is-the-two-phase-commit-protocol
    
# Data Model
## User Table (SQL)
| Field      | Description |
| ----------- | ----------- |
| Id(shard key) | Title       |
| name          | Text        |
| status          | Text        |
* online status can be detected by websocket. status can be in another table
## Friend Table (SQL)
| Field      | Description |
| ----------- | ----------- |
| user_id1    | Title       |
| user_id2    | Text        |
| connected_date | Text     | 
| last view date user1 | 16234556,process highlight unread message   |
*  you can query user_id1's all friends, 1 see 2's message last read, 2 see 1's message last read. 2nd reason is sharding easily,  this timstamp cannot be on message table
## Group Table (SQL)
| Field      | Description |
| ----------- | ----------- |
| group_id    | Title       |
| user_id    | Text        |
| joined_date | Text     | 
| role| admin/user   | 
| last_view date | Text     | 
## channel table (Redis)
| Field      | Description |
| ----------- | ----------- |
| key    | Denny(userId)       |
| value | [Davlid:{unread_msg:10;last_view_timestamp:1111}, Uncle:{unread_msg:10;last_view_timestamp:1111}]     | 
## Message storage Table (DynamoDB)
| Field      | Description |
| ----------- | ----------- |
| containerId(partition_key, groupId)    | Title       |
| timestamp -msg_id (sort_key)    | Text        |
| msg_id | Text     | 
| sender_id | Text     | 
| message | Text     |
| timestamp | Text     | 
* for group message, containerid is groupId, fir p2p chat, containerId is userId1_UserId2 sorted. 
## Emoji table (Redis)
| Field      | Description |
| ----------- | ----------- |
| key    | message_Id       |
| value | [Davlid:smile, Uncle:good]     | 
# Bottle Neck/Scale
* mysql shardding by zookeeper, spend sometime on zookeeper
* sharding, periodically cleanup
* throttling


 
 
 
