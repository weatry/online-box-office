## Business Logic
See [Business Script file](BusinessScript.md) to understand the business flow.

## Technical point
### Spring Cloud Version
1. Seata was included in [Spring Cloud Alibaba](https://spring.io/projects/spring-cloud-alibaba#learn), 
so the best way to involve seata is adding Spring Cloud Alibaba. 
The following table shows the version relationship between Spring Cloud Alibaba and Spring Cloud, Spring Boot.

| Spring Cloud Alibaba | Spring Cloud | Spring Boot | Seata |
|----------------------|--------------|-------------|-------|
| 2021.0.1.0           | 2021.0.x     | 2.6.x       | 1.4.2 |
| 2.2.7.RELEASE        | Hoxton.SRx   | 2.3.x       | 1.3.0 |
 
2. Seata Server don't need to be the same version with Seata client. 

### Kyro for Seralization

Seata 1.4.2 will throw exception while updating 'datatime' column.
The reason is that SEATA uses Jackson framework to parse datetime data, but Jackson doesn't have appropriate parser for 'datetime'.
So 'seata.client.undo.log-serialization=kryo' is set to let SEATA use Kryo.

### Durid for Datasource

SEATA must generate a proxy for the datasource, which can give SEATA opportunities to parse SQL statement and generate undo SQL for the transaction.
But only when Durid is used as datasource, the proxy can be generated automatically. For other datasource type, the proxy needs to inject to spring container manually.
The proxy datasource is io.seata.rm.datasource.DataSourceProxy.