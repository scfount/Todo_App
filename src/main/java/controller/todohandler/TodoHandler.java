package controller.todohandler;

import commands.AbstractCmd;
import commands.Complete;
import commands.add.Add;
import commands.display.Display;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import model.todo.Todo;
import model.todo.Todo.Builder;
import model.updater.IUpdater;
import model.updater.Updater;
import utilities.ErrorLogger;
import utilities.FileIO;
import utilities.IdGenerator;

/**
 * Represents a TodoHandler which is the Controller of this System
 */
public class TodoHandler implements ITodoHandler {
  private List<Todo> todoList;
  private final String csvPath;

  public List<Todo> getTodoList() {
    return this.todoList;
  }

  public String getCsvPath() {
    return csvPath;
  }

  private final IUpdater updater;

  /**
   * Instantiates a new To-do handler.
   *
   * @param csvPath the csv path for output
   */
  public TodoHandler(String csvPath) {
    this.csvPath = csvPath;
    this.updater = new Updater();
  }


  /**
   * Reads a csv to a list of todos.
   */
  private void readCSVToListOfTodos(){
    String csvPath = this.csvPath;
    String csvData = FileIO.readFile(csvPath);
    this.todoList = this.makeListOfTodos(csvData);
  }

  /**
   * Create a list of todos list.
   *
   * @param csvBlobData the csv blob data
   * @return the list
   */
  public List<Todo> makeListOfTodos(String csvBlobData){
    List<Todo> listOfTodos;
    IdGenerator.resetIDGenerator();
    String[] resultArr = csvBlobData.split("\r?\n");
    List<List<String>> todosList = new ArrayList<>();
    for(int i = 1; i < resultArr.length; i++){
      resultArr[i] = resultArr[i].replaceAll("^\"|\"$", "");
      todosList.add(Arrays.asList(resultArr[i].split("\",\"")));
    }
    listOfTodos = createListOfTodos(todosList);
    return listOfTodos;
  }

  /**
   * Constructs a To-do
   *
   * @param todoData A List containing the data for a To-do
   * @return A To-do with the given specifications.
   */
  private Todo makeTodoFromData(List<String> todoData){
    String text = todoData.get(1);
    boolean completed = Boolean.parseBoolean(todoData.get(2));
    String formatterTemp = todoData.get(3);
    String priorityTemp = todoData.get(4);
    String category = todoData.get(5);
    Builder todoBuilder = new Builder(text);
    if (!formatterTemp.equals("?")) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu");
      LocalDate dueDate = LocalDate.parse(formatterTemp, formatter);
      todoBuilder.addDueDate(dueDate);
    }
    if (!priorityTemp.equals("?")) {
      int priority = Integer.parseInt(priorityTemp);
      todoBuilder.addPriority(priority);
    }
    if(completed){
      todoBuilder.addCompleted(true);
    }
    if(!category.equals("?")){
      todoBuilder.addCategory(category);
    }
    return todoBuilder.build();
  }

  /**
   * Creates a list of To-dos
   *
   * @param todosList A List containing lists of Strings with data for To-dos
   * @return A List of To-do objects
   */
  private List<Todo> createListOfTodos(List<List<String>> todosList) {
    List<Todo> listOfTodos = new ArrayList<>();
    for (List<String> data : todosList) {
      listOfTodos.add(this.makeTodoFromData(data));
    }
    return listOfTodos;
  }

  /**
   * Adds a To-do to this system.
   *
   * @param todo The todo to add
   */
  private void addTodo(Todo todo) {
    Todo maxID = this.todoList.stream()
        .max(Comparator.comparing(Todo::getID))
        .orElseThrow(NoSuchElementException::new);
    todo = todo.setID(maxID.getID() + 1);
    this.todoList.add(todo);
  }

  /**
   * Sets the To-do of the provided ID to complete
   *
   * @param id the ID of the To-do to complete
   */
  private void completeTodo(int id) {
    for (Todo todo : this.todoList) {
      if (todo.getID() == id) {
        todo.setCompleted(true);
        break;
      }
    }
    ErrorLogger.add("ID not found, please make sure the ID exists");
    ErrorLogger.printLog();
  }

  /**
   * Prints a list of To-dos
   *
   * @param todoList The To-do list to print
   */
  private void printTodos(List<Todo> todoList) {
    printBorder();
    String format = "%1$-5s%2$-40s%3$-10s%4$-12s%5$-10s%6$-10s\n";
    System.out.format(format, "ID", "Text", "Completed", "Due Date",
        "Priority", "Category");
    if (todoList.isEmpty()) {
      ErrorLogger.add("No results found, adjust your display arguments");
      ErrorLogger.printLog();
    } else {
      for (Todo currTodo : todoList) {
        System.out.format(format, currTodo.getID(), currTodo.getText(),
            currTodo.isCompleted(), currTodo.getDueDate(),
            currTodo.getPriority(), currTodo.getCategory());
    }
    }
    printBorder();
  }

  /**
   * Prints a line of *
   */
  private void printBorder() {
    int lineSize = 85;
    for (int i = 0; i < lineSize; i++) {
      System.out.print("*");
    }
    System.out.println();
  }

  /**
   * Processes the Display command
   *
   * @param display The Display object
   */
  private void display(Display display) {
    List<Todo> todoListCopy = this.todoList;
    for (Display cmd : display.getDisplayProperties()) {
      todoListCopy = cmd.applyDisplayProperty(todoListCopy);
    }
    this.printTodos(todoListCopy);
  }

  /**
   * Sends each command for execution based on its type
   *
   * @param cmd The given command to execute
   */
  private void TodoFactory (AbstractCmd cmd) {
    if (cmd instanceof Add) {
      Add newAdd = (Add) cmd; // Steven added
      addTodo(newAdd.getNewTodo());
    }
    else if (cmd instanceof Complete) {
      Complete newComplete = (Complete) cmd; // Steven added
      completeTodo(newComplete.getId());
    }
    else if (cmd instanceof Display) { // display
      Display newDisplay = (Display) cmd;
      display(newDisplay);
    }
  }

  /**
   * Executes each command in the command list.
   *
   * @param commandList The list of Commands.
   */
  @Override
  public void takeAction(List<AbstractCmd> commandList) {
    readCSVToListOfTodos();
    for (AbstractCmd command : commandList) {
      this.TodoFactory(command);
    }
    this.updater.updateCSV(this.todoList, this.csvPath);
  }

}