package commands.add;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddFactoryTest {
  AddFactory addCmdFactory;
  @Before
  public void setUp() throws Exception {
    addCmdFactory = new AddFactory();
  }

  @Test
  public void getCmd() {
    assertNull(addCmdFactory.getCmd("bad text"));
    assertNull(addCmdFactory.getCmd("null"));
    assertNull(addCmdFactory.getCmd(" "));
    assertNull(addCmdFactory.getCmd(""));
    assertTrue(addCmdFactory.getCmd("--todo-text") instanceof Text);
    assertTrue(addCmdFactory.getCmd("--completed") instanceof Completed);
    assertTrue(addCmdFactory.getCmd("--due") instanceof DueDate);
    assertTrue(addCmdFactory.getCmd("--priority") instanceof Priority);
    assertTrue(addCmdFactory.getCmd("--category") instanceof Category);
  }

  @Test
  public void verifyCommands() {
    addCmdFactory.verifyCommands();
  }
}