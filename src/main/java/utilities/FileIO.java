package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that instantiates a file object every time a file is read from/ written into. This class
 * consists of the methods used to read and write to files in a single class. It also includes
 * methods used to create directory.
 */
public class FileIO {

  private FileIO() {
  }


  /**
   * reads the contents of the csv file and returns the contents of the file as a single string
   * including the line separators to keep up with the format of the template file.
   *
   * @param path the path
   * @return the csv content as a string. If the contents are empty, returns an empty string.
   */
  public static String readFile(String path) {
    String content = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line = "";
      while ((line = reader.readLine()) != null) {
        content = content + line + System.lineSeparator();
      }
    } catch (FileNotFoundException e) {
      ErrorLogger.add("File not found");
      ErrorLogger.printLog();
    } catch (IOException e) {
      ErrorLogger.add("IO exception");
      ErrorLogger.printLog();
    }
    return content.trim();
  }

  public static void appendToFile(String content, String path) {
    BufferedWriter bw = null;
    try {
      File file = new File(path);
      if (!file.exists()) {
        file.createNewFile();
      }
      bw = new BufferedWriter(new FileWriter(file, true));
      bw.write(content);
    } catch (IOException ioe) {
      ErrorLogger.add("Something went wrong! Could not append to file :" + ioe.getMessage());
      ErrorLogger.printLog();
    } finally {
      if (bw != null) {
        try {
          bw.close();
        } catch (IOException e) {
          ErrorLogger.add("Failed to close input stream");
          ErrorLogger.printLog();
        }
      }
    }
  }

  /**
   * Write to file.
   *
   * @param content the content
   * @param path    the path
   */
  public static void writeToFile(String content, String path) {
    BufferedWriter bw = null;
    try {
      File file = new File(path);
      if (!file.exists()) {
        file.createNewFile();
      }
      bw = new BufferedWriter(new FileWriter(file));
      bw.append("\"" + "ID" + "\"");
      bw.append(",");
      bw.append("\"" + "text"+"\"");
      bw.append(",");
      bw.append("\""+"completed"+"\"");
      bw.append(",");
      bw.append("\""+"due"+"\"");
      bw.append(",");
      bw.append("\""+"priority"+"\"");
      bw.append(",");
      bw.append("\""+"category"+"\"");
      bw.append("\n");
      bw.write(content);
    } catch (IOException ioe) {
      ErrorLogger.add("Something went wrong! Could not write to file :" + ioe.getMessage());
      ErrorLogger.printLog();
    } finally {
      if (bw != null) {
        try {
          bw.close();
        } catch (IOException e) {
          ErrorLogger.add("Failed to close input stream");
          ErrorLogger.printLog();
        }
      }
    }
  }

}
