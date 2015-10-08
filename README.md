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
