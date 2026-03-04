package com.ejemplo_junit.passwordvalidator.rule;

public interface PasswordRule {

  boolean validate(String password);

  String getErrorMessage();
}