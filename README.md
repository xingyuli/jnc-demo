# jnc-demo

## How to run
```shell
$ cd $repository_dir/rest-api
$ gradle clean shadowJar
$ java -jar build/libs/rest-api-1.0-SNAPSHOT-all.jar
```

## API Usage:
### Step 1. client side should authenticate himself via with his info
```shell
$ curl -XPOST http://localhost:4567/api/client_auth -d '
> {"clientid":"jnc","clientsecret":"123456"}
> '

{"token":"482533680","nonce":"703920951","timestamp":1465287768674}
```

### Step 2. send order info via exposed api
temporarily demonstrate single order creation, could handle batch orders in reality

```shell
$ curl -XPOST http://localhost:4567/api/erp/order -d '
> {
>   "token": "482533680",
>   "externalOrderNo": "0001",
>   "dealer": "JNC-CD",
>   "projectNo": "PRJ_0001",
>   "boxNo": "b_0001",
>   "materialNo": "m_0001"
> }'

{"id":1,"tradeNo":"DEMO1","dealer":"JNC-CD","externalOrderNo":"0001","projectNo":"PRJ_0001","boxNo":"b_0001","materialNo":"m_0001"}
```

###NOTE: If token is missing or token verification failure, 403 would be returned
```shell
$ curl -i -XPOST http://localhost:4567/api/erp/order -d '
{
  "token": "482533680_not_match",
  "externalOrderNo": "0001",
  "dealer": "JNC-CD",
  "projectNo": "PRJ_0001",
  "boxNo": "b_0001",
  "materialNo": "m_0001"
}'

HTTP/1.1 403 Forbidden
Date: Tue, 07 Jun 2016 08:28:43 GMT
Content-Type: text/html;charset=utf-8
Transfer-Encoding: chunked
Server: Jetty(9.3.z-SNAPSHOT)
```
