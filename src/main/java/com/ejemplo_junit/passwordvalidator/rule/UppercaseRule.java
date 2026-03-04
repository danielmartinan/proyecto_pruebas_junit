package com.ejemplo_junit.passwordvalidator.rule;

public class UppercaseRule implements PasswordRule {

  @Override
  public boolean validate(String password) {
    if (password == null) return false;
    return password.chars().anyMatch(Character::isUpperCase);
  }

  @Override
  public String getErrorMessage() {
    return "Password must contain at least one uppercase letter";
  }
}