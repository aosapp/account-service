CREATE OR REPLACE FUNCTION get_all_countries_with_sleep(p_sleep_for INTEGER)
   RETURNS text AS $$
DECLARE 
   countries 		TEXT 	DEFAULT '{ "countries": [ ';
   country_rec		RECORD;
   countries_cur	refcursor;
BEGIN

   RAISE INFO 'Current timestamp: %', timeofday()::TIMESTAMP;
   RAISE INFO 'Going to sleep for % seconds', p_sleep_for;
   
   -- Make PostgreSQL go to sleep for a few seconds
   PERFORM pg_sleep(p_sleep_for);
   
   RAISE INFO 'Woke up. Current timestamp: %', timeofday()::TIMESTAMP;

   -- Open the cursor
   OPEN countries_cur FOR SELECT country_id, name, iso_name, phone_prefix from country;
 
   LOOP
      -- fetch row into the film
      FETCH countries_cur INTO country_rec;

      -- exit when no more row to fetch
      EXIT WHEN NOT FOUND;

      -- build the output
      countries := countries || '{ "id": ' || country_rec.country_id || ', "name": "' || country_rec.name || '", "isoName": "' || country_rec.iso_name || '", "phonePrefix": ' || country_rec.phone_prefix || ' }, ';
      
   END LOOP;

   -- Remove last comma and close JSON array
   countries := LEFT(countries, LENGTH(countries) - 3) || '} ] }';
   
   -- Close the cursor
   CLOSE countries_cur;
 
   RETURN countries;
END; $$
 
LANGUAGE plpgsql STRICT;