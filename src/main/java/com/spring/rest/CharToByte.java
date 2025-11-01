package com.spring.rest;

import org.apache.tomcat.util.digester.DocumentProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Encoding types:
 *
 * ASCII
 * UTF-8
 * ISO-8859-1
 *
 * UTF-16 - Most commonly used encoding types in Java for Internal String Representation (default). While UTF-16 is the default encoding for Java strings, you can choose other encodings, such as UTF-8, when working with byte arrays or streams.
 *
 * <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supporting Encodings</a>
 */
public class CharToByte {

    public static void main(String[] args) {
        System.out.println("Main From CHarToByte");

        String charA = "ab";

        // Using UTF-8 encoding (most common)
        byte[] utf8Bytes = charA.getBytes(StandardCharsets.UTF_8);
        System.out.println("utf8Bytes size :"+utf8Bytes.length);

        // Using UTF-8 encoding (most common)
        byte[] usASCIIBytes = charA.getBytes(StandardCharsets.US_ASCII);
        System.out.println("usASCIIBytes size :"+usASCIIBytes.length);

        byte[] iso88591Bytes = charA.getBytes(StandardCharsets.ISO_8859_1);
        System.out.println("iso88591Bytes size :"+iso88591Bytes.length);

        // Using UTF-8 encoding (most common)
        byte[] utf16Bytes = charA.getBytes(StandardCharsets.UTF_16);
        System.out.println("utf16Bytes size :"+utf16Bytes.length);

        System.out.println("Default charset :"+ Charset.defaultCharset().displayName());


    }
}
