package controller.parser;

import commands.AbstractCmd;
import commands.CSVFile;
import commands.CmdFactory;
import controller.todohandler.ITodoHandler;
import controller.todohandler.TodoHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import utilities.ErrorLogger;
import utilities.Usage;

/**
 * Represents an Argument Parser
 */
public class Parser {

  private List<String> cmdList;
  private List<AbstractCmd> cmdObjList = new ArrayList<>();
  private CmdFactory cmdFactory = new CmdFactory();
  private ITodoHandler todoHandler;

  public List<String> getCmdList() {
    return this.cmdList;
  }

  public List<AbstractCmd> getCmdObjList() {
    return this.cmdObjList;
  }

  public CmdFactory getCmdFactory() {
    return this.cmdFactory;
  }

  public ITodoHandler getTodoHandler() {
    return this.todoHandler;
  }

  /**
   * Instantiates a new Args parser.
   */
  public Parser() {
  }

  /**
   * Validates the arguments
   *
   * @param args The arguments
   * @return The given arguments if they are valid
   */
  private boolean validateArgs(String[] args) {
    if (args.length <= 1) {
      ErrorLogger.add("No arguments provided");
      return false;
    }
    this.cmdList = Arrays.asList(args.clone());
    return true;
  }

  /**
   * Processes the arguments.
   *
   * @param args the arguments
   */
  public void processCmds(String[] args) {
    if (this.validateArgs(args)) {
      for (String userCmd : this.cmdList) {
        AbstractCmd cmd = this.cmdFactory.getCmd(userCmd);
        if (cmd != null) {
          if (cmd.findArgument(this.cmdList)) {
            this.cmdObjList.add(cmd);
          }
        }
      }
    }
    if (this.cmdFactory.verifyCommands() && ErrorLogger.isEmpty()) {
      this.sortList();
      for (AbstractCmd cmd : cmdObjList) {
        if (cmd instanceof CSVFile) {
          this.todoHandler = new TodoHandler(cmd.getArgument());
        }
      }
      todoHandler.takeAction(this.cmdObjList);
    } else {
      ErrorLogger.printLog();
      System.out.println(new Usage());
      return;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Parser parser = (Parser) o;
    return getCmdList().equals(parser.getCmdList()) && getCmdObjList()
        .equals(parser.getCmdObjList())
        && getCmdFactory().equals(parser.getCmdFactory()) && getTodoHandler()
        .equals(parser.getTodoHandler());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCmdList(), getCmdObjList(), getCmdFactory(), getTodoHandler());
  }

  private void sortList() {
    Collections.sort(cmdObjList, Comparator.comparing(AbstractCmd::getName));
  }
}
