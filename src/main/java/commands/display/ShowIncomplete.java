package commands.display;

import java.util.List;
import java.util.stream.Collectors;
import model.todo.Todo;
import utilities.Constants;

/**
 * The type Show incomplete.
 */
public class ShowIncomplete extends Display {

  /**
   * Instantiates a new Show incomplete.
   */
  public ShowIncomplete() {
    super(Constants.SHOW_INCOMPLETE_NAME, Constants.SHOW_INCOMPLETE);
  }

  /**
   * Applies a specific filter or sort on a list of todos
   *
   * @param todoList the todo list
   * @return the list with the display property applied
   */
  @Override
  public List<Todo> applyDisplayProperty(List<Todo> todoList) {
    List<Todo> newList = todoList.stream()
        .filter(todo -> todo.isCompleted() == false)
        .collect(Collectors.toList());
    return newList;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "ShowIncomplete{" +
        "displayStatus=" + displayStatus +
        '}';
  }
}
