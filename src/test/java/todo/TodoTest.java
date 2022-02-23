package todo;

import static org.junit.Assert.*;

import commands.add.Add;
import java.time.LocalDate;
import model.todo.Todo;
import model.todo.Todo.Builder;
import org.junit.Before;
import org.junit.Test;
import controller.parser.Parser;
import controller.todohandler.TodoHandler;
import utilities.ErrorLogger;

public class TodoTest {
  Todo newTodo;
  TodoHandler todoHandler;
  Add addCmd;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    String[] input = new String[]{"--add-todo", "--priority", "1",
        "--todo-text", "This is the text description", "--csv-file",
        "todos.csv", "--display", "--sort-by-priority", "--due", "12/15/2021"};
    Parser parser = new Parser();
    parser.processCmds(input);
    addCmd = (Add) parser.getCmdObjList().get(0);
    todoHandler =  (TodoHandler) parser.getTodoHandler();
    newTodo = addCmd.getNewTodo();
  }

  @Test
  public void getText() {
    assertEquals("This is the text description", newTodo.getText());
  }

  @Test
  public void isCompleted() {
    assertEquals(false, newTodo.isCompleted());
  }

  @Test
  public void getDueDate() {
    LocalDate date = LocalDate.of(2021, 12, 15);
    assertEquals(date, newTodo.getDueDate());
  }

  @Test
  public void getPriority() {
    assertEquals(1, newTodo.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals(null, newTodo.getCategory());
  }

  @Test
  public void setCompleted() {
    newTodo.setCompleted(true);
    assertTrue(newTodo.isCompleted());
  }

  @Test
  public void testEquals() {
    Todo todo2 = addCmd.getNewTodo();
    Todo todo3 = addCmd.getNewTodo();
    assertTrue(newTodo.equals(newTodo));
    assertFalse(newTodo.equals(null));
    assertFalse(newTodo.equals(""));
    assertEquals(todo2, newTodo);
    assertTrue(newTodo.equals(todo2) && todo2.equals(todo3)
        && newTodo.equals(todo3));
  }

  @Test
  public void testEqualsBuilder() {
    Todo todo1 = new Builder("test")
        .addCompleted(true)
        .addDueDate(LocalDate.of(2021, 04, 22))
        .addPriority(3)
        .addCategory("test")
        .build();
    Todo todo2 = todo1;
    Todo todo3 = todo1;
    assertTrue(todo1.equals(todo1));
    assertFalse(todo1.equals(null));
    assertFalse(todo1.equals(""));
    assertTrue(todo1.equals(todo2) && todo2.equals(todo1));
    assertTrue(todo1.equals(todo2) && todo2.equals(todo3)
        && todo1.equals(todo3));

    Todo todo4 = new Builder("test")
        .addCompleted(true)
        .addDueDate(LocalDate.of(2021, 04, 22))
        .addPriority(3)
        .addCategory("test")
        .build();
    assertTrue(todo1.equals(todo4));

    Todo diffTest = new Builder("different")
        .addCompleted(true)
        .addDueDate(LocalDate.of(2021, 04, 22))
        .addPriority(3)
        .addCategory("test")
        .build();
    assertFalse(todo1.equals(diffTest));
  }

  @Test
  public void testHashcode() {
    int testHash = newTodo.hashCode();
    assertEquals(testHash, newTodo.hashCode());
  }

  @Test
  public void testToString() {
    int expID = newTodo.getID();
    assertEquals("Todo{ID=" + expID + ", text='This is the text description', completed=false, dueDate=2021-12-15, priority=1, category='null'}", newTodo.toString());
  }
}