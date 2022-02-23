package commands.display;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.todo.Todo;
import utilities.Constants;

/**
 * The type Date sort.
 */
public class DateSort extends Display {

  /**
   * Instantiates a new Date sort.
   */
  public DateSort() {
    super(Constants.DATE_SORT_NAME, Constants.DATE_SORT);
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
    List<Todo> nullDatesList = new ArrayList<>();
    for (Todo todo : todoList) {
      if (todo.getDueDate() != null) {
        newList.add(todo);
      } else {
        nullDatesList.add(todo);
      }
    }
    newList.sort(Comparator.comparing(Todo::getDueDate));
    newList.addAll(nullDatesList);

    return newList;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "DateSort{" +
        "displayStatus=" + displayStatus +
        '}';
  }
}
