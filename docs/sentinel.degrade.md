## Sentinel Degrade Rule 
The key json fields for degrade rule:

| Field               | Description                                                                               | Default              |
|---------------------|-------------------------------------------------------------------------------------------|----------------------|
| resource            | the resource on which the rule will be applied                                            | \                    |
| grade               | circuit breaking strategy: <br/>0. slow request rate<br/>1. error rate<br/>2. error count | 0, slow request rate |
| count               | upper-bound response time (ms) when grade is 0<br/> threshold when grade is 1 or 2        |                      |
| timeWindow          | breaking time in seconds                                                                  |                      |
| minRequestAmount    | the minimum calls required before breaking                                                | 5                    |
| statIntervalMs      | sliding window in million seconds                                                         | 1000                 |
| slowRationThreshold | threshold of the slow ratio, only available for slow ratio strategy(0)                    |                      |
| passCount           | consecutive count of slow request (only for slow request ratio)                           |                      |

An example:
```json
[
  {
    "resource": "demo",
    "count": 150,
    "grade": 0,
    "passCount": 0,
    "timeWindow": 10
  }
]
```