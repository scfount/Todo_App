package commands.display;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import utilities.Constants;

public class DisplayFactoryTest {
  DisplayFactory displayFactory;
  private String showIncomplete;
  private String showCategory;
  private String dateSort;
  private String prioritySort;

  @Before
  public void setUp() throws Exception {
    displayFactory = new DisplayFactory();
    showIncomplete = Constants.SHOW_INCOMPLETE;
    showCategory = Constants.SHOW_CATEGORY;
    dateSort = Constants.DATE_SORT;
    prioritySort = Constants.PRIORITY_SORT;
  }

  @Test
  public void getIncompleteCmd() {
    assertEquals(new ShowIncomplete(), displayFactory.getCmd(showIncomplete));
  }
  @Test
  public void getCategoryCmd() {
    assertEquals(new ShowCategory(), displayFactory.getCmd(showCategory));
  }

  @Test
  public void getDateSortCmd() {
    assertEquals(new DateSort(), displayFactory.getCmd(dateSort));
    assertEquals(1, displayFactory.getSortCount());
  }

  @Test
  public void getPrioritySortCmd() {
    assertEquals(new PrioritySort(), displayFactory.getCmd(prioritySort));
    assertEquals(1, displayFactory.getSortCount());
  }

  @Test
  public void verifyCommands() {
    assertEquals(new DateSort(), displayFactory.getCmd(dateSort));
    assertEquals(new PrioritySort(), displayFactory.getCmd(prioritySort));
    assertEquals(2, displayFactory.getSortCount());
  }
}