package adventofcode.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OpCode {

   private static final Logger _log = Logger.getLogger(OpCode.class.getName());

   public static void calculateOutput( LinkedList<Integer> integers ) {
      if ( integers == null || integers.isEmpty() ) {
         return;
      }
      int currentIndex = 0;
      int opcode = integers.get(currentIndex);
      while ( opcode != 99 ) {
         int firstParameterPosition = integers.get(currentIndex + 1);
         int secondParameterPosition = integers.get(currentIndex + 2);
         int targetOutputPosition = integers.get(currentIndex + 3);
         Integer firstInput = integers.get(firstParameterPosition);
         Integer secondInput = integers.get(secondParameterPosition);
         int result = operate(opcode, firstInput, secondInput);
         _log.log(Level.INFO, "Operation: " + opcode + ", " + firstInput + ", " + secondInput + ", " + targetOutputPosition);
         integers.set(targetOutputPosition, result);
         currentIndex += 4;
         opcode = integers.get(currentIndex);
      }
   }

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

   private static LinkedList<Integer> getIntegers() {
      LinkedList<Integer> integers = new LinkedList<>();
      try (Scanner scanner = new Scanner(new File("src/main/resources/adventofcode.two/input.txt"))) {
         scanner.useDelimiter("\\,");
         while ( scanner.hasNextInt() ) {
            integers.add(scanner.nextInt());
         }
      }
      catch ( FileNotFoundException e ) {
         _log.log(Level.SEVERE, e.getLocalizedMessage());
      }
      return integers;
   }

   private static int operate( int operator, Integer firstInput, Integer secondInput ) {
      switch ( operator ) {
      case 1: // addition
         return firstInput + secondInput;
      case 2: // multiply
         return firstInput * secondInput;
      default:
         throw new IllegalStateException("Unknown operator: " + operator);
      }
   }

}
