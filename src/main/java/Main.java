import com.ejemplo_junit.passwordvalidator.rule.MinLengthRule;
import com.ejemplo_junit.passwordvalidator.rule.NumberRule;
import com.ejemplo_junit.passwordvalidator.rule.PasswordValidator;
import com.ejemplo_junit.passwordvalidator.rule.SpecialCharacterRule;
import com.ejemplo_junit.passwordvalidator.rule.UppercaseRule;
import com.ejemplo_junit.passwordvalidator.rule.ValidationResult;

public class Main {

  public static void main(String[] args) {

    PasswordValidator validator = new PasswordValidator()
        .addRule(new MinLengthRule(8))
        .addRule(new UppercaseRule())
        .addRule(new NumberRule())
        .addRule(new SpecialCharacterRule());

    ValidationResult result = validator.validate("Password1!");

    if (result.isValid()) {
      System.out.println("Password is valid");
    } else {
      result.getErrors().forEach(System.out::println);
    }
  }
}