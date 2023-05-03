# cxfCycle

### Projects

This project is separated in submodules using DDD as:

* app - A quarkus application
* infrastructure - A quarkus application to deal with the infrastructure layer (repository implementation, kafka, and etc)
* domain - A pure kotlin application with all domain logic 

### Project Checks

We use detekt static analyzer (`./gradlew detekt`). IntelliJ auto import feature 
breaks the WildCardImport rule. In order to stop it, follow [these instructions](https://blog.marcnuri.com/intellij-idea-how-to-disable-wildcard-imports).

We use [earthly](https://earthly.dev/) to run our CI/CD pipelines 

### Building the native executable:

` ./gradlew app:build -Dquarkus.package.type=native  -Dquarkus.native.container-build=true
`


