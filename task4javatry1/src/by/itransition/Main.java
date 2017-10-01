package by.itransition;

import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int computersMove;
        int playersMove = 0;
        Scanner scanner = new Scanner(System.in);
        Rules currentRulse = new Rules();
        String salt = Encoder.csRandomAlphaNumericString(8);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

        //check if there's an odd number of rules
        try {
            currentRulse.checkRulesCount();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        //print the menu
        System.out.println("Welcome to the game. The rules:");
        for (int i = 0; i < currentRulse.getRules().size(); i++) {
            System.out.println(Integer.toString(i) + " = " + currentRulse.getRules().get(i));
        }
        //make the move
        computersMove = currentRulse.makeMove();

        //prove computer didn't cheat
        try {
            System.out.println("Computer has made a move. Here's an hmac+sha2 proof: "
                                + Encoder.encode(salt, currentRulse.getRules().get(computersMove)));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm error");
        }
        System.out.println("Your move: ");

        //let player make a move
        try {
            playersMove = scanner.nextInt();
            if (playersMove > currentRulse.getRules().size() -1 || playersMove < 0) {
                throw new Exception("wrong number");
            }
        } catch (Exception e) {
            System.out.println("Wrong input; " + e);
            System.exit(0);
        }

        //decide result
        System.out.println("Result: " + resourceBundle.getString(String
                    .valueOf(currentRulse.decideResult(computersMove, playersMove))));
        System.out.println("Computer's move was: " + currentRulse.getRules().get(computersMove));
        System.out.println("Salt was: " + salt);

    }
}
