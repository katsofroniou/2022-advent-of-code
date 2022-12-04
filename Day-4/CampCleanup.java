import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampCleanup {
    public static List<String> readFile(List<String> allPairs) {
        File input = new File("Day-4/input.txt");
        String line;

        try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
            while ((line = bf.readLine()) != null) {
                allPairs.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allPairs;
    }

    // Part 1 - 547
    // Part 2 - 843
    public static int checkPairs(String elf1, String elf2) {
        int elf1Small, elf2Small, elf1Big, elf2Big;
        String[] elf1Split, elf2Split;

        elf1Split = elf1.split("-");
        elf2Split = elf2.split("-");

        elf1Small = Integer.parseInt(elf1Split[0]);
        elf1Big = Integer.parseInt(elf1Split[1]);

        elf2Small = Integer.parseInt(elf2Split[0]);
        elf2Big = Integer.parseInt(elf2Split[1]);

        if (((elf2Small <= elf1Small) && (elf1Big <= elf2Big))
                || ((elf1Small <= elf2Small) && (elf2Big <= elf1Big))) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int checkOverlap(String elf1, String elf2) {
        int elf1Small, elf2Small, elf1Big, elf2Big;
        String[] elf1Split, elf2Split;

        elf1Split = elf1.split("-");
        elf2Split = elf2.split("-");

        elf1Small = Integer.parseInt(elf1Split[0]);
        elf1Big = Integer.parseInt(elf1Split[1]);

        elf2Small = Integer.parseInt(elf2Split[0]);
        elf2Big = Integer.parseInt(elf2Split[1]);

        if (((elf1Small <= elf2Small) && (elf2Small <= elf1Big))
                || ((elf2Small <= elf1Small) && (elf1Small <= elf2Big))) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int totalPairs(List<String> allPairs) {
        String[] pairValues;
        String elf1, elf2;
        int pairs = 0;

        for (String line : allPairs) {
            pairValues = line.split(",");
            elf1 = pairValues[0];
            elf2 = pairValues[1];

            pairs += checkPairs(elf1, elf2);
        }

        return pairs;
    }

    public static void main(String[] args) {
        List<String> allPairs = new ArrayList<>();

        readFile(allPairs);
        System.out.println("Total pairs: " + totalPairs(allPairs));
    }
}