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
                Please select the difficulty level:
                    1. Easy (10 chances)
                    2. Medium (5 chances)
                    3. Hard (3 chances)
                """
        );

        String difficulty = "Invalid";
        do {
            System.out.print("Enter Difficulty Level: ");
            game.setChances(switch (scanner.nextInt()) {
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
        boolean gameEnded = false;
        int guess;
        outer: // name of the loop
        while (true) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            GameState state = game.guess(guess);

            switch (state) {
                case WON -> {
                    System.out.println("Congratulations! You guessed the correct number in " + game.getAttempts() + " attempts.");
                    break outer;
                }
                case GREATER, LESS -> {
                    System.out.println("Incorrect! The number is " + state.toString().toLowerCase() + " than " + guess);
                    break;
                }
                case LOST -> {
                    System.out.println("Incorrect! You ran out of attempts. The correct number was " + game.cheat());
                    break outer;
                }
            }
        }

        scanner.close();
    }
}
