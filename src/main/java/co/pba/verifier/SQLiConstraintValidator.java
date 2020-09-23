package co.pba.verifier;

import java.lang.reflect.Field;

import static java.util.Objects.requireNonNull;

/**
 * {@code SanitizedConstraintValidator.class}
 */
public class SQLiConstraintValidator {

    private SQLiConstraintValidator() {
        //prevent instantiation
    }

    /**
     * Method to scan objects looking for {@link SQLiVerifiedElement} annotation
     * and verify possible sql injections in the values of the annotated fields
     *
     * @param value object value
     * @param <E>   object type
     * @throws SQLiValidationException if validation fails
     */
    public static <E> void validate(E value) throws SQLiValidationException {
        try {
            Class<?> clazz = requireNonNull(value).getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(SQLiVerifiedElement.class) && !isValid((String) field.get(value))) {
                    String ex = field.getName() + " with possible corrupt payload. Current data: " + field.get(value);
                    throw new SQLiValidationException(ex);
                }
            }
        } catch (IllegalAccessException e) {
            throw new SQLiValidationException(e.getMessage());
        }
    }

    /**
     * Receives the input value and decides whether it is valid or is not.
     *
     * @param value input
     * @return {@code Boolean} if the input is valid or not
     */
    public static boolean isValid(String value) {
        return Validator.valid(value);
    }
}