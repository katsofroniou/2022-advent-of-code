import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class calorieCounter {
    public static void addCalories(List<Integer> calories) {
        Path calorieInput = Paths.get("Calories-Input.txt");
        
        try {
            Scanner sc = new Scanner(calorieInput);
            List<String> lines = Files.readAllLines(calorieInput);

            int addToCalories = 0;

            for (String line: lines) {
                if (line.trim().isEmpty()) {
                    calories.add(addToCalories);
                    addToCalories = 0;
                }
                else {
                    addToCalories += Integer.parseInt(line);
                }
            }

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLargestCalories(List<Integer> calories) {
        int largest = 0;
        for (int i = 0; i < calories.size(); i++) {
            if (calories.get(i) > largest) {
                largest = calories.get(i);
            }
        }

        return largest;
    }

    public static int getTopThree(List<Integer> calories, int topThree) {
        List<Integer> sortedCalories = calories.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()); 

        for (int i = 0; i < 3; i++) {
            topThree += sortedCalories.get(i);
        }

        return topThree;
    }

    public static void main(String[] args) {
        int topThree = 0;

        List<Integer> calories = new ArrayList<Integer>();
        addCalories(calories);

        System.out.println("Largest Calories: " + getLargestCalories(calories));

        topThree = getTopThree(calories, topThree);
        System.out.print("Top 3 Calories: " + topThree);
    }
}
