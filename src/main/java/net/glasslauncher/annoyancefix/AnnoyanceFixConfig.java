package net.glasslauncher.annoyancefix;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Boolean.TRUE;

public class AnnoyanceFixConfig {

    public static boolean enableBoatFixes = false;
    public static boolean enableArmourCombining = false;

    @EventListener
    void onInit(InitEvent event) {
        try
        {
            ReadConfigFile();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public static void ReadConfigFile() throws FileNotFoundException {
        String configFile = "AnnoyanceFixConfig.yaml";

        if (CreateFile(configFile))
        {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("enableBoatFixes", TRUE);

            PrintWriter writer = new PrintWriter(new File(configFile));
            Yaml yaml = new Yaml();
            yaml.dump(dataMap, writer);
            writer.close();
        }

        InputStream inputStream = new FileInputStream(new File(configFile));

        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        enableBoatFixes = (boolean)data.getOrDefault("enableBoatFixes", TRUE);
    }

    public static boolean CreateFile (String filename) {
        try {
            File myObj = new File(filename);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public static void DeleteFile (String filename) {
        File myObj = new File(filename);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void WriteToFile (String filename, String data) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String ReadFile (String filename) {
        String data = "";

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }
}
