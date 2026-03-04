# Proyecto para pruebas de JUnit 5

## Descripción

Este proyecto es un ejemplo de cómo configurar y utilizar JUnit 5 para realizar pruebas unitarias en Java. Incluye ejemplos de pruebas básicas, pruebas parametrizadas y pruebas de excepciones. Está organizado como un pequeño validador de contraseñas compuesto por reglas reutilizables.

## Estructura del proyecto (resumen)

- `src/main/java` - Código de producción
  - `Main.java` - Programa de ejemplo/entry-point mínimo
  - `com.ejemplo_junit.passwordvalidator.rule` - Reglas y clases del validador
- `src/test/java` - Pruebas unitarias con JUnit 5
  - `com.ejemplo_junit.passwordvalidator.PasswordValidatorTest` - Pruebas del validador
- `pom.xml` - Configuración de Maven y dependencias

## Requisitos

- Java JDK 8+ (recomendado 11+)
- Maven 3.6+

## Cómo ejecutar

Desde la raíz del proyecto (donde está `pom.xml`) ejecutar:

```powershell
mvn test
```

Esto ejecutará las pruebas unitarias con JUnit 5 y mostrará el informe en la consola.

## Descripción de las clases

A continuación se describen brevemente las clases principales del paquete `com.ejemplo_junit.passwordvalidator.rule` y componentes relacionados.

- `PasswordRule` (interface)
  - Contrato para una regla de validación de contraseña. Define un método para comprobar si una contraseña cumple la regla y devolver un `ValidationResult`.

- `ValidationResult`
  - Representa el resultado de aplicar una regla: éxito/fracaso y mensaje asociado.

- `PasswordValidator`
  - Composición de múltiples `PasswordRule`. Permite validar una contraseña aplicando todas las reglas configuradas y agregando los resultados.
  - Uso típico: construir un validador añadiendo reglas (mínimo, mayúsculas, números, caracteres especiales, etc.) y ejecutar la validación sobre una cadena.

- `MinLengthRule`
  - Valida que la contraseña tenga al menos una longitud mínima configurada.

- `UppercaseRule`
  - Valida que la contraseña contenga al menos una letra mayúscula.

- `NumberRule`
  - Valida que la contraseña contenga al menos un dígito numérico.

- `SpecialCharacterRule`
  - Valida que la contraseña contenga al menos un carácter especial (según la implementación que se use).

- `Main`
  - Clase con `main` de ejemplo. Muestra un uso sencillo del `PasswordValidator` desde la línea de comandos o como demostración.

## Organización de las pruebas

Las pruebas se encuentran en `src/test/java` y están diseñadas para cubrir los distintos aspectos del validador:

- `PasswordValidatorTest` - Clase de pruebas principal que contiene subdivisiones (clases internas estáticas) para agrupar tests por regla: `MinLengthTests`, `UppercaseTests`, `NumberTests`, `SpecialCharacterTests`, etc. Cada grupo valida el comportamiento esperado (casos positivos y negativos y casos límite).

## Buenas prácticas y siguientes pasos

- Añadir pruebas parametrizadas para cubrir múltiples casos de forma concisa.
- Documentar claramente los requisitos de seguridad para la contraseña (longitud mínima, conjunto de caracteres especiales permitidos, etc.).
- Añadir un perfil de Maven para generar informes de cobertura (por ejemplo, usando JaCoCo) si se desea medir la cobertura.

## Contribuir

Si quieres contribuir:
1. Haz un fork y crea una rama nueva por cada feature o bugfix.
2. Asegúrate de que todos los tests pasen: `mvn test`.
3. Abre un pull request describiendo los cambios.