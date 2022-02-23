package commands.add;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import model.todo.Todo;
import org.junit.Before;
import org.junit.Test;
import utilities.Constants;
import utilities.ErrorLogger;

public class CategoryTest {
  Category category;
  Add addCmd;
  Todo newTodo;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    category = new Category();
    cmdList = Arrays.asList("--add-todo", "--todo-text",
        "This is the text description", "--csv-file", "todos.csv",
        "--category", "test");
    addCmd = new Add(Constants.ADD_TODO_NAME, Constants.TEXT_CMD);
    addCmd.findArgument(cmdList);
    newTodo = addCmd.getNewTodo();
  }

  @Test
  public void appendBuilder() {
    assertEquals("test", addCmd.getNewTodo().getCategory());
  }

  @Test
  public void testToString() {
    Category category1 = new Category();
    assertEquals("Category{argument='null'}", category1.toString());
  }
}