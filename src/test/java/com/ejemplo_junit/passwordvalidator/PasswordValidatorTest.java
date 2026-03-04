package com.ejemplo_junit.passwordvalidator;

import com.ejemplo_junit.passwordvalidator.rule.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

  private PasswordValidator validator;

  @BeforeEach
  void setUp() {
    validator = new PasswordValidator()
        .addRule(new MinLengthRule(8))
        .addRule(new UppercaseRule())
        .addRule(new NumberRule())
        .addRule(new SpecialCharacterRule());
  }

  @Test
  @DisplayName("Debe validar una contraseña correcta")
  void shouldValidateCorrectPassword() {
    ValidationResult result = validator.validate("Password1!");

    assertAll(
        () -> assertTrue(result.isValid()),
        () -> assertEquals(0, result.getErrors().size())
    );
  }

  @Nested
  @DisplayName("Pruebas de longitud mínima")
  class MinLengthTests {

    @ParameterizedTest
    @ValueSource(strings = {"A1!", "Short1!"})
    @DisplayName("Debe fallar cuando no cumple longitud mínima")
    void shouldFailWhenTooShort(String password) {
      ValidationResult result = validator.validate(password);

      assertFalse(result.isValid());
      assertTrue(result.getErrors().contains(
          "Password must be at least 8 characters long"));
    }
  }

  @Nested
  @DisplayName("Pruebas de mayúscula")
  class UppercaseTests {

    @Test
    void shouldFailWhenNoUppercase() {
      ValidationResult result = validator.validate("password1!");

      assertFalse(result.isValid());
      assertTrue(result.getErrors().contains(
          "Password must contain at least one uppercase letter"));
    }
  }

  @Nested
  @DisplayName("Pruebas de número")
  class NumberTests {

    @Test
    void shouldFailWhenNoNumber() {
      ValidationResult result = validator.validate("Password!");

      assertFalse(result.isValid());
      assertTrue(result.getErrors().contains(
          "Password must contain at least one number"));
    }
  }

  @Nested
  @DisplayName("Pruebas de carácter especial")
  class SpecialCharacterTests {

    @Test
    void shouldFailWhenNoSpecialCharacter() {
      ValidationResult result = validator.validate("Password1");

      assertFalse(result.isValid());
      assertTrue(result.getErrors().contains(
          "Password must contain at least one special character"));
    }
  }

  @Test
  @DisplayName("Debe acumular múltiples errores")
  void shouldReturnMultipleErrors() {
    ValidationResult result = validator.validate("pass");

    assertAll(
        () -> assertFalse(result.isValid()),
        () -> assertEquals(4, result.getErrors().size())
    );
  }

  @Test
  @DisplayName("Debe fallar cuando la contraseña es null")
  void shouldFailWhenPasswordIsNull() {
    ValidationResult result = validator.validate(null);

    assertFalse(result.isValid());
    assertEquals(4, result.getErrors().size());
  }
}