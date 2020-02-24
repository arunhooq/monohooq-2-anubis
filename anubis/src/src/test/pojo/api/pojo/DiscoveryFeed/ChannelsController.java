package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.automation.testengine.ConfigDetails;

import api.pojo.DiscoveryFeed.ChannelsModel.Data;
import api.pojo.DiscoveryFeed.ChannelsModel.Datum;
import sanctuary.utils.ReusableMethods;

public class ChannelsController {

	/**
	 * @author mdafsarali
	 * @date 16 jan 2020
	 * @apiNote This method will provide Channels Object.
	 * @return
	 * @throws Exception
	 */

	private static ChannelsModel ChannelsControllerObj() throws Exception {

		String baseurl = "https://cdn-discover.hooq.tv/v1.6/";
		String endpoint = "discover/tv/channels?";
		String params = "region=" + ConfigDetails.country.toUpperCase()
				+ "&page=1&perPage=40&locale=en&enableAdPodding=true&userAccessLevel="
				+ ReusableMethods.getUserAccessLevel(ConfigDetails.userType);

		String response = ReusableMethods.getAPI(baseurl + endpoint + params);

		JSONParser parser = new JSONParser();
		JSONObject parentObj = (JSONObject) parser.parse(response);

		JSONArray parentobjArray = (JSONArray) parentObj.get("data");

		ChannelsModel obj = new ChannelsModel();
		List<ChannelsModel.Data> data = new ArrayList<ChannelsModel.Data>();

		ChannelsModel.Data channels;
		for (int i = 0; i < parentobjArray.size(); i++) {
			JSONObject finalobj = (JSONObject) parentobjArray.get(i);
			channels = new ChannelsModel().new Data();

			channels.setRowName((ContentDetailsController.getJSONValueIfKeyExists(finalobj, "row_name").toString()));
			channels.setRowId((ContentDetailsController.getJSONValueIfKeyExists(finalobj, "row_id").toString()));
			channels.setRegion((ContentDetailsController.getJSONValueIfKeyExists(finalobj, "region").toString()));
			channels.setType((ContentDetailsController.getJSONValueIfKeyExists(finalobj, "type").toString()));

			JSONArray childDataObj = (JSONArray) finalobj.get("data");
			ChannelsModel.Datum datum;
			List<ChannelsModel.Datum> datumarray = new ArrayList<Datum>();
			for (int j = 0; j < childDataObj.size(); j++) {
				datum = new ChannelsModel().new Datum();
				JSONObject datumObj = (JSONObject) childDataObj.get(j);
				datum.setId(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "type").toString());
				datum.setTitle(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "title").toString());
				datum.setIsPremium(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "isPremium").toString());
				datum.setId(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "avod").toString());
				datum.setId(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "adLink").toString());
				datum.setId(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "adFreqCap").toString());
				datum.setId(ContentDetailsController.getJSONValueIfKeyExists(datumObj, "provider").toString());
				datumarray.add(datum);
			}
			channels.setData(datumarray);
			data.add(channels);
		}
		obj.setData(data);
		return obj;
	}

	public static List<String> getChannelsList(String channelType) {
		List<String> channelsList = new ArrayList<String>();
		try {
			List<Data> channelObj = ChannelsControllerObj().getData();
			for (Data dataobj : channelObj) {
				String rowName = dataobj.getRowName();
				if (rowName.equalsIgnoreCase(channelType)) {
					List<Datum> datum = dataobj.getData();
					for (Datum childObj : datum) {
						String title = childObj.getTitle();
						channelsList.add(title);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelsList;
	}

	public static List<String> getAllChannelsList() {
		List<String> channelsList = new ArrayList<String>();
		try {
			List<Data> channelObj = ChannelsControllerObj().getData();
			for (Data dataobj : channelObj) {
				List<Datum> datum = dataobj.getData();
				for (Datum childObj : datum) {
					String title = childObj.getTitle();
					channelsList.add(title);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelsList;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getChannelsList(""));
	}

}
