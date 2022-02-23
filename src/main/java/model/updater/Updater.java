package model.updater;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.todo.Todo;
import utilities.FileIO;
import utilities.IdGenerator;

public class Updater implements IUpdater {
 public Updater(){
 }

  /**
   * Updates the csvfile with the new list of todos
   * @param listOfTodos the new list of todos.
   * @param csvFileName name of the csvfile which is to be overwritten
   */
  @Override
  public void updateCSV(List<Todo> listOfTodos, String csvFileName) {
    IdGenerator.resetIDGenerator();
    String toWrite = getTodoInCSVFormat(listOfTodos);
    FileIO.writeToFile(toWrite,csvFileName);
  }

  /** private helper method to iterate over
   * list of todos, call respective functions to fomat Todo
   * to a single string and build the final csv content to
   * be written as a single string.
   * @param listOfTodos List of todos to be iterated through
   * @return the final string with Todos formatted as string.
   */
  private String getTodoInCSVFormat(List<Todo> listOfTodos){
    List<String> csvRowStrings = new ArrayList<>();
    for(Todo todoObj: listOfTodos) {
      csvRowStrings.add(formatTodoData(todoObj));
    }
    String result = buildCSVData(csvRowStrings);
    return result;
  }

  /**
   * formats the todo to a csv row printable string
   * Checks if the field value is null,
   * if yes, replaces with "?" to be printed to the csv column.
   * @param todoObj The object to be formatted
   * @return formatted todo object as a string
   */
  private String formatTodoData(Todo todoObj) {
    String todoString;
    String ID;
    String text;
    String completed;
    LocalDate localDate;
    String date;
    String priority;
    String category;
      ID = "\"" + String.valueOf(todoObj.getID()) + "\"";
      text = "\""+ todoObj.getText() + "\"";
      completed = "\"" + String.valueOf(todoObj.isCompleted()) + "\"";
      localDate = todoObj.getDueDate();
      date = this.setDateValue(localDate);
      priority = "\"" + String.valueOf(todoObj.getPriority()) + "\"";
      category = todoObj.getCategory()==null ? "\"" + "?" + "\"" : "\"" + todoObj.getCategory() + "\"";
      todoString = String.join(",",Arrays.asList(ID, text, completed, date, priority, category));
      return todoString;
  }

  /**
   * iterated through each todo in string format and
   * builds the entire collection/list of strings
   * as a single string that will be written to csv file
   * @param listOfRowData list of todo as String
   * @return final string containing all string formatted
   * Todos as a single string.
   */
  private String buildCSVData(List<String> listOfRowData){
    StringBuilder sb = new StringBuilder();
    for(String todoItem: listOfRowData){
      sb.append(todoItem);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * private helper method to format the duedate
   * @param date The duedate object to be formatted
   * @return duedate as a string
   */
  private String setDateValue(LocalDate date){
   String dateInString;
    if(date == null){
      dateInString = "?";
    }else{
      DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("M/d/uuuu");
      dateInString = date.format(formatter);
    }
    return "\"" + dateInString + "\"";
  }

  /**
   * appends the new Todo to the last row of the csv file
   * @param newTodo The new Todo object created
   * @param csvFileName the name of the csvfile to which the new todo is to
   *                 be appended
   */
  @Override
  public void appendTodo(Todo newTodo, String csvFileName) {
    String toDoToAppend = this.formatTodoData(newTodo);
    FileIO.appendToFile(toDoToAppend, csvFileName);
  }


}
