package com.rk.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Created by Qin_Yikai on 2018-10-02.
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String ConvertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper.writeValueAsString(object);
    }

    public static <T> String Serialize(T t) {
        if (t == null) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            //mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("序列化失败", e);
            return null;
        }
    }

    public static <T> T Deserialize(String jsonData, Class<T> valueType) {
        if (jsonData == null) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonData, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("反序列化失败", e);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("反序列化失败", e);
            return null;
        }
    }
}
