package commands.add;

import java.util.List;
import model.todo.Todo.Builder;
import utilities.Constants;

/**
 * The type Completed.
 */
public class Completed extends Add {

  protected boolean complete = true;

  /**
   * Instantiates a new Completed.
   */
  public Completed() {
    super(Constants.COMPLETED_NAME, Constants.COMPLETED_CMD);
  }

  /**
   * Indicates that the todo to add is already complete
   *
   * @return the boolean
   */
  public boolean isComplete() {
    return this.complete;
  }


  /**
   * Find the argument for commands that relate to adding a todo
   *
   * @return true if found, false otherwise
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  @Override
  protected boolean findAddArgument(List<String> cmdList) throws IndexOutOfBoundsException {
    return true;
  }

  /**
   * Uses Builder pattern to build a todo with the required argument
   *
   * @param todoBuilder the todo to build
   * @return the builder with the required argument appended
   */
  @Override
  protected Builder appendBuilder(Builder todoBuilder) {
    todoBuilder.addCompleted(this.complete);
    return todoBuilder;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Completed{" +
        "complete=" + complete +
        '}';
  }
}
