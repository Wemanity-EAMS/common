package com.wemanity.common.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XmlMapper {
    private XmlMapper(){}
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();


    //Xml string to object
    public static <T> T xmlStringToObject(String xml, Class<T> type) throws IOException {
        return xmlMapper.readValue(xml, type);
    }
    //Xml String to Json String
    public static String xmlStringToJsonString(String xml) throws IOException {
        JsonNode jsonNode = xmlMapper.readTree(xml);
        return objectMapper.writeValueAsString(jsonNode);
    }
    //Xml String to Json file
    public static void xmlStringToJsonFile(String xml, String filePath) throws IOException {
        JsonNode jsonNode = xmlMapper.readTree(xml);
        String json = objectMapper.writeValueAsString(jsonNode);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(json);
        }
    }
    //Xml String to xml file
    public static void xmlStringToXmlFile(String xml, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(xml);
        }
    }


    //Xml file to object
    public static <T> T xmlFileToObject(String filePath, Class<T> type) throws IOException {
        File xmlFile = new File(filePath);
        return xmlMapper.readValue(xmlFile, type);
    }
    //Xml file to xml string
    public static String xmlFileToXmlString(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return String.join("", lines);
    }
    //Xml file to Json file
    public static void xmlFileToJsonFile(String xmlFilePath, String jsonFilePath) throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(xmlFilePath)), StandardCharsets.UTF_8);
        JsonNode jsonNode = xmlMapper.readTree(xml);
        String json = objectMapper.writeValueAsString(jsonNode);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilePath))) {
            writer.write(json);
        }
    }
    //xml file to Json string
    public static String xmlFileToJsonString(String xmlFilePath) throws IOException {
        File xmlFile = new File(xmlFilePath);
        String xml = new String(Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);
        JsonNode jsonNode = xmlMapper.readTree(xml);
        return objectMapper.writeValueAsString(jsonNode);
    }


}
