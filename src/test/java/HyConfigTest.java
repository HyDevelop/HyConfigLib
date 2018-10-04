import cc.moecraft.yaml.ConfigExplorer;
import cc.moecraft.yaml.HyConfig;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/10/04 创建!
 * Created by Hykilpikonna on 2018/10/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class HyConfigTest
{
    public static void main(String[] args)
    {
        HyConfig config = new HyConfig(new File("TestConfig/test.yml"));

        ConfigExplorer explorer = new ConfigExplorer(config);

        explorer.runInConsole();
    }
}
