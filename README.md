##
一个爬虫的例子
##maven
```
mvn eclipse:eclipse
```

```
mvn checkstyle:help --查看checkstyle所有任务goal
mvn findbugs:help       查看findbugs插件的帮助
mvn findbugs:check      检查代码是否通过findbugs检查，如果没有通过检查，检查会失败，但检查不会生成结果报表
mvn findbugs:findbugs   检查代码是否通过findbugs检查，如果没有通过检查，检查不会失败，会生成结果报表保存在target/findbugsXml.xml文件中
mvn findbugs:gui        检查代码并启动gui界面来查看结果
```

```
mvn findbugs:findbugs
mvn checkstyle:checkstyle
mvn cobertura:cobertura
mvn dashboard:dashboard
```

```
mvn sonar:sonar
```

###maven->gradle
gradle init --type pom

##gradle
gradle build -x test
