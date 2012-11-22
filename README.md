# CukeSteps
=========

_Steps Navigator for cucumber-JVM projects_

    Cuke Steps is a maven plugin that helps you by creating a library of all the available Cucumber steps
    It currently locates all available steps in .java or .feature files.
    After compilation phase, it generates a cukes.html report which can be found in the target/ folder

### How to use it?

Introduce the following in the plugins section of your pom.xml:

    <plugin>
                <groupId>net.masterthought</groupId>
                <artifactId>cukesteps-maven-plugin</artifactId>
                <version>1.0</version>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>cukesteps</goal>
                        </goals>
                    </execution>
                </executions>
    </plugin>

If you don't want to integrate this plugin to your project but search another project instead,
then you can add the following:

	<configuration>
         <directory>/DIRECTORY/TO/PARSE</directory>
    </configuration>

underneath the goals section of this plugin. 
                         
&copy;2012 - [MasterThought.net](http://www.masterthought.net)