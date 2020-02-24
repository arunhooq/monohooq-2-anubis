package api.pojo.DiscoveryFeed;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.automation.reports.ReporterLog;
import com.automation.testengine.ConfigDetails;

import api.pojo.DiscoveryFeed.ContentDetailsModel.Data;
import sanctuary.utils.ReusableMethods;

public class ContentDetailsController {

	@SuppressWarnings("unchecked")
	public static ContentDetailsModel getContentdetailsObject(String contentId) {
		
		ContentDetailsModel content = null ;
		ContentDetailsModel.Data data =null;
		try {
			String baseurl = "https://cdn-discover.hooq.tv/v1.5/";
			String endpoint = "discover/titles/";
			// String params ="1191b8a6-63d6-4f9e-81e6-527a69d88c41";

			String response = ReusableMethods.getAPI(baseurl + endpoint + contentId);

			JSONParser parser = new JSONParser();
			JSONObject parentObj = (JSONObject) parser.parse(response);

			JSONObject dataObj = (JSONObject) parentObj.get("data");
			content = new ContentDetailsModel();
			data = content.new Data();
			content.setData(data);
			data.setId(getJSONValueIfKeyExists(dataObj, "id").toString());
			data.setDescription(getJSONValueIfKeyExists(dataObj, "description").toString());
			data.setAs(getJSONValueIfKeyExists(dataObj, "as").toString());
			data.setAvailability(getJSONValueIfKeyExists(dataObj, "availability").toString());
			data.setBroadcast_date(getJSONValueIfKeyExists(dataObj, "broadcast_date").toString());
			data.setCreated_at(getJSONValueIfKeyExists(dataObj, "created_at").toString());
			data.setDownloadable(Boolean.parseBoolean(getJSONValueIfKeyExists(dataObj, "downloadable").toString()));
			data.setExpires_on(getJSONValueIfKeyExists(dataObj, "expires_on").toString());
			data.setPilot(Boolean.parseBoolean(getJSONValueIfKeyExists(dataObj, "pilot").toString()));
			data.setRegion(getJSONValueIfKeyExists(dataObj, "region").toString());
			data.setRunning_time(getJSONValueIfKeyExists(dataObj, "running_time").toString());
			data.setSeasons(getJSONValueIfKeyExists(dataObj, "seasons").toString());
			data.setShort_description(getJSONValueIfKeyExists(dataObj, "short_description").toString());
			data.setStreamable(Boolean.parseBoolean(getJSONValueIfKeyExists(dataObj, "streamable").toString()));
			data.setTitle(getJSONValueIfKeyExists(dataObj, "title").toString());
			data.setRunning_time_friendly(getJSONValueIfKeyExists(dataObj, "running_time_friendly").toString());
			data.setRunning_time_player(getJSONValueIfKeyExists(dataObj, "running_time_player").toString());
			data.setSeasonList(getJSONValueIfKeyExists(dataObj, "seasonList").toString());
			data.setContent_level(getJSONValueIfKeyExists(dataObj, "content_level").toString());

			ContentDetailsModel.Meta meta = content.new Meta();
			
			meta.setAgeRating(getJSONValueIfKeyExists((JSONObject) dataObj.get("meta"), "ageRating").toString());
			meta.setReleaseYear(getJSONValueIfKeyExists((JSONObject) dataObj.get("meta"), "releaseYear").toString());
			content.setMeta(meta);
			// Language
			data.setLanguages((JSONArray)getJSONValueIfKeyExists(dataObj, "languages"));

			// audio
			data.setAudios((JSONArray)getJSONValueIfKeyExists(dataObj, "audios"));
			
		} catch (Exception e) {
			//ReporterLog.failAndContinue("Content Details API ",
				//	"Failed at Content Details API for Content :  and excpetion is : " + e.getLocalizedMessage());
		}
		return content;
	}

	public static Object getJSONValueIfKeyExists(JSONObject childObj, String key) {
		Object obj = "";
		try {

			if (childObj.containsKey(key)) {
				obj = childObj.get(key);
				if (obj == null) {
					obj = "";
				}
			}
		} catch (Exception e) {

		}
		return obj;
	}

	public static void main(String[] args) throws Exception {
		
		ConfigDetails.country="SG";
		ContentDetailsModel contentdetails =DiscoverFeedController.getMovieContentDetails("Salt");
		System.out.println("Title : "+contentdetails.getData().getDescription());
		
	}

}
