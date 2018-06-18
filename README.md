## HyConfigLib
A port of Bukkit's YAML config.<br>

归功于 @md_5<br>
Credit to @md_5<br>

一个和Bukkit完全一样的YAML配置文件库<br>
嗯真的是完全一样....<br>
除了优化然后封装了一点以外代码都一样 (捂脸...<br>
只是习惯了Bukkit的YAML配置写法就搬过来做了个不用基于Bukkit的类库版本!<br>

Bukkit的用法教程: https://www.spigotmc.org/wiki/creating-a-config-file/ <br>

懒得写教程了... 看看ConfigTest类就应该知道怎么用了吧_(:з」∠)_<br>
<br>

## Maven 导入方法:

首先添加Repo, 在pom里面把这些粘贴进去:

    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

然后添加这个库:
    
    <dependency>
        <groupId>com.github.hykilpikonna</groupId>
        <artifactId>HyConfigLib</artifactId>
        <version>2.0.1-SNAPSHOT</version>
    </dependency>