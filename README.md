## Project Description

Project to solve an interview question described below.

## The Problem

---

![](assets/problem.png)

Create a backend application in Java (*Frameworks allowed*) that uses the provided database (*SQLite 3*) to list and categorize country phone numbers. Phone numbers should be categorized by country, state (valid or not valid), country code and number.

**Build endpoint(s) for:**

* Rendering a list of all phone numbers available in the DB.
* It should be possible to filter by country and state.
* *Pagination is an extra.*

Upload the contents of your project to GitHub or similar.

Use dockers to quickly have a running application. Topics to take into account:

- Try to show your OOP skills
- Code standards/clean code
- Do not use external libs to validate the numbers.
- Unit Tests
- Write a README file for build/execution instructions
- Provide cURLs to test the endpoint(s)

Regular expressions to validate the numbers:


| Country    | Country Code | Regex                    |
| ------------ | -------------- | -------------------------- |
| Cameroon   | +237         | \(237\)\ ?[2368]\d{7,8}$ |
| Ethiopia   | +251         | \(251\)\ ?[1-59]\d{8}$   |
| Morocco    | +212         | \(212\)\ ?[5-9]\d{8}$    |
| Mozambique | +258         | \(258\)\ ?[28]\d{7,8}$   |
| Uganda     | +256         | \(256\)\ ?\d{9}$         |

## Tech Stack

The project uses SpringBoot for core and other libs like:

**Server:** SpringBoot, SpringData, RestAssured, Git, Lombok, maven, docker and Java 17

## Installation

For Run, you just need to install the Java 17 JDK, docker, git and Maven 3.6.0+ and follow the steps:

### Installing Apache Maven

The installation of Apache Maven is a simple process of extracting the archive and adding the bin folder with the `mvn` command to the `PATH`.
> MacOS users with Homebrew installed can also use: `brew install maven`

Detailed steps are:

You must have a JDK installed on your system. Either set the `JAVA_HOME` environment variable pointing to your JDK installation or have the java executable on your `PATH`.

Extract distribution archive in any directory

```bash
  $ unzip apache-maven-3.8.4-bin.zip
```

Add the `bin` directory of the created directory `apache-maven-3.8.4` to the `PATH` environment variable

Confirm with `mvn -v` in a new shell. The result should look similar to:

```bash
Running `/Users/username/folder/mvnw`...
Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: /Users/username/.m2/wrapper/dists/apache-maven-3.8.4-bin/52ccbt68d252mdldqsfsn03jlf/apache-maven-3.8.4
Java version: 17.0.1, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "12.1", arch: "x86_64", family: "mac"
```

### Installing Docker

To install docker, follow the [Get Started](https://www.docker.com/get-started) steps.

## Running

To run this project, you have two options:

#### 1. Run without docker:

```bash
  $ cd [root directory]
  $ mvn spring-boot:run
```

#### 2. Run with docker:

2.1. Build docker image:

```bash
  $ cd [root directory]
  $ mvn spring-boot:build-image
```

2.2. Then create and run the container:

```bash
  $ docker run -d --name exercise -p 8080:8080 exercise:1.0.0.Final
```

You can use `-e` option if you want to run the application with different values for some environment variables, e.g.:

```bash
  $ docker run -d --name exercise -p 8080:8080 -e LOGGIN_LEVEL_COM_JUMIA=INFO -e LOGGIN_LEVEL_ROOT=ERROR exercise:1.0.0.Final
```

## Running Tests

To run tests, you can use the `mvn test` command.

```bash
  mvn test
```

The result should look similar to:

![](assets/testresult.png)

## CURL Exemples (Optional)

The API has some parameters like:

<table>
    <thead>
        <tr>
            <th>Param</th>
            <th>Type</th>
            <th>Description</th>
            <th>Possible Values</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th>page</th>
            <th>Integer</th>
            <th>Current page to return (default: 0)</th>
            <th>any integer</th>
        </tr>
        <tr>
            <th>size</th>
            <th>Integer</th>
            <th>Number of elements on a page (default: 10)</th>
            <th>any integer</th>
        </tr>
        <tr>
            <th rowspan=2>status</th>
            <th rowspan=2>Text</th>
            <th rowspan=2>The status of phone number</th>
            <th>VALID</th>
        </tr>
        <tr>
            <th>INVALID</th>
        </tr>
        <tr>
            <th rowspan=5>country</th>
            <th rowspan=5>Text</th>
            <th rowspan=5>The country name of a phone number</th>
            <th>CAMEROON</th>
        </tr>
        <tr>
            <th>ETHIOPIA</th>
        </tr>
        <tr>
            <th>MOROCCO</th>
        </tr>
        <tr>
            <th>MOZAMBIQUE</th>
        </tr>
        <tr>
            <th>UGANDA</th>
        </tr>
    </tbody>
</table>

To test, you can use the `curl` commands:

```bash
  curl -X GET http://localhost:8080/api/v1/customers/phones
  curl -X GET http://localhost:8080/api/v1/customers/phones?country=CAMEROON
  curl -X GET http://localhost:8080/api/v1/customers/phones?country=CAMEROON&status=VALID
  curl -X GET http://localhost:8080/api/v1/customers/phones?size=2&page=0&country=CAMEROON
  curl -X GET http://localhost:8080/api/v1/customers/phones?size=2&page=0&status=VALID&country=CAMEROON
```
