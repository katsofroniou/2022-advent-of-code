import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PointsCalc {
    public static List<String> formatList(List<String> lines) {
        for (String line: lines) {
            line = line.trim().replaceAll(" ", "");
        }

        return lines;
    }
    
    public static int addPoints(List<String> lines, int points) {
        int win = 6;
        int draw = 3;
        int rock = 1; // x a
        int paper = 2; // y b
        int scissors = 3; // z c

        for (String line: lines) {
            line = line.trim().replaceAll(" ", "");
            switch (line) {
                case "AY":
                    points += (win + paper);
                    break;
                case "AX":
                    points += (draw + rock); 
                    break;
                case "AZ":
                    points += scissors;
                    break;

                case "BY":
                    points += (draw + paper);
                    break;

                case "BX":
                    points += rock;
                    break;

                case "BZ":
                    points += (win + scissors);
                    break;
                  
                case "CY":
                    points += paper;
                    break;

                case "CX":
                    points += (rock + win);
                    break;

                case "CZ":
                    points += (scissors + draw);
                    break;
            }
        }

        return points;
    }

    public static int secretCode(List<String> lines, int points) {
        int win = 6;
        int draw = 3;
        int rock = 1; // x a
        int paper = 2; // y b
        int scissors = 3; // z c

        for (String line: lines) {
            line = line.trim().replaceAll(" ", "");

            switch (line) {
                case "AY":
                    points += draw + rock;
                    break;

                case "AX":
                    points += scissors; 
                    break;

                case "AZ":
                    points += win + paper;
                    break;

                case "BY":
                    points += draw + paper;
                    break;

                case "BX":
                    points += rock;
                    break;

                case "BZ":
                    points += win + scissors;
                    break;
                    
                case "CY":
                    points += draw + scissors;
                    break;

                case "CX":
                    points += paper;
                    break;

                case "CZ":
                    points += rock + win;
                    break;
            }
        }
        return points;
    }

    public static void main(String[] args) {
        int points = 0;

        Path gameInput = Paths.get("Day-2/rps-input.txt");
        List<String> lines = null;

        try {
            lines = Files.readAllLines(gameInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        formatList(lines);
        System.out.println(addPoints(lines, points));
        System.out.println(secretCode(lines, points));
    }
}
    
    