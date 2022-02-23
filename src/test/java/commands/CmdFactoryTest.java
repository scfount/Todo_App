package commands;

import static org.junit.Assert.*;

import commands.add.Add;
import commands.display.Display;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import utilities.Constants;
import utilities.ErrorLogger;

public class CmdFactoryTest {
  CmdFactory cmdFactory;
  private String add;
  private String csv;
  private String complete;
  private String display;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    cmdFactory = new CmdFactory();
    add = Constants.ADD_TODO_CMD;
    csv = Constants.CSV_FILE_CMD;
    complete = Constants.COMPLETE_TODO_CMD;
    display = Constants.DISPLAY_CMD;
  }

  @Test
  public void getCmd() {
    assertEquals(new CSVFile(), cmdFactory.getCmd(csv));

    assertEquals(new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD),
        cmdFactory.getCmd(add));

    assertEquals(new Complete(), cmdFactory.getCmd(complete));

    assertEquals(new Display(Constants.DISPLAY_NAME, Constants.DISPLAY_CMD),
        cmdFactory.getCmd(display));
  }

  @Test
  public void verifyCommands() {
    assertEquals(new CSVFile(), cmdFactory.getCmd(csv));
    assertEquals(new Complete(), cmdFactory.getCmd(complete));
    assertTrue(cmdFactory.verifyCommands());
  }

  @Test
  public void verifyCommands2() {
    assertEquals(new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD),
        cmdFactory.getCmd(add));
    assertEquals(new Complete(), cmdFactory.getCmd(complete));
    assertFalse(cmdFactory.verifyCommands());
  }

  @Test
  public void verifyCommands3() {
    assertEquals(new CSVFile(), cmdFactory.getCmd(csv));
    assertFalse(cmdFactory.verifyCommands());
  }
}