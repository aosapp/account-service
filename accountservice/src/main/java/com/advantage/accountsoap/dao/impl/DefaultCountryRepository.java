package com.advantage.accountsoap.dao.impl;

import com.advantage.accountsoap.dao.AbstractRepository;
import com.advantage.accountsoap.dao.CountryRepository;
import com.advantage.accountsoap.dto.country.CountryStatusResponse;
import com.advantage.accountsoap.model.Country;
import com.advantage.accountsoap.util.ArgumentValidationHelper;
import com.advantage.accountsoap.util.JPAQueryHelper;
import com.advantage.accountsoap.util.fs.FileSystemHelper;
import com.advantage.common.Constants;
import com.advantage.accountsoap.util.RestApiHelper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.IOException;
import java.util.*;

@Component
@Qualifier("countryRepository")
@Repository
public class DefaultCountryRepository extends AbstractRepository implements CountryRepository {

    private CountryStatusResponse countryStatusResponse;

    @Override
    public Country createCountry(String name, int phonePrefix) {
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(name, "country name");
        ArgumentValidationHelper.validateNumberArgumentIsPositive(phonePrefix, "phone prefix");

        final Country country = new Country(name, phonePrefix);
        entityManager.persist(country);

        countryStatusResponse = new CountryStatusResponse(true, "New user created successfully.", country.getId());

        return country;
    }

    @Override
    public Country createCountry(String name, String isoName, int phonePrefix) {
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(name, "country name");
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(isoName, "ISO name");
        ArgumentValidationHelper.validateNumberArgumentIsPositive(phonePrefix, "phone prefix");

        final Country country = new Country(name, isoName, phonePrefix);

        ArgumentValidationHelper.validateArgumentIsNotNull(country, "Country is null");

        entityManager.persist(country);

        return country;
    }

    @Override
    public CountryStatusResponse create(String name, int phonePrefix) {
        Country country = createCountry(name, phonePrefix);

        if (country == null) {
            //  Country was not created
            return countryStatusResponse;
        }

        return new CountryStatusResponse(true, "Country created successfully", country.getId());
    }

    @Override
    public CountryStatusResponse create(String name, String isoName, int phonePrefix) {
        Country country = createCountry(name, isoName, phonePrefix);

        if (country == null) {
            //  Country was not created
            return countryStatusResponse;
        }

        return new CountryStatusResponse(true, "Country created successfully", country.getId());
    }

