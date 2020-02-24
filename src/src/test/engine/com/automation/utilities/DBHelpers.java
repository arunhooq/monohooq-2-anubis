package com.automation.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import com.automation.testengine.TestUtilities;
import partner.utils.Constants;
import sanctuary.utils.ReusableMethods;

public class DBHelpers
{
	private static HashMap<String, Object> setDBConfig(String env) throws Exception
	{
		HashMap<String, Object> dbConfig = new HashMap<>();

		try
		{
			dbConfig.put("hostname", ReusableMethods.strReadIniFile("BACKSTAGE-" +env+"-DB-DETAILS", "DATABASE_HOST"));
			dbConfig.put("port", ReusableMethods.strReadIniFile("BACKSTAGE-" +env+"-DB-DETAILS", "DATABASE_PORT"));
			dbConfig.put("username", ReusableMethods.strReadIniFile("BACKSTAGE-" +env+"-DB-DETAILS", "DATABASE_USER"));
			dbConfig.put("password", ReusableMethods.strReadIniFile("BACKSTAGE-" +env+"-DB-DETAILS", "DATABASE_PASS"));
			dbConfig.put("name", ReusableMethods.strReadIniFile("BACKSTAGE-" +env+"-DB-DETAILS", "DATABASE_DB"));
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}

		return dbConfig;
	}

	private static ResultSet getResultSet(String env, String query) throws Exception
	{
		ResultSet rs = null;

		HashMap<String, Object> dbConfig = setDBConfig(env);

		String hostName = dbConfig.get("hostname")+":"+dbConfig.get("port");
		String userName = dbConfig.get("username").toString();
		String password = dbConfig.get("password").toString();
		String dbName   = dbConfig.get("name").toString();

	    final String url = "jdbc:postgresql://"+hostName+"/"+dbName+"?user="+userName+"&password="+password+"";

		Connection conn = null;

        try
        {
        	DriverManager.setLoginTimeout(5);
        	conn  = DriverManager.getConnection(url);
    		Statement  st  = conn.createStatement();
            rs = st.executeQuery(query);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
        	conn.close();
        }
        return rs;
    }

	public static HashMap<String, String> getQueryResult(String env, String query) throws Exception
	{
		HashMap<String, String> result = new HashMap<>();
		String column_name = "title_id";

        try
        {
        	ResultSet rs = getResultSet(env, query);
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs.next() == false)
            {
            	result.put(column_name, Constants.NO_RESULT_FOUND);
            	return result;
            }
            else
            {
            	do
            	{
            		int numColumns = rsmd.getColumnCount();
            		for (int i=1; i<=numColumns; i++)
            		{
            			column_name = rsmd.getColumnName(i);
            			result.put(column_name, rs.getString(column_name));
            		}
            	}
            	while (rs.next());
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
