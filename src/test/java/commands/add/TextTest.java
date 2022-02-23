package commands.add;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

public class TextTest {
  Add text;
  Add addCmd;
  Todo newTodo;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    text = new Text();
    addCmd = new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    cmdList = Arrays.asList("--add-todo", "--todo-text",
        "This is the text description", "--csv-file", "todos.csv");
    addCmd.findArgument(cmdList);
    newTodo = addCmd.getNewTodo();
  }
  @Test
  public void appendBuilder() {
    assertEquals("This is the text description", newTodo.getText());
  }

  @Test
  public void testToString() {
    Text testText = new Text();
    assertEquals("Text{argument='null'}", testText.toString());
  }
}