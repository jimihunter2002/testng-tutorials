package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropFileReading {

    public FileInputStream fileInputStream;
    public Properties prop;
    String path;

    public PropFileReading(String filePath){
        this.path = filePath;
    }

    public String getData(String key) throws IOException {
        fileInputStream = new FileInputStream(path);
        prop = new Properties();
        prop.load(fileInputStream);

        String value = prop.getProperty(key);
        return value;
    }
}
