# HyConfigLib

一个和 Minecraft Bukkit (Spigot) 完全一样的 YAML 配置文件库<br>
嗯真的是完全一样...<br>
( 除了优化然后封装了一点以外代码都一样w (/▽＼)<br>
只是习惯了Bukkit的YAML配置写法就搬过来做了个不用基于Bukkit的类库版本!<br>

归功于 @md_5<br>

Bukkit的用法教程: https://www.spigotmc.org/wiki/creating-a-config-file/ <br>

懒得写教程了... 看看HyConfigTest类就应该知道怎么用了吧_(:з」∠)_<br>
<br>

<a name="maven"></a>
Maven 导入:
--------

没有添加 JitPack 的 Repo 的话首先添加 Repo,在 pom 里面把这些粘贴进去:

```xml
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```

然后添加这个库:

```xml
<dependency>
    <groupId>com.github.hydevelop</groupId>
    <artifactId>HyConfigLib</artifactId>
    <version>3.0.38</version>
</dependency>
```

然后ReImport之后就导入好了!
