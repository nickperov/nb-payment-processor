# Non-blocking payment processor

The application simulates the work of a payment processor, which can execute a huge number of payments simultaneously. However, there is a particular delay that can be configured for the execution of each payment.    

Application utilises technology of java virtual threads.

## Run-build requirements

Application written in Kotlin employs Quarkus, the Supersonic Subatomic Java Framework.

In order to utilize Java virtual threads it required to use **JDK 19** or later versions. For **JDK 19** and **JDK 20** java virtual threads feature is in preview status. 
To enable preview feature the following JVM arguments need to be passed to both `javac` and `java`:
```
--enable-preview --add-opens java.base/java.lang=ALL-UNNAME
```
## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw clean package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw clean package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java --enable-preview --add-opens java.base/java.lang=ALL-UNNAMED -jar target/*-runner.jar`.

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin

## APIs

* To get current execution delay: GET - /payment/delay
* To set current execution delay: PUT - /payment/delay/{delay}
* To get current execution success rate: GET - /payment/success-rate
* To set current execution success rate: PUT - /payment/success-rate/{rate}
* To execute payment: POST - /payment/execute

To get open API contract - run in dev mode and navigate to - http://localhost:8088/q/swagger-ui/

## Authors

Nikolay Perov

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
