////import java.util.ArrayList;
////
////public class Pokemon {
////  public String toString() {
////    return "Pokemon!";
////  }
////}
////
//// class Bulbasaur extends Pokemon {
////  public String toString() {
////    return "Bulbasaur!";
////  }
////}
////
//// class Squirtle extends Pokemon {
////  public String toString() {
////    return "Squirtle!";
////  }
////}
////
//// class Driver {
////  public static void main(String[] args){
////    ArrayList<Pokemon> pokemons = new ArrayList<>();
////    pokemons.add(new Pokemon());
////    pokemons.add(new Bulbasaur());
////    pokemons.add(new Squirtle());
////    for(int i=0; i < pokemons.size(); i++){
////      System.out.println(pokemons.get(i));
////    }
////  }
////}
//
//class Pokemon implements Comparable<Pokemon> {
//  Pokemon p = new Wooper();      // LINE A
//  Object obj = p;                // LINE B
//  WaterType h2o = (WaterType) p; // LINE C
//  Squirtle sq = (Squirtle) h2o;  // LINE D
//  Wooper woop = (Wooper) h2o;    // LINE E
//
//  @Override
//  public int compareTo(Pokemon o) {
//    return 0;
//  }
//}
//class WaterType extends Pokemon {}
//class Squirtle extends WaterType {}
//class Wooper extends WaterType {
//
//}
//
//
//
//
