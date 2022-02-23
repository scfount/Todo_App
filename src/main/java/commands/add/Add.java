package commands.add;

import commands.AbstractCmd;
import java.util.List;
import model.todo.Todo;
import model.todo.Todo.Builder;
import utilities.ErrorLogger;

/**
 * The type Add.
 */
public class Add extends AbstractCmd {

  protected static AddFactory addFactory = new AddFactory();
  protected Todo newTodo;
  protected Builder buildTodo;


  /**
   * Instantiates a new Add.
   *
   * @param name    the name
   * @param command the command
   */
  public Add(String name, String command) {
    super(name, command);
  }

  /**
   * Gets new todo.
   *
   * @return the new todo
   */
  public Todo getNewTodo() {
    return this.newTodo;
  }

  /**
   * Find argument for specified command
   *
   * @param cmdList the cmd list
   * @return True if the argument for the command is found, otherwise false
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  @Override
  public boolean findArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    boolean found = super.findArgument(cmdList);
    if (found) {
      this.buildTodo = appendBuilder(this.buildTodo);
      for (String userCmd : cmdList) {
        Add cmd = addFactory.getCmd(userCmd);
        if (cmd != null) {
          if (cmd.findAddArgument(cmdList)) {
            cmd.appendBuilder(this.buildTodo);
          }
        }
      }
      this.newTodo = this.buildTodo.build();
    } else {
      ErrorLogger.add("Please add text description for Todo");
    }
    return found;
  }

  /**
   * Find the argument for commands that relate to adding a todo
   *
   * @return true if found, false otherwise
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  protected boolean findAddArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    if (cmdList.contains(this.getCommand())) {
      try {
        int argIndex = cmdList.indexOf(this.getCommand()) + 1;
        this.argument = cmdList.get(argIndex);
        if (this.isValidArgument()) {
          return true;
        } else {
          ErrorLogger.add(this.getName() + " argument not provided");
        }
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        ErrorLogger.add(this.getName() + " argument not provided");
      }
    }
    return false;
  }

  /**
   * Uses Builder pattern to build a todo with the required argument
   *
   * @param todoBuilder the todo to build
   * @return the builder with the required argument appended
   */
  protected Builder appendBuilder(Builder todoBuilder) {
    todoBuilder = new Builder(this.getArgument());
    return todoBuilder;
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    if (!super.equals(o)) {
//      return false;
//    }
//    Add add = (Add) o;
//    //2 added todos would never be same because of different ID's
//    if (buildTodo == null) {
//      if (add.buildTodo == null) {
//        return true;
//      } else {
//        return false;
//      }
//    } else {
//      return buildTodo.equals(add.buildTodo);
//    }
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(super.hashCode(), buildTodo);
//  }

  /**
   * @return String representation of an Add object
   */
  @Override
  public String toString() {
    return "Add{" +
        "newTodo=" + newTodo +
        '}';
  }
}
