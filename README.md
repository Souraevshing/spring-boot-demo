# Spring 6

## Spring IOC Container

* responsible for objects creation.
* responsible for injecting 1 obj to other object using DI.
* managing entire beans life cycle from obj creation to destruction.
* obj are loosely coupled

## Two Types of IOC Container are there

### BeanFactory container
* Used for creating and destroying obj during lifecycle.
### ApplicationContext container
* It's an advanced container and provides messages (i18) func, supports almost all types of beans scopes, easy integration with Spring AOP and is annotation based dependency injection.

### Steps for Java based configuration
* annotate class with @Configuration
* create method and annotate with @Bean
* create IoC container and retrieve spring beans from IoC container.

### Steps for annotation based configuration
* annotate class with @Component
* use @ComponentScan and pass package name to scan classes
* use @Autowired to inject spring beans
* use @Qualifier to avoid confusion between beans of same type
* create IoC container (ApplicationContext) and retrieve spring beans from spring IoC container

## Dependency Injection

### Dependency
* An object usually requires obj of other classes to perform its operations and we call this dependencies.
#### Injection
* The process of providing required dependencies to an obj.

## 3 types of Dependency Injection

* Constructor Injection - 
* Setter Injection - 
* Field Injection - 