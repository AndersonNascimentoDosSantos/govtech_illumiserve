-- create a tablespace using system as normal
DECLARE
  -- Declare variables
  your_datafile VARCHAR2(4000);

  -- Declare cursor
  CURSOR c_datafiles IS
    SELECT FILE_NAME
    FROM dba_data_files
    WHERE rownum = 1;
BEGIN
  -- Open the cursor
  OPEN c_datafiles;

  -- Fetch the first row into the variable
  FETCH c_datafiles INTO your_datafile;

  -- Close the cursor
  CLOSE c_datafiles;

  -- Create the tablespace with the concatenated datafile path and filename
  EXECUTE IMMEDIATE 'CREATE TABLESPACE RM89390 DATAFILE ''' || your_datafile || 'RM89390.dbf'' SIZE 100M AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED';
END;


-- grant permission to connect and to create table
GRANT CREATE SESSION TO RM89390;
GRANT CREATE TABLE TO RM89390;


-- grant access to use the http module using sys as sysadmin
GRANT EXECUTE ON sys.utl_http TO RM89390;
