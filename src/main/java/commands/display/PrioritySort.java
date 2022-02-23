package commands.display;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.todo.Todo;
import utilities.Constants;

/**
 * The type Priority sort.
 */
public class PrioritySort extends Display {

  /**
   * Instantiates a new Priority sort.
   */
  public PrioritySort() {
    super(Constants.PRIORITY_SORT_NAME, Constants.PRIORITY_SORT);
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
        .sorted(Comparator.comparing(Todo :: getPriority))
        .collect(Collectors.toList());
    return newList;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "PrioritySort{" +
        "displayStatus=" + displayStatus +
        '}';
  }
}
