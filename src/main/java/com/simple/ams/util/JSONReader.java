package com.simple.ams.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ams.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class  JSONReader {

    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(JSONReader.class);
    public Page page;

    public JSONReader(String file) {

        try {
            page = mapper.readValue(new File(file), Page.class);
        } catch (IOException e) {
            logger.error("Error in reading json file");
            e.printStackTrace();
        }
    }

}
