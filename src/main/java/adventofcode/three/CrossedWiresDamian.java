package adventofcode.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CrossedWiresDamian {

   public static void main( String[] args ) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File("src/main/resources/adventofcode/three/input.txt"));
      final String wire1 = scanner.nextLine();
      final String wire2 = scanner.nextLine();

      //      int distance = calcMinSteps(wire1, wire2);
      int distance = calcDistance(wire1, wire2);
      System.out.println(distance);
   }

   static int calcDistance( String path1, String path2 ) {
      List<Point> crosspoints = getCrossPoints(path1, path2);
      return crosspoints.stream().map(Point::getDistanceToZero).peek(System.out::println).min(Integer::compareTo).orElse(0);
   }

   static int calcMinSteps( String path1, String path2 ) {
      List<Point> points1 = getPointsForPath(path1);
      List<Point> points2 = getPointsForPath(path2);
      List<Point> crosspoints = getCrossPoints(path1, path2);
      List<Integer> minSteps = new ArrayList<>();
      for ( Point point : crosspoints ) {
         minSteps.add(points1.indexOf(point) + points2.indexOf(point) + 2);
      }
      return minSteps.stream().min(Integer::compareTo).orElse(0);
   }

   private static List<Point> getCrossPoints( String path1, String path2 ) {
      List<Point> points1 = getPointsForPath(path1);
      List<Point> points2 = getPointsForPath(path2);
      return points1.stream().filter(points2::contains).collect(Collectors.toList());
   }

   private static Point getNextPoint( Point current, String direction ) {
      switch ( direction ) {
      case "R":
         return new Point(current.getX() + 1, current.getY());
      case "L":
         return new Point(current.getX() - 1, current.getY());
      case "U":
         return new Point(current.getX(), current.getY() + 1);
      case "D":
         return new Point(current.getX(), current.getY() - 1);
      default:
         throw new RuntimeException("dude...");
      }
   }

   private static List<Point> getPointsForPath( String path ) {
      String[] pathSteps = path.split(",");

      List<Point> points = new LinkedList<>();
      Point current = new Point(0, 0);
      int steps = 0;

      for ( String step : pathSteps ) {
         String direction = step.substring(0, 1);
         int distance = Integer.parseInt(step.substring(1));
         while ( distance > 0 ) {
            current = getNextPoint(current, direction);
            points.add(current);
            distance--;
         }
      }

      return points;
   }

   private static class Point {

      int x;
      int y;

      public Point( int x, int y ) {
         this.x = x;
         this.y = y;
      }

      @Override
      public boolean equals( Object o ) {
         if ( this == o ) {
            return true;
         }
         if ( o == null || getClass() != o.getClass() ) {
            return false;
         }
         Point point = (Point)o;
         return x == point.x && y == point.y;
      }

      public int getDistanceToZero() {
         return Math.abs(x) + Math.abs(y);
      }

      public int getX() {
         return x;
      }

      public int getY() {
         return y;
      }

      @Override
      public int hashCode() {
         return Objects.hash(x, y);
      }

      @Override
      public String toString() {
         return "Point{" + "x=" + x + ", y=" + y + '}';
      }
   }

}
