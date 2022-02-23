package commands.add;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

public class AddTest {
  Add addCmd;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    addCmd = new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    cmdList = Arrays.asList("--add-todo", "--priority", "1",
        "--todo-text", "This is the text description", "--csv-file",
        "todos.csv", "--display", "--sort-by-priority", "--due", "12/15/2021");
  }

  @Test
  public void getNewTodo() {
    addCmd.findArgument(cmdList);
    Todo newTodo = addCmd.getNewTodo();
    assertEquals(newTodo.getText(), "This is the text description");
  }

  @Test
  public void findArgument() {
    assertTrue(addCmd.findArgument(cmdList));
  }

  @Test
  public void findAddArgument() {
    assertTrue(addCmd.findAddArgument(cmdList));

    List<String > noAddArgTest = Arrays.asList("--add-todo", "--csv-file",
        "todos.csv","--todo-text", "--display", "--sort-by-priority");
    assertFalse(addCmd.findAddArgument(noAddArgTest));

    List<String> noAddCmdTest = Arrays.asList("--add-todo", "--csv-file",
        "todos.csv", "--display", "--sort-by-priority");
    assertFalse(addCmd.findAddArgument(noAddCmdTest));
  }

  @Test
  public void testToString() {
    Add addCmd1 = new Add(Constants.ADD_TODO_CMD, Constants.TEXT_CMD);
    assertEquals("Add{newTodo=null}", addCmd1.toString());
  }
}