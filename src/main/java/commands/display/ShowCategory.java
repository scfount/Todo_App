package commands.display;


import java.util.ArrayList;
import java.util.List;
import model.todo.Todo;
import utilities.Constants;
import utilities.ErrorLogger;

/**
 * The type Show category.
 */
public class ShowCategory extends Display {

  /**
   * Instantiates a new Show category.
   */
  public ShowCategory() {
    super(Constants.SHOW_CATEGORY_NAME, Constants.SHOW_CATEGORY);
  }

  /**
   * Find the argument for command that relates to displaying todos
   *
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  @Override
  public void findAddArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    int argIndex = cmdList.indexOf(this.command) + 1;
    this.argument = cmdList.get(argIndex);
    if (this.argument.startsWith("--")) {
      ErrorLogger.add(this.name + " argument not provided");
    }
  }

  /**
   * Applies a specific filter or sort on a list of todos
   *
   * @param todoList the todo list
   * @return the list with the display property applied
   */
  @Override
  public List<Todo> applyDisplayProperty(List<Todo> todoList) {
    List<Todo> newList = new ArrayList<>();
    for (Todo todo : todoList) {
      if (todo.getCategory() != null && todo.getCategory()
          .toLowerCase().equals(this.argument)) {
        newList.add(todo);
      }
    }
    return newList;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "ShowCategory{" +
        "displayStatus=" + displayStatus +
        '}';
  }
}
