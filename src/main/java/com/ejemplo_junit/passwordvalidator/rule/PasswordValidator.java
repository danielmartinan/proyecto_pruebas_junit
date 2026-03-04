package com.ejemplo_junit.passwordvalidator.rule;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

  private final List<PasswordRule> rules = new ArrayList<>();

  public PasswordValidator addRule(PasswordRule rule) {
    rules.add(rule);
    return this;
  }

  public ValidationResult validate(String password) {

    List<String> errors = new ArrayList<>();

    for (PasswordRule rule : rules) {
      if (!rule.validate(password)) {
        errors.add(rule.getErrorMessage());
      }
    }

    return new ValidationResult(errors.isEmpty(), errors);
  }
}