package co.pba.verifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


/**
 * {@code @Sanitized} annotation is intended to be used in fields, parameters or local_variables of
 * type {@code String.class} to validate that no SQL code is in the payload of the variable that can
 * be injected into database queries.
 *
 * @author Alain Plana
 * @since 1.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({PARAMETER, FIELD, LOCAL_VARIABLE})
public @interface SQLiVerifiedElement {
}