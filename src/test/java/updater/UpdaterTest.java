package updater;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.todo.Todo;
import model.todo.Todo.Builder;
import model.updater.Updater;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import utilities.ErrorLogger;
import utilities.FileIO;
import utilities.IdGenerator;

public class UpdaterTest {
  Updater updater;
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    updater = new Updater();
  }

  @Test
  public void updateCSV() {
    List<Todo> listOfTodos = new ArrayList<>();
    Builder newBuilder = new Builder("This is a test todo");
    IdGenerator.resetIDGenerator();
    Todo newTodo = newBuilder.addCategory("assignment")
        .addPriority(1)
        .build();
    listOfTodos.add(newTodo);
    File testFile = new File(tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    assertFalse(testFile.exists());
    updater.updateCSV(listOfTodos, tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    String content = FileIO.readFile(tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    assertEquals("\"ID\",\"text\",\"completed\",\"due\",\"priority\",\"category\"\n"
        + "\"1\",\"This is a test todo\",\"false\",\"?\",\"1\",\"assignment\"", content);
  }

  @Test
  public void appendTodo() {
    List<Todo> listOfTodos = new ArrayList<>();
    Builder newBuilder = new Builder("This is a test todo");
    IdGenerator.resetIDGenerator();
    Todo newTodo = newBuilder.addCategory("assignment")
        .addPriority(1)
        .build();
    listOfTodos.add(newTodo);
    File testFile = new File(tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    assertFalse(testFile.exists());
    updater.updateCSV(listOfTodos, tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    String content = FileIO.readFile(tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    assertEquals("\"ID\",\"text\",\"completed\",\"due\",\"priority\",\"category\"\n"
        + "\"1\",\"This is a test todo\",\"false\",\"?\",\"1\",\"assignment\"", content);

    Todo newTodo2 = newBuilder.addCategory("next element")
        .addPriority(2)
        .build();
    updater.appendTodo(newTodo2, tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    content = FileIO.readFile(tempFolder.getRoot().getPath()+File.separator+"test_todos.csv");
    // since this is being externally added and not via commands, the ID is not being incremented automatically
    // incrementing ID is done inside todohandler
    assertEquals("\"ID\",\"text\",\"completed\",\"due\",\"priority\",\"category\"\n"
        + "\"1\",\"This is a test todo\",\"false\",\"?\",\"1\",\"assignment\"\n"
        + "\"1\",\"This is a test todo\",\"false\",\"?\",\"2\",\"next element\"", content);

  }
}