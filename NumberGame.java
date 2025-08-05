package NumberGame;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error setting console encoding to UTF-8: " + e.getMessage());
        }
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain;
        System.out.println("Welcome to the Number Guessing Game");
        System.out.println("Think of a number between 1 and 100!");
        System.out.println("Try to guess within 5 attempts.");
        System.out.println("Lets Begin!!!!");

        do {
            int number = random.nextInt(100) + 1;
            int guess = 0;
            int attempts = 0;
            int maxAttempts = 5;

            System.out.println("Start guessing a number between 1 and 100!");
            System.out.println("You have " + maxAttempts + "  attempts left to guess.");

            while (attempts < maxAttempts) {
                System.out.println("Enter your guess:");

                try {
                    guess = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!! Please enter a number between 1 and 100.");
                    input.next();
                    continue;
                }
                if (guess < 1 || guess > 100) {
                    System.out.println("Number is to be guessed between 1 and 100.");
                    continue;
                }
                attempts++;
                if (guess < number) {
                    System.out.println("Your guess is too low!!!Try to guess a higher number.......");
                } else if (guess > number) {
                    System.out.println("Your guess is too high!!!!guess a lower number again......");
                } else {
                    System.out.println("Congratulations!! You guessed the randomly generated number " + number
                            + " correctly!!!");
                    System.out.println("You've guessed it in  " + attempts + " number of attempts.");
                    int roundScore = calculateScore(maxAttempts, attempts);
                    score += roundScore;
                    System.out.println("Your score for this round is:" + roundScore);
                    break;
                }
                System.out.println("You've attempted " + attempts + " number of times.");
                System.out.println("You've  " + (maxAttempts - attempts) + "  chances left to guess....");

            }
            if (attempts == maxAttempts && guess != number) {
                System.out.println("Sorry! You've used all your attempts.");
                System.out.println("The right guess was:" + number);
            }
            System.out.println("---------Round Over---------");
            System.out.println("Your total score for the round is:" + score);
            System.out.println("Would you like to play again? (yes/no)");
            playAgain = input.next().equalsIgnoreCase("yes");

        } while (playAgain);
        System.out.println("Thanks for playing!!");
        System.out.println("TOTAL SCORE IS:" + score);
        input.close();

    }

    private static int calculateScore(int maxAttempts, int attempts) {
        int pointsperAttempt = 5;
        int score = (maxAttempts - attempts + 1) * pointsperAttempt;
        return Math.max(score, 0);
    }
}
