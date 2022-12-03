import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PrioritySum {
    public static int sumOfPriority(List<String> lines, int retVal) {
        Path backpackInput = Paths.get("C:/Users/Katerina/Documents/test/day3/src/backpack-input.txt");

        try {
            lines = Files.readAllLines(backpackInput);
            String lower = "abcdefghijklmnopqrstuvwxyz";
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            String front = "";
            String back = "";

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                front = line.substring(0, line.length() / 2);
                back = line.substring(line.length() / 2);

                for (char c : lower.toCharArray()) {
                    if ((front.indexOf(c) >= 0) && (back.indexOf(c) >= 0)) {
                        retVal += lower.indexOf(c) + 1;
                    }
                }

                for (char c : upper.toCharArray()) {
                    if ((front.indexOf(c) >= 0) && (back.indexOf(c) >= 0)) {
                        retVal += upper.indexOf(c) + 27;
                    }
                }
            }

            return retVal;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        int retVal = 0;

        // Part 1
        System.out.println("Total sum of priority items: " + sumOfPriority(lines, retVal));
    }
}