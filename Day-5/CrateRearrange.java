import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CrateRearrange {
    public static int splitListIns(List<String> input, ArrayList<String> boxList) {
        int resume = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                resume = i;
                break;
            }

            boxList.add(input.get(i));
        }
        return resume;
    }

    public static void storeArrList(ArrayList<String> boxList, ArrayList<Integer> indexList,
            ArrayList<String> arrangedList) {
        for (int i = boxList.size() - 1; i >= 0; i--) {
            if (i == boxList.size() - 1) {
                for (int index = 0; index < boxList.get(i).length(); index++) {
                    if ((int) boxList.get(i).charAt(index) >= 49 && (int) boxList.get(i).charAt(index) <= 57) {
                        indexList.add(index);
                        arrangedList.add("");
                    }
                }
            }

            for (int u = 0; u < indexList.size(); u++) {
                if (boxList.get(i).charAt(indexList.get(u)) != ' ') {
                    arrangedList.set(u, arrangedList.get(u) + boxList.get(i).charAt(indexList.get(u)));
                }
            }
        }
    }

    public static void applyInsP1(List<String> input, int resume, ArrayList<String> arrangedList) {
        String[] split;
        String letters;
        int move, from, to;

        for (int i = resume + 1; i < input.size(); i++) {
            split = input.get(i).replaceAll("[^\\d.]", " ").trim().split("\\W+");
            move = Integer.parseInt(split[0]);
            from = Integer.parseInt(split[1]);
            to = Integer.parseInt(split[2]);
            letters = arrangedList.get(from - 1);
            letters = letters.substring(letters.length() - move);

            StringBuffer reverse = new StringBuffer(letters);
            reverse.reverse();

            arrangedList.set(from - 1,
                    arrangedList.get(from - 1).substring(0, arrangedList.get(from - 1).length() - move));
            arrangedList.set(to - 1, arrangedList.get(to - 1) + reverse);
        }
    }

    public static void applyInsP2(int resume, List<String> input, List<String> arrangedList) {
        String[] split;
        String letters;
        int move, from, to;

        for (int i = resume + 1; i < input.size(); i++) {
            split = input.get(i).replaceAll("[^\\d.]", " ").strip().split("\\W+");
            move = Integer.parseInt(split[0]);
            from = Integer.parseInt(split[1]);
            to = Integer.parseInt(split[2]);

            letters = arrangedList.get(from - 1);
            letters = letters.substring(letters.length() - move);

            arrangedList.set(from - 1,
                    arrangedList.get(from - 1).substring(0, arrangedList.get(from - 1).length() - move));
            arrangedList.set(to - 1, arrangedList.get(to - 1) + letters);
        }
    }

    public static String concLastLetters(ArrayList<String> arrangedList) {
        String result = "";
        for (int i = 0; i < arrangedList.size(); i++) {
            result = result + arrangedList.get(i).substring(arrangedList.get(i).length() - 1);
        }
        return result;
    }

    public static void part1(List<String> input) {
        ArrayList<String> boxList = new ArrayList<>();
        ArrayList<Integer> indexList = new ArrayList<>();
        ArrayList<String> arrangedList = new ArrayList<>();
        int resume = splitListIns(input, boxList);

        storeArrList(boxList, indexList, arrangedList);
        applyInsP1(input, resume, arrangedList);
        String result = concLastLetters(arrangedList);
        System.out.printf("Part 1: %s", result + "\n");
    }

    public static void part2(List<String> input) {
        ArrayList<String> boxList = new ArrayList<>();
        ArrayList<Integer> indexList = new ArrayList<>();
        ArrayList<String> arrangedList = new ArrayList<>();
        int resume = splitListIns(input, boxList);

        storeArrList(boxList, indexList, arrangedList);
        applyInsP2(resume, input, arrangedList);
        String result = concLastLetters(arrangedList);
        System.out.printf("Part 2: %s", result + "\n");
    }

    public static void main(String[] args) throws IOException {
        // Read input from file
        Path path = Paths.get("input.txt");
        List<String> input = Files.readAllLines(path);

        long startTime = System.nanoTime();
        part1(input);

        long part1EndTime = System.nanoTime();
        long part1Speed = part1EndTime - startTime;
        part2(input);

        long part2Speed = System.nanoTime() - part1EndTime;

        System.out.printf("\nPart 1 Speed: %s seconds\nPart 2 Speed: %s seconds", part1Speed / 1000000000.0,
                part2Speed / 1000000000.0);

    }
}