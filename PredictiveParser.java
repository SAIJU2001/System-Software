import java.util.*;

public class PredictiveParser 
{
    private static String input;
    private static int index;

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an arithmetic expression: ");
        input = scanner.nextLine() + "$"; // Add '$' as the end marker
        index = 0;

        try 
        {
            parseE();
            if (input.charAt(index) == '$') 
            {
                System.out.println("Valid expression!");
            } 
            else 
            {
                System.out.println("Invalid expression!");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Invalid expression!");
        }
    }

    private static void parseE() 
    {
        parseT();
        parseEPrime();
    }

    private static void parseEPrime() 
    {
        if (input.charAt(index) == '+') 
        {
            index++;
            parseT();
            parseEPrime();
        }
        // E' can also be ε (empty), so no action needed in that case.
    }

    private static void parseT() 
    {
        parseF();
        parseTPrime();
    }

    private static void parseTPrime() 
    {
        if (input.charAt(index) == '*') 
        {
            index++;
            parseF();
            parseTPrime();
        }
        // T' can also be ε (empty), so no action needed in that case.
    }

    private static void parseF() 
    {
        if (input.charAt(index) == '(') 
        {
            index++;
            parseE();
            if (input.charAt(index) == ')') 
            {
                index++;
            } 
            else 
            {
                throw new RuntimeException("Invalid expression - missing closing parenthesis");
            }
        } 
        else if (Character.isLetterOrDigit(input.charAt(index))) 
        {
            index++;
        } 
        else 
        {
            throw new RuntimeException("Invalid expression - unexpected symbol");
        }
    }
}
