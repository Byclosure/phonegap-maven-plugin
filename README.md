##PHONEGAP MAVEN PLUGIN

A plugin for building a phonegap project using maven. The build is done locally on your pc.

You need to have phonegap installed in your computer to use this plugin and your project must generate html.

This plugin can be use with:
* http:www.gwtproject.org
* http://www.m-gwt.com
* http://jbake.org
* Any other framework that produces html apps

##How to use

Add the plugin dependency to your pom.xml

```xml
<dependency>
  <groupId>com.byclosure.maven.plugins</groupId>
  <artifactId>phonegap-maven-plugin</artifactId>
  <version>0.0.1</version>
</dependency>
```

Create the phonegap project dir (by default it will create in target/phonegap)
```bash
mvn phonegap:create
````

Add support for a platform (by default it will add android platform)
```bash
mvn phonegap:platform-add
```

Add support for plugin (i.g. adding splashscreen plugin)
```bash
mvn phonegap:plugin-add -Dphonegap.plugin="org.apache.cordova.splashscreen"
```

Copy the compile html to the phonegap www folder (any plugin that copies files can be used)
```xml
<plugin>
      <artifactId>maven-antrun-plugin</artifactId>
      <version>1.4</version>
      <executions>
        <execution>
          <id>copy</id>
          <phase>compile</phase>
          <configuration>
            <tasks>
              <copy file="target/compiled_html" tofile="target/phonegap/www"/>
            </tasks>
          </configuration>
          <goals>
            <goal>run</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
```

Execute build
```bash
mvn phonegap:build
```



## Parameters for goals

To see the available goals
```bash
mvn help:describe -Dplugin=com.byclosure.maven.plugins:phonegap-maven-plugin
```

To see the parameters available for a goal (for other goals replace phonegap:create with goal)
```bash
mvn help:describe -Dcmd=phonegap:create -Ddetail
```

## Additional Links

* http://phonegap.com/
* https://groups.google.com/forum/#!forum/phonegap
* http://www.m-gwt.com/

## Contributions

We welcome all the help we can get!