package co.pba.verifier;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static co.pba.verifier.Constants.SQL_REGEXPS;
import static java.util.regex.Pattern.*;


/**
 * {@code Validator} is a validation class for {@code String} variables that potentially
 * can contains SQL code to inject in databases
 */
public class Validator {

    /**
     * Eval param received with existent regEx to check if contains sql patterns that can lead to Sql
     * Injection attacks
     *
     * @param s received value
     * @return {@code Boolean.class}
     */
    public static boolean valid(String s) {
        if (isNullOrEmpty.test(s)) return true;
        return patterns().get().noneMatch(pattern -> pattern.matcher(s).matches());
    }

    /**
     * Compiles {@code SQL_REGEXPS} existing regEx in {@link Constants} class
     *
     * @return {@code Stream<Pattern>} elements
     */
    private static Supplier<Stream<Pattern>> patterns() {
        return () -> SQL_REGEXPS.get().map(regEx -> compile(regEx, CASE_INSENSITIVE | UNICODE_CASE));
    }


    // Test if given String is null or empty
    public static final Predicate<String> isNullOrEmpty = s -> s == null || s.length() == 0;


    private Validator() {
        // Prevents Instantiation
    }
}