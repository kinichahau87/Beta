package jutil;

import java.util.List;

import phrase.Phrase;

import Value.Environment;
import Value.Value;

public class OmegaConsole extends Console {
   public OmegaConsole(Environment model) {
      super(model);
   }
   public OmegaConsole() { this(new Environment()); }
   protected Environment getModel() { return (Environment)model; }

   protected String execute(String cmmd) throws AppError {
      List<String> tokens = Lex.scan(Phrase.TOKEN, cmmd);
      Phrase phrase = Phrase.parse(tokens);
      Environment env = getModel();
      Value value = phrase.eval(env);
      unsavedChanges = true;
      return value.toString();
   }

   public static void main(String[] args) {
      Environment model = new Environment();
      Console console = new OmegaConsole(model);
      console.controlLoop();
   }
}

