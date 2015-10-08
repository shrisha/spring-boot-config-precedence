Possible bug in spring-boot (or is it JAVA 8?). The precedence of loading the profiles is reversed when I switch between JAVA 7 and JAVA 8. Reference to git issue - https://github.com/spring-projects/spring-boot/issues/4111

Steps to reproduce
------------------
* set JAVA_HOME to java 1.7 (I'm using 1.7.0_67)
* run mvn clean install. Tests pass.
* set JAVA_HOME to java 1.8 (I'm using 1.8.0_60)
* run mvn clean install. Tests fail.

Description of Issue
------------------
There are two profiles that are loaded - default and test.

* Running JAVA 7, the test passes with prop1's value set to 300
* Running JAVA 8, the test fails with prop1's value set to 200 (it's as if the application-test.properties wasn't loaded)
* 
Another sample (with less dependencies) that demonstrates the issue: https://github.com/shrisha/spring-boot-config-precedence

when you run the sample app against 1.7 and then 1.8, you'll notice the following difference in property loading order:

## Java 1.7
```2015-10-07 21:07:31.783 DEBUG 1453 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application-test.properties]] PropertySource with search precedence immediately lower than [applicationConfigurationProperties]```
```2015-10-07 21:07:31.784 DEBUG 1453 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]#test] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application-test.properties]]```
```2015-10-07 21:07:31.784 DEBUG 1453 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]#default] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application.yml]#test]```
```2015-10-07 21:07:31.784 DEBUG 1453 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application.yml]#default]```


## Java 1.8
```2015-10-07 21:00:35.110 DEBUG 1416 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]#test] PropertySource with search precedence immediately lower than [applicationConfigurationProperties]```
```2015-10-07 21:00:35.110 DEBUG 1416 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application-test.properties]] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application.yml]#test]```
```2015-10-07 21:00:35.110 DEBUG 1416 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]#default] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application-test.properties]]```
```2015-10-07 21:00:35.111 DEBUG 1416 --- [           main] o.s.core.env.StandardEnvironment         : Adding [applicationConfig: [classpath:/application.yml]] PropertySource with search precedence immediately lower than [applicationConfig: [classpath:/application.yml]#default]```

