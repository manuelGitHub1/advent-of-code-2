package adventofcode.two;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;


class OpCodeTest {

   @Test
   void calculateTest1() {
      test(new Integer[] { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 }, new Integer[] { 3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50 });
   }

   @Test
   void calculateTest2() {
      test(new Integer[] { 1, 0, 0, 0, 99 }, new Integer[] { 2, 0, 0, 0, 99 });
   }

   @Test
   void calculateTest3() {
      test(new Integer[] { 1, 1, 1, 4, 99, 5, 6, 0, 99 }, new Integer[] { 30, 1, 1, 4, 2, 5, 6, 0, 99 });
   }

   private void test( Integer[] input, Integer[] expectedOutput ) {
      LinkedList<Integer> integers = new LinkedList<>();
      integers.addAll(Arrays.asList(input));
      IntComputer.calculateOutput(integers);
      assert integers.containsAll(Arrays.asList(expectedOutput));
   }

}