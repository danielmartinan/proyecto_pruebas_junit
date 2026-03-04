package com.ejemplo_junit.passwordvalidator.rule;

public class SpecialCharacterRule implements PasswordRule {

  private static final String SPECIALS = "!@#$%^&*()_+-=[]{}|;':\",.<>?/";

  @Override
  public boolean validate(String password) {
    if (password == null) return false;
    return password.chars()
        .mapToObj(c -> (char) c)
        .anyMatch(c -> SPECIALS.indexOf(c) >= 0);
  }

  @Override
  public String getErrorMessage() {
    return "Password must contain at least one special character";
  }
}