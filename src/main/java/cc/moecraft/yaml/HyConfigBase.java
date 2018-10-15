package cc.moecraft.yaml;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/10/07 创建!
 * Created by Hykilpikonna on 2018/10/07!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public abstract class HyConfigBase extends YamlConfiguration
{
    /**
     * Package-private no args constructor.
     */
    HyConfigBase() {}

    /**
     * Get keys in a path.
     *
     * Example:
     *   KeyA:
     *     SubKey1: "TestValue"
     *     SubKey2: "TestValue2"
     *   KeyB: "TestValue3"
     *
     *   getKeys("") will return ["KeyA", "KeyB"]
     *   getKeys("KeyA") will return ["SubKey1", "SubKey2"]
     *
     * @param path Path of the parent key.
     * @return Keys under the parent key.
     */
    public ArrayList<String> getKeys(String path)
    {
        try
        {
            return new ArrayList<>(getConfigurationSection(path).getKeys(false));
        }
        catch (NullPointerException e)
        {
            return new ArrayList<>();
        }
    }

    /**
     * Determine whether a path is a directory or not.
     *
     * @param path Path
     * @return Is dir -> true, Not dir -> false
     */
    public boolean isDir(String path)
    {
        return get(path) instanceof MemorySection;
    }

    /**
     * Remove entry in a path.
     *
     * @param path Path to an entry, or a dir.
     */
    public void remove(String path)
    {
        set(path, null);
    }
}
