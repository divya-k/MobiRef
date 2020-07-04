package com.simple.ams.util;


import org.apache.commons.beanutils.PropertyUtils;
import org.ho.yaml.Yaml;
import java.io.File;


public class YAMLReader {

    Object ymlData;

    public YAMLReader(String file){
        try{
            this.ymlData= Yaml.load(new File(file));

        }catch (Exception e){
            System.out.println("ERROR: Error while reading yml file");
            e.printStackTrace();
        }
    }

    public  String getKey(String key){
        Object ret = null;
        try{
            ret = PropertyUtils.getProperty(this.ymlData, key);

        }catch (Exception e){
            System.out.println("ERROR: Error while reading yml file");
            e.printStackTrace();
        }
        return ret.toString();
    }

}
