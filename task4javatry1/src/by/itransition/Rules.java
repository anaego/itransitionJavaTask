package by.itransition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Администратор on 24.06.2017.
 */
public class Rules {
    private List<String> rules;

    public Rules() {
        rules = new ArrayList<String>();
        rules.add("Scissors");
        rules.add("Paper");
        rules.add("Stone");
        rules.add("Lizard");
        rules.add("Spock");
    }

    public Rules(ArrayList<String> rules) {
        this.rules = rules;
    }

    public void checkRulesCount() throws Exception {
        if (rules.size() % 2 == 0) { //it's even
            throw new Exception("Wrong number of rules");
        }
    }

    public int decideResult(Integer computersMove, int playersMove) {
        if (computersMove == playersMove) {
            return 0;
        }

        if (( (computersMove + playersMove) % 2 == 0 //even sum
                            && computersMove > playersMove ) //AND computer's number is bigger
                || ( (computersMove + playersMove) % 2 != 0 //odd sum
                            && computersMove < playersMove ) //AND computer's number is smaller
                ) {
            return 1; //computer won
        } else {
            return -1; //player won
        }

    }

    public int makeMove() {
        Random random = new Random();
        return random.nextInt(rules.size());
    }

    public List<String> getRules() {
        return rules;
    }

}
