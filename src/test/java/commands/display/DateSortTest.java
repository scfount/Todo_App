package commands.display;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.todo.Todo;

public class DateSortTest {
  Display dateSort;
  List<Todo> todoList;
  List<Todo> dateSortedList;
  Todo todo1;
  LocalDate date1;
  Todo todo2;
  LocalDate date2;
  Todo todo3;
  LocalDate date3;
  Todo todo4;
  LocalDate date4;

  @Before
  public void setUp() throws Exception {
    dateSort = new DateSort();
    date1 = LocalDate.of(2021, 4, 22);
    todo1 = new Todo.Builder("Todo1")
        .addDueDate(date1).build();
    date2 = LocalDate.of(2021, 4, 23);
    todo2 = new Todo.Builder("Todo2")
        .addDueDate(date2).build();
    date3  = null;
    todo3 = new Todo.Builder("Todo3")
        .addDueDate(date3).build();
    date4 = LocalDate.of(2020, 4, 23);
    todo4 = new Todo.Builder("Todo4")
        .addDueDate(date4).build();
    todoList = Arrays.asList(todo3, todo2, todo1, todo4);

  }

  @Test
  public void applyDisplayProperty() {
    dateSortedList = Arrays.asList(todo4, todo1, todo2, todo3);
    todoList = dateSort.applyDisplayProperty(todoList);
    assertEquals(dateSortedList, todoList);
  }
}