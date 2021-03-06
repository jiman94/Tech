
https://jenkins.io/download/

```bash 
~/.aws/credentials
[default] 
aws_access_key_id= aws_secret_access_key=

~/.aws/config
[default] 
region=ap-northeast-2 
output=json
```

```bash 

rem java -jar D:/Project/tools/jenkins/jenkins.war --httpPort=8080
rem # nohup java –jar /data/jenkins.war  --httpPort= 8089 --prefix=/sonarqube > /data/jenkins/app/logs/sonarqube/jenkins.log 2>&1 &

set MODULE_NAME=dev
set JAVA_HOME=Z:/Project/Java/jdk1.8.0_241

set JENKINS_BASE=Z:/Project/CI
set JENKINS_HOME=Z:/Project/CI/%MODULE_NAME%

set JENKINS_LOG=%JENKINS_BASE%/logs/%MODULE_NAME%
set JENKINS_HTTP_PORT=8090
set JENKINS_VERSION=2.204.2

set JENKINS_OPTS=--prefix=%JENKINS_PREFIX%
set JENKINS_OPTS=%JENKINS_OPTS% --httpPort=%JENKINS_HTTP_PORT%


set JAVA_OPTS=-server
set JAVA_OPTS=%JAVA_OPTS% -noverify -Dfile.encoding=UTF-8 
set JAVA_OPTS=%JAVA_OPTS% -Dserver=jenkins

rem # ----------------------
rem # jenkins opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -DJENKINS_HOME=%JENKINS_HOME%

rem # ----------------------
rem # java memery opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -Djava.awt.headless=true
set JAVA_OPTS=%JAVA_OPTS% -Xms2g
set JAVA_OPTS=%JAVA_OPTS% -Xmx6g
set JAVA_OPTS=%JAVA_OPTS% -Xss2m
set JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=2g
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxMetaspaceSize=6g 

rem # ----------------------
rem # java gc opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -verbose:gc
set JAVA_OPTS=%JAVA_OPTS% -Xloggc:%JENKINS_LOG/gc.log
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintGCDetails
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintGCTimeStamps
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintHeapAtGC
set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError
set JAVA_OPTS=%JAVA_OPTS% -XX:HeapDumpPath=Z:/

%JAVA_HOME%/bin/java -jar "%JENKINS_BASE%/jenkins-%JENKINS_VERSION%.war" %JENKINS_OPTS%

```



# Jenkins ver. 2.204.2 플러그인 관리
 
#### 1. Amazon ECR plugin
This plugin generates Docker authentication token from Amazon Credentials to access Amazon ECR.
1.6  
 
#### 2. Amazon Web Services SDK
This plugin provides AWS SDK for Java for other plugins.
1.11.700 

 
#### 3. Ant
Adds Apache Ant support to Jenkins
1.10  
 
#### 4. Apache HttpComponents Client 4.x API Plugin
Bundles Apache HttpComponents Client 4.x and allows it to be used by Jenkins plugins.
4.5.10-2.0  
 
#### 5. Authentication Tokens API Plugin
This plugin provides an API for converting credentials into authentication tokens in Jenkins.
1.3  
 
#### 6. AWS CodePipeline Plugin
AWS CodePipeline Integration
0.39  
 
#### 7. Bitbucket Branch Source
Allows to use Bitbucket Cloud and Bitbucket Server as sources for multi-branch projects. It also provides the required connectors for Bitbucket Cloud Team and Bitbucket Server Project folder (also known as repositories auto-discovering).
2.6.0 

 
#### 8. Bitbucket Plugin
Integrates with BitBucket
1.1.11  
 
#### 9. Bitbucket Pullrequest Builder Plugin
This plugin polls BitBucket to determine whether there are Pull Requests that should be built.
1.5.0  
 
