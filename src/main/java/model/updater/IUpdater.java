package model.updater;

import java.util.List;
import model.todo.Todo;

/**
 * The interface Updater.
 */
public interface IUpdater {

  /**
   * appens the new Todo to the last row of the csv file
   *
   * @param newTodo  The new Todo object created
   * @param filename the name of the csvfile to which the new todo is to                 be
   *                 appended
   */
  void appendTodo(Todo newTodo, String filename);

  /**
   * Updates the csvfile with the new list of todos
   *
   * @param listOfTodos the new list of todos.
   * @param csvFileName name of the csvfile which is to be overwritten
   */
  void updateCSV(List<Todo> listOfTodos, String csvFileName);
}
