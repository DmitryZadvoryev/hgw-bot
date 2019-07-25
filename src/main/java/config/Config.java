package config;

import java.io.*;
import java.util.Properties;

public class Config {

    public static final String BOT_FILE = "src/main/resources/bot.properties";

    public static String BOT_NAME;
    public static String BOT_TOKEN;

    public static void loadBotSettings() {

        Properties bot = new Properties();

        try (InputStream is = new FileInputStream(BOT_FILE)) {
            bot.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BOT_NAME = bot.getProperty("bot.name");
        BOT_TOKEN = bot.getProperty("bot.token");
    }
}



