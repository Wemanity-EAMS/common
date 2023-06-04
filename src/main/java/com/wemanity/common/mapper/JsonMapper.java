package com.wemanity.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class JsonMapper {
    private JsonMapper() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();
     //Json string to Object
    public static <T> T jsonStringToObject(String json, Class<T> objectType) throws JsonProcessingException {
        return objectMapper.readValue(json, objectType);
    }
    // Json string to xml string
    public static String jsonStringToXmlString(String json) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);
        return xmlMapper.writeValueAsString(jsonNode);
    }
    //Json string to xml file
    public static <T> void jsonStringToXmlFile(String json, String filePath, Class<T> type) throws IOException {
        T object = objectMapper.readValue(json, type);
        xmlMapper.writeValue(new File(filePath), object);
    }
    public static void jsonStringToJsonFile(String json, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(json);
        }
    }



    //Json file to object
    public static <T> T jsonFileToObject(File jsonFile, Class<T> type) throws IOException {
        return objectMapper.readValue(jsonFile, type);
    }
    //Json file to xml string
    public static String jsonFileToXmlString(File jsonFile) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        return xmlMapper.writeValueAsString(jsonNode);
    }
    //Json file to xml file
    public static void jsonFileToXmlFile(String jsonFilePath, String xmlFilePath) throws IOException {
        File jsonFile = new File(jsonFilePath);
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        String xml = xmlMapper.writeValueAsString(jsonNode);
        xml = xml.replace("<String>", "").replace("</String>", "");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFilePath))) {
            writer.write(xml);
        }
    }
    //Json file to json string
    public static String jsonFileToJsonString(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return String.join("", lines);
    }


}
