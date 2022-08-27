## Sentinel Degrade Rule 
The key json fields for degrade rule:

| Field           | Description                                                                                 | Default                              |
|-----------------|---------------------------------------------------------------------------------------------|--------------------------------------|
| resource        | the resource on which the rule will be applied                                              | \                                    |
| grade           | flow control strategy: <br/>0. concurrent thread count <br/>1. QPS                          | 1, QPS                               |
| count           | threshold of flow control                                                                   |                                      |
| limitApp        | flow control to a specific caller                                                           | default, means callers are not aware |
| strategy        | 0. direct<br/>1. relate<br/>2. chain                                                        | 0, direct                            |
| controlBehavior | control behavior:<br/>0. reject <br/>1. warmup<br/>2. rate limit<br/>3. warmup + rate limit | 0, reject                            |


### limitApp
limitApp is used to specify the caller that will apply the rule. the following piece of code is used to specify the caller's name:
```java
 ContextUtil.enter(resourceName, origin);
```
The parameter 'resourceName' represents the **_entrance_** of the invocation, 'origin' represents the caller's **_name_**.

limitApp has three options:
* default: not aware of the callers
* {caller}: only the specific caller will be applied the rule
* other: other callers than {caller}

Multiple rules can be defined for a single resource, the order of the effective rule: 
{caller}>other>default

### strategy
strategy defines how the rule will be applied to the resource.
* 0(direct): the rule will be applied directly
* 1(relate): the rule will be applied when the related resource is very busy
  ```
  Notes: related resource is defined by 'refResource'.
  If two resources have a rivalry relationship on a same thing, this stategy can be used to protect the most important one.
  For example, if read_db and write_db work on the same database, we setup rule for read_db and specify write_db as refResource.
  Then the rule will be applied to read_db when write_db is very busy. Since the access to read_db is limited, the access to write_db is protected effectively.
  ```
* 2(chain): only access from a specific entrance will be counted
  ```
  Notes: the entrance is specified by 'refResource'.
  The entrance is defined by "ContextUtil.enter(resourceName, origin);"
  It should be invoked before the beginning of the invocation.
  ```

### Example
```json
[
  {
    "resource": "obo-payment.pay",
    "grade": 1,
    "count": 1,
    "clusterMode": false,
    "controlBehavior": 0,
    "strategy": 0
  }
]
```
Resource is declared by annotation @SentinelResource:
```java
    @Transactional
    @SentinelResource("obo-payment.pay")
    public String payFor(PayRequest payRequest) {
        // Invoke Alipay service
        String alipayId = alipayClient.pay(payRequest);
        Payment payment = payRequest.toPayment();
        payment.setProvider(Payment.Provider.ALIPAY);
        payment.setPayId(alipayId);
        payment.setPayTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment.getId();
    }
```