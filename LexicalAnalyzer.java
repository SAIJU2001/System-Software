import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer 
{
    public static List<Token> analyze(String input) 
    {
        List<Token> tokens = new ArrayList<>();

        String regex = "\\s*(\\d+\\.\\d+|\\d+|[+\\-*/()])\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) 
        {
            String tokenValue = matcher.group(1);
            TokenType tokenType = determineTokenType(tokenValue);
            tokens.add(new Token(tokenType, tokenValue));
        }

        return tokens;
    }

    private static TokenType determineTokenType(String tokenValue) 
    {
        if (tokenValue.matches("\\d+\\.\\d+|\\d+")) 
        {
            return TokenType.NUMBER;
        } 
        else if (tokenValue.matches("[+\\-*/()]")) 
        {
            return TokenType.OPERATOR;
        } 
        else 
        {
            return TokenType.UNKNOWN;
        }
    }

    public static void main(String[] args) 
    {
        String inputExpression = "3.14 + 2 * (7 - 5)";
        List<Token> tokens = analyze(inputExpression);

        System.out.println("Tokens:");
        for (Token token : tokens) 
        {
            System.out.println(token);
        }
    }
}

class Token 
{
    TokenType type;
    String value;

    public Token(TokenType type, String value) 
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() 
    {
        return "(" + type + ", '" + value + "')";
    }
}

enum TokenType 
{
    NUMBER,
    OPERATOR,
    UNKNOWN
}
