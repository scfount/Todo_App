package commands.add;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

public class DueDateTest {
  DueDate due;
  Add addCmd;
  Todo newTodo;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    due = new DueDate();
    addCmd = new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    cmdList = Arrays.asList("--add-todo", "--todo-text", "This is the text description",
        "--csv-file", "todos.csv", "--due", "12/15/2021");
    addCmd.findArgument(cmdList);
    newTodo = addCmd.getNewTodo();
  }

  @Test
  public void getDueDate() {
    due.findAddArgument(cmdList);
    assertEquals(due.getDueDate(), LocalDate.of(2021, 12, 15));
  }

  @Test
  public void isValidArgument() {
    assertTrue(due.findAddArgument(cmdList));
  }

  @Test
  public void invalidArgument() {
    cmdList = Arrays.asList("--add-todo", "--todo-text", "This is the text description",
        "--csv-file", "todos.csv", "--due", "12-15-2021");
    assertFalse(due.findAddArgument(cmdList));
  }

  @Test
  public void appendBuilder() {
    assertEquals(newTodo.getDueDate(), LocalDate.of(2021, 12, 15));
  }

  @Test
  public void testToString() {
    assertEquals("DueDate{argument='null'}", due.toString());
  }
}