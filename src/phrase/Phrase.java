package phrase;

import java.lang.reflect.Type;
import java.util.List;

import Value.Environment;
import Value.Value;

import jutil.AppError;
import jutil.Lex;

public abstract class Phrase {
   // Omega tokens:
   public static final String NUMBER = "\\d*\\.?\\d+";
   public static final String OPERATOR = "\\+|\\*|-|/|=|<|>|%";
   public static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9]*";
   public static final String PUNCTUATION =     
                                    ";|\\(|\\.|\\)|\\]|\\[|\\}|\\{";
    public static final String SYMBOL =
        Lex.join(IDENTIFIER, OPERATOR);
    public static String TOKEN = SYMBOL;
    static {
     TOKEN = Lex.join(TOKEN, PUNCTUATION);
     TOKEN = Lex.join(TOKEN, NUMBER);
    }
  // top level parser:
  public static Phrase parse(List<String> tokens) throws AppError {
     if (Expression.isNext(tokens)) return Expression.parse(tokens);
     //if (Command.isNext(tokens)) return Command.parse(tokens);
     if (Declaration.isNext(tokens)) return Declaration.parse(tokens);
     throw new AppError("Unrecognized phrase in Phrase.parse//Error in phrase");
   }
  // top level evaluator:
  abstract public Value eval(Environment env) throws AppError;
  
  abstract public Type getType(Environment env) throws AppError;
}

