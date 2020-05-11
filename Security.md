# Authentication
Who are you?
What do you want to do?

Principal - person identified through authentication

AuthenticationManager manages authentication in Spring app

AuthenticationProvider performs authentication

https://www.youtube.com/watch?v=caCJAJC41Rk&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE&index=6

The SecurityContext and SecurityContextHolder are two fundamental classes of Spring Security. The SecurityContext is used to store the details of the currently authenticated user, also known as a principle. So, if you have to get the username or any other user details, you need to get this SecurityContext first. The SecurityContextHolder is a helper class, which provide access to the security context. By default, it uses a ThreadLocal object to store security context, which means that the security context is always available to methods in the same thread of execution, even if you don't pass the SecurityContext object around. Don't worry about the ThreadLocal memory leak in web application though, Spring Security takes care of cleaning ThreadLocal.

# Authorization
Are you allowed to do this?

![roles](https://github.com/akravets/Spring/blob/master/roles.png)
     
By extending WebSecurityConfigurerAdapter we can configure rules for accessing specific endpoints

# Filters
Authorizes execution of web methods in web based Spring Application

# Cross-cutting concerns

The crosscutting concern is a concern which is applicable throughout the application and it affects the entire application. 
Cross Cutting Concerns are the scenarios which should always be present irrespective of the type of application.

For example: logging, security and data transfer are the concerns which are needed in almost every module of an application, hence they are cross-cutting concerns.

When Spring Security is added, the default behaviors are:

Adds manditory authentication
	Everything is secured by default, except error pages for example
Adds login form
	Adds default login form
Handles login error
	When authentication fails
Creates default user and sets default password
	Look in logs for "Using generated security password", username is 'user'
	can be changed in application.properties by changing spring.security.user.name and spring.security.user.password
