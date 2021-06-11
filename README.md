# Spring

## Articles
 - http://websystique.com/spring/spring-dependency-injection-annotation-beans-auto-wiring-using-autowired-qualifier-resource-annotations-configuration/
 - [@ModelAttribute explanation and discussion](https://stackoverflow.com/questions/3423262/what-is-modelattribute-in-spring-mvc)

## Auto-Configuration
Spring uses Auto-Configuration mechanism to preconfigure classes that are on application's classpath. For example, if JDBC driver is put on classpath (using maven dependency, for example) then Spring will automatically configure JDBC template for application and make it avaiable to be used with @Autowried.

You need to opt-in to auto-configuration by adding the @EnableAutoConfiguration or @SpringBootApplication annotations to one of your @Configuration classes. You should only use one or another, not both.

Auto-configuration is non-invasive. At any point, you can start to define your own configuration to replace specific parts of the auto-configuration. For example, if you add your own DataSource bean, the default embedded database support backs away.

If you need to find out what auto-configuration is currently being applied, and why, start your application with the --debug switch. Doing so enables debug logs for a selection of core loggers and logs a conditions report to the console.

You can exclude particular class from auto configuration if you still want to participate in it except one class
```
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
```

## CommandLineRunner and ApplicationRunner
Spring Boot provides two interfaces, CommandLineRunner and ApplicationRunner, to run specific pieces of code when an application is fully started. These interfaces get called just before run() once SpringApplication completes.

### CommandLineRunner
Provides access to application arguments as string array
```
@Component

public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    @Override
    public void run(String...args) throws Exception {
        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
    }
}
```

### ApplicationLineRunner
Wraps the raw application arguments and exposes the ApplicationArguments interface, which has many convinent methods to get arguments, like getOptionNames() to return all the arguments' names, getOptionValues() to return the agrument value, and raw source arguments with method getSourceArgs()
```
@Component

public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Your application started with option names : {}", args.getOptionNames());
    }
}
```

### When to Use It

When you want to execute some piece of code exactly before the application startup completes, you can use it then. In one of our projects, we used these to source data from other microservices via service discovery, which was registered in Consul.

### Spring Anatomy
Spring uses annotations to build dependencies between Classes. https://www.baeldung.com/spring-dependency-injection explains different DI approaches in Spring well.

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

# Spring AOP
Can be thought of as middleware in execution of application. [See project spring-aop project](https://github.com/akravets/Spring/tree/master/spring-aop) for example.

**@Before** annotation is used to resolve access control on method execution fo example.
**@After** called after method is called, can be used for logging result of execution for example
**@AfterReturning** called after method is called, and also returns return value of execution
**@AfterThrowing** called if method threw an exception
**@Around** allows to execute code before and after method is called, so in essence it's like pause for method execution

For all these methods we need to keep defining weavers:
```@Before("execution(* com.example.helloworld.Person.getCar(..))")```

This presents problems for big projects where weavers need to be repeated. To solve this issue we can use @Pointcut to define one place for all our weavers:

```
package com.example.helloworld.confg;

public class CommonJoinPointConfig{
	@Pointcut("execution(* com.example.helloworld.Person.getCar(..))")
	public void car(){}
}
```
And to use this @Pointcut we would use car() method's qualifid name:

```
@Before("com.example.helloworld.confg.CommonJoinPointConfig")
```

# Database Support (JDBC)
Adding following dependencies will add JDBC support to Spring applicaton:

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

To enable h2 database we add ``` spring.h2.console.enabled=true ``` to application.properties file.

In resources folder adding ```data.sql``` database schema file will execute it when Spring application has started.

## Accessing H2 console
Navigate to http://localhost:8999/h2-console to access the console

![image](https://github.com/akravets/Spring/blob/master/database-jpa/jpa/src/main/resources/h2console.png)
#### Note JDBC url, this syntax is needed when using Spring Boot.
[Person](https://github.com/akravets/Spring/blob/master/database/src/main/java/com/akravets/spring/database/model/Person.java) class is the model of the database table. In order to communicate with the database we are using [PersonDAO](https://github.com/akravets/Spring/blob/master/database/src/main/java/com/akravets/spring/database/repository/PersonDAO.java) class that is used to query the database.

# Database Support (JPA)
Java Persistance API maps Java objects directly to database tables. Instead of creating SQL queries and mapping results to Java objects, JPA maps Java objects directly to database tables so that queries can be executed directly.

In Spring JDBC we had insert SQL query explicitly

```return template.query("select * from person where id=?", new Object[]{id});```

This is fine for small queries, but more complex queries are difficult to express in this syntax. Using JPA we can map objects directly to database tables:

```
@Entity // flags that this class maps to db table
@Table(name="person") // if name of class is different from db table name
public class Person {
    @Id // primary key
    @GeneratedValue // value should generated
    private int id;
    private String name;
    private String location;
    private Date birthday;

    // We need to have no-args constructor when using JPA
    public Person(){}

    public Person(int id, String name, String location, Date birthday) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }

    // A constuctor for our class where Id is generated
    public Person(String name, String location, Date birthday) {
        super();
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }
```

An we can execute our query as

```
@PersistenceContext
EntityManager entityManager;

public Person findById(int id){
        return entityManager.find(Person.class, id);
    }
```

Our Person class changes as follows

```
@Entity // flags that this class maps to db table
@Table(name="person") // if name of class is different from db table name
public class Person {
    @Id // primary key
    @GeneratedValue // value should generated
    private int id;
    private String name;
    private String location;
    private Date birthday;

    // We need to have no-args constructor when using JPA
    public Person(){}

    public Person(int id, String name, String location, Date birthday) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }

    // A constuctor for our class where Id is generated
    public Person(String name, String location, Date birthday) {
        super();
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }
```

JPA has the methods to insert and update records. The difference how database will be updated depends on data that is POJO being passed (https://github.com/akravets/Spring/blob/master/database-jpa/jpa/src/main/java/com/akravets/spring/database/jpa/repository/PersonRepository.java)

```

    /**
     * Insert new record
     * @param person {@link Person} to be added
     * @return
     */
    public Person insert(Person person){
        return entityManager.merge(person);
    }

    /**
     * Update record
     * @param person {@link Person} to be updated
     * @return
     */
    public Person update(Person person){
        return entityManager.merge(person);
    }
```

# SpringMVC
In Spring MVC , the core dispatcher component is the DispatcherServlet, which act as the front-controller (design pattern). Every web request has to go through this DispatcherServlet, and the DispatcherServlet will dispatch the web request to suitable handlers. 
