package adventofcode.four;

import static adventofcode.four.SecureContainer.convertToIntegerList;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.jupiter.api.Test;


class SecureContainerTest_doubles {

   @Test
   public void testDoubles1() {
      doublesCheck(333).isFalse();
   }

   @Test
   public void testDoubles2() {
      doublesCheck(33322).isTrue();
   }

   @Test
   public void testDoubles3() {
      doublesCheck(123456789).isFalse();
   }

   @Test
   public void testDoubles4() {
      doublesCheck(1231).isFalse();
   }

   @Test
   public void testDoubles5() {
      doublesCheck(112233).isTrue();
   }

   @Test
   public void testDoubles6() {
      doublesCheck(123444).isFalse();
   }

   @Test
   public void testDoubles7() {
      doublesCheck(111122).isTrue();
   }

   @Test
   public void testDoubles8() {
      doublesCheck(111222).isFalse();
   }

   private AbstractBooleanAssert<?> doublesCheck( int i ) {
      return assertThat(SecureContainer.checkForDoubles(convertToIntegerList(i)));
   }

}