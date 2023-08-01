package net.glasslauncher.annoyancefix;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnnoyanceFixConfig {

    public static boolean enableBoatFixes = false;
    public static boolean enableArmourCombining = false;

    @EventListener
    void onInit(InitEvent event) {
        ReadConfigFile();
    }

    public static void ReadConfigFile() {
        String configSettingsString;
        String configFile = "AnnoyanceFixConfig.json";

        CreateFile(configFile);
        configSettingsString = ReadFile(configFile);

        if (0 < configSettingsString.length())
        {
            char setting1 = configSettingsString.charAt(0);
            enableBoatFixes = (setting1 == 'Y') ? true : false;
        }
    }

    public static void CreateFile (String filename) {
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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
