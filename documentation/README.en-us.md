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
<a href="#usage">Usage</a>&nbsp;&nbsp;
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

Add the JitPack repo into your `pom.xml` first:

```xml
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then you can add this library as dependency:

```xml
<dependency>
    <groupId>com.github.hydevelop</groupId>
    <artifactId>HyConfigLib</artifactId>
    <version>3.0.38</version>
</dependency>
```

Make sure you reimport if you're using IntelliJ IDEA!

<br>

<a name="usage"></a>
Usage:
--------

#### 1. Create a HyConfig Object:

```java
// Pass in only the java.io.File object that stores the config file path.
// This will load automatically after the object is initialized.
// Read only will be enabled by default, use config.setEnableWrite(true); to enable write.
HyConfig config = new HyConfig(new File("./config.yml"));
```

#### 2. Use it:

```yml
# Example YML:
Test:
  TestString: StringValue1
  TestInt: 2
  TestDouble: 3.5
  TestStringList:
  - hi
  - there
```

```java
// Code to read Example YML

// Read Config
// Comment before each variable represents their value.

// "StringValue1"
String testString = config.getString("Test.TestString");

// 2
int testInt = config.getInt("Test.TestInt");

// ["hi", "there"]
List<String> testList = config.getStringList("Test.TestStringList");

// ["TestString", "TestInt", "TestDouble", "TestStringList"]
ArrayList<String> keysUnderTest = config.getKeys("Test");


// Write Config
// Set value requires enableWrite to be true, if not, calling save() won't do anything.
// All comments will be removed when saving.
config.setEnableWrite(true);
config.set("Test.TestSetString", "TestValue");
config.save();


// Enable Auto Backup
// This will take up a lot of space because it creates a new backup every time save() is called.
config.setBackupDir(new File("./backups/"));
```
