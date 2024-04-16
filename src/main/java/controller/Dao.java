package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * Class for MySQL connection and their methods
 */
public class Dao {
	protected String url       = "jdbc:mysql://localhost/FinalAssignment";
    protected String user      = "root";
    protected String password  = "password";
    protected String tableName = "BOOK";
    protected Connection connection; 
    protected HttpSession session;
    
    public void connect() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	connection = DriverManager.getConnection(url, user, password);
    }
    
    /**
     * Select all data from TABLE BOOK
     * 
     * @return Arraylist of all data which are stored in an arraylist.
     * @throws Exception
     */
    public ArrayList<ArrayList<String>> select() throws Exception {
    	
    	String sql = "SELECT * FROM " + tableName;
    	ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
    
    	connect();
    	PreparedStatement statement = connection.prepareStatement(sql);
    	ResultSet results = statement.executeQuery();
    	
    	while (results.next()) {
    		ArrayList<String> row = new ArrayList<String>();
			row.add(results.getString("JAN_CD"));
			row.add(results.getString("ISBN_CD"));
			row.add(results.getString("BOOK_NM"));
			row.add(results.getString("BOOK_KANA"));
			row.add(results.getString("PRICE"));
			row.add(results.getString("ISSUE_DATE"));
			row.add(results.getString("CREATE_DATETIME"));
			row.add(results.getString("UPDATE_DATETIME"));
			toReturn.add(row);
    	}
    	
    	return toReturn;
    }
    
    /**
     * Edit data which update the TABLE BOOK.
     * 
     * @return row count, or 0 if SQL statement returned nothing
     * @throws Exception
     */
    public int update(String isbnCD, String bookNM, String price, String JAN_CD) throws Exception {

    	String sql = "UPDATE " + tableName + " SET ";
    	if (!isbnCD.isBlank()) {
    		sql += "ISBN_CD=" + isbnCD + ", ";
    	}
    	if (!bookNM.isBlank()) {
    		sql += "BOOK_NM=\'" + bookNM + "\', ";
    		sql += "BOOK_KANA=\'" + getNewKANA(bookNM) + "\', ";
    	}
    	if (!price.isBlank() && Integer.parseInt(price) >= 0) {
    		sql += "PRICE=" + price + ", ";
    	}
    	sql += "UPDATE_DATETIME=CURRENT_TIMESTAMP" + " WHERE " + "JAN_CD=" + JAN_CD + ";";
    
    	connect();
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement.executeUpdate();
    }
    
    /**
     * Get KANA corresponds to inputStr.
     * Code obtained from https://tech.kurojica.com/archives/5291/
     * 
     * @param inputStr
     * @return ひらがな表記をカタカナに変換。その他はそのまま。
     */
    private String getNewKANA(String inputStr) {
    	
		StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < inputStr.length(); i++) {
	        char code = inputStr.charAt(i);
	        if ((code >= 0x3041) && (code <= 0x3093)) {
	          buf.append((char) (code + 0x60));
	        } else {
	          buf.append(code);
	        }
	    }
		return buf.toString();
    }
    
}
