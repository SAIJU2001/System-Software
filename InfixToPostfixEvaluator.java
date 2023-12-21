import java.util.*;

public class InfixToPostfixEvaluator 
{
    // algorithm for infix to postfix conversion
    public static String infixToPostfix(String expression) 
    {
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (char ch : expression.toCharArray()) 
        {
            if (Character.isWhitespace(ch)) 
            {
                continue;  // Skip spaces
            } 
            else if (Character.isLetterOrDigit(ch)) 
            {  // Operand
                postfix.append(ch);
            } 
            else if (ch == '(') 
            {
                stack.push(ch);
            } 
            else if (ch == ')') 
            {
                while (!stack.isEmpty() && stack.peek() != '(') 
                {
                    postfix.append(stack.pop());
                }
                stack.pop();  // Pop '(' from stack
            } 
            else if (precedence.containsKey(ch)) 
            {  // Valid operator
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(stack.peek()) >= precedence.get(ch)) 
                {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            } 
            else 
            {
                throw new IllegalArgumentException("Invalid character in the expression: " + ch);
            }
        }

        while (!stack.isEmpty()) 
        {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    // algorithm for evaluate the postfix expression
    public static int evaluatePostfix(String postfixExpression) 
    {
        Stack<Integer> stack = new Stack<>();

        for (char ch : postfixExpression.toCharArray()) 
        {
            if (Character.isLetterOrDigit(ch)) 
            {  // Operand
                stack.push(Character.getNumericValue(ch));
            } 
            else if (stack.size() >= 2) 
            {  // Ensure there are enough operands
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (ch) 
                {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                    case '^':
                        stack.push((int) Math.pow(operand1, operand2));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + ch);
                }
            } 
            else 
            {
                throw new IllegalArgumentException("Invalid postfix expression: insufficient operands");
            }
        }

        if (stack.size() == 1) 
        {
            return stack.pop();
        } else 
        {
            throw new IllegalArgumentException("Invalid postfix expression: unexpected operands");
        }
    }

    //main method for drive the program
    public static void main(String[] args) 
    {
        String infixExpression = "3 + 5 * ( 2 - 8 ) / 2";
        String postfixExpression = infixToPostfix(infixExpression);
        int result = evaluatePostfix(postfixExpression);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Result: " + result);
    }
}
