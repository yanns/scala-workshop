scala-workshop
==============

1. start the sbt console with ./sbt

2. In the sbt console, generate the configuration for your IDE:
- for eclipse: 'eclipse with-source=true'
- for IDEA: 'gen-idea'

In Intellij, if you have an error concerning a shared output path, check if there is no `project` folder in the `macros` project. If there is one, just remove it.

3. wait to have downloaded the whole internet

4. open the project in your IDE

5. In the sbt console, compile the project with 'compile' 

6. In the sbt console, let the tests run with 'test'
No surprise, the tests are not successful.

