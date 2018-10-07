package cc.moecraft.yaml;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2018/10/07 创建!
 * Created by Hykilpikonna on 2018/10/07!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class HyVirtualConfig extends HyConfigBase
{
    public HyVirtualConfig(String config)
    {
        try
        {
            super.loadFromString(config);
        }
        catch (InvalidConfigurationException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override @Deprecated
    public void load(File file)
    {
        throw new RuntimeException("Virtual config cannot load from file.");
    }

    @Override @Deprecated
    public void load(String file)
    {
        throw new RuntimeException("Virtual config cannot load from file.");
    }


    @Override
    public String toString()
    {
        return saveToString();
    }
}
