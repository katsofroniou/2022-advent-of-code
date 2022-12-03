import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrioritySum {
    public static List<String> readFile(List<String> lines, Path backpackInput) throws IOException {
        Scanner sc = new Scanner(backpackInput);

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();

            if (!temp.isEmpty()) {
                lines.add(sc.nextLine());
            }
        }

        sc.close();
        return lines;
    }

    public static int sumOfPriority(List<String> lines) {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String front = "";
        String back = "";

        int retVal = 0;

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
    }

    public static int groupSum(List<String> lines) {
        List<Integer> groupSum = new ArrayList<>();

        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < lines.size(); i += 3) {
            int tempSum = 0;

            String line1 = lines.get(i);
            String line2 = lines.get(i + 1);
            String line3 = lines.get(i + 2);

            for (char c : lower.toCharArray()) {
                if ((line1.indexOf(c) >= 0) && (line2.indexOf(c) >= 0) && (line3.indexOf(c) >= 0)) {
                    tempSum += lower.indexOf(c) + 1;
                }
            }

            for (char c : upper.toCharArray()) {
                if ((line1.indexOf(c) >= 0) && (line2.indexOf(c) >= 0) && (line3.indexOf(c) >= 0)) {
                    tempSum += upper.indexOf(c) + 27;
                }
            }

            groupSum.add(tempSum);
        }

        List<Integer> sortedGroupSum = groupSum.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int top = 0;

        for (int i = 0; i < sortedGroupSum.size(); i++) {
            top += sortedGroupSum.get(i);
        }

        return top;
    }

    public static void main(String[] args) {
        Path backpackInput = Paths.get("C:/Users/Katerina/Documents/test/day3/src/backpack-input.txt");
        List<String> lines = new ArrayList<>();

        try {
            readFile(lines, backpackInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1
        System.out.println("Total sum of priority items: " + sumOfPriority(lines));

        // Part 2
        System.out.println("Total sum of group priority items: " + groupSum(lines));
    }
}