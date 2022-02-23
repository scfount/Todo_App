package commands;

import static org.junit.Assert.*;

import commands.add.Add;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import utilities.ErrorLogger;

public class CompleteTest {
  AbstractCmd completeId;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    completeId = new Complete();
    cmdList = Arrays.asList("--complete-todo", "1", "--csv-file", "todos.csv");
  }

  @Test
  public void isValidArgument() {
    completeId.findArgument(cmdList);
    assertTrue(completeId.isValidArgument());
    assertEquals("1", completeId.getArgument());
  }

  @Test
  public void isValidArgument2() {
    List<String> cmdList = Arrays.asList("--complete-todo", "-1", "--csv-file", "todos.csv");
    completeId.findArgument(cmdList);
    assertFalse(completeId.isValidArgument());
  }

  @Test
  public void isValidArgument3() {
    List<String> cmdList = Arrays.asList("--complete-todo", "hi", "--csv-file", "todos.csv");
    completeId.findArgument(cmdList);
    assertFalse(completeId.isValidArgument());
  }
}