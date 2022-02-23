package commands.add;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

public class PriorityTest {
  Priority priority;
  Add addCmd;
  Todo newTodo;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    priority = new Priority();
    cmdList = Arrays.asList("--add-todo", "--priority", "1",
        "--todo-text", "This is the text description", "--csv-file", "todos.csv");
    addCmd = new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    addCmd.findArgument(cmdList);
    newTodo = addCmd.getNewTodo();
  }

  @Test
  public void isValidArgument() {
    priority.findAddArgument(cmdList);
    assertTrue(priority.isValidArgument());
  }

  @Test
  public void testBadPriority() {
    List<String> badPriority = Arrays.asList("--add-todo", "--priority", "4",
        "--todo-text", "This is the text description", "--csv-file", "todos.csv");
    assertFalse(priority.findAddArgument(badPriority));

    List<String> badPriority2 = Arrays.asList("--add-todo", "--priority", "one",
        "--todo-text", "This is the text description", "--csv-file", "todos.csv");
    assertFalse(priority.findAddArgument(badPriority2));

    List<String> badPriority3 = Arrays.asList("--add-todo", "--priority", "0",
        "--todo-text", "This is the text description", "--csv-file", "todos.csv");
    assertFalse(priority.findAddArgument(badPriority3));
  }


  @Test
  public void appendBuilder() {
    assertEquals(1, newTodo.getPriority());
  }

  @Test
  public void testToString() {
    Priority newPriority = new Priority();
    assertEquals("Priority{argument='null'}", newPriority.toString());
  }
}