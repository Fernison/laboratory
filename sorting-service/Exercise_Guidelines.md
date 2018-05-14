# DDS UST EXERCISE

## General guidelines

The delivery must include a markdown readme file with concise and complete instructions on how to use (unpack, build, install, execute, access, etc.) your service.
Your code and other deliverables must be provided as a link to a private Git repository (GitHub or Bitbucket).
Your solution must only be accessible to you and us; please make sure it is not available for a wider audience, especially not publicly. 

## The problem
Sorting algorithms are well known in computer science. They are divided in two categories, linear and divide and conquer algorithms. The latter offer a better performance and one of them is the mergesort algorithm. If you are not familiar with the algorithm you can start with the relevant Wikipedia article or browse other Web sources.

### The task
You are asked to develop a web service that should do the following:

- implement the mergesort algorithm;
- allow the user to provide an unsorted array of numbers;
- invoke the service asynschronously for each array provided by the user;
- each execution should be identified by a job id, timestamp and run time;
- provide a REST API (see below for details);
- retrieve all jobs with their id's, timestamps and status {pending, complete};
- retrieve specific jobs given their id's with the resulting sorted array;
- You are free to reuse existing implementations of the mergesort algorithm.

### Functional requirements

Your delivery should:

- include complete code needed to execute the solution;
- include a suite of unit tests covering all key components of your solution;
- contain logging functionality;
- use a build system with targets for building, testing, deploying and executing the solution;
- either contain all additional dependencies (e.g., external libraries, if you use any) or handle the - download and installation thereof as needed (ideally, as part of the build process);
- provide a REST API interface;

### Additionnal requirements

- For deployment, your solution should be containerised: the solution should be launched as a container (for docker images, provide Dockerfile); There should be no requirement to install anything on the host other than what is needed to provide an environment for executing containers e.g., Docker machine;
- A code analysis report of the solution;
- For the REST API, provide a complete documentation (YAML or RAML preferred) of all endpoints, with details of the required arguments, input and output formats, possible http status codes, and their semantics;
- Integration tests : Each REST API method should be tested without mocking;

### REST API

The following is a concise list of the REST APIs calls for the solution. See the examples section below for their usage with curl.

- POST /mergesort content: application/json with mergesort problem specification, see below input: json with an array of numbers output: json with execution object, i.e. job id, timestamp, status
- GET /mergesort/executions output: json with list of executions and input array
- GET /mergesort/executions/<id> output: json with execution object and output sorted array

You are free to extend the API (e.g., enable deleting tasks). The json requests and responses should have at least the following formats:

```
execution
{
    "execution": {
        "id": # non-negative number, 
        "timestamp": #time/date started,
        "duration": # duration,
        "status": # {penging, completed}
        "input": # array of numbers,
        "output": # array of numbers, same size as input
    }
}
```

```json
executions
{
    "executions": {
         # array of job executions with statuts for each job
    }
}
```

*Example session*

The following is a hypothetical session using docker to start a composite service, listening on port 8888:

`$ cd <root directory of the service> $ docker run … ... $ curl -XPOST -H 'Content-type: application/json' http://localhost:8888/mergesort/ \ -d [3,5,1,83,51,99] {"id":"123","status":"pending"}`
`$ curl -XGET -H http://localhost:8888/mergesort/executions/123 {"id": "123", "timestamp":"112382233","duration":"12431",status:"completed" "input": [3,5,1,83,51,99], "output": [1,3,5,51,83,99]}`

#### Objectives

MINIMALLY, IT SHOULD BE POSSIBLE TO:
- build and start your solution as a service on a local machine;
- interact with your solution through the REST API (e.g. using curl);
- all other dependencies should be handled within the images by a package manager corresponding to your code (maven, gradle, npm, etc.);

#### Technical frameworks

- readme.md (or .txt) to explain how to use the solution. Recommended .md editor;
- Spring Boot with embedded http server;
- Spring REST API;
- Logging with Logback;
- Java 8;
- Maven or others java dependencies tools;
- Junit and Mockito Unit Test frameworks;
- Docker (additional requirement);