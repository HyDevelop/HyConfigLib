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

    /**
     * Create a file
     *
     * @param file The file object of the actual file in storage to create.
     * @return Success or not.
     */
    static boolean createFile(File file)
    {
        File absoluteFile = file.getAbsoluteFile();

        if(absoluteFile.exists()) log("Failed to create file: File " + absoluteFile + " already exists.");
        else if (absoluteFile.isDirectory()) log("Failed to create file: File " + absoluteFile + "is a directory.");
        else
        {
            // Detect if parent dir exists.
            if(!absoluteFile.getParentFile().exists())
                if(!absoluteFile.getParentFile().mkdirs())
                    error("Failed to create file: " + absoluteFile + ": Failed to create parent directory.");

            // Create the actual file.
            try
            {
                if (absoluteFile.createNewFile())
                {
                    log("File " + absoluteFile + " is successfully created.");
                    return true;
                }
                else error("Failed to create file " + absoluteFile + ". Reason Unknown.");
            }
            catch (Exception e)
            {
                throw new RuntimeException("[HyConfig] Failed to create file " + absoluteFile + ".", e);
            }
        }
        return false;
    }

    private static void error(String msg)
    {
        throw new RuntimeException("[HyConfig] " + msg);
    }

    public static void log(String msg)
    {
        if (debug) System.out.println("[HyConfig] " + msg);
    }
}
