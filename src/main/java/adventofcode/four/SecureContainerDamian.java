package adventofcode.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SecureContainerDamian {

   public static boolean isValid( int pass ) {
      String passStr = String.valueOf(pass);
      String[] splitted = passStr.split("");
      String sorted = Arrays.stream(splitted).sorted().collect(Collectors.joining());
      if ( !sorted.equals(passStr) ) {
         return false;
      }
      String prev = splitted[0];
      String group = prev;
      List<String> groups = new ArrayList<>();
      for ( int i = 1; i < splitted.length; i++ ) {
         if ( prev.equals(splitted[i]) ) {
            group += splitted[i];
         } else {
            groups.add(group);
            group = splitted[i];
         }
         prev = splitted[i];
      }
      groups.add(group);
      final boolean match = groups.stream().anyMatch(s -> s.length() == 2);
      if ( match ) {
         System.out.println(pass);
      }
      return match;
   }

   public static void main( String[] args ) {
      Integer min = 272091;
      Integer max = 815432;

      //      String input = "193651-649729";
      //      String[] range = input.split("-");
      long count = IntStream.rangeClosed(min, max).mapToObj(SecureContainerDamian::isValid).filter(Boolean::booleanValue).count();
      System.out.println(count);
   }
}

