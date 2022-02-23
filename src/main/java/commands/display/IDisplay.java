package commands.display;

import java.util.List;
import model.todo.Todo;

/**
 * The interface Display.
 */
public interface IDisplay {

  /**
   * Applies a specific filter or sort on a list of todos
   *
   * @param todoList the todo list
   * @return the list with the display property applied
   */
  List<Todo> applyDisplayProperty(List<Todo> todoList);

}
