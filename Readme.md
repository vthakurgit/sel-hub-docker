# Application URLs

- [Flight Reservation](https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html)
- [Vendor Portal](https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html)

Note:
- docker-compose.xml by spin up selenium/hub + 2 replicas each for FF & chrome
- Automation framework enhanced to take screenshot(on failures).
- Hardcoded values (like app url's), other parameters which required to over-ridden by command line are to util and config implementations:
 a) util/Config.java || Read properties from "config/default.properties" file and also have implementation where it can override those properties incase passed running of mvn projects from terminal.
 b) util/Constants.java || as a best practices constants moved here, to improve code readability.


# Maven Dependencies

```
 <properties>
        <selenium.java.version>4.16.0</selenium.java.version>
        <logback.version>1.4.14</logback.version>
        <webdriver.manager.version>5.6.3</webdriver.manager.version>
        <testng.version>7.9.0</testng.version>
        <jackson.version>2.16.1</jackson.version>
        <!-- plugins versions -->
        <maven.compiler.version>3.11.0</maven.compiler.version>
        <maven.dependency.version>3.6.0</maven.dependency.version>
        <maven.jar.version>3.3.0</maven.jar.version>
        <maven.surefire.version>3.1.2</maven.surefire.version>
        <maven.resources.plugin>3.3.1</maven.resources.plugin>
        <!-- output directory -->
        <package.directory>${project.build.directory}/docker-resources</package.directory>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.java.version}</version>
        </dependency>
        <!-- logging library -->
        <!-- https://www.baeldung.com/logback -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>
        <!-- web-driver manager for local testing -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>${webdriver.manager.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- To deserialize json into Java object or vice versa -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- Test framework -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <finalName>selenium-docker</finalName>
        <plugins>
            <!-- To compile the source code using specific java version. also to force IDE to change the settings -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.version}</version>
                <configuration>
                    <release>17</release>
                </configuration>
            </plugin>
            <!-- To run the tests using specific parameters, change thread count, testng report output directory etc -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.version}</version>
                <configuration>
                    <systemPropertyVariables>
                        <browser>chrome</browser>
                    </systemPropertyVariables>
                    <suiteXmlFiles>
                        <file>test-suite.xml</file>
                    </suiteXmlFiles>
                    <threadCount>1</threadCount>
                    <reportsDirectory>target/test-output</reportsDirectory>
                </configuration>
            </plugin>
            <!-- To copy all the dependencies to run our tests as part of "mvn package" -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>${maven.dependency.version}</version>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>
                                ${package.directory}/libs
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <!-- To package our page objects, test classes into jar -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>${maven.jar.version}</version>
                <configuration>
                    <outputDirectory>${package.directory}</outputDirectory>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>test-jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <!-- To copy resources into the output directory -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>${maven.resources.plugin}</version>
                <executions>
                    <execution>
                        <id>copy-resources</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${package.directory}</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>src/test/resources</directory>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```