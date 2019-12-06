package adventofcode.four;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SecureContainer {

   public static boolean checkForDoubles( List<Integer> asIntegerList ) {

      Map<Integer, Integer> doubles = new HashMap<>();

      for ( int i = 1; i < asIntegerList.size(); i++ ) {
         final Integer currentInt = asIntegerList.get(i);
         final Integer previousInt = asIntegerList.get(i - 1);
         if ( currentInt == previousInt ) {
            doubles.merge(currentInt, 1, Integer::sum);
         }
      }
      if ( doubles.isEmpty() ) {
         return false;
      }

      return doubles.entrySet().stream().anyMatch(e -> e.getValue() == 1);

      //      for ( Map.Entry<Integer, Integer> entry : doubles.entrySet() ) {
      //         if ( entry.getValue() > 1 ) {
      //            return doubles.size() > 1 ? true : false;
      //         }
      //      }
      //      return true;
   }

   public static boolean checkForNeverDecreasingNumbers( List<Integer> asIntegerList ) {
      for ( int i = 1; i < asIntegerList.size(); i++ ) {
         final Integer currentInt = asIntegerList.get(i);
         final Integer previousInt = asIntegerList.get(i - 1);
         if ( currentInt.compareTo(previousInt) < 0 ) {
            return false;
         }
      }
      return true;
   }

   public static List<Integer> convertToIntegerList( int i ) {
      List<Integer> asIntegerList = new ArrayList<>();
      final String asString = String.valueOf(i);
      for ( int c = 0; c < asString.length(); c++ ) {
         final int currentInt = Integer.parseInt(String.valueOf(asString.charAt(c)));
         asIntegerList.add(currentInt);
      }
      return asIntegerList;
   }

   public static void main( String[] args ) throws IOException {
      Integer min = 272091;
      Integer max = 815432;

      List<Integer> valids = new ArrayList<>();

      for ( int i = min; i <= max; i++ ) {
         final List<Integer> asIntegerList = convertToIntegerList(i);
         if ( !checkForNeverDecreasingNumbers(asIntegerList) ) {
            continue;
         }
         if ( !checkForDoubles(asIntegerList) ) {
            continue;
         }
         //         System.out.println("Valid doubles found: " + asIntegerList);
         valids.add(i);
      }
      System.out.println("Total relevant numbers: " + valids.size());
      System.out.println("HINT: 609 is correct");
   }

}
