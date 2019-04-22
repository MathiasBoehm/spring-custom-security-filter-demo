# spring-custom-security-filter-demo
A Demo Application, which demonstrates the use of a custom Spring Security Filter



## Successful Call as a known user: 'user'

```shell
curl -H "x-user: user" localhost:8080/demo | jq '.'
```

## Unsuccessful Call as a known user: 'user'

```shell
curl -H "x-user: user" localhost:8080/admin | jq '.'
```

## Successful Call as a known user: 'admin'

```shell
curl -H "x-user: admin" localhost:8080/admin | jq '.'
```

## Unsuccessful Call as an unknown user

```shell
curl -H "x-user: unknown" localhost:8080/demo | jq '.'
```
