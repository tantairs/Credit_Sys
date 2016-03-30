/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit_sys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Melons
 */
public class ConfigUtil {

    private static ConfigUtil instance = null;
    String configFilePath = "config.properties";
    private Properties configProperties = null;

    public static ConfigUtil getInstance() {
        if (instance == null) {
            instance = new ConfigUtil();
            instance.loadConfigFile();
        }
        return instance;
    }

    private void loadConfigFile() {
        configProperties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(configFilePath);
            configProperties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Config File: " + configFilePath + " Not Found,\n Generating new config file...");
        } catch (IOException e) {
            System.out.println("Load Failure..");
        }
    }

    public String getProperty(String key) {
        String value = null;
        if (configProperties == null) {
            loadConfigFile();//initialize... if is null, then load....
        }
        value = configProperties.getProperty(key);
        return value;
    }
}
