package sanctuary.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;


public class DBhelpers extends ApiConfigDetails{
	
		
	@SuppressWarnings("unchecked")
	public static JSONObject dbconnect(String query,String country) throws SQLException {

		setConfigDetails();
		
		String hostName = ApiConfigDetails.db_hostname+":"+ApiConfigDetails.db_port;
		String userName = ApiConfigDetails.db_username;
		String password = ApiConfigDetails.db_password;
		String dbName   = ApiConfigDetails.db_name;

        DriverManager.registerDriver(new org.postgresql.Driver());
	    final String url = "jdbc:postgresql://"+hostName+"/"+dbName+"?user="+userName+"&password="+password+"";
          	
		Connection conn = null;  		
		JSONObject obj = new JSONObject();
		
        try 
        {
        	DriverManager.setLoginTimeout(5);
        	conn  = DriverManager.getConnection(url);
    		System.out.println("Connected to the PostgreSQL server successfully.");
    		Statement  st  = conn.createStatement();
            ResultSet rs = st.executeQuery(query);	            
            ResultSetMetaData rsmd = rs.getMetaData();
	           
            while(rs.next())
            {
                int numColumns = rsmd.getColumnCount();
                for (int i=1; i<=numColumns; i++) 
                {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                }
            }
	            
            System.out.println(obj);	            
        		            	            
        }        
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        finally 
        {	        	
        	conn.close();	        	
        }
 
        return obj;
    
    }
	
	
	@SuppressWarnings("unchecked")
	public static JSONObject dbconnect(String query,String hostName,String dbName) throws SQLException {

		setConfigDetails();
		
		String hName = hostName+":"+ApiConfigDetails.db_port;
		String userName = ApiConfigDetails.db_username;
		String password = ApiConfigDetails.db_password;
		String dName   = dbName;
			
	    final String url = "jdbc:postgresql://"+hName+"/"+dName+"?user="+userName+"&password="+password+"";
          	
		Connection conn = null;  		
		JSONObject obj = new JSONObject();
		
        try 
        {
        	DriverManager.setLoginTimeout(5);
        	conn  = DriverManager.getConnection(url);
    		System.out.println("Connected to the PostgreSQL server successfully.");
    		Statement  st  = conn.createStatement();
            ResultSet rs = st.executeQuery(query);	            
            ResultSetMetaData rsmd = rs.getMetaData();
	           
            while(rs.next())
            {
                int numColumns = rsmd.getColumnCount();
                for (int i=1; i<=numColumns; i++) 
                {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                }
            }
	            
            System.out.println(obj);	            
        		            	            
        }        
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        finally 
        {	        	
        	conn.close();	        	
        }
 
        return obj;
    
    }
	
}