#### 10. Bitbucket Push and Pull Request
Integrates with Bitbucket Cloud (rest api version >=2.0) Server triggering on push and pull requests
Warning: After updating the plugin from a version prior to 2.x.x, the jobs with a pull request need to be reconfigured.
2.2.1 

 
#### 11. Bitbucket Server Integration
This plugin integrates Bitbucket Server with Jenkins.
1.0.3 
1.0.1으로 다운그레이드
 
#### 12. bouncycastle API Plugin
This plugin provides an stable API to Bouncy Castle related tasks.
2.18 

 
#### 13. Branch API Plugin
This plugin provides an API for multiple branch based projects.
2.5.5 

 
#### 14. Build Authorization Token Root Plugin
Lets build and related REST build triggers be accessed even when anonymous users cannot see Jenkins.
1.6 

 
#### 15. Build Timeout
This plugin allows builds to be automatically terminated after the specified amount of time has elapsed.
1.19  
 
#### 16. CloudBees AWS Credentials Plugin
Allows storing Amazon IAM credentials within the Jenkins Credentials API. Store Amazon IAM access keys (AWSAccessKeyId and AWSSecretKey) within the Jenkins Credentials API. Also support IAM Roles and IAM MFA Token.
1.28  
 
#### 17. CloudBees Docker Build and Publish plugin
This plugin enables building Dockerfile based projects, as well as publishing of the built images/repos to the docker registry.
1.3.2  
 
#### 18. Command Agent Launcher Plugin
Allows agents to be launched using a specified command.
1.4 

 
#### 19. Config File Provider Plugin
Ability to provide configuration files (e.g. settings.xml for maven, XML, groovy, custom files,...) loaded through the UI which will be copied to the job workspace.
3.6.3 
 
#### 20. Credentials
This plugin allows you to store credentials in Jenkins.
2.3.0  
 
#### 21. Credentials Binding Plugin
Allows credentials to be bound to environment variables for use from miscellaneous build steps.
1.20  
 
#### 22. Display URL API
Provides the DisplayURLProvider extension point to provide alternate URLs for use in notifications
2.3.2  
 
#### 23. Docker API Plugin
This plugin provides docker-java API for other plugins.
3.0.14  
 
#### 24. Docker Commons Plugin
Provides the common shared functionality for various Docker-related plugins.
1.16 

#### 25. Docker Pipeline
Build and use Docker containers from pipelines.
1.21  
 
#### 26. Docker plugin
This plugin integrates Jenkins with Docker
1.1.9 

#### 27. docker-build-step
This plugin allows to add various docker commands to your job as build steps.
2.4  
 
#### 28. Durable Task Plugin
Library offering an extension point for processes which can run outside of Jenkins yet be monitored.
1.33  
 
#### 29. Email Extension Plugin
This plugin is a replacement for Jenkins's email publisher. It allows to configure every aspect of email notifications: when an email is sent, who should receive it and what the email says
2.68  
 
#### 30. Folders
This plugin allows users to create "folders" to organize jobs. Users can define custom taxonomies (like by project type, organization type etc). Folders are nestable and you can define views within folders. Maintained by CloudBees, Inc.
6.10.1 

 
#### 31. Git
This plugin integrates Git with Jenkins.
4.0.0  
 
#### 32. Git client
Utility plugin for Git support in Jenkins
3.0.0  
 
#### 33. GIT server Plugin
Allows Jenkins to act as a Git server.
1.9 
 
#### 34. GitHub API
This plugin provides GitHub API for other plugins.
1.95  
 
#### 35. GitHub Branch Source
Multibranch projects and organization folders from GitHub. Maintained by CloudBees, Inc.
2.5.8  
 
#### 36. GitHub plugin
This plugin integrates GitHub to Jenkins.
1.29.5  
 
#### 37. Gradle Plugin
This plugin allows Jenkins to invoke Gradle build scripts directly.
1.36 
 
#### 38. Handy Uri Templates 2.x API Plugin
Bundles Handy Uri Templates 2.x and allows it to be used by Jenkins plugins
2.1.8-1.0  
 
