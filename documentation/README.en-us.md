<h1 align="center">
  <br>
  <br>
  HyConfigLib
  <h4 align="center">
  A simple YAML configuration library.
  </h4>
  <h5 align="center">
<a href="#basics">Basic Info</a>&nbsp;&nbsp;
<a href="#maven">Maven Import</a>&nbsp;&nbsp;
<a href="#development">Development</a>&nbsp;&nbsp;
<a href="#license">License</a>
</h5>
  <br>
  <br>
  <br>
</h1>

<a name="basics"></a>
Basic Information:
--------

* A port of the config lib in Minecraft Bukkit (Spigot).
* There's no difference except that this config lib can run witout a Minecraft server.
* For people who is used to write config on Minecraft plugins but now want to write some independent programs.

All credit belongs to [@md_5](https://www.spigotmc.org/members/md_5.1/).<br>
Tutorial on creating a Minecraft config: https://www.spigotmc.org/wiki/creating-a-config-file/ <br>

<br>

<a name="maven"></a>
Maven Import:
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
