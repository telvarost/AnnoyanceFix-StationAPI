package net.glasslauncher.annoyancefix;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public class AnnoyanceFixConfig {

    public static boolean enableBoatFixes = false;
    public static boolean enableArmourCombining = false;

    @EventListener
    void onInit(InitEvent event) {
        try
        {
            LoadConfigFile();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("AnnoyanceFix config file not found.");
            e.printStackTrace();
        }
    }

    public static void LoadConfigFile() throws FileNotFoundException {
        String configFile = "AnnoyanceFixConfig.yaml";

        /** - Write default config file, if config file does not exist */
        if (CreateFile(configFile))
        {
            /** - Config file defaults */
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("enableBoatFixes", TRUE);

            PrintWriter writer = new PrintWriter(new File(configFile));
            Yaml yaml = new Yaml();
            yaml.dump(dataMap, writer);
            writer.close();
        }

        /** - Read out config file */
        InputStream inputStream = new FileInputStream(new File(configFile));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        /** - Apply config file to application */
        enableBoatFixes = (boolean)data.getOrDefault("enableBoatFixes", TRUE);
    }

    public static boolean CreateFile (String filename) {
        try {
            File myObj = new File(filename);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create file: " + filename);
            e.printStackTrace();
            return false;
        }
    }
}
