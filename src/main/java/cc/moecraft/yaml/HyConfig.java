package cc.moecraft.yaml;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static cc.moecraft.yaml.ConfigUtils.createFile;

/**
 * 此类由 Hykilpikonna 在 2018/09/30 创建!
 * Created by Hykilpikonna on 2018/09/30!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Getter @Setter
public class HyConfig extends YamlConfiguration
{
    private File configFile;
    private File backupDir;

    private boolean enableWrite;

    private String datePattern = "yyyy-MM-dd-HH-mm-ss";

    /**
     * Construct a HyConfig object.
     *
     * @param configFile The config file path stored in a File object.
     *                   The actual file in storage will be created when initializing if it
     *                   does not exist.
     * @param initialize Load the config when the object is initialized or not.
     * @param enableWrite If the config file is dynamic, this have to be enabled.
     *                    Otherwise disable this because saving file would cause the comments
     *                    to be removed, and normal config files don't need to be saved, only read.
     */
    public HyConfig(File configFile, boolean initialize, boolean enableWrite)
    {
        super();

        options().copyDefaults(true);

        this.configFile = configFile;
        this.enableWrite = enableWrite;

        if (initialize) initialize();
    }

    /**
     * Copy the config file out from jar resource.
     *
     * @param loader Class loader with resources in it.
     * @param resourceName The name of the resource in the jar file.
     * @throws IOException IO error.
     */
    public void createFromResources(ClassLoader loader, String resourceName) throws IOException
    {
        InputStream resourceAsStream = loader.getResourceAsStream(resourceName);
        Files.copy(resourceAsStream, Paths.get(configFile.getAbsolutePath()));
    }

    /**
     * Initialize or Reload
     */
    public void initialize()
    {
        beforeLoad();
        save();
        load();
        afterLoad();
    }

    /**
     * Called after load
     */
    public void afterLoad() {}

    /**
     * Called before load
     */
    public void beforeLoad() {}

    /**
     * Save, and backup if the backupDir is not null.
     *
     * @return Success or not
     */
    public boolean save()
    {
        if (!enableWrite) return false;
        try
        {
            if (backupDir != null) backup(backupDir);
            save(configFile);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Make a backup of the current config file.
     *
     * @param backupDir Backup path
     * @return Success or not
     */
    public boolean backup(File backupDir)
    {
        try
        {
            save(generateBackupFile(backupDir));
            return true;
        }
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a backup file File object.
     * This does NOT create the actual file in storage.
     *
     * @param backupDir Backup path
     * @return File object.
     */
    private File generateBackupFile(File backupDir)
    {
        String date = DateTimeFormatter.ofPattern(datePattern).format(LocalDateTime.now());
        return new File(backupDir, configFile.getName() + "@" + date + ".bak");
    }

    /**
     * Load the config file.
     */
    public void load()
    {
        try
        {
            super.load(configFile);
        }
        catch (FileNotFoundException e)
        {
            if (createFile(configFile)) load();
            else throw new RuntimeException("[HyConfig] Failed to create file. " + configFile, e);
        }
        catch (InvalidConfigurationException e)
        {
            throw new RuntimeException("[HyConfig] Failed to read config: invalid YML format. " + configFile, e);
        }
        catch (IOException e)
        {
            throw new RuntimeException("[HyConfig] Failed to read config with IO problems. " + configFile, e);
        }
    }

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
        return new ArrayList<>(getConfigurationSection(path).getKeys(false));
    }
}
