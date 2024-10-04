import java.util.ArrayList;

public class Permutations {
  public static ArrayList<String> permute(String s) {
    if (s.length() == 0)
      return new ArrayList<String>();
    return permuteHelper("", s);
  }

  /**
   * Recursive helper method to accomplish our permutation
   * 
   * @param fixed     the letters we DO NOT want to change
   * @param toPermute the letters we have left to permute
   * @return an arraylist containing all permutations beginning with fixed
   */
  //O(n!)
  private static ArrayList<String> permuteHelper(String fixed, String toPermute) {
    ArrayList<String> result = new ArrayList<String>();
    // base case: one letter left 
    if(toPermute.length()==1) 
     result.add(fixed + toPermute);
    // the only permutation is fixed + toPermute, put that in an arraylist return it
    else {  // recursive case: toPermute.length() > 1
      for(int i=0; i < toPermute.length(); i++) { // loop through the characters left in toPermute
        
        // add the character to fixed and remove it from toPermute
        String newFixed = fixed + toPermute.charAt(i);
        String newPermute = toPermute.substring(0,i) + toPermute.substring(i+1);
        
        // call the helper method with these new strings
        // add all of the resulting permutations to our results list
        result.addAll(permuteHelper(newFixed, newPermute));
      }
    }
    return result;
  }
 
  public static void main(String[] args) {
    System.out.println(permute("ABC"));
  }
}


