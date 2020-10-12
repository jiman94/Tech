 
 # AdoptOpenJDK - HomeBrew TAP

brew cask install adoptopenjdk


### Available Versions
| Java Version | JDK | JRE
|--|--|--|
| Latest OpenJDK with Hotspot JVM | `adoptopenjdk` | `adoptopenjdk-jre` | 
| Latest OpenJDK with OpenJ9 JVM | `adoptopenjdk-openj9` | `adoptopenjdk-openj9-jre` | 
| Latest OpenJDK with OpenJ9 JVM, large heap* | `adoptopenjdk-openj9-large` | `adoptopenjdk-openj9-jre-large` | 
| OpenJDK8 with Hotspot JVM | `adoptopenjdk8` | `adoptopenjdk8-jre` |
| OpenJDK8 with OpenJ9 JVM | `adoptopenjdk8-openj9` | `adoptopenjdk8-openj9-jre` |
| OpenJDK8 with OpenJ9 JVM, large heap* | `adoptopenjdk8-openj9-large` | `adoptopenjdk8-openj9-jre-large` |
| OpenJDK9 with Hotspot JVM | `adoptopenjdk9` | n/a |
| OpenJDK10 with Hotspot JVM | `adoptopenjdk10` | n/a |
| OpenJDK11 with Hotspot JVM | `adoptopenjdk11` | `adoptopenjdk11-jre` |
| OpenJDK11 with OpenJ9 JVM | `adoptopenjdk11-openj9` | `adoptopenjdk11-openj9-jre` |
| OpenJDK11 with OpenJ9 JVM, large heap* | `adoptopenjdk11-openj9-large` | `adoptopenjdk11-openj9-jre-large` |
| OpenJDK12 with Hotspot JVM | `adoptopenjdk12` | `adoptopenjdk12-jre` |
| OpenJDK12 with OpenJ9 JVM | `adoptopenjdk12-openj9` | `adoptopenjdk12-openj9-jre` |
| OpenJDK12 with OpenJ9 JVM, large heap* | `adoptopenjdk12-openj9-large` | `adoptopenjdk12-openj9-jre-large` |
| OpenJDK13 with Hotspot JVM | `adoptopenjdk13` | `adoptopenjdk13-jre` |
| OpenJDK13 with OpenJ9 JVM | `adoptopenjdk13-openj9` | `adoptopenjdk13-openj9-jre` |
| OpenJDK13 with OpenJ9 JVM, large heap* | `adoptopenjdk13-openj9-large` | `adoptopenjdk13-openj9-jre-large` |
| OpenJDK14 with Hotspot JVM | `adoptopenjdk14` | `adoptopenjdk14-jre` |
| OpenJDK14 with OpenJ9 JVM | `adoptopenjdk14-openj9` | `adoptopenjdk14-openj9-jre` |
| OpenJDK14 with OpenJ9 JVM, large heap* | `adoptopenjdk14-openj9-large` | `adoptopenjdk14-openj9-jre-large` |
| OpenJDK15 with Hotspot JVM | `adoptopenjdk15` | `adoptopenjdk15-jre` |
| OpenJDK15 with OpenJ9 JVM | `adoptopenjdk15-openj9` | `adoptopenjdk15-openj9-jre` |
| OpenJDK15 with OpenJ9 JVM, large heap* | `adoptopenjdk15-openj9-large` | `adoptopenjdk15-openj9-jre-large` |


brew tap AdoptOpenJDK/openjdk

brew cask install adoptopenjdk11

vi ~/.zshrc

jdk() {
        version=$1
        export JAVA_HOME=$(/usr/libexec/java_home -v"$version");
        java -version
 }


