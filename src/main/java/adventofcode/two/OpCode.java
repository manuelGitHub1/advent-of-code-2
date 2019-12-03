package adventofcode.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpCode {

    private static final Logger _log = Logger.getLogger(OpCode.class.getName());

    private List<Integer> opcode;

    public List<Integer> getOpcode() {
        return opcode;
    }

    public void setOpcode(List<Integer> opcode) {
        this.opcode = opcode;
    }

    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();
        try (Scanner scanner =new Scanner(new File("src/main/resources/adventofcode.two/input.txt"))) {
            scanner.useDelimiter("\\,");
            while (scanner.hasNextInt()) {
                // System.out.println(scanner.next());
                integers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {


        }

_log.log(Level.INFO,"found " + integers.size() + " elements");
        calculate(integers);

    }

    private static void calculate(LinkedList<Integer> integers) {
        if (integers == null || integers.isEmpty()) {
            return;
        }
        int currentIndex = 0;
        int operator = integers.get(currentIndex);
        while (operator != 99) {
            int firstInputPosition = integers.get(currentIndex + 1);
            int secondInputPosition = integers.get(currentIndex + 2);
            int targetOutputPosition = integers.get(currentIndex + 3);
            Integer firstInput = integers.get(firstInputPosition);
            Integer secondInput = integers.get(secondInputPosition);
            int result = operate(operator, firstInput, secondInput);
            _log.log(Level.INFO, "Operation: " + operator + ", " + firstInput + ", " + secondInput + ", " + targetOutputPosition);
            integers.set(result, targetOutputPosition);
            currentIndex += 4;
            operator = integers.get(currentIndex);
        }
        System.out.println(integers.get(0));
        System.out.println("found 99");
        System.exit(0);
    }

    private static int operate(int operator, Integer firstInput, Integer secondInput) {
        switch (operator) {
            case 1: // addition
                return firstInput + secondInput;
            case 2: // multiply
                return firstInput * secondInput;
            default:
                throw new IllegalStateException("Unknown operator: " + operator);
        }
    }

}
