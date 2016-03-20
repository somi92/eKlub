/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author milos
 */
public class Config {
    
    private Properties props;
    private FileInputStream fis;
    private FileOutputStream fos;
    private static Config INSTANCE;
    
    private Config() {
        props = new Properties();
    }
    
    public static Config getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }
    
    public String getValue(String key) {
        try {
            fis = new FileInputStream("src/main/resources/config/config.properties");
            props.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props.getProperty(key);
    }
    
    public void setValue(String key, Object value) {
        try {
            fos = new FileOutputStream("src/main/resources/config/config.properties");
            props.put(key, value);
            props.store(fos, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
