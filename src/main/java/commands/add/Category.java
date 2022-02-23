package commands.add;

import model.todo.Todo.Builder;
import utilities.Constants;

/**
 * The type Category.
 */
public class Category extends Add {

  /**
   * Instantiates a new Category.
   */
  public Category() {
    super(Constants.CATEGORY_NAME, Constants.CATEGORY_CMD);
  }

  /**
   * Uses Builder pattern to build a todo with the required argument
   *
   * @param todoBuilder the todo to build
   * @return the builder with the required argument appended
   */
  @Override
  protected Builder appendBuilder(Builder todoBuilder) {
    todoBuilder.addCategory(this.argument);
    return todoBuilder;
  }

  /**
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Category{" +
        "argument='" + argument + '\'' +
        '}';
  }
}
