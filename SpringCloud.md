### Spring Cloud Configuration
Spring Cloud Config is Spring's client/server approach for storing and serving distributed configurations across multiple applications and environments. This configuration store is ideally versioned under Git version control and can be modified at application runtime. While it fits very well in Spring applications using all the supported configuration file formats together with constructs like Environment.

Multiple services can get properties from its own properties file, as well from centralized Git properties file. If service A has properties file (bootstrap.properties) and contains keys a,b,c and centralized properties file (service-dev.properties) also contains a,b,c then service A will get properties from its bootstrap.properties file. If key *b* is missing from bootstrap.properties, then properties will be retrieved from Git serivce-dev.properties. Furthermore, if *b* is missing from service-dev.properties, then the default properties file can contain *b* and the value of it will be retreived from centralized service.properties.

### Feign
Feign aims at simplifying HTTP API clients. Simply put, the developer needs only to declare and annotate an interface while the actual implementation is provisioned at runtime.
