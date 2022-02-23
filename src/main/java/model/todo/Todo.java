package model.todo;

import java.time.LocalDate;
import java.util.Objects;
import utilities.IdGenerator;

/**
 * The type Todo.
 */
public class Todo {
  private int ID;
  private String text;
  private boolean completed;
  private LocalDate dueDate;
  private int priority;
  private String category;

  private Todo(Builder builder) {
    this.ID = this.assignID();
    this.text = builder.text;
    this.completed = builder.completed;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  private int assignID(){
    return IdGenerator.generateID();
  }

  public int getID() {
    return this.ID;
  }

  public String getText() {
    return this.text;
  }

  public boolean isCompleted() {
    return this.completed;
  }

  public LocalDate getDueDate() {
    return this.dueDate;
  }

  public int getPriority() {
    return this.priority;
  }

  public String getCategory() {
    return this.category;
  }

  // Setters
  public Todo setID(int ID) {
    Todo newTodo = this;
    newTodo.ID = ID;
    return newTodo;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return "Todo{" +
        "ID=" + ID +
        ", text='" + text + '\'' +
        ", completed=" + completed +
        ", dueDate=" + dueDate +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        '}';
  }

  /**
   * The nested type Builder.
   */
  public static class Builder {
    private String text; // required
    private boolean completed; // optional
    private LocalDate dueDate; // optional
    private int priority = 3; // optional
    private String category; // optional
    
    public Builder(String text) {
      this.text = text;
      this.completed = false;
      this.dueDate = null;
    }

    /**
     * Add completed builder.
     *
     * @param completed the completed
     * @return the builder
     */
    public Builder addCompleted(boolean completed) {
      this.completed = completed;
      return this;
    }

    /**
     * Add due date to builder.
     *
     * @param dueDate the due date
     * @return the builder
     */
    public Builder addDueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    /**
     * Add priority to builder.
     *
     * @param priority the priority
     * @return the builder
     */
    public Builder addPriority(int priority) {
      this.priority = priority;
      return this;
    }

    /**
     * Add category to builder.
     *
     * @param category the category
     * @return the builder
     */
    public Builder addCategory(String category) {
      this.category = category;
      return this;
    }

    /**
     * Build todo.
     *
     * @return the todo
     */
    public Todo build() {
      return new Todo(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Builder builder = (Builder) o;
      return completed == builder.completed && priority == builder.priority && text
          .equals(builder.text)
          && dueDate.equals(builder.dueDate) && category.equals(builder.category);
    }

    @Override
    public int hashCode() {
      return Objects.hash(text, completed, dueDate, priority, category);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Todo todo = (Todo) o;
    return isCompleted() == todo.isCompleted() && getPriority() == todo.getPriority() && getText()
        .equals(todo.getText()) && getDueDate().equals(todo.getDueDate()) && getCategory()
        .equals(todo.getCategory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(isCompleted(), getDueDate(), getPriority(), getCategory());
  }
}
