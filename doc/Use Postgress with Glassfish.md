# Use Postgress with Glassfish

1. Once Postgress is installed, downlaod the JDBC 4.2 (for Java8) at this [address](https://jdbc.postgresql.org/) 

2. Put the downloaded file in `[glassfish_home]/glassfish/domains/domain1/lib/`

3. Go to the admin console of Glassfish. By default, it is localhost:4848

4. Go to `Resources/JDBC/Connection Pools`

5. Create a new Connection with name `PostgreSQLPool`, resource type `javax.sql.ConnectionPoolDataSource`, database vendor `PostgreSQL`

6. Hit next and for datasource classname select `org.postgresql.ds.PGConnectionPoolDataSource`

7. In the additional properties, fill the following lines : DatabaseName, Password, PortNumber = 5432, ServerName = localhost, User. User and password are corresponding to user that will access databasename.

8. Click finish and ping.

9. Now go to `Resources/JDBC Resources`, and create a JNDI.

10. Set the name to `jdbc/[databaseName]`, select the connection pool of postgresql

11. THEN edit the __default one, and select postgresql pool.

12. If this does not work, we invite you to contact us.
