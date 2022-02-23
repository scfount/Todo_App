package commands.display;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import model.todo.Todo;
import org.junit.Before;
import org.junit.Test;

public class ShowIncompleteTest {
  Display showIncomplete;
  List<String> cmdList;
  List<Todo> todoList;
  List<Todo> incompleteList;
  Todo todo1;
  Todo todo2;
  Todo todo3;

  @Before
  public void setUp() throws Exception {
    showIncomplete = new ShowIncomplete();
    cmdList = Arrays.asList("--display", "--show-incomplete", "--csv-file", "todos.csv");
    todo1 = new Todo.Builder("Todo1")
        .addCompleted(true).build();
    todo2 = new Todo.Builder("Todo2").build();
    todo3 = new Todo.Builder("Todo3")
        .addCompleted(false).build();
    todoList = Arrays.asList(todo1, todo2, todo3);
  }

  @Test
  public void applyDisplayProperty() {
    incompleteList = Arrays.asList(todo2, todo3);
    todoList = showIncomplete.applyDisplayProperty(todoList);
    assertEquals(incompleteList, todoList);
  }
}