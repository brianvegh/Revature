## Local Data Storage Operations
- Place json data file in data_unstaged
    * If JSON is split in multiple files that is fine. Only place one JSON file (split or whole) per run.
- **Run dataStaginScript.sh**
  ____
 **Script performs the following functions:**
- Joins all parts of a split JSON file into one
- Copies the complete JSON to HDFS /user/hive/warehouse/data_staged
- Performs chmod 777 (full access) permissions on files moved to HDFS
- Moves local files to data_processed folder to prevent re-import
