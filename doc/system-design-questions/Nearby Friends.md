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
- QPS: 10x10^6/86400 = 10 x 10 x1.25 =125 


