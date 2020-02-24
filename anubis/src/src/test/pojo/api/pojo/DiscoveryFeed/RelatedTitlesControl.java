package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sanctuary.utils.ReusableMethods;

public class RelatedTitlesControl {

	/**
	 * @author mdafsarali
	 * @date 22 jan 2020
	 * @description This method will return  lists of Object of Similar title contents for a particular content
	 * @param enterTitleName
	 * @return
	 */
	public static RelatedTitlesModel getSimilarTitleObject(String enterTitleName) {
		
		RelatedTitlesModel relatedTitles = new RelatedTitlesModel();
		try {
			String baseurl = "https://cdn-discover.hooq.tv/v1.6/";
			String endpoint = "discover/titles/";
			String params1 = DiscoverFeedController.getContentId(enterTitleName).get(0);
			String params2 = "/related?page=1&perPage=50&locale=eng";
			String response = ReusableMethods.getAPI(baseurl + endpoint + params1 + params2);
			
			JSONParser parser = new JSONParser();
			JSONObject parentObj = (JSONObject) parser.parse(response);

			JSONArray dataObj = (JSONArray) parentObj.get("data");	
			List<RelatedTitlesModel.Data> datumArray = new ArrayList<RelatedTitlesModel.Data>();
			
			RelatedTitlesModel.Data datumObj =null;
			
			
			for(int i=0 ;i<dataObj.size();i++) {
				datumObj = new RelatedTitlesModel().new Data();
				JSONObject childObj =((JSONObject)dataObj.get(i));
				
				datumObj.setTitle(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "title").toString());
				datumObj.setId(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "id").toString());
				datumObj.setAs(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "as").toString());
				datumObj.setRunningTime(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "running_time").toString());
				datumObj.setAvailability(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "availability").toString());
				datumObj.setContentLevel(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "content_level").toString());
				datumObj.setStreamable(Boolean.parseBoolean(DiscoverFeedController.getJSONValueIfKeyExists(childObj, "streamable").toString()));
				datumArray.add(datumObj);
			}
			relatedTitles.setData(datumArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatedTitles;
	}
	
	
	
}
