#!/bin/bash

# #start ssh
# service ssh start
# #connect ssh to localhost
# ssh localhost
# #start hdfs and yarn
# startdfs
# startyarn

#set relative path
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )

###
### For Script to stage data
###
#merge files into one json and move json to data_staged folder
cat $parent_path/data_unstaged/*.csv > $parent_path/data_staged/data_`ALLDATA`.csv
#delete contents from data_unstaged (ie leftover zip files, folders)
find $parent_path/data_unstaged/ -mindepth 1 -delete

#copy all files in data_staged to hdfs
hdfs dfs -put $parent_path/data_staged/* "/user/hive/warehouse/data_staged"
#modify permissions (full) on all moved files
hdfs dfs -chmod -R 777 /user/hive/warehouse/

#move the local files now in HDFS to data_processed folder
mv $parent_path/data_staged/data_* $parent_path/data_processed

