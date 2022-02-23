package commands.add;

import model.todo.Todo.Builder;
import utilities.Constants;

/**
 * The type Text.
 */
public class Text extends Add {

  /**
   * Instantiates a new Text.
   */
  public Text() {
    super(Constants.TEXT_NAME, Constants.TEXT_CMD);
  }

  /**
   * Uses Builder pattern to build a todo with the required argument
   *
   * @param todoBuilder the todo to build
   * @return the builder with the required argument appended
   */
  @Override
  protected Builder appendBuilder(Builder todoBuilder) {
    return todoBuilder;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Text{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
