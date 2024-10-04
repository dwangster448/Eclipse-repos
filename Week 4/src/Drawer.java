

public class Drawer <T> {
  private T stuff;
  public Drawer(T value) { stuff = value; }
  public T get() { return stuff; }
}

  class UseDesk {
  public static void main(String[] args) {
    Drawer<String> c = new Drawer<String>("string");
    String s = c.get();
  }
}

