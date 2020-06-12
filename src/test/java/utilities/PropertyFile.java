package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

    public String getPropertyValue(String key, String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties pro = new Properties();
        try {
            pro.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro.getProperty(key);
    }
}
