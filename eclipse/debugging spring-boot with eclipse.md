# debugging spring-boot with eclipse

debugging your spring-boot application with eclipse

## overview

we are going to do this in a few phases. 

- re-configure our `Spring Boot App` **run configuration** to allow remote debugging
- create a new `Remote Java Application` **debug configuration** to connect
- we will then proceed as normal! i.e. mark break points, navigate to `localhost:3000`, etc.

## setup

- check out [developing with eclipse](developing-with-eclipse) and make sure eclipse is configured correctly
- open spring perspective, and see boot dashboard
    * or open boot dashboard view


## guided steps

1. edit your `Spring Boot App` **run configuration**
    - enable debug output in the first tab
    - copy these arguments to `VM arguemnts` in the Arguments tab
        * `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000`
            - notice the server will now listen on port 8000 (debug), as well as 3000 (browser)
    - apply and save
    - now run the `Spring Boot App` in **normal mode** (not debug mode)

1. create a new **debug configuration** of type `Remote Java Application`
    - add name
    - configure
        * browse for your project root
        * connection type `Standard (Socket Attach)`
        * host: localhost
        * port: 8000
    - save

1. add break point
    - add a break point to the return statement of the testGetJson() method in the TestController class
    - `src/main/java/io/abnd/rest/TestController.java`

1. launch our new `Remote Java Application` **debug configuration** in debug mode
    - the server will connect, begin the remote debugging session, and start the application
        * you may receive an initial `SilentExitException`, just press `F8` and resume
    - navigate to `localhost:3000` then press the `Hello Page` button
    - enter debugging mode and see the return object at the break point
        * press `F8` to resume