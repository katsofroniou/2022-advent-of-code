import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class PacketFinder {

    private static int uniqueChars(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }

    private static String readFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }

            reader.close();
            return sb.toString();

        } catch (IOException e) {
            return e.toString();
        }
    }

    public static void main(String[] args) {

        int part1 = 4;
        int part2 = 14;

        String file = readFile("input.txt");

        // Part 1 Solution
        for (int i = part1; i < file.length(); i++) {
            String packet = file.substring(i - part1, i);
            if (uniqueChars(packet) == part1) {
                System.out.println(i);
                break;
            }
        }

        // Part 2 Solution
        for (int i = part2; i < file.length(); i++) {
            String packet = file.substring(i - part2, i);
            if (uniqueChars(packet) == part2) {
                System.out.println(i);
                break;
            }
        }
    }
}
