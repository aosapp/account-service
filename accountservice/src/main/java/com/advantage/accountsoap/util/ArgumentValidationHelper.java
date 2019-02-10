package com.advantage.accountsoap.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * A helper class for validations on method arguments.
 */
public abstract class ArgumentValidationHelper {

    private static final String STATUS_ERROR_CITY_VALUE = "ERROR. City value not valid";
    private static final String STATUS_ERROR_STATE_VALUE = "ERROR. State value not valid";
    private static final String STATUS_ERROR_POSTAL_VALUE = "ERROR. Postal value valid";
    private static final String STATUS_ERROR_ADDRESS_LINE1 = "ERROR. Address value not valid";
    private static final String STATUS_ERROR_NAME_VALUE = "ERROR. Name Value not valid, Use 2 character or longer";
    private static final int COUNTRY_PATTERN = 10;
    private static final int ADDRESS_LINE_PATTERN = 50;
    private static final int STATE_PATTERN = 10;
    private static final int NAME_MIN_PATTERN = 2;
    private static final int NAME_MAX_PATTERN = 30;
    private static final int CITY_PATTERN = 25;
    private static final int POSTALCODE_PATTERN = 10;

    private static final String ARGUMENT_INFORMATIVE_NAME = "argument informative name";

    private ArgumentValidationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Validate that the given argument does not reference <b>null</b>.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the argument to check if it references <b>null</b>.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given argument to validate (first argument)
     *                                  references <b>null</b>, or if the given argument-informative-name argument references
     *                                  <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static void validateArgumentIsNotNull(final Object argument,
                                                 final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument == null) {

            final String messageString = ArgumentValidationHelper.getNullArgumentMessage(argumentInformativeName);
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given string argument does not reference <b>null</b>, and that it is
     * <b>not</b> a blank string.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the string argument to validate that it does not reference
     *                                <b>null</b>, and that it is <b>not</b> a blank string.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given argument to validate (first argument)
     *                                  references <b>null</b>, or if it <b>is</b> a blank string, or if the given
     *                                  argument-informative-name argument references <b>null</b>, or if it <b>is</b> a blank
     *                                  string.
     */
    public static void validateStringArgumentIsNotNullAndNotBlank(final String argument,
                                                                  final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);
        ArgumentValidationHelper.validateArgumentIsNotNull(argument, argumentInformativeName);
        final String trimmedArgument = argument.trim();
        final int trimmedArgumentLength = trimmedArgument.length();

        if (trimmedArgumentLength == 0) {

            final String messageString = ArgumentValidationHelper.getBlankStringArgumentMessage(argumentInformativeName);
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given collection argument does not reference <b>null</b>, and that
     * it is <b>not</b> an empty collection.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the collection argument to validate that it does not reference
     *                                <b>null</b>, and that it is <b>not</b> an empty collection.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given argument to validate (first argument)
     *                                  references <b>null</b>, or if it <b>is</b> an empty collection, or if the given
     *                                  argument-informative-name argument references <b>null</b>, or if it <b>is</b> a blank
     *                                  string.
     */
    public static void validateCollectionArgumentIsNotNullAndNotEmpty(final Collection<?> argument,
                                                                      final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);
        ArgumentValidationHelper.validateArgumentIsNotNull(argument,
                argumentInformativeName);

        if (argument.isEmpty()) {

            final StringBuilder message = new StringBuilder("Could not accept an empty collection for argument [");
            message.append(argumentInformativeName);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given array argument does not reference <b>null</b>, and that it is
     * <b>not</b> a zero length array.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the array argument to validate that it does not reference <b>null</b>,
     *                                and that it is <b>not</b> a zero length array.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given argument to validate (first argument)
     *                                  references <b>null</b>, or if it <b>is</b> a zero length array, or if the given
     *                                  argument-informative-name argument references <b>null</b>, or if it <b>is</b> a blank
     *                                  string.
     */
    public static void validateArrayArgumentIsNotNullAndNotZeroLength(final Object[] argument,
                                                                      final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);
        ArgumentValidationHelper.validateArgumentIsNotNull(argument,
                argumentInformativeName);

        if (argument.length == 0) {

            final StringBuilder message = new StringBuilder("Could not accept a zero length array for argument [");
            message.append(argumentInformativeName);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given number argument <b>is</b> positive.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the number argument to validate that it <b>is</b> positive.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given number argument to validate (first
     *                                  argument) <b>is not</b> positive, or if the given argument-informative-name argument
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static void validateNumberArgumentIsPositive(final int argument,
                                                        final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument <= 0) {

            final StringBuilder message = new StringBuilder("Could not accept a non positive value for argument [");
            message.append(argumentInformativeName);
            message.append("]: [");
            message.append(argument);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given number argument <b>is</b> positive.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the number argument to validate that it <b>is</b> positive.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given number argument to validate (first
     *                                  argument) <b>is not</b> positive, or if the given argument-informative-name argument
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static void validateLongArgumentIsPositive(final long argument,
                                                      final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument <= 0) {

            final StringBuilder message = new StringBuilder("Could not accept a non positive value for argument [");
            message.append(argumentInformativeName);
            message.append("]: [");
            message.append(argument);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given number argument <b>is</b> positive.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the number argument to validate that it <b>is</b> positive.
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given number argument to validate (first
     *                                  argument) <b>is not</b> positive, or if the given argument-informative-name argument
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     */
    public static void validateDoubleArgumentIsPositive(final double argument,
                                                        final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument <= 0) {

            final StringBuilder message = new StringBuilder("Could not accept a non positive value for argument [");
            message.append(argumentInformativeName);
            message.append("]: [");
            message.append(argument);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given number argument <b>is</b> positive.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the number argument to validate that it <b>is</b> positive or zero (0).
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given number argument to validate (first
     *                                  argument) <b>is not</b> zero or positive, or if the given argument-informative-name argument
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     *                                  <br/>
     *                                  Created by Binyamin Regev on 03/12/2015 - not to change method {@code validateNumberArgumentIsPositive}
     *                                  which is already in use.
     */
    public static void validateNumberArgumentIsPositiveOrZero(final long argument,
                                                              final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument < 0) {
            final StringBuilder message = new StringBuilder("Could not accept a value for argument which is not 0 and not positive [");
            message.append(argumentInformativeName);
            message.append("]: [");
            message.append(argument);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    /**
     * Validate that the given number argument <b>is</b> positive.
     * <br/>
     * <b>If no {@link IllegalArgumentException} was throw, than the validation has been
     * successful.</b>
     *
     * @param argument                the number argument to validate that it <b>is</b> positive or zero (0).
     * @param argumentInformativeName the informative name of the argument, to use for the
     *                                error message, if the validation didn't pass.
     * @throws IllegalArgumentException if the given number argument to validate (first
     *                                  argument) <b>is not</b> zero or positive, or if the given argument-informative-name argument
     *                                  references <b>null</b>, or if it <b>is</b> a blank string.
     *                                  <br/>
     *                                  Created by Binyamin Regev on 03/12/2015 - not to change method {@code validateNumberArgumentIsPositive}
     *                                  which is already in use.
     */
    public static void validateDoubleArgumentIsPositiveOrZero(final double argument,
                                                              final String argumentInformativeName) {

        ArgumentValidationHelper.validateArgumentInformativeNameArgument(argumentInformativeName);

        if (argument < 0) {
            final StringBuilder message = new StringBuilder("Could not accept a value for argument which is not 0 and not positive [");
            message.append(argumentInformativeName);
            message.append("]: [");
            message.append(argument);
            message.append("]");
            final String messageString = message.toString();
            throw new IllegalArgumentException(messageString);
        }
    }

    private static String getNullArgumentMessage(final String argumentInformativeName) {

        assert StringUtils.isNotBlank(argumentInformativeName);

        final StringBuilder message = new StringBuilder("Could not accept null for argument [");
        message.append(argumentInformativeName);
        message.append("]");
        return message.toString();
    }

    private static void validateArgumentInformativeNameArgument(final String argumentInformativeName) {

        if (argumentInformativeName == null) {

            final String messageString = ArgumentValidationHelper.getNullArgumentMessage(ArgumentValidationHelper.ARGUMENT_INFORMATIVE_NAME);
            throw new IllegalArgumentException(messageString);
        }

        final String trimmedArgumentInformativeName = argumentInformativeName.trim();
        final int trimmedArgumentInformativeNameLength = trimmedArgumentInformativeName.length();

        if (trimmedArgumentInformativeNameLength == 0) {

            final String messageString = ArgumentValidationHelper.getBlankStringArgumentMessage(ArgumentValidationHelper.ARGUMENT_INFORMATIVE_NAME);
            throw new IllegalArgumentException(messageString);
        }
    }

    private static String getBlankStringArgumentMessage(final String argumentInformativeName) {

        assert StringUtils.isNoneBlank(argumentInformativeName);

        final StringBuilder message = new StringBuilder("Could not accept a blank string for argument [");
        message.append(argumentInformativeName);
        message.append("]");
        return message.toString();
    }

    public static void validateFirstAndLastName(String firstLastName) {
        if(firstLastName != null && firstLastName.length()>0)
            if (firstLastName.length() < NAME_MIN_PATTERN || firstLastName.length() > NAME_MAX_PATTERN) {
                throw new IllegalArgumentException(STATUS_ERROR_NAME_VALUE);
            }
    }

    public static void validateCityName(String sity) {
        if(sity != null && sity.length()>0) {
            if (sity.length() > CITY_PATTERN) {
                throw new IllegalArgumentException(STATUS_ERROR_CITY_VALUE);
            }
        }
    }

    public static void validateStateProvice(String state) {
        if(state != null && state.length()>0) {
            if (state.length()> STATE_PATTERN) {
                throw new IllegalArgumentException(STATUS_ERROR_STATE_VALUE);
            }
        }
    }

    public static void validateAddress(String address) {
        if(address != null && address.length()>0) {
            if (address.length()> ADDRESS_LINE_PATTERN) {
                throw new IllegalArgumentException(STATUS_ERROR_ADDRESS_LINE1);
            }
        }
    }

    public static void validatePostalCode(String zipcode) {
        if(zipcode != null && zipcode.length()>0) {
            if (zipcode.length()> POSTALCODE_PATTERN) {
                throw new IllegalArgumentException(STATUS_ERROR_POSTAL_VALUE);
            }
        }
    }


}