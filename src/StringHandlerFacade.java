/**
 * Created by Kirill Kudaev on 3/29/16.
 */

import java.io.*;
import java.util.*;

public class StringHandlerFacade {

    public static void main(String [ ] args) {

        Scanner reader = new Scanner(System.in);

        final double EPSILON = 0.000000001;

        printMenu();

        System.out.print("Your choice: ");
        String menuAnswer = reader.nextLine();  // Gets user choice on the menu.
        char menuLetter = Character.toUpperCase(menuAnswer.charAt(0));

        while (menuLetter != 'C' && menuLetter != 'L' && menuLetter != 'F') {
            // While invalid character was entered.

            System.out.println("Invalid option. Please enter again.");
            menuAnswer = reader.nextLine(); // Ask user to choose a menu option.
            menuLetter = Character.toUpperCase(menuAnswer.charAt(0));
        }


        switch (menuLetter) {
            case 'C':
                // Evaluates single expressions on the console.

                System.out.print("Enter the expression: ");
                String expressionC = reader.nextLine();  // Reads in the expression from the console.

                try {
                    Parser myParser = new Parser(new Lexer(expressionC).getTokens());

                    System.out.println(myParser.getExpressionValue());

                } catch (ExpressionFormatException e) {
                    System.out.println(e.getMessage());
                } catch (ExpressionLogicException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 'L':
                // Tokenize single expressions on the console.

                System.out.print("Enter the expression: ");
                String expressionL = reader.nextLine();  // Reads in the expression from the console.

                try {
                    Lexer myLexer = new Lexer(expressionL);  // Creates instance of the Lexer class.

                    System.out.println();
                    System.out.println("Token Type          Lexeme         Value");
                    System.out.println("========================================");

                    for (Token t : myLexer.getTokens()) {
                        // For each token in the expression:
                        System.out.println(t.toString());   // Prints out token info.
                    }

                } catch (ExpressionFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 'F':
                // Tests expressions from the file.

                Map<String, Double> expressions = new LinkedHashMap<>();    // Holds pairs of expressions right and answers.

                try (BufferedReader br = new BufferedReader(new FileReader(menuAnswer.substring(3, menuAnswer.length() - 1)))) {
                    String currentLine;

                    while ((currentLine = br.readLine()) != null) {
                        // Reads in lines until the end of the file.

                        if (currentLine.charAt(0) != '#') {
                            // Reads in lines that doesn't start with '#'.
                            expressions.put(currentLine, Double.parseDouble(br.readLine()));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Iterate through key value pair.
                for (Map.Entry<String, Double> entry : expressions.entrySet()) {

                    try {

                        double result = new Parser(new Lexer(entry.getKey()).getTokens()).getExpressionValue();

                        // In order to neglect computing error check if: answer - E < result < answer + E
                        if (result > entry.getValue() - EPSILON  && result < entry.getValue() + EPSILON)
                            // If result is correct.
                            System.out.printf("Correct: '%s' == %f\n", entry.getKey(), result);
                        else
                            // If result is wrong.
                            System.out.printf("Incorrect: '%s' != %f\n", entry.getKey(), result);

                    } catch (ExpressionFormatException e) {
                        System.out.println(e.getMessage());
                    } catch (ExpressionLogicException e) {
                        System.out.println(e.getMessage());
                    }
                }

                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static void printMenu() {
        System.out.printf(  "                      MENU                     \n" +
                            "-----------------------------------------------\n" +
                            "C - Evaluate single expressions on the console.\n" +
                            "L - Tokenize single expressions on the console.\n" +
                            "F <filename> - Test expressions from the file.\n\n");
    }
}