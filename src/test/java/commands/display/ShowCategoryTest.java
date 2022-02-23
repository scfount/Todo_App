package commands.display;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;
import utilities.ErrorLogger;


public class ShowCategoryTest {
  Display showCategory;
  List<String> cmdList;
  List<Todo> todoList;
  List<Todo> categoryList;
  Todo todo1;
  Todo todo2;
  Todo todo3;
  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    showCategory = new ShowCategory();
    cmdList = Arrays.asList("--display", "--show-category", "work", "--csv-file", "todos.csv");
    todo1 = new Todo.Builder("Todo1")
        .addCategory("work").build();
    todo2 = new Todo.Builder("Todo2")
        .addCategory("work").build();
    todo3 = new Todo.Builder("Todo3")
        .addCategory("school").build();
    todoList = Arrays.asList(todo3, todo1, todo2);
  }

  @Test
  public void findAddArgument() {
    showCategory.findAddArgument(cmdList);
    assertEquals("work", showCategory.getArgument());
  }

  @Test
  public void findAddArgumentError() {
    List<String> cmdList2 = Arrays.asList("--display", "--show-category", "--csv-file", "todos.csv");
    showCategory.findAddArgument(cmdList2);
    assertEquals(1, ErrorLogger.getSize());
  }

  @Test
  public void applyDisplayProperty() {
    showCategory.findAddArgument(cmdList);
    categoryList = Arrays.asList(todo1, todo2);
    todoList = showCategory.applyDisplayProperty(todoList);
    assertEquals(categoryList, todoList);
  }
}