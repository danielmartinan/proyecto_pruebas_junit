package com.ejemplo_junit.passwordvalidator.rule;

public class NumberRule implements PasswordRule {

  @Override
  public boolean validate(String password) {
    if (password == null) return false;
    return password.chars().anyMatch(Character::isDigit);
  }

  @Override
  public String getErrorMessage() {
    return "Password must contain at least one number";
  }
}