package parser;

import static org.junit.Assert.*;
import controller.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import utilities.ErrorLogger;

public class ParserTest {
  Parser parser;
  String[] input;

  Parser parser2;
  String[] indexOutOfBoundsTestInput;
  Parser parser3;
  String[] invalidArgumentTestInput;
  @Before
  public void setUp() throws Exception {
    ErrorLogger.create();
    input = new String[]{"--add-todo", "--priority", "1",
        "--todo-text", "This is the text description", "--csv-file",
        "todos.csv", "--display", "--sort-by-priority", "--due", "12/15/2021"};
    parser = new Parser();

    parser2 = new Parser();
    indexOutOfBoundsTestInput = new String[]{"--add-todo", "--priority", "1",
        "--csv-file",
        "todos.csv", "--display", "--sort-by-priority", "--due", "12/15/2021", "--todo-text"};

    parser3 = new Parser();
    invalidArgumentTestInput = new String[]{"--add-todo", "--priority", "1",
        "--todo-text", "--csv-file",
        "todos.csv", "--display","todo text is here", "--sort-by-priority", "--due", "12/15/2021"};
  }

  @Test
  public void processCmds() {
    parser.processCmds(input);
    List<String> list = new ArrayList<>();
    list.add("--add-todo");
    list.add("--priority");
    list.add("1");
    list.add("--todo-text");
    list.add("This is the text description");
    list.add("--csv-file");
    list.add("todos.csv");
    list.add("--display");
    list.add("--sort-by-priority");
    list.add("--due");
    list.add("12/15/2021");
    assertEquals(list, parser.getCmdList());
  }

  @Test
  public void testNoTextArgs(){
    Parser parserNoArgs = new Parser();
    String[] badArg = new String[]{
        ""
    };
    parserNoArgs.processCmds(badArg);
  }

  @Test
  public void testTodoTextWrongOrder(){
    parser2.processCmds(indexOutOfBoundsTestInput);
  }
}