package commands.display;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import model.todo.Todo;
import org.junit.Before;
import org.junit.Test;

public class PrioritySortTest {
  Display prioritySort;
  List<Todo> todoList;
  List<Todo> prioritySortList;
  Todo todo1;
  Todo todo2;
  Todo todo3;

  @Before
  public void setUp() throws Exception {
    prioritySort = new PrioritySort();
    todo1 = new Todo.Builder("Todo1")
        .addPriority(1).build();
    todo2 = new Todo.Builder("Todo2")
        .addPriority(2).build();
    todo3 = new Todo.Builder("Todo3")
        .addPriority(3).build();
    todoList = Arrays.asList(todo3, todo1, todo2);
  }

  @Test
  public void applyDisplayProperty() {
    prioritySortList = Arrays.asList(todo1, todo2, todo3);
    todoList = prioritySort.applyDisplayProperty(todoList);
    assertEquals(prioritySortList, todoList);
  }
}