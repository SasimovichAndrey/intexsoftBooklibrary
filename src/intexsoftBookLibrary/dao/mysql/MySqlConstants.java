package intexsoftBookLibrary.dao.mysql;

import java.util.HashMap;

public class MySqlConstants {
	
	public static String sqlServer = null;
	public static String sqlDataBase = null;
	public static String sqlUserName = null;
	public static String sqlUserPass = null;
	public static final String sqlDriverName = "com.mysql.jdbc.Driver";
	
	/*public static final String bookTitleString = "bookTitle";
	public static final String bookAuthorString = "bookAuthor";
	public static final String bookIDString = "bookID";
	public static final String libraryIDString = "libraryID";
	public static final String libraryNameString = "libraryName";
	public static final String readerNameString = "readerName";
	public static final String readerIDString = "readerID";*/
	
	public static final String sqlAuthorFieldName = "author";
	public static final String sqlTitleFieldName = "title";
	public static final String sqlIssueDateFieldName = "issuedate";
	public static final String SQL_READER_FIELD_NAME = "reader";
	public static final String SQL_ID_IN_BOOKS_FIELD_NAME = "books.id";
	
	public static void Initialize(HashMap<String, String> propMap){
		sqlServer = propMap.get("mysqlserver");
		sqlDataBase = propMap.get("mysqldatabase");
		sqlUserName = propMap.get("mysqlusername");
		sqlUserPass = propMap.get("mysqlpass");
	}
}
