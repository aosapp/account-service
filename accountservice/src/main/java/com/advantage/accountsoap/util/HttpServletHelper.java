package com.advantage.accountsoap.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A helper class for the HTTP servlet API.
 */
public abstract class HttpServletHelper {

    private HttpServletHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Validate that the given request, contains all of the given parameters.
     *
     * @param request                       the request to check that it contains certain parameters.
     * @param considerBlankStringAsNotExist a flag for telling the method to treat a parameter
     *                                      blank with a blank string value, as a non existing parameter.
     * @param parameterNames                the parameter to check their existence in the given request.
     * @throws IllegalArgumentException if the given HTTP servlet request argument references
     *                                  <b>null</b>, or if the given array argument of parameter names references <b>null</b>,
     *                                  or if it <b>is</b> a zero length array.
     */
    public static void validateParametersExistenceInRequest(final HttpServletRequest request,
                                                            final boolean considerBlankStringAsNotExist, final String... parameterNames) {

        ArgumentValidationHelper.validateArgumentIsNotNull(request, "http servlet request");
        ArgumentValidationHelper.validateArrayArgumentIsNotNullAndNotZeroLength(parameterNames,
                "parameter names");
        final Collection<String> nonExistingParameters = new ArrayList<String>();

        for (final String parameterName : parameterNames) {

            final String parameterValue = request.getParameter(parameterName);

            if (parameterValue == null ||
                    (considerBlankStringAsNotExist && StringUtils.isBlank(parameterValue))) {

                nonExistingParameters.add(parameterName);
            }
        }

        if (nonExistingParameters.isEmpty() == false) {

            HttpServletHelper.generateMessageAndThrowError(nonExistingParameters);
        }
    }

    private static void generateMessageAndThrowError(final Collection<String> nonExistingParameters) {

        assert CollectionUtils.isNotEmpty(nonExistingParameters);

        final StringBuilder errorMessage = new StringBuilder("Missing mandatory parameter in HTTP request: [");
        final Iterator<String> nonExistingParametersIterator = nonExistingParameters.iterator();

        while (nonExistingParametersIterator.hasNext()) {

            final String parameterName = nonExistingParametersIterator.next();
            errorMessage.append("{");
            errorMessage.append(parameterName);
            errorMessage.append("}");

            if (nonExistingParametersIterator.hasNext()) {

                errorMessage.append(", ");
            }
        }

        errorMessage.append("]");
        final String errorMessageString = errorMessage.toString();
        throw new RuntimeException(errorMessageString);
    }
}