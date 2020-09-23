package co.pba.verifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SQLiConstraintValidatorTest {

    @org.junit.jupiter.api.Test
    void validate_testThatDetectInjectionInAnnotatedFields() throws SQLiValidationException {
        Tester tester = new Tester();
        tester.annotatedField = "first value with possible Select * from beta";
        assertThrows(SQLiValidationException.class, () -> SQLiConstraintValidator.validate(tester));

        tester.annotatedField = "first value with comments -- SQL comment";
        assertThrows(SQLiValidationException.class, () -> SQLiConstraintValidator.validate(tester));

        tester.annotatedField = "mock delete from beta where id='1'";
        assertThrows(SQLiValidationException.class, () -> SQLiConstraintValidator.validate(tester));

        tester.annotatedField = "Clean value";
        tester.nonAnnotatedField = "mock delete from beta where id='1'";
        assertDoesNotThrow(() -> SQLiConstraintValidator.validate(tester));
    }
}