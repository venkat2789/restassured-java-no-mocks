# restassured-java-no-mocks

A starter project and automation framework for api testing, using rest-assured and java. Uses api endpoints from [restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html) for tests. 

> Note: If you are looking for a way to avoid depending on external apis for tests, please check my other repo on api testing with mocks: [restassured-java-with-mocks](https://github.com/venkat2789/restassured-java-with-mocks)

Below are the tech-stack used:

Maven
- manage dependencies and plugins, integrate with CI/CD tools

JUnit4
- runners, annotations etc. Can be easily upgraded to JUnit5.

Tests
- simple tests validating a response
- tests with RequestSpecification
- tests using GPath to traverse through response JSON and get required values
- tests with Serialization and Deserialization

GIT
- source control and integration options

Jenkins
- integration options using Maven commands

Reports
- native surefire-reports included in xml and txt formats
- can be extended to html report by running appropriate maven commands `mvn site`
