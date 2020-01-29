CREATE OR REPLACE FUNCTION truncate_account_tables()
  RETURNS TEXT AS $$
BEGIN

  RAISE INFO 'Current timestamp: %', timeofday()::TIMESTAMP;

  RAISE INFO 'Truncate ACCOUNT-SERVICE tables...';

  TRUNCATE TABLE account, country, paymentpreferences, shippingaddress RESTART IDENTITY CASCADE;

  RAISE INFO 'Truncate ACCOUNT-SERVICE tables...Done!';

  RAISE INFO 'Woke up. Current timestamp: %', timeofday()::TIMESTAMP;

  RETURN 'Completed Successfully';

END; $$

LANGUAGE plpgsql STRICT;