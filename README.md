# hsqldb-test-scripts

- tdb.script contains a database using test data.
- ConnectDatabase.java just connects to the database and runs a simple SQL query.
- NewCustomer.java connects to the database and ads a new customer to the Customer table.
- Main.java and ResSet.java are unfinished and dont really do anything. They exist for personal testing purposes only.

Commands to start a server running the hsqldb database on a personal computer:

cd /home/kyy/hsqldb-2.7.2/hsqldb/
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/tdb --dbname.0 testdb

The first command just  cd's into the hsqldb files on my computer. The database is called testdb in this case, while the files belonging to it are called tdb. The name testdb is only used for running multiple databases under the same name or for logging into the database. All files regarding this particular database are named tdb in the /hsqldb/hsqldb/ folder.

After executing the commands above the database can be accessed by either executing the hsqldb.jar file in the /hsqldb/lib/ folder or by using java. To access the database using java you first have to set the hsqldb.jar as the java build path in your IDE of choice (Eclipse in my case). To connect to the database and to execute SQL queries in java just read the ConnectDatabase.java and the NewCustomer.java code.
