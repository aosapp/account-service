package com.advantage.accountsoap.util;

/**
 * A helper class for JPA queries.
 */
public abstract class JPAQueryHelper {

    private JPAQueryHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get a HQL query for a deletion of a JPA entity, by it's primary key field.
     *
     * @param entityClass  the class of the JPA entity to delete.
     * @param pkFieldName  the name of the primary key field, of the entity to delete.
     * @param pkFieldValue (the value of) the primary key, of the entity to delete.
     * @return a HQL string.
     * @throws IllegalArgumentException if any one of the arguments references <b>null</b>, or
     *                                  if the primary key field name argument is a blank string, or if the primary key field
     *                                  value argument is a blank string.
     */
    public static String getDeleteByPkFieldQuery(final Class<?> entityClass,
                                                 final String pkFieldName, final Object pkFieldValue) {

        ArgumentValidationHelper.validateArgumentIsNotNull(entityClass, "entity class");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(pkFieldName,
                "pk field name");
        ArgumentValidationHelper.validateArgumentIsNotNull(pkFieldValue, "pk field value");
        final StringBuilder hql = new StringBuilder("DELETE FROM ");
        final String entityClassName = entityClass.getName();
        hql.append(entityClassName);
        hql.append(" WHERE ");
        hql.append(pkFieldName);
        hql.append(" = ");
        hql.append(pkFieldValue);
        return hql.toString();
    }

    /**
     * Get a HQL query for a deletion of JPA entities, by their primary key field.
     *
     * @param entityClass            the class of the JPA entities to delete.
     * @param pkFieldName            the name of the primary key field, of the entities to delete.
     * @param pkFieldValuesParamName the name of the parameter, to set in the query for the
     *                               primary key field values.
     * @return a HQL string.
     * @throws IllegalArgumentException if any one of the arguments references <b>null</b>, or
     *                                  if the primary key field name argument <b>is</b> a blank string, or if the primary key
     *                                  field values argument <b>is</b> a blank string.
     */
    public static String getDeleteByPkFieldsQuery(final Class<?> entityClass,
                                                  final String pkFieldName, final String pkFieldValuesParamName) {

        ArgumentValidationHelper.validateArgumentIsNotNull(entityClass, "entity class");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(pkFieldName,
                "pk field name");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(pkFieldValuesParamName,
                "pk field values param name");
        final StringBuilder hql = new StringBuilder("DELETE FROM ");
        final String entityClassName = entityClass.getName();
        hql.append(entityClassName);
        hql.append(" WHERE ");
        hql.append(pkFieldName);
        hql.append(" IN ( :");
        hql.append(pkFieldValuesParamName);
        hql.append(")");
        return hql.toString();
    }

    public static String getSelectByPkFieldQuery(final Class<?> entityClass,
                                                 final String pkFieldName, final Object pkFieldValue) {

        ArgumentValidationHelper.validateArgumentIsNotNull(entityClass, "entity class");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(pkFieldName,
                "pk field name");
        ArgumentValidationHelper.validateArgumentIsNotNull(pkFieldValue, "pk field value");
        final StringBuilder hql = new StringBuilder("FROM ");
        final String entityClassName = entityClass.getName();
        hql.append(entityClassName);
        hql.append(" WHERE ");
        hql.append(pkFieldName);
        hql.append(" = ");
        hql.append(pkFieldValue);
        return hql.toString();
    }

    public static String getSelectActiveByPkFieldQuery(final Class<?> entityClass,
                                                       final String pkFieldName,
                                                       final Object pkFieldValue) {

        ArgumentValidationHelper.validateArgumentIsNotNull(entityClass, "entity class");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(pkFieldName, "pk field name");
        ArgumentValidationHelper.validateArgumentIsNotNull(pkFieldValue, "pk field value");

        final String entityClassName = entityClass.getName();
        final StringBuilder hql = new StringBuilder("FROM ")
                .append(entityClassName)
                .append(" WHERE ")
                .append("UPPER(active)")
                .append(" = ")
                .append("'Y'")
                .append(" AND ")
                .append(pkFieldName)
                .append(" = ")
                .append(pkFieldValue);

        return hql.toString();
    }
}