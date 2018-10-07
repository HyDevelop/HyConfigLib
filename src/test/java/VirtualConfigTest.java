import cc.moecraft.yaml.ConfigExplorer;
import cc.moecraft.yaml.HyVirtualConfig;

/**
 * 此类由 Hykilpikonna 在 2018/10/07 创建!
 * Created by Hykilpikonna on 2018/10/07!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class VirtualConfigTest
{
    public static void main(String[] args)
    {
        String configInString =
                "Test:\n" +
                "  TestString: StringValue1\n" +
                "  TestInt: 2\n" +
                "  TestDouble: 3.5\n" +
                "  TestStringList:\n" +
                "  - hi\n" +
                "  - there";

        HyVirtualConfig virtualConfig = new HyVirtualConfig(configInString);

        ConfigExplorer explorer = new ConfigExplorer(virtualConfig);

        explorer.runInConsole();
    }
}
