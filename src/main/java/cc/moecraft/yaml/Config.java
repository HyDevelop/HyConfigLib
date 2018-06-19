package cc.moecraft.yaml;

import cc.moecraft.yaml.utils.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static cc.moecraft.yaml.utils.FileUtils.createFile;

/**
 * 此类由 Hykilpikonna 在 2017/07/25 创建!
 * Created by Hykilpikonna on 2017/07/25!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class Config extends YamlConfiguration
{
    private File configFile;

    private String dir;
    private String fileName;
    private String fileExtension;

    private boolean autoBackup;

    private static final String RELATIVE_PATH_PREFIX = System.getProperty("os.name").toLowerCase().contains("win") ? ".\\" : "";

    private boolean writeOnInit;

    /**
     * 新建一个Config
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @param autoBackup 是否启用保存前自动备份
     * @param initialize 是否创建对象后直接加载
     */
    public Config(String dir, String fileName, String fileExtension, boolean autoBackup, boolean initialize, boolean writeOnInit)
    {
        super();

        options().copyDefaults(true);

        this.dir = dir;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.autoBackup = autoBackup;

        this.configFile = getFileFromDir(dir, fileName, fileExtension);

        if (initialize) initialize();
    }

    public Config(String dir, String fileName, String fileExtension, boolean autoBackup, boolean initialize)
    {
        this(dir, fileName, fileExtension, autoBackup, initialize, true);
    }

    public Config(String dir, String fileName, String fileExtension)
    {
        this(dir, fileName, fileExtension, false, true, true);
    }

    /**
     * 从resources导出
     * @param resourceClass 带resources的类
     * @throws IOException 复制错误
     */
    public void createFromResources(Class resourceClass) throws IOException, NullPointerException
    {
        FileUtils.copy(new File(resourceClass.getClassLoader().getResource(File.separator + fileName + "." + fileExtension).getPath()), configFile);
    }

    /**
     * 初始化
     */
    public void initialize()
    {
        load();
        checkConfig();
    }

    /**
     * 检测配置是否是最新
     *
     * 如果未生成, 执行writeDefaultConfig()方法
     * 如果生成过但不是最新, 执行writeConfig()方法
     * 然后保存, 然后重载, 然后执行readConfig()方法
     */
    public void checkConfig()
    {
        if (writeOnInit)
        {
            writeDefaultConfig();
            save();
            load();
        }
        readConfig();
    }

    /**
     * 在检测配置后 读取配置
     */
    public abstract void readConfig();

    /**
     * 在检测到配置未生成时 写默认配置
     */
    public abstract void writeDefaultConfig();

    /**
     * 重载配置
     * 重载后会执行readConfig();
     */
    public void reload()
    {
        load();
        checkConfig();
    }

    /**
     * 保存, 如果自动备份的话就备份
     *
     * @return 是否保存成功
     */
    public boolean save()
    {
        try {
            if (autoBackup) backup();
            save(configFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 备份
     *
     * @return 是否备份成功
     */
    public boolean backup()
    {
        try {
            save(getBackupFileFromDir(dir, fileName, fileExtension));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从一个路径获取配置文件
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @return 配置文件
     */
    public static File getFileFromDir(String dir, String fileName, String fileExtension)
    {
        dir = dir == null || dir.equals("") ? "" : dir;
        return new File(RELATIVE_PATH_PREFIX + dir + File.separator + fileName + "." + fileExtension);
    }

    /**
     * 从路径获取备份文件
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @return 备份文件(格式为 backup 路径 文件名at当前系统时间.文件后缀
     */
    public static File getBackupFileFromDir(String dir, String fileName, String fileExtension)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return new File(RELATIVE_PATH_PREFIX + dir + File.separator + "Backup" + File.separator + fileName + "@" + dtf.format(now) + "." + fileExtension);
    }

    public void load()
    {
        try {
            super.load(configFile);
        } catch (FileNotFoundException e) {
            if (createFile(configFile)) load();
            else System.out.println("[MoeConfig] 配置读取失败: 无法创建文件");
        } catch (InvalidConfigurationException e) {
            System.out.println("[MoeConfig] 配置读取失败: YML非法");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[MoeConfig] 配置读取失败: 无法加载文件");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getKeys(String path)
    {
        return new ArrayList<>(getConfigurationSection(path).getKeys(false));
    }

    public String getDir() {
        return dir;
    }

    public boolean isAutoBackup() {
        return autoBackup;
    }

    public File getConfigFile() {
        return configFile;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }
}

