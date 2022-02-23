package commands;

import utilities.Constants;

/**
 * The type Csv file.
 */
public class CSVFile extends AbstractCmd {

  /**
   * Instantiates a new Csv file.
   */
  public CSVFile() {
    super(Constants.CSV_FILE_NAME, Constants.CSV_FILE_CMD);
  }


  @Override
  public String toString() {
    return "CSVFile{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
