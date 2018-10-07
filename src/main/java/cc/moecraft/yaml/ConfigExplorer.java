package cc.moecraft.yaml;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.MemorySection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.in;

/**
 * 此类由 Hykilpikonna 在 2018/10/04 创建!
 * Created by Hykilpikonna on 2018/10/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@RequiredArgsConstructor
public class ConfigExplorer
{
    @NonNull
    private HyConfigBase config;
    private String currentPath = "";

    /**
     * Run a command line interface explorer.
     */
    public void runInConsole()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (true)
        {
            System.out.print("[" + (config instanceof HyConfig ? ((HyConfig) config).getConfigFile().getName() : "Virtual Config")
                    + " " + currentPath + "]:$ ");
            String input;

            try
            {
                input = reader.readLine();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            if (input.equalsIgnoreCase("exit")) return;
            if (input.equalsIgnoreCase("ll")) printListEntries();
            if (input.toLowerCase().startsWith("cd ")) changePath(input.substring(3));
            if (input.toLowerCase().startsWith("get ")) printValue(input.substring(4));
        }
    }

    /**
     * Print value of a path under the current path.
     * This only supports relative path.
     *
     * @param path The path of the value.
     */
    private void printValue(String path)
    {
        System.out.println(config.get(currentPath + "." + path).toString());
    }

    /**
     * List entries under the current path.
     */
    public void printListEntries()
    {
        StringBuilder result = new StringBuilder();
        ArrayList<String> keys = config.getKeys(currentPath);

        result.append("total ").append(keys.size()).append("\n");

        for (String key : keys)
        {
            String keyPath = currentPath + "." + key;
            Object value = config.get(keyPath);

            if (value instanceof MemorySection) result.append("> DIR    : ").append(key).append("\n");
            else if (value instanceof String)   result.append("> String : ").append(key).append("\n");
            else if (value instanceof Boolean)  result.append("> Bool   : ").append(key).append("\n");
            else if (value instanceof Double)   result.append("> Double : ").append(key).append("\n");
            else if (value instanceof Integer)  result.append("> Int    : ").append(key).append("\n");
            else if (value instanceof List)     result.append("> List   : ").append(key).append("\n");
            else                                result.append("> UNKNOWN: ").append(key).append("\n");
        }

        System.out.println(result);
    }

    /**
     * Change the current path to another path.
     * This only supports relative path.
     *
     * @param path relative path.
     */
    public void changePath(String path)
    {
        ArrayList<String> current = splitPath(currentPath);
        String[] split = path.replace("..", ".prev.").split("\\.");

        for (String onePath : split)
        {
            if (onePath.isEmpty()) continue;
            if (onePath.equals("prev"))
            {
                if (current.size() > 1) current.remove(current.size() - 1);
            }
            else current.add(onePath);
        }

        path =
                recombinePath(current);

        try
        {
            config.getKeys(path);
        }
        catch (NullPointerException e)
        {
            System.err.println("Path " + path + " does not exist or is not directory.");
            ConfigUtils.safeSleep(5);
            return;
        }

        currentPath = path;
    }

    /**
     * Split the path string as ArrayList.
     *
     * @return path split
     */
    private static ArrayList<String> splitPath(String path)
    {
        return new ArrayList<>(Arrays.asList(path.split("\\.")));
    }

    /**
     * Recombine the path ArrayList to a string.
     *
     * @param path path in ArrayList.
     * @return path in String.
     */
    public static String recombinePath(ArrayList<String> path)
    {
        StringBuilder pathInString = new StringBuilder();

        for (String onePath : path)
            pathInString.append(onePath).append(".");

        pathInString.deleteCharAt(pathInString.length() - 1);

        return String.valueOf(pathInString);
    }
}
