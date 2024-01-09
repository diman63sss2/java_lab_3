package com.example.dezdemoniyslab.controllers;

import com.example.dezdemoniyslab.models.Book;
import com.example.dezdemoniyslab.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;

public class Mapper2XLT {


        public static String transform2XLT(List<?> list, String xsltPath)  {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule()); // java 8 date/time type `java.time.LocalDateTime` not supported by default:
            StringWriter writer = new StringWriter();
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/" + xsltPath))
                        .newTransformer();

                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(list))),
                        new StreamResult(writer));
            } catch (TransformerException | JsonProcessingException e){
                throw new RuntimeException(e);
            }


            return writer.toString();
        }

        public static String transform2XLT(Object obj, String xsltPath)  {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule()); // java 8 date/time type `java.time.LocalDateTime` not supported by default:
            StringWriter writer = new StringWriter();
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/" + xsltPath))
                        .newTransformer();

                transformer.transform(new StreamSource(
                                new ByteArrayInputStream(xmlMapper.writeValueAsBytes(obj))),
                        new StreamResult(writer));
            } catch (TransformerException | JsonProcessingException e){
                throw new RuntimeException(e);
            }


            return writer.toString();
        }


}
