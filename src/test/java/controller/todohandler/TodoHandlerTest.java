package controller.todohandler;

import static org.junit.Assert.*;

import commands.CmdFactory;
import commands.add.Add;
import controller.parser.Parser;
import java.util.Arrays;
import java.util.List;
import model.todo.Todo;
import org.junit.Before;
import org.junit.Test;
import utilities.ErrorLogger;
import utilities.FileIO;
import static org.junit.Assert.assertArrayEquals;

public class TodoHandlerTest {
  String[] noCategory;
  Parser parser;
  Add addCmd;
  List<String> cmdList;

  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    noCategory = new String[]{"--add-todo", "--priority", "1",
        "--todo-text", "Comb hair", "--csv-file",
        "todos.csv", "--due", "12/15/2021"};
    parser = new Parser();
    parser.processCmds(noCategory);
    CmdFactory parserCmdFactory = parser.getCmdFactory();
    addCmd = (Add) parserCmdFactory.getCmd("--add-todo");
    cmdList = Arrays.asList(noCategory);
  }

  @Test
  public void noPriorityTest() {
    String[] noPriority = new String[]{"--add-todo", "--todo-text",
        "Fix shed door", "--csv-file", "todos.csv"};
    Parser noPriorityParser = new Parser();
    noPriorityParser.processCmds(noPriority);
  }

  @Test
  public void completeTodo() {
    addCmd.findArgument(cmdList);
    Todo newTodo = addCmd.getNewTodo();
    assertFalse(newTodo.isCompleted());

    int id = newTodo.getID();
    Parser testCompleted = new Parser();
    System.out.println(id);
    String[] completedInput = new String[]{"--complete-todo", String.valueOf(id), "--csv-file",
        "todos.csv"};
    parser.processCmds(completedInput);
  }

  @Test
  public void printTodos() {
    noCategory = new String[]{"--csv-file",
        "todos.csv", "--display", "--show-category", "error"};
    parser = new Parser();
    parser.processCmds(noCategory);
  }

  @Test
  public void getTodoListTest() {
    ITodoHandler parserHandler = parser.getTodoHandler();
    TodoHandler parserHandlerConv = (TodoHandler) parserHandler;
    String csvData = FileIO.readFile(parserHandlerConv.getCsvPath());
    List<Todo> expParserTodoList = parserHandlerConv.makeListOfTodos(csvData);
    List<Todo> parserTodoList = parserHandlerConv.getTodoList();
  }
}