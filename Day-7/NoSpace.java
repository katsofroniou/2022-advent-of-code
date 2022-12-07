import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class NoSpace {
    public static Map<String, Integer> readInput(String path, Map<String, Integer> dirs) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String command;
            while ((command = br.readLine()) != null) {
                if (command.charAt(0) == '$') {
                    if (command.substring(2, 4).equals("ls")) {
                        // Do nothing

                    } else if (command.substring(2, 4).equals("cd")) {
                        if (command.charAt(5) == '/') {
                            path = "/home";
                        } else if (command.substring(5, 7).equals("..")) {
                            path = path.substring(0, path.lastIndexOf('/'));
                        } else {
                            String dirName = command.substring(5);
                            path = path + '/' + dirName;
                            dirs.put(path, 0);
                        }
                    }
                } else if (command.substring(0, 3).equals("dir")) {
                    // Do nothing

                } else {
                    int size = Integer.parseInt(command.substring(0, command.indexOf(' ')));

                    String dir = path;
                    for (int i = 0; i < path.length() - path.replace("/", "").length(); i++) {
                        dirs.put(dir, dirs.get(dir) + size);
                        dir = dir.substring(0, dir.lastIndexOf('/'));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dirs;
    }

    public static void solveParts(Map<String, Integer> dirs, ArrayList<Integer> validDirs) {
        int limit = 30000000 - (70000000 - dirs.get("/home"));
        int total = 0;
        int smallest = 0;

        for (String dir : dirs.keySet()) {
            if (dirs.get(dir) < 100000) {
                total += dirs.get(dir);
            }
            if (limit <= dirs.get(dir)) {
                validDirs.add(dirs.get(dir));
            }
        }

        smallest = validDirs.get(0);
        for (int dirSize : validDirs) {
            if (dirSize < smallest) {
                smallest = dirSize;
            }
        }

        System.out.println("Part 1: " + total);
        System.out.println("Part 2: " + smallest);
    }

    public static void main(String[] args) {
        Map<String, Integer> dirs = new HashMap<>();
        dirs.put("/home", 0);
        String path = "/home";
        ArrayList<Integer> validDirs = new ArrayList<>();

        readInput(path, dirs);
        solveParts(dirs, validDirs);
    }
}
