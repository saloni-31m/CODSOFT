package StudentGradeCalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        System.out.println("Enter your id:");
        String id = sc.nextLine();

        int subjects_num = 0;
        System.out.println("Enter the number of subjects:");
        while (true) {
            try {
                subjects_num = sc.nextInt();
                if (subjects_num <= 0) {
                    System.out.println("Subjects cannot be zero!!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ivalid input entered...");
                sc.nextLine();
            }
        }
        sc.nextLine();

        String[] sub = new String[subjects_num];
        int[] sub_marks = new int[subjects_num];
        double total_marks = 0;

        for (int i = 0; i < subjects_num; i++) {
            System.out.println("Enter the name of the subjects" + (i + 1) + ":");
            sub[i] = sc.nextLine();

            
            boolean validInput= false;
            int marks = 0;
            while (!validInput) {
                System.out.println("Enter the marks of" + sub[i] + ":");
                try {
                    marks = sc.nextInt();
                    if (marks < 0 || marks > 100) {
                        System.out.println("Marks should be between 0 and 100....");
                        ;
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!!");
                    sc.nextLine();
                }

            }
            sub_marks[i] = marks;
            total_marks += marks;

            sc.nextLine();
        }
        double percent = total_marks / subjects_num;

        String grade;
        if (percent >= 95) {
            grade = "A++";
        } else if (percent >= 90) {
            grade = "A+";
        } else if (percent >= 85) {
            grade = "A";
        } else if (percent >= 80) {
            grade = "B";
        } else if (percent >= 70) {
            grade = "C";
        } else if (percent >= 60) {
            grade = "D";
        } else if (percent >= 50) {
            grade = "E";
        } else {
            grade = "F";
        }

        System.out.println("----------Student Details----------");
        System.out.println("Name of the student is: "  + name);
        System.out.println("Id:  "  + id);
        System.out.println("--------------------------------------");
        System.out.println("Subject wise marks:");
        for (int j = 0; j < subjects_num; j++) {
            System.out.printf("Subject: %s, Marks: %d%n", sub[j], sub_marks[j]);
        }

        System.out.println("----------Report----------");
        System.out.printf("Total marks obtained:%.2f%n" , total_marks);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.println("Grade obtained is:" + grade);

        sc.close();

    }
}
