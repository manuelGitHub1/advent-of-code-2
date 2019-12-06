package adventofcode.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CrossedWires {

   public static void main( String[] args ) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File("src/main/resources/adventofcode/three/input.txt"));
      final String wire1 = scanner.nextLine();
      final String wire2 = scanner.nextLine();

      //      process("R8,U5,L5,D3", "U7,R6,D4,L4");
      //      process("L4,U4", "U4,L4");
      process("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83");
      process("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

      final int closestDistance = process(wire1, wire2);
      System.out.println("Correct: 5357. Result is: " + closestDistance);

   }

   private static int distance( Integer key, Integer value ) {
      return Math.abs(key) + Math.abs(value);
   }

   private static int process( String wire1, String wire2 ) {
      Grid grid = new Grid();
      grid.addWire(wire1);
      grid.addWire(wire2);
      //      System.out.println("All wires: " + grid.getWirePaths());
      final List<Entry<Integer, Integer>> crossings = grid.getCrossings();
      if ( crossings.isEmpty() ) {
         System.out.println("No crossings found");
         return 0;
      }
      System.out.println("All crossings: " + crossings);

      Entry<Integer, Integer> firstCrossing = crossings.get(0);
      int closestCrossingDistance = distance(firstCrossing.getKey(), firstCrossing.getValue());

      for ( Entry<Integer, Integer> crossing : crossings ) {
         final int yanCrossing = distance(crossing.getKey(), crossing.getValue());
         if ( yanCrossing < closestCrossingDistance ) {
            firstCrossing = crossing;
            closestCrossingDistance = distance(firstCrossing.getKey(), firstCrossing.getValue());
         }

      }
      System.out.println("Closest crossing " + firstCrossing + "  distance: " + closestCrossingDistance);
      System.out.println("**********************************");
      System.out.println();
      return closestCrossingDistance;
   }

   static class Grid {

      private static Entry<Integer, Integer> getNextPoint( Entry<Integer, Integer> current, char direction ) {
         switch ( direction ) {
         case 'R':
            return point(current.getKey() + 1, current.getValue());
         case 'L':
            return point(current.getKey() - 1, current.getValue());
         case 'U':
            return point(current.getKey(), current.getValue() + 1);
         case 'D':
            return point(current.getKey(), current.getValue() - 1);
         default:
            throw new RuntimeException("dude...");
         }
      }

      private static Entry<Integer, Integer> point( int i, int lastY ) {
         return Map.entry(i, lastY);
      }

      private List<List<Entry<Integer, Integer>>> wirePoints = new ArrayList<>();

      public void addWire( String wirePath ) {
         addWire(Arrays.asList(wirePath.split(",")));
      }

      //      int offsetX = 0;
      //      int offsetY = 0;
      //            switch ( direction ) {
      //         case 'R':
      //            offsetX = lastX;
      //            newX = lastX + steps;
      //            log(lastX, newX, lastY, newY, instruction);
      //            for ( int i = 1; i <= steps; i++ ) {
      //               createPoint(i, lastY + offsetX, currentWirePoints);
      //            }
      //            lastX += steps;
      //            break;
      //         case 'L':
      //            offsetX = lastX - steps;
      //            newX = lastX - steps;
      //            log(lastX, newX, lastY, newY, instruction);
      //            for ( int i = 1; i <= steps; i++ ) {
      //               createPoint(i + offsetX, lastY, currentWirePoints);
      //            }
      //            lastX -= steps;
      //            break;
      //         case 'U':
      //            offsetY = lastY;
      //            newY = lastY + steps;
      //            log(lastX, newX, lastY, newY, instruction);
      //            for ( int i = 1; i <= steps; i++ ) {
      //               createPoint(lastX, i + offsetY, currentWirePoints);
      //            }
      //            lastY += steps;
      //            break;
      //         case 'D':
      //            offsetY = lastY - steps;
      //            newY = lastY - steps;
      //            log(lastX, newX, lastY, newY, instruction);
      //            for ( int i = 0; i <= steps; i++ ) {
      //               createPoint(lastX, offsetY + i, currentWirePoints);
      //            }
      //            lastY -= steps;
      //            break;
      //      }

      public void addWire( List<String> wirePath ) {
         List<Entry<Integer, Integer>> currentWirePoints = new ArrayList<>();
         Entry<Integer, Integer> current = point(0, 0);
         for ( String instruction : wirePath ) {
            char direction = instruction.charAt(0);
            int steps = Integer.parseInt(instruction.substring(1));
            while ( steps > 0 ) {
               current = getNextPoint(current, direction);
               currentWirePoints.add(current);
               steps--;
            }
         }
         wirePoints.add(currentWirePoints);
      }

      public List<Entry<Integer, Integer>> getCrossings() {
         final List<Entry<Integer, Integer>> entries = wirePoints.get(0);
         final List<Entry<Integer, Integer>> entries2 = wirePoints.get(1);
         return entries.stream().filter(entries2::contains).collect(Collectors.toList());
      }

      public void printGrid() {
         final List<String> gridLayout = new ArrayList<>();
         for ( int y = 0; y < 9; y++ ) {
            StringBuilder line = new StringBuilder();
            for ( int x = 0; x < 9; x++ ) {
               if ( y == 0 && x == 0 ) {
                  line.append("| X |");
                  continue;
               }
               final Entry<Integer, Integer> point = point(x, y);
               //               if ( wirePaths.containsKey(point) ) {
               //                  //                  line.append("*");
               //                  line.append('|').append(point).append('|');
               //               } else {
               //                  line.append("|...|");
               //               }
            }

            gridLayout.add(line.toString());
         }
         Collections.reverse(gridLayout);
         gridLayout.stream().forEach(System.out::println);

      }

      private void createPoint( int x, int y, List<Entry<Integer, Integer>> currentWirePoints ) {
         final Entry<Integer, Integer> newPoint = point(x, y);
         currentWirePoints.add(newPoint);
      }

      private void log( int lastX, int newX, int lastY, int newY, String instruction ) {
         StringBuilder instructionInfo = new StringBuilder(instruction + " = ");
         if ( newX != lastX ) {
            final int difference = newX - lastX;
            if ( difference > 0 ) {
               instructionInfo.append("Increase");
            } else {
               instructionInfo.append("Decrease");
            }
            instructionInfo.append(" X by ").append(difference);
         } else if ( newY != lastY ) {
            final int difference = newY - lastY;
            if ( difference > 0 ) {
               instructionInfo.append("Increase");
            } else {
               instructionInfo.append("Decrease");
            }
            instructionInfo.append(" Y by ").append(difference);
         }
         System.out.println(instructionInfo);
         System.out.println("Current Position: " + lastX + ":" + lastY);
         System.out.println("Next Position: " + newX + ":" + newY);
         System.out.println();
      }
   }
}
