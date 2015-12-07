package dbConnections;

/**
 * 
 * @author Milan Zelenka
 * Builds a DbManager class object. Enables to make some of its parameters optional.
 *
 */
public class DbManagerBuilder {
	// Mandatory parameters
	private DbManager.DbType dbType;
	private String URL;
	// Optional parameters
	private String port = null;
	private String username = null;
	private String password = null;
	private String dbName = null;
	
	/**
	 * 
	 * @param dbType Enum from  DbManager specifying what database you are connecting to
	 * @param URL URL to the database. Make sure the string starts with '//' 
	 * if the database is not a file on local filesystem
	 */
	public DbManagerBuilder(DbManager.DbType dbType, String URL) {
		this.dbType = dbType;
		this.URL = URL;
	}
	
	public DbManager buildDbManager() throws Exception {
		return new DbManager(dbType, URL, port, username, password, dbName);
	}
	
	public DbManagerBuilder port(String port) {
		this.port = port;
		return this;
	}
	
	public DbManagerBuilder credentials(String username, String password) {
		this.username = username;
		this.password = password;
		return this;
	}
	
	public DbManagerBuilder dbName(String dbName) {
		this.dbName = dbName;
		return this;
	}
}