#### 39. Jackson 2 API Plugin
This plugin exposes the Jackson 2 JSON APIs to other Jenkins plugins.
2.10.2 
 
#### 40. Javadoc Plugin
1.5  
 
#### 41. JavaScript GUI Lib: ACE Editor bundle plugin
1.1  
 
#### 42. JavaScript GUI Lib: Handlebars bundle plugin
1.1.1  
 
#### 43. JavaScript GUI Lib: jQuery bundles (jQuery and jQuery UI) plugin
1.2.1  
 
#### 44. JavaScript GUI Lib: Moment.js bundle plugin
1.1.1  
 
#### 45. Job DSL
This plugin allows Jobs and Views to be defined via DSLs
1.76  
 
#### 46. JSch dependency
Jenkins plugin that brings the JSch library as a plugin dependency, and provides an SSHAuthenticatorFactory for using JSch with the ssh-credentials plugin.
0.1.55.1  
 
#### 47. JUnit Plugin
Allows JUnit-format test results to be published.
1.28  
 
#### 48. LDAP Plugin
Adds LDAP authentication to Jenkins
1.21 
 
#### 49. Lockable Resources plugin
This plugin allows to define external resources (such as printers, phones, computers) that can be locked by builds. If a build requires an external resource which is already locked, it will wait for the resource to be free.
2.7  
 
#### 50. Mailer
This plugin allows you to configure email notifications for build results
1.29  
 
#### 51. MMapDB API Plugin
This plugin provides a shared dependency on the MapDB library so that other plugins can co-operate when using this library.
1.0.9.0  
 
#### 52. Matrix Authorization Strategy Plugin
Offers matrix-based security authorization strategies (global and per-project).
2.5  
 
#### 53. Matrix Project Plugin
Multi-configuration (matrix) project type.
1.14  
 
#### 54. Maven Integration plugin
This plug-in provides, for better and for worse, a deep integration of Jenkins and Maven: Automatic triggers between projects depending on SNAPSHOTs, automated configuration of various Jenkins publishers (Junit, ...).
3.4  
 
#### 55. Mercurial plugin
This plugin integrates Mercurial SCM with Jenkins. It includes repository browsing support for hg serve/hgweb, Google Code, Bitbucket, FishEye, KilnHG and RhodeCode. Features include guaranteed clean builds, named branch support, module lists, Mercurial tool installation, and automatic caching.
2.8  
 
#### 56. NodeJS Plugin
NodeJS Plugin executes NodeJS script as a build step.
1.3.4  
 
#### 57. Oracle Java SE Development Kit Installer Plugin
Allows the Oracle Java SE Development Kit (JDK) to be installed via download from Oracle's website.
1.4 

 
#### 58. OWASP Markup Formatter
Uses the OWASP Java HTML Sanitizer to allow safe-seeming HTML markup to be entered in project descriptions and the like.
1.7 
 
#### 59. PAM Authentication plugin
Adds Unix Pluggable Authentication Module (PAM) support to Jenkins
1.6  
 
#### 60. Pipeline
A suite of plugins that lets you orchestrate automation, simple or complex. See Pipeline as Code with Jenkins for more details.
2.6  
 
#### 61. Pipeline Graph Analysis Plugin
Provides a REST API to access pipeline and pipeline run data.
1.10  
 
#### 62. Pipeline: API
Plugin that defines Pipeline API.
2.38 
 
#### 63. Pipeline: Basic Steps
Commonly used steps for Pipelines.
2.19 
 
#### 64. Pipeline: Build Step
Adds the Pipeline step build to trigger builds of other jobs.
2.11 

 
#### 65. Pipeline: Declarative
An opinionated, declarative Pipeline.
1.5.0 
 
#### 66. Pipeline: Declarative Agent API
Replaced by Pipeline: Declarative Extension Points API plugin.
1.1.1  
 
#### 67. Pipeline: Declarative Extension Points API
APIs for extension points used in Declarative Pipelines.
1.5.0 

