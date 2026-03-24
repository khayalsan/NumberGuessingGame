package khayalsan;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        NumberGuessingGame game = new NumberGuessingGame();
        System.out.println(
                """
                Welcome to the Number Guessing Game!
                I'm thinking of a number between 1 and 100.
                You have only limited number of chances to guess the correct number.
                """
        );
        game:
        while (true) {
            System.out.println(
                """
                Please select the difficulty level:
                    1. Easy (10 chances)
                    2. Medium (5 chances)
                    3. Hard (3 chances)
                """
            );
            String difficulty = "Invalid";
            do {
                System.out.print("Enter Difficulty Level: ");
                game.setChances(switch (getInt(scanner)) {
                    case 1 -> {
                        difficulty = "Easy";
                        yield 10;
                    }
                    case 2 -> {
                        difficulty = "Medium";
                        yield 5;
                    }
                    case 3 -> {
                        difficulty = "Hard";
                        yield 3;
                    }
                    default -> {
                        System.out.println("Invalid input!");
                        yield 0;
                    }
                });
            }
            while (!game.canGuess());

            System.out.println("Great! You have selected the " + difficulty + " difficulty level.");
            System.out.println("Let's start the game!");
            int guess;

            long start = System.currentTimeMillis(); // to get how much time player spent
            round: // name of the loop
            while (true) {
                System.out.print("Enter your guess: ");
                guess = getInt(scanner);
                GameState state = game.guess(guess);

                switch (state) {
                    case WON -> {
                        long elapsed = (System.currentTimeMillis() - start) / 1000; //seconds since start
                        System.out.println("Congratulations! You guessed the correct number in " + elapsed + " seconds using " + game.getAttempts() + " attempts.");
                        break round;
                    }
                    case GREATER, LESS -> {
                        System.out.println("Incorrect! The number is " + state.toString().toLowerCase() + " than " + guess);
                        break;
                    }
                    case LOST -> {
                        long elapsed = (System.currentTimeMillis() - start) / 1000;
                        System.out.println("Incorrect! You ran out of attempts. The correct number was " + game.cheat() + ". This round lasted " + elapsed + " seconds.");
                        break round;
                    }
                }
            }
            System.out.println("Do you want to play again? (1 - yes | 0 - no): ");
            boolean stopPlaying = getInt(scanner) != 1;
            if (stopPlaying) {
                System.out.println("Thanks for playing!");
                break game;
            }
            game.reset();
        }
        scanner.close();

    }

    private static int getInt(Scanner scanner) {
        int number;
        while (true) {
            try {
                return scanner.nextInt();
            }
            catch (Exception e) {
                System.out.print("Invalid input! Try again: ");
                scanner.nextLine();
            }
        }
    }
}
