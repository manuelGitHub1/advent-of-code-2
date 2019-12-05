package adventofcode.three;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossedWires {

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.addWire("R8,U5,L5,D3");
        System.out.println(grid.getWirePaths());

    }

    static class Grid {

        HashMap<Map.Entry<Integer, Integer>, Boolean> wirePaths = new HashMap<>();

        public HashMap<Map.Entry<Integer, Integer>, Boolean> getWirePaths() {
            return wirePaths;
        }

        public void addWire(String wirePath) {
            addWire(Arrays.asList(wirePath.split(",")));
        }

        public void addWire(List<String> wirePath) {
            int lastX = 0;
            int lastY = 0;
            for (String pathAngabe : wirePath) {
                char direction = pathAngabe.charAt(0);
                int steps = Integer.parseInt(pathAngabe.substring(1));
                switch (direction) {
                    case 'R':
                        for (int i = 1 + lastX; i <= steps; i++) {
                            Map.Entry<Integer, Integer> pair = Map.entry(i, lastY);
                            boolean containsKey = wirePaths.containsKey(pair);
                            wirePaths.put(pair, containsKey);
                        }
                        lastX += steps;
                        break;
                    case 'U':
                        for (int i = 1 + lastY; i <= steps; i++) {
                            Map.Entry<Integer, Integer> pair = Map.entry(lastX, i);
                            boolean containsKey = wirePaths.containsKey(pair);
                            wirePaths.put(pair, containsKey);
                        }
                        lastY += steps;
                        break;

                }

            }
        }
    }
}
