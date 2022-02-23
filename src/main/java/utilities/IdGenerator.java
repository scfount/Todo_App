package utilities;

public class IdGenerator {
// static class
  private static Integer idMax = 1;

  public IdGenerator() {
  }

  public static int generateID(){
    return idMax++;
  }

  public static Integer getIdMax() {
    return idMax;
  }

  public static void resetIDGenerator(){
    IdGenerator.idMax = 1;
  }

  public static void setIdMax(Integer idMax) {
    IdGenerator.idMax = idMax;
  }
}
