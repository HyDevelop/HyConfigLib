import cc.moecraft.yaml.Config;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/05/03 创建!
 * Created by Hykilpikonna on 2018/05/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class ConfigTest
{
    public static void main(String[] args)
    {
        MyConfig config = new MyConfig();
    }

    public static class MyConfig extends Config
    {
        public MyConfig()
        {
            super("TestConfig" + File.separator + "Test2ndDir", "Test", "yml1", true, true);
        }

        @Override
        public void readConfig()
        {
            System.out.println("TestString: " + getString("Test.TestString"));
            System.out.println("TestInt: " + getString("Test.TestInt"));
            System.out.println("TestDouble: " + getString("Test.TestDouble"));
            System.out.println("TestStringList: " + getString("Test.TestStringList"));
            System.out.println("Keys: " + getKeys("Test"));
        }

        @Override
        public void writeDefaultConfig()
        {
            addDefault("Test.TestString", "StringValue1");
            addDefault("Test.TestInt", 2);
            addDefault("Test.TestDouble", 3.5d);
            addDefault("Test.TestStringList", new String[]{"hi", "there"});
        }
    }
}
