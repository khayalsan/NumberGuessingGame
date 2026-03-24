package khayalsan;

import java.util.Random;

public class NumberGuessingGame {
    int chances = 0;
    int attempts = 0;
    int number = new Random().nextInt(100) + 1;

    public void setChances(int chances) {
        this.chances = chances;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean canGuess() {
        return chances > 0;
    }

    public int cheat() {
        return number;
    }

    public GameState guess(int guess) {
        chances--;
        attempts++;
        if (canGuess()) {
            if (guess == number) {
                return GameState.WON;
            }
            else if (guess > number) {
                return GameState.LESS;
            }
            else {
                return GameState.GREATER;
            }
        }
        else {
            return GameState.LOST;
        }
    }

    public void reset() {
        attempts = 0;
        chances = 0;
        number = new Random().nextInt(100) + 1;
    }
}