    /**
     * For testing the {@code CSV} file is here: <b>C:/Users/regevb/Downloads/countries_20150630.csv</b>.
     * <b><ul>CSV File Format:</ul></b>
     * <b>Column A</b> <ul>SKIP</ul>. Country name in Hebrew, need correct code-page.
     * <b>Column B</b> Country name in English.
     * <b>Column C</b> Country ISO name, the way it will be in URL address. e.g. &quot;www.gov.il&quot;
     * <b>Column D</b> <ul>SKIP</ul>. Country international phone prefix. e.g. Israel's 972, Ukraine's 380, etc.
     *
     * @return number of rows inserted into Country table.
     */
    @Override
    public int fillCountryTable(final String csvFilePath) {

        //Country country;
        String csvSplitBy = ",";    // use comma as separator
        ClassPathResource filePathCSV = new ClassPathResource("/Users/regevb/Downloads/countries_20150630.csv");
        String protocol = this.getClass().getResource("").getProtocol();
        List<String> countries = null;
        //List<String> countries = Country.readFileCsv("/Users/regevb/Downloads/countries_20150630.csv");
        if(protocol.contains("jar")){
            try {
                countries = FileSystemHelper.readFileCsv(null, true, filePathCSV.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                countries = FileSystemHelper.readFileCsv(filePathCSV.getFile().getAbsolutePath(), false, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (countries == null) {
            return 0;
        }

        for (String str : countries) {
            String[] substrings = str.split(csvSplitBy);

            System.out.println("Country: " + substrings[1] +
                    Constants.SPACE + substrings[2] +
                    Constants.SPACE + substrings[3]);

            //country = createCountry(substrings[1], substrings[2], (int)Integer.valueOf(substrings[3]));
            createCountry(substrings[1], substrings[2], (int) Integer.valueOf(substrings[3]));


        }

        return countries.size();
    }

    /**
     * <b>DELETE</b> list of countries by IDs in {@link Collection} of {@link Integer}.
     *
     * @param countryIds {@link Collection} of {@link Integer} containing {@code countryId}s to delete.
     * @return {@code int} 1 when successful.
     */
    @Override
    public int deleteCountriesByIds(Collection<Long> countryIds) {
        ArgumentValidationHelper.validateCollectionArgumentIsNotNullAndNotEmpty(countryIds, "countries IDs");

        for (Long countryId : countryIds) {
            final String hql = JPAQueryHelper.getDeleteByPkFieldQuery(Country.class,
                    Country.FIELD_ID,
                    countryId);

            Query query = entityManager.createQuery(hql);

            query.executeUpdate();
        }

        return 1;
    }

    /**
     * <b>DELETE</b> list of countries, names are in {@link Collection} of {@link String}.
     *
     * @param names {@link Collection} of {@link String} containing names of countries to delete.
     * @return {@code int} result of <b>DELETE</b>.
     */
    @Override
    public int deleteCountriesByNames(Collection<String> names) {

        ArgumentValidationHelper.validateCollectionArgumentIsNotNullAndNotEmpty(names,
                "countries names");

        final int namesCount = names.size();
        final Collection<Long> countryIds = new ArrayList<>(namesCount);

        for (final String name : names) {

            final long countryId = this.getCountryIdByName(name);
            countryIds.add(countryId);
        }

        return deleteCountriesByIds(countryIds);

    }

    /**
     * Get {@code countryId} value by country name.
     *
     * @param countryName Name of the county which we are interested to get value of {@code countryId}.
     * @return {@link Integer} value of {@code countryId}.
     */
    @Override
    public long getCountryIdByName(String countryName) {
        ArgumentValidationHelper.validateStringArgumentIsNotNullAndNotBlank(countryName, "country name");

        final Query query = entityManager.createNamedQuery(Country.QUERY_GET_BY_COUNTRY_NAME);

        query.setParameter(Country.PARAM_COUNTRY_NAME, countryName);

        @SuppressWarnings("unchecked")

        List<Country> countries = query.getResultList();

        final long countryId;

        if (countries.isEmpty()) {

            countryId = -1;
        } else {

            countryId = countries.get(0).getId();
        }

        return countryId;
    }

    /**
     * Get {@link List} of all countries (about 243 in total). <br/>
     * <b>DO NOT</b> limit the number of countries retrieved.
     *
     * @return {@link List} of {@code Country}.
     */
    @Override
    public List<Country> getAllCountries() {
        String configSlowDBCall = RestApiHelper.getDemoAppConfigParameterValue("DB_call_delay");
        List<Country> countries = new ArrayList<>();

        if (configSlowDBCall.equals("0")) {
            countries = entityManager.createNamedQuery(Country.QUERY_GET_ALL, Country.class)
                    .getResultList();
        } else {
            String jsonCountries = this.getAllCountriesWithSleep(Integer.parseInt(configSlowDBCall))
                    .replaceAll("\\t", "")
                    .replaceAll("\\n", "");

            if (! jsonCountries.isEmpty()) {
                try {
                    JsonNode node = new ObjectMapper()
                            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                            .readValue(jsonCountries, JsonNode.class);

                    JsonNode countriesNode = node.get("countries");
                    for (int i = 0; i < countriesNode.size(); i++) {
                        JsonNode jsonNode = countriesNode.get(i);
                        Country country = new Country(jsonNode.get("name").asText(),
                                jsonNode.get("isoName").asText(),
                                jsonNode.get("phonePrefix").asInt());
                        country.setId(jsonNode.get("id").asLong());

                        countries.add(country);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int i = 0;
            }

        }
        return countries.isEmpty() ? null : countries;
    }

    /**
     * Retrieve all countries with name that starts with {@code partialName} and make
     * sure {@code partialName} ends with <i>PERCENT SIGN</i>. <br/>
     *
     * @param partialName Partial county name to match.
     * @return {@link List} or countries ({@link Country} class) with names starting
     * with {@code partialName}.
     */
    @Override
    public List<Country> getCountriesByPartialName(String partialName) {
        StringBuilder partialCountryName = new StringBuilder(partialName);

        //  if partial country name does not end with PERCENT-SIGN then add it. It's MANDATORY for QUERY with "LIKE" keyword.
        if (!partialName.endsWith(String.valueOf('%'))) {
            partialCountryName.append('%');
        }

        List<Country> countries = entityManager.createNamedQuery(Country.QUERY_GET_COUNTRIES_BY_PARTIAL_NAME, Country.class)
                .setParameter(Country.PARAM_COUNTRY_NAME, partialCountryName.toString())
                .setMaxResults(Country.MAX_NUM_OF_COUNTRIES)
                .getResultList();

        return countries.isEmpty() ? null : countries;
    }

    @Override
    public List<Country> getCountriesByPhonePrefix(int phonePrefix) {
        List<Country> countries = entityManager.createNamedQuery(Country.QUERY_GET_COUNTRIES_BY_PHONE_PREFIX, Country.class)
                .setParameter(Country.PARAM_PHONE_PREFIX, phonePrefix)
                .setMaxResults(Country.MAX_NUM_OF_COUNTRIES)
                .getResultList();

        return countries.isEmpty() ? null : countries;
    }

    @Override
    public Country getCountryIsoNameById(final Long countryId) {
        Country country = entityManager.createNamedQuery(Country.QUERY_GET_COUNTRY_BY_ID, Country.class)
                .setParameter(Country.PARAM_COUNTRY_ID, countryId.longValue())
                .setMaxResults(1)
                .getSingleResult();

        return country;
    }

    @Override
    public int delete(Country[] entities) {
        return 0;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Country get(Long entityId) {
        return entityManager.find(Country.class, entityId);
    }

    @Override
    public String getAllCountriesWithSleep(int seconds_to_sleep) {
        String statement = "SELECT * FROM public.get_all_countries_with_sleep(" + seconds_to_sleep + ")";

        String jsonString = (String) entityManager.createNativeQuery(statement)
                .getSingleResult();

        return jsonString;
    }
}
