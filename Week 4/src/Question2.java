
public class Question2 implements ListADT{
  public static String recursiveBeta(int n) {
    if (n==85) return "";
    return "" + recursiveBeta(n-1) + (char)n ;
  }
  
  public static void main(String[] args) {
    //System.out.println(recursiveBeta(90));
    ListADT<String> list = new ListADT<String>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.remove(2);
    list.remove(list.indexOf("A"));
    list.remove(0);

    System.out.println(list.contains("B"));
  }
}
