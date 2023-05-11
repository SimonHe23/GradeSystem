import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Enter the percentage of course sections
        System.out.println("Please enter the percentage of each component (enter four components only, in dec)：");
        double part1Weight = sc.nextDouble();
        double part2Weight = sc.nextDouble();
        double part3Weight = sc.nextDouble();
        double part4Weight = sc.nextDouble();

        // Create grade objects
        Grade grade = new Grade(part1Weight, part2Weight, part3Weight, part4Weight);

        // enter each grade component
        System.out.println("Please enter each conponent grade (press enter to enter next grade): ");
        double part1Score = sc.nextDouble();
        double part2Score = sc.nextDouble();
        double part3Score = sc.nextDouble();
        double part4Score = sc.nextDouble();

        // addd grade
        grade.addScore(part1Score, part2Score, part3Score, part4Score);

        // calculate total grade
        double totalScore = grade.calculateTotalScore();
        System.out.println("Over All Score: " + totalScore);

        // record the total grade for each course and calculatr GPA
        System.out.println("Enter the course grade（press enter if you finish entering each grade, type -1 to finish recording）：");
        List<Double> examScores = new ArrayList<>();
        while (true) {
            double examScore = sc.nextDouble();
            if (examScore < 0) {
                break;
            }
            examScores.add(examScore);
        }
        double averageGPA = grade.calculateAverageGPA(examScores);
        System.out.println("GPA: " + averageGPA);
    }
}

class Grade {
    private double part1Weight;
    private double part2Weight;
    private double part3Weight;
    private double part4Weight;
    private List<Double> part1Scores;
    private List<Double> part2Scores;
    private List<Double> part3Scores;
    private List<Double> part4Scores;

    public Grade(double part1Weight, double part2Weight, double part3Weight, double part4Weight) {
        this.part1Weight = part1Weight;
        this.part2Weight = part2Weight;
        this.part3Weight = part3Weight;
        this.part4Weight = part4Weight;
        this.part1Scores = new ArrayList<>();
        this.part2Scores = new ArrayList<>();
        this.part3Scores = new ArrayList<>();
        this.part4Scores = new ArrayList<>();
    }

    public void addScore(double part1Score, double part2Score, double part3Score, double part4Score) {//method add score
        this.part1Scores.add(part1Score);
        this.part2Scores.add(part2Score);
        this.part3Scores.add(part3Score);
        this.part4Scores.add(part4Score);
    }//close method

    public double calculateTotalScore() {//method calculate total score
        double totalScore = 0.0;
        for (int i = 0; i < part1Scores.size(); i++) {
            double part1Score = part1Scores.get(i);
            double part2Score = part2Scores.get(i);
            double part3Score = part3Scores.get(i);
            double part4Score = part4Scores.get(i);
            double score = part1Score * part1Weight + part2Score * part2Weight +
                    part3Score * part3Weight + part4Score * part4Weight;
            totalScore += score;
        }
        return totalScore;
    }//close method

    public double calculateAverageGPA(List<Double> examScores) {//method calculate average GPA
        double totalGPA = 0.0;
        for (double examScore : examScores) {
            double gpa = calculateGPA(examScore);
            totalGPA += gpa;
        }
        double averageGPA = totalGPA / examScores.size();
        return averageGPA;
    }//close method

    private double calculateGPA(double score) {//method calculate GPA
        if (score >= 90) {
            return 4.0;
        } else if (score >= 80) {
            return 3.0;
        } else if (score >= 70) {
            return 2.0;
        } else if (score >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }//close method
}