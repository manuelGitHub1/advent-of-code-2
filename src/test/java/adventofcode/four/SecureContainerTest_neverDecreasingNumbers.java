package adventofcode.four;

import static adventofcode.four.SecureContainer.convertToIntegerList;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.jupiter.api.Test;


class SecureContainerTest_neverDecreasingNumbers {

   @Test
   public void testNeverDecreasingNumbers1() {
      neverDecrease(333).isTrue();
   }

   @Test
   public void testNeverDecreasingNumbers2() {
      neverDecrease(33322).isFalse();
   }

   @Test
   public void testNeverDecreasingNumbers3() {
      neverDecrease(123456789).isTrue();
   }

   @Test
   public void testNeverDecreasingNumbers4() {
      neverDecrease(1255677889).isTrue();
   }

   private AbstractBooleanAssert<?> neverDecrease( int i ) {
      return assertThat(SecureContainer.checkForNeverDecreasingNumbers(convertToIntegerList(i)));
   }

}