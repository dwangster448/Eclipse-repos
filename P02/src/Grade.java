
public class Grade implements Comparable<Grade>{
  private char letterGrade;
  private double score;

  public Grade(double score) {
    public String toString() {}
    public boolean equals(Object o){}
    public boolean equals(Grade o){}
    public int compareTo(Grade o){}
  }

  @Override
  public int compareTo(Grade o) {
    return 0;
  }
}

class Main {
  public static void main(String[] args){
    //Craft obj = new Craft();

//    Decoupage obj = new Decoupage();
//
//    PaperCraft obj = new PaperCraft();
//
//    Whittling obj = new Whittling();
//
    WoodCraft obj = new WoodCraft();

  }

}
