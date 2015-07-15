scala-workshop
==============

## How to start?

1. start the sbt console with `./activator` or `activator.bat`

2. In the sbt console, compile the project with `compile`.
   The project should compile without any errors.

3. In the sbt console, let the tests run with `test`.
   No surprise, the tests are not successful.

More commands:

- `run` runs a program (main class)
- `test` launches the tests
- `testOnly *.MyTest` launches only the tests with the full name matching `*.MyTest`
- `testQuick` launches only the tests that failed and the tests impacted by the changes
- `~<command>` trigger the <command> each time the code is changed. For example, `~testQuick` is a good way to practice [TDD](https://en.wikipedia.org/wiki/Test-driven_development).


## Integration in IDE

### Intellij

In [Intellij](https://www.jetbrains.com/idea/), create a new project from existing sources by selecting the file `build.sbt`.

### Scala IDE (based on Eclipse)

In [Scala IDE](http://scala-ide.org/):

1. Start the sbt console

2. Enter `eclipse with-source=true`

3. Open the generated project with Scala IDE.

[Look at the documentation for more info](http://scala-ide.org/docs/user/gettingstarted.html).

### no IDE?

run `./activator ui` or `activator.bat ui`.

A web interface will be opened to interact with the project.

