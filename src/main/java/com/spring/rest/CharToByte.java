package com.spring.rest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encoding types:
 *
 * <p>ASCII UTF-8 ISO-8859-1
 *
 * <p>UTF-16 - Most commonly used encoding types in Java for Internal String Representation
 * (default). While UTF-16 is the default encoding for Java strings, you can choose other encodings,
 * such as UTF-8, when working with byte arrays or streams.
 *
 * <p><a
 * href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supporting
 * Encodings</a>
 */
public class CharToByte {

  static Logger logger = LoggerFactory.getLogger(CharToByte.class);

  public static void main(String[] args) {
    logger.info("Main From CHarToByte");

    String charA = "ab";

    // Using UTF-8 encoding (most common)
    byte[] utf8Bytes = charA.getBytes(StandardCharsets.UTF_8);
    logger.info("utf8Bytes size :{}", utf8Bytes.length);

    // Using UTF-8 encoding (most common)
    byte[] usASCIIBytes = charA.getBytes(StandardCharsets.US_ASCII);
    logger.info("usASCIIBytes size :{}", usASCIIBytes.length);

    byte[] iso88591Bytes = charA.getBytes(StandardCharsets.ISO_8859_1);
    logger.info("iso88591Bytes size :{}", iso88591Bytes.length);

    // Using UTF-8 encoding (most common)
    byte[] utf16Bytes = charA.getBytes(StandardCharsets.UTF_16);
    logger.info("utf16Bytes size :{}", utf16Bytes.length);

    logger.info("Default charset :{}", Charset.defaultCharset().displayName());
  }
}
