# codegen-gradle/
* https://www.jooq.org/doc/latest/manual/code-generation/codegen-gradle/

```java
// 3.11 이후 버전에서 package가 변경되었으므로 GenerationTool과 Configuration 의 package를 다시 확인해야 한다!
buildscript {
  dependencies {
    classpath 'org.jooq:jooq-codegen:3.11.4'
    classpath "mysql:mysql-connector-java:${mySqlDriverVersion}"
  }
}

ext {
  jooqSourceDir = "src/generated/jooq"
}

sourceSets {
  main {
    java {
      srcDir file(jooqSourceDir)
    }
  }
}
idea {
    module {
        sourceDirs += file(jooqSourceDir)
        generatedSourceDirs += file(jooqSourceDir) // just hint. 자동 생성 소스라는 마크가 붙음.
    }
}

dependencies {
    compile('org.jooq.trial:jooq:3.11.4')
}

task generateJooqSources {
  outputs.upToDateWhen { false }

  doLast {
      def writer = new StringWriter()
      new groovy.xml.MarkupBuilder(writer)
        .configuration('xmlns': 'http://www.jooq.org/xsd/jooq-codegen-3.11.0.xsd') {
        jdbc() {
          driver('com.mysql.jdbc.Driver')
          url('jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8')
          user('un')
          password('password')
        }
        generator() {
          strategy {
            matchers {
              tables {
                // table 설정은 구체적인 것이 보편적인 것보다 앞에와야만 적용된다.
                table {
                  expression(".*user_table") // user_table을 JqMember, MemberRecord 로 매핑함
                  tableClass {
                    expression("JqMember")
                  }
                  recordClass {
                    expression("MemberRecord")
                  }
                }
                table { // 나머지 테이블들에 대한 보편적 설정
                  tableClass {
                    transform('PASCAL') // JqUser 와 같은 형식
                    expression('JQ_$0')
                  }
                }
              }
              fields {
                field {
                  fieldIdentifier {
                    transform('AS_IS') // 컬럼 필드 이름은 컬럼 이름과 동일하게
                  }
                }
              }
            }
          }
          database() {
            delegate.name('org.jooq.meta.mysql.MySQLDatabase') // DB 타입 설정. gradle Task.setName과 충돌
            includes('.*')
            excludes('db_migration_history') // flyway migration history 제외
            schemata {
              schema {
                inputSchema('test')
              }
              schema {
                inputSchema('sakila')
              }
            }
            forcedTypes {
              forcedType { // varchar(14)로 날짜와 시간을 표현하는 *DateTime 컬럼은 java.time.LocalDateTime 으로 매핑
                userType("java.time.LocalDateTime")
                converter("com.example.LocalDateTimeForVarcharConverter")
                expression(".*DateTime")
                types("VARCHAR\\(14\\)")
              }
            }

          }

          // Watch out for this caveat when using MarkupBuilder with "reserved names"
          // - http://stackoverflow.com/a/11389034/521799
          generate([:]) {
            pojos false
            daos false
            javaTimeTypes true // Java 8 java.time API 로 날짜,시간 표현
          }
          target() {
            packageName('kr.pe.kwonnam.test.jooq')
            directory(file(jooqSourceDir).absolutePath)
          }
        }
      }

      org.jooq.codegen.GenerationTool.generate(
        javax.xml.bind.JAXB.unmarshal(new StringReader(writer.toString()), org.jooq.meta.jaxb.Configuration.class)
      )
  }
}
```