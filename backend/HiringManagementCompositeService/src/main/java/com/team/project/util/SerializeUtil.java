package com.team.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.project.domain.message.SimpleMessage;

import java.io.IOException;

public class SerializeUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(SimpleMessage message){

        String result = null;
        try {
            result = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static SimpleMessage deSerializeToSimpleMessage(byte[] input){

        SimpleMessage result = null;
        try {
            result = objectMapper.readValue(input, SimpleMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