#### 68. Pipeline: GitHub Groovy Libraries
Allows Pipeline Grrovy libraries to be loaded on the fly from GitHub.
1.0  
 
#### 69. Pipeline: Groovy
Pipeline execution engine based on continuation passing style transformation of Groovy scripts.
2.78 
 
#### 70. Pipeline: Input Step
Adds the Pipeline step input to wait for human input or approval.
2.11  
 
#### 71. Pipeline: Job
Defines a new job type for pipelines and provides their generic user interface.
2.36 
 
#### 72. Pipeline: Milestone Step
Plugin that provides the milestone step
1.3.1  
 
#### 73. Pipeline: Model API
Model API for Declarative Pipeline.
1.5.0 
 
#### 74. Pipeline: Multibranch
Enhances Pipeline plugin to handle branches better by automatically grouping builds from different branches.
2.21  
 
#### 75. Pipeline: Nodes and Processes
Pipeline steps locking agents and workspaces, and running external processes that may survive a Jenkins restart or slave reconnection.
2.35  
 
#### 76. Pipeline: REST API
Provides a REST API to access pipeline and pipeline run data.
2.12  
 
#### 77. Pipeline: SCM Step
Adds a Pipeline step to check out or update working sources from various SCMs (version control).
2.9  
 
#### 78. Pipeline: Shared Groovy Libraries
Shared libraries for Pipeline scripts.
2.15  
 
#### 79. Pipeline: Stage Step
Adds the Pipeline step stage to delineate portions of a build.
2.3  
 
#### 80. Pipeline: Stage Tags Metadata
Library plugin for Pipeline stage tag metadata.
1.5.0 
 
#### 81. Pipeline: Stage View
Pipeline Stage View Plugin.
2.12  
 
#### 82. Pipeline: Step API
API for asynchronous build step primitive.
2.22 
 
#### 83. PPipeline: Supporting APIs
Common utility implementations to build Pipeline Plugin
3.3  
 
#### 84. PPlain Credentials
Allows use of plain strings and files as credentials.
1.5  
 
#### 85. PPublish to Bitbucket Plugin
This plugin allows you to publish the current code in workspace to a bitbucket server by creating a new repository and associated project if required.
0.4  
 
#### 86. PResource Disposer Plugin
Dispose resources asynchronously. Utility plugin for resources that require more retries or take a long time to delete.
0.14  
 
#### 87. PSCM API Plugin
This plugin provides a new enhanced API for interacting with SCM systems.
2.6.3  
 
#### 88. PScript Security
Allows Jenkins administrators to control what in-process scripts can be run by less-privileged users.
1.68 
 
#### 89. SonarQube Scanner for Jenkins
This plugin allows an easy integration of SonarQube, the open source platform for Continuous Inspection of code quality.
2.11 
 
#### 90. SSH Build Agents
Allows to launch agents over SSH, using a Java implementation of the SSH protocol.
1.31.0  
 
#### 91. SSSH Credentials
Allows storage of SSH credentials in Jenkins
1.18  
 
#### 92. SStructs Plugin
Library plugin for DSL plugins that need names for Jenkins objects.
1.20  
 
#### 93. SSubversion
2.13.0 

#### 94. STimestamper
Adds timestamps to the Console Output
1.10  
 
#### 95. SToken Macro Plugin
This plug-in adds reusable macro expansion capability for other plug-ins to use.
2.10  
 
#### 96. STrilead API Plugin
Trilead API Plugin provides the Trilead library to any dependent plugins in an easily update-able manner.
1.0.5  
 
#### 97. SVariant Plugin
This user-invisible library plugin allows other multi-modal plugins to behave differently depending on where they run.
1.3  
 
#### 98. SWMI Windows Agents Plugin
Allows you to setup agents on Windows machines over Windows Management Instrumentation (WMI)
1.6 
 
#### 99. SWorkspace Cleanup Plugin
This plugin deletes the project workspace when invoked.
0.38 
