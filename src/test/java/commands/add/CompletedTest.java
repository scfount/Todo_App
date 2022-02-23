package commands.add;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import controller.parser.Parser;
import model.todo.Todo;
import utilities.ErrorLogger;

public class CompletedTest {
  Completed completedCmd;
  Todo completeTodo;
  List<String> cmdList;
  Add addcmd;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    String[] inputCompleted = new String[]{"--add-todo", "--priority", "1",
        "--todo-text", "This is the text description", "--csv-file",
        "todos.csv", "--display", "--category", "test", "--completed"};
    Parser parser = new Parser();
    parser.processCmds(inputCompleted);
    addcmd = (Add) parser.getCmdObjList().get(0);
    cmdList = Arrays.asList(inputCompleted);
    completedCmd = new Completed();
    completeTodo = addcmd.getNewTodo();
  }

  @Test
  public void isComplete() {
    //ass
    assertTrue(completedCmd.isComplete());
  }

  @Test
  public void findAddArgument() {
    assertTrue(addcmd.findAddArgument(cmdList));
  }

  @Test
  public void appendBuilder() {
    assertTrue(addcmd.getNewTodo().isCompleted());
  }

  @Test
  public void testToString() {
    Completed completed = new Completed();
    assertEquals("Completed{complete=true}", completed.toString());
  }
}