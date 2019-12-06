package adventofcode.two;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OpCode extends IntComputer {

   private static final Logger _log = Logger.getLogger(OpCode.class.getName());

   public static void main( String[] args ) {
      LinkedList<Integer> integers = getIntegers();

      _log.log(Level.INFO, "found " + integers.size() + " elements");
      integers.set(1, 12);
      integers.set(2, 2);
      calculateOutput(integers);
      System.out.println("found 99. Position 0 is: " + integers.get(0));
      System.out.println("found 99");

      int noun = -1;
      int verb = 0;
      while ( integers.get(0) != 19690720 ) {
         if ( noun > 99 ) {
            noun = 0;
            verb++;
         }
         if ( verb > 99 ) {
            _log.log(Level.SEVERE, "Result not found");
            break;
         }
         noun++;

         integers = getIntegers();
         integers.set(1, noun);
         integers.set(2, verb);
         calculateOutput(integers);
      }
      System.out.println("noun: " + noun + ", verb: " + verb);
      System.out.println(100 * noun + verb);
   }

}
