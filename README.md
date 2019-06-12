# Spring

Spring uses annotations to build dependencies between Classes.

Main application get Person bean

```
@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Demo1Application.class, args);
		ctx.getBean(Person.class);
	}
}

public interface Person {
	public String getName();
}

```
Person bean is marked as Component via annotation and autowires Id via constructor (constructor injection). Note that Manager and Employee are both Components of type Person. When Spring loads these classes it complain about loading these at the same time. To fix the issue @Primary annotation can be used to mark Component to be used.

Example of constructor injection

```
@Component
@Primary
public class Manager extends Person {
	@Autowired
	private Id id;
	
	public Person(Id id) {
		super();
		this.id = id;
	}
}

```

Example of setter injection

```
@Component
@Primary
public class Manager extends Person {
	private Id id;

	@Autowired
public void setId(Id id){
	this.id = id;
}	
}
```

It’s possible to omit setter altogether and injection will still work. This becomes the preferred way to manage dependencies as you can add all of them in the class without stacking them to the constructor’s arguments.

```
@Component
@Primary
public class Manager extends Person {
	@Autowired
	private Id id;
}

@Component
public class Employee extends Person {
	@Autowired
	private Id id;
	
	public Person(Id id) {
		super();
		this.id = id;
	}
}
```

Id is marked as Component

```
@Component
public class Id {
	public Id() {}
}
```

## Dependency Injection by Variable

If we are wiring to classes that share the same interface, one way as we saw earlier is to use @Primary annotation, however, we can also use Dependency Injection by Variable.
In the above example of interface Person, if we were to use this interface in another class, instead of marking Manager or Employee as @Primary, we can can name Person variable as person and autowiring will pick the right implementation:

```
Class Main {
	@Autowired
private Person manager;

@Autowired
private Person employee;
}
```

Note that variables names must correspond with class names for these entities. If we were to name employee variable as employee2, then we would get error:

Field manager2 in com.example.demo.Department required a single bean, but 2 were found:
	- developer: defined in file [/home/dev/dev/workspaces/spring-sandbox/demo-1/target/classes/com/example/demo/Developer.class]
	- manager: defined in file [/home/dev/dev/workspaces/spring-sandbox/demo-1/target/classes/com/example/demo/Manager.class]

If we have scenario where only one Person variable is defined, but Developer is marked as @Primary, then Developer will be loaded into Person interface variable.

```
class Main {
	@Autowired
	private Person manager;
}

@Component
@Primary
class Developer implements Person {

}
```

### Dependency Injection without @Primary or named variable

In above examples we can forgo using @Primary annotation or named variables and use @Qualifier annotation:

```
@Component
@Qualifier(“manager”)
public class Manager extends Person {
	@Autowired
	private Id id;
}


class Main {
	@Autowired
	@Qualifier(“manager”)
	private Person employee;
}
```

Here even though Manager class doesn’t have Primary and Person variable is not named after Manager class, Manager will still be loaded in Person variable because of use of @Qualifier.

### Bean Scope

Singleton: One instance per Spring context
Prototype:	New Bean whenever requested

Web Context:
Request:	One bean per http request
Session:	One bean per session request

Singleton is a default scope
When getting beans as:

ConfigurableApplicationContext ctx = SpringApplication.run(Demo1Application.class, args);
Department bean = ctx.getBean(Department.class);
Department bean2 = ctx.getBean(Department.class);

both instances bean and bean2 will be referred to the same object.


### Prototype scope
To get two different instances, we would annotate instantiating class with annotation:

@Scope(“prototype”) or @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

However, if the instances are class variables of another class, then this approach will not work. For example,

```
@Component
class Person {
	@Autowried
private JDBCConnection conn;

// getter
getJDBConnection() {
return conn;
}

// setter
}
```

If we wanted to get new JDBCConnection each time we call getJDBConnecton() on the same Person object, then we need to scope JDBCConnection in a different way:

```
@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE. proxyMode=ScopedProxyMode=TARGET_CLASS)
public class JDBCConnection {
	public JDBConnection(){
}}
```

### ComponentScan

By default Spring searches for @Component in the same package where @SpringBootApplication resides. However, if we want to scan for other packages as well, we need to use @ComponentScan:

```
package com.spring.example; // default component scan location

@SpringBootApplication
@ComponentScan(“com.spring.example.comonentscan”)
public class Demo1Application {	
	…
	…

}
```

@PostConstruct: called before component is added to container
@PostDestroy: called when component is removed from container


Component Annotations

Spring Component annotation is used to denote a class as Component. This is a broad annotation that does not tell us purpose of the class. For more granular description there are these annotations:

Service
Denotes that the class provides some services. Our utility classes can be marked as Service 
classes.

Repository
This annotation indicates that the class deals with CRUD operations, usually it’s used with DAO implementations that deal with database tables.

Controller 
Mostly used with web applications or REST web services to specify that the class is a front controller and responsible to handle user request and return appropriate response.

In addition to better describe function of the class, these annotations have more functionality than a @Component. For example, when you annotate a class @Repository, spring container understands it's a DAO class and translates all unchecked exceptions (thrown from DAO methods) into Spring DataAccessException. @Service doesn’t have any additional functionality.
Properties

Define properties file in something like resources folder:

@Configuration
@PropertySource("classpath:foo.properties")
public class PropertiesWithJavaConfig {
    //...
}

Then this property can be used in Class:

@Value( "${jdbc.url}" )
private String jdbcUrl;


More info https://www.baeldung.com/properties-with-spring

# Spring Actuator
Actuator is mainly used to expose operational information about the running application – health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.

## Loading changes to application without server restart
Add dependency on spring-boot-devtools:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
</dependency>
```
