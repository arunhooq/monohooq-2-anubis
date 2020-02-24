package partner.utils;

import com.automation.testengine.TestUtilities;

public class QueryCollection
{
	// List Valid brightcove_id on Sandbox env
	private static String brighcoveId = "'6097535056001','6017489074001','6102863336001','6036424275001', '5997190049001',"
			+ "'5990298332001','6084886060001','5991360037001','5991632462001','5991637077001','5997190049001'";
	private static String disneyLicensorId = "aa8e60d8-8011-466e-abc8-26fb891ccdbf";

	public static String getSVODTitle(String country, String type, String contentLevel)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN (SELECT id FROM titles WHERE region='%s' AND availability = 'SVOD' AND "
					+ "titles.as = '%s' AND streamable = true AND content_level = %s) AND bc_video_id IN(%s) LIMIT 1", country, type, contentLevel,brighcoveId);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getR21Title(String country)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN (SELECT id FROM titles WHERE region='%s' "
					+ "AND streamable = true AND meta ->> 'ageRating' = 'R21') AND bc_video_id IN(%s) LIMIT 1", country,brighcoveId);
		}
			catch (Exception e)
		{
		    TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getTVODTitle(String country)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN(SELECT title_id FROM titles_tvod WHERE title_id "
					+ "IN(SELECT id FROM titles WHERE region='%s' AND availability='TVOD' AND streamable=true)) AND bc_video_id IN(%s) LIMIT 1", country,brighcoveId);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getAVODTitle(String country)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN(SELECT title_id FROM titles_avod WHERE avod = true AND title_id IN"
					+ "(SELECT id FROM titles WHERE region='%s' AND streamable=true))", country);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getNonStreamableTitle(String country)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN (SELECT id FROM titles WHERE region='%s' "
					+ "AND streamable = false) LIMIT 1", country);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getDownloadableTitle(String country, boolean isDownloadable)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN (SELECT id FROM titles WHERE region='%s' "
					+ "AND downloadable = %s AND content_level = 10) LIMIT 1", country, String.valueOf(isDownloadable));
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getDisneyTitle(String country)
	{
		String query = "";
		try
		{
			query = String.format("SELECT title_id FROM title_mapping WHERE title_id IN (SELECT id FROM titles WHERE region='%s' AND availability = 'SVOD' "
					+ "AND streamable = true and lid = '%s') AND bc_video_id IN(%s) LIMIT 1", country,disneyLicensorId,brighcoveId);
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}

	public static String getTVTitle(String country, boolean isPremium)
	{
		String query = "";
		try
		{
			query = String.format("SELECT id as title_id FROM tv_channels WHERE country ='%s' AND is_premium IS %s AND enabled IS true LIMIT 1", country, String.valueOf(isPremium));
		}
		catch (Exception e)
		{
			TestUtilities.logReportFatal(e);
		}
		return query;
	}
}
