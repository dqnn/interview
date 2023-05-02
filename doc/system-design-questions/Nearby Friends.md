# Functional Requirements
- Enable Users to see their friends nearby, with name, distance and timestamp, the list should be updated every 5s
# Non functional requrements
- low latency, get location updates without much delay
- reliability, needs to be reliable, it is ok to have some data loss 
- eventually consistency 
# Back-of-envelop estimation 
- nearby friends are within 5 miles, which should be configurable, like 10,15, 25, 50 miles
- location refresh interval can be 30s, since human walking in 30s does not make much difference, walking can be 3-4 miles per hour
- 1 billion users can 100 million users are nearby every day
- 10 million can be DAU
- on averge, a user can have 400 friends, assume all of them are near by 
- display 20 nearby friends per page
- QPS: 10x10^6/30s = 3.3 x 10^5/s but we have to push 10m x 400 5rr

# Overall Arch
![image](https://user-images.githubusercontent.com/19477057/235686088-6b70f79c-8cbf-4b22-9cc2-ddf25ad4565e.png)
- Notes 
  - you will need to demonstrate this from two users, 1 just 2 have friends
  - Websocket vs restful API
  - websocket is full-duplex communications between clients and server, both  can send message while for restful, server is passive and only respond to its request. they both send message via one TCP connection, but resultful are stateless while websocket have states, both can transfer text message and unstructural data. websocket is small memory when we want low latency and huge updates from server/clients, and it requires no response. restful is a fit if there is stateless requests which does not require real-time communications
# Each componnet
## load balance 
- load balance is to distribute traffc to its hosts behind it, it has several ways to distribute traffic, for example, load based or evenly distributed etc
## user database, location databse history, location database cache, pub sub
- 
   


