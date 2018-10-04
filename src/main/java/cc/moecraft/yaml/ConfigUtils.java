package cc.moecraft.yaml;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/09/30 创建!
 * Created by Hykilpikonna on 2018/09/30!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class ConfigUtils
{
    public static boolean debug = false;


    private static void error(String msg)
    {
        throw new RuntimeException("[HyConfig] " + msg);
    }

    public static void log(String msg)
    {
        if (debug) System.out.println("[HyConfig] " + msg);
    }
}
