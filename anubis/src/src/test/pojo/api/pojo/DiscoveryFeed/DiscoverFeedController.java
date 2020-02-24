package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.ReadTestData;

import api.pojo.DiscoveryFeed.DiscoverFeedModel.Data;
import sanctuary.utils.ReusableMethods;

public class DiscoverFeedController {

	private static List<DiscoverFeedModel> DiscoverFeedControllerObj() throws Exception {

		// TODO - Need to enable for Stag as well and move this to config file..
		String baseurl = "https://cdn-discover.hooq.tv/v1.5/";
		String endpoint = "discover/feed?";
		String params = "region=" + ConfigDetails.country.toUpperCase()
				+ "&page=1&perPage=40&locale=en&enableAdPodding=true&userAccessLevel="
				+ ReusableMethods.getUserAccessLevel(ConfigDetails.userType) + "&platform="
				+ ReusableMethods.getPlatformName(ConfigDetails.project);

		String response = ReusableMethods.getAPI(baseurl + endpoint + params);

		JSONParser parser = new JSONParser();
		JSONObject parentObj = (JSONObject) parser.parse(response);

		JSONArray parentobjArray = (JSONArray) parentObj.get("data");
		List<DiscoverFeedModel> listofParentObj = new ArrayList<DiscoverFeedModel>();
		for (int i = 0; i < parentobjArray.size(); i++) {

			JSONObject finalobj = (JSONObject) parentobjArray.get(i);
			DiscoverFeedModel discoverModel = new DiscoverFeedModel();

			discoverModel.setRow_id(finalobj.get("row_id").toString());
			discoverModel.setRegion(finalobj.get("region").toString());
			discoverModel.setType(finalobj.get("order").toString());

			if (!(getJSONValueIfKeyExists(finalobj, "row_name") == null)) {
				discoverModel.setRow_name(getJSONValueIfKeyExists(finalobj, "row_name").toString());
			}

			discoverModel.setType(finalobj.get("type").toString());

			JSONArray childDataObj = (JSONArray) finalobj.get("data");

			List<DiscoverFeedModel.Data> discoverfeedChildDatalist = new ArrayList<>();
			if (!(childDataObj == null)) {
				for (int j = 0; j < childDataObj.size(); j++) {
					DiscoverFeedModel.Data data = new DiscoverFeedModel().new Data();
					JSONObject childObj = (JSONObject) childDataObj.get(j);

					data.setAs(getJSONValueIfKeyExists(childObj, "as").toString());
					data.setAudios(getJSONValueIfKeyExists(childObj, "audios").toString());
					data.setAvailability(getJSONValueIfKeyExists(childObj, "availability").toString());

					if (!(getJSONValueIfKeyExists(childObj, "content_level").toString().isEmpty()
							|| getJSONValueIfKeyExists(childObj, "content_level").toString() == "")) {
						data.setContent_level(
								Integer.parseInt(getJSONValueIfKeyExists(childObj, "content_level").toString()));
					}

					data.setId(getJSONValueIfKeyExists(childObj, "id").toString());
					// data.setImages((JSONArray) getJSONValueIfKeyExists(childObj, "images"));
					data.setLanguages(getJSONValueIfKeyExists(childObj, "languages"));
					data.setMeta(getJSONValueIfKeyExists(childObj, "meta"));

					if (!(getJSONValueIfKeyExists(childObj, "pilot").toString().isEmpty()
							|| getJSONValueIfKeyExists(childObj, "pilot").toString() == "")) {
						data.setPilot((boolean) getJSONValueIfKeyExists(childObj, "pilot"));
					}

					if (!(getJSONValueIfKeyExists(childObj, "name").toString().isEmpty()
							|| getJSONValueIfKeyExists(childObj, "name").toString() == "")) {
						data.setName(getJSONValueIfKeyExists(childObj, "name").toString());
					}

					if (!(getJSONValueIfKeyExists(childObj, "isPremium").toString().isEmpty()
							|| getJSONValueIfKeyExists(childObj, "isPremium").toString() == "")) {
						data.setPremium(getJSONValueIfKeyExists(childObj, "isPremium").toString());
					}

					data.setRegion(getJSONValueIfKeyExists(childObj, "region").toString());

					data.setRunning_time_player(getJSONValueIfKeyExists(childObj, "running_time_player").toString());
					data.setTitle(getJSONValueIfKeyExists(childObj, "title").toString());
					data.setRunning_time(getJSONValueIfKeyExists(childObj, "running_time").toString());

					// Set Data object for iteration
					discoverfeedChildDatalist.add(data);

				}
			}
			discoverModel.setData(discoverfeedChildDatalist);
			listofParentObj.add(discoverModel);
		}
		return listofParentObj;
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

	public static List<String> getTVODR21MovieList() {

		List<DiscoverFeedModel> obj;
		List<String> r21movie = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {
					if (o.getAs().contentEquals("MOVIE") && o.getAvailability().contentEquals("TVOD")) {
						if (!(o.getMeta() == "")) {
							JSONObject meta = (JSONObject) o.getMeta();
							if (meta.get("ageRating").toString().contains("R21")) {
								r21movie.add(o.getTitle());
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r21movie;
	}

	public static List<String> getSVODR21MovieList() {

		List<DiscoverFeedModel> obj;
		List<String> r21movie = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {

					if (o.getAs().contentEquals("MOVIE") && o.getAvailability().contentEquals("SVOD")) {
						if (!(o.getMeta() == "")) {
							JSONObject meta = (JSONObject) o.getMeta();
							if (meta.get("ageRating").toString().startsWith("18+")
									|| meta.get("ageRating").toString().startsWith("R21")
									|| meta.get("ageRating").toString().startsWith("R(US)")) {
								r21movie.add(o.getTitle());
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			TestUtilities.logReportFailure(e);

		}
		return r21movie;
	}

	public static List<String> getSVODNonR21MovieList() {

		List<DiscoverFeedModel> obj;
		List<String> r21movie = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {
					if (o.getAs().contentEquals("MOVIE")) {
						if (!(o.getMeta() == "")) {
							JSONObject meta = (JSONObject) o.getMeta();
							if (!(meta.get("ageRating").toString().contentEquals("R21")
									|| meta.get("ageRating").toString().contentEquals("NC16"))
									&& o.getAvailability().contentEquals("SVOD")) {
								r21movie.add(o.getTitle());
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r21movie;
	}

	public static List<String> getSVODNonR21MovieList(boolean isShortDuration) {

		List<DiscoverFeedModel> obj;
		List<String> r21movie = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {
					if (o.getAs().contentEquals("MOVIE")) {
						if (!(o.getMeta() == "")) {
							JSONObject meta = (JSONObject) o.getMeta();
							if (!(meta.get("ageRating").toString().contentEquals("R21")
									|| meta.get("ageRating").toString().contentEquals("NC16"))
									&& o.getAvailability().contentEquals("SVOD")) {
								if (isShortDuration) {

									if (Long.parseLong(o.getRunning_time()) < 1800000) {
										r21movie.add(o.getTitle());
									}
								} else {
									if (Long.parseLong(o.getRunning_time()) > 1800000) {
										r21movie.add(o.getTitle());
									}

								}

							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return r21movie;
	}

	public static List<String> getTVODNonR21MovieList() {

		List<DiscoverFeedModel> obj;
		List<String> r21movie = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {
					if (o.getAs().contentEquals("MOVIE")) {
						if (!(o.getMeta() == "")) {
							JSONObject meta = (JSONObject) o.getMeta();
							if (!meta.get("ageRating").toString().contentEquals("R21")
									&& o.getAvailability().contentEquals("TVOD")) {
								r21movie.add(o.getTitle());
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r21movie;
	}

	public static List<String> getTVShowList() {

		List<DiscoverFeedModel> obj;
		List<String> tvshows = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> dataobject = parent.getData();
				for (Data o : dataobject) {
					if (o.getAs().contentEquals("TVSHOW")) {
						tvshows.add(o.getTitle());
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tvshows;

	}

	public static List<String> getSpotlightContentList() {

		List<DiscoverFeedModel> obj;
		List<String> sptlight = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				if (parent.getType().startsWith("Multi-Title-Spotlight")) {

					List<Data> dataobject = parent.getData();
					for (Data o : dataobject) {
						sptlight.add(o.getTitle());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sptlight;

	}

	public static List<String> getQuickLinkList() {
		List<DiscoverFeedModel> obj;
		List<String> quicklinks = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				if (parent.getType().contains("Quicklinks")) {

					List<Data> dataobject = parent.getData();
					for (Data o : dataobject) {
						quicklinks.add(o.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quicklinks;
	}

	public static ContentDetailsModel getMovieContentDetails(String MovieName) {

		List<DiscoverFeedModel> obj;
		String id = "";
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> data = parent.getData();

				for (Data o : data) {

					if (o.getTitle().contains(MovieName) && o.getAs().contentEquals("MOVIE")) {

						id = o.getId().toString();
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ContentDetailsController.getContentdetailsObject(id);
	}

	public static ContentDetailsModel getTVShowContentDetails(String TvShowName) {

		List<DiscoverFeedModel> obj;
		String id = "";
		try {
			obj = DiscoverFeedControllerObj();

			outer: for (DiscoverFeedModel parent : obj) {

				List<Data> data = parent.getData();

				for (Data o : data) {

					if (o.getTitle().contains(TvShowName) && o.getAs().contentEquals("TVSHOW")) {

						id = o.getId().toString();
						break outer;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ContentDetailsController.getContentdetailsObject(id);
	}

	public static String getTVShowContentId(String TvShowName) {

		List<DiscoverFeedModel> obj;
		String id = "";
		try {
			obj = DiscoverFeedControllerObj();

			outer: for (DiscoverFeedModel parent : obj) {

				List<Data> data = parent.getData();

				for (Data o : data) {

					if (o.getTitle().contains(TvShowName) && o.getAs().contentEquals("TVSHOW")) {

						id = o.getId().toString();
						break outer;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public static List<String> getContentId(String contentName) {

		List<DiscoverFeedModel> obj;
		List<String> id = new ArrayList<String>();
		try {
			obj = DiscoverFeedControllerObj();

			for (DiscoverFeedModel parent : obj) {

				List<Data> data = parent.getData();

				for (Data o : data) {
					// System.out.println("From API "+o.getId().toString());
					if (o.getTitle().contains(contentName)) {
						id.add(o.getId().toString());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public static TVShowEpisodeModel getObjEpisodesForASeason(String tvShowName, String seasonNumber) throws Exception {

		// GetList of Episodes
		TVShowEpisodeModel tvshow = new TVShowEpisodeModel();
		ContentDetailsModel contentDetails = getTVShowContentDetails(tvShowName);
		String contentId = contentDetails.getData().getId();

		// Get Map of Season and Episodes
		String baseurl = "https://cdn-discover.hooq.tv/v1.5/discover/titles/";

		String response = ReusableMethods
				.getAPI(baseurl + contentId + "/episodes?season=" + seasonNumber + "&enableAdPodding=true");

		JSONParser parser = new JSONParser();
		JSONObject parentObj = (JSONObject) parser.parse(response);

		JSONArray parentObjdata = (JSONArray) parentObj.get("data");
		List<TVShowEpisodeModel.Data> dataArr = new ArrayList<TVShowEpisodeModel.Data>();

		for (int i = 0; i < parentObjdata.size(); i++) {
			JSONObject finalobj = (JSONObject) parentObjdata.get(i);
			TVShowEpisodeModel.Data data = tvshow.new Data();
			data.setDescription(ReusableMethods.getJSONValueIfKeyExists(finalobj, "description").toString());
			data.setTitle(ReusableMethods.getJSONValueIfKeyExists(finalobj, "title").toString());
			data.setDownloadable(Boolean
					.parseBoolean((ReusableMethods.getJSONValueIfKeyExists(finalobj, "downloadable").toString())));
			data.setStreamable(
					Boolean.parseBoolean(ReusableMethods.getJSONValueIfKeyExists(finalobj, "streamable").toString()));
			data.setSeason(Integer.parseInt((ReusableMethods.getJSONValueIfKeyExists(finalobj, "season").toString())));
			data.setEpisode(Integer.parseInt(ReusableMethods.getJSONValueIfKeyExists(finalobj, "episode").toString()));
			data.setMeta(ReusableMethods.getJSONValueIfKeyExists(finalobj, "meta").toString());
			data.setPilot(Boolean.parseBoolean(ReusableMethods.getJSONValueIfKeyExists(finalobj, "pilot").toString()));
			data.setRunning_time_friendly(
					ReusableMethods.getJSONValueIfKeyExists(finalobj, "running_time_friendly").toString());
			data.setRunning_time_player(
					(ReusableMethods.getJSONValueIfKeyExists(finalobj, "running_time_player").toString()));
			dataArr.add(data);
		}
		tvshow.setData(dataArr);

		return tvshow;

	}

	public static String getListOfSeasons(String tvShowName) {
		ContentDetailsModel contentDetails = getTVShowContentDetails(tvShowName);
		return contentDetails.getData().getSeasons();
	}

	public static List<String> getListOfEpisodes(String tvShowName, String seasonNumber) throws Exception {

		TVShowEpisodeModel tvshowObj = getObjEpisodesForASeason(tvShowName, seasonNumber);

		List<api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data> listOfData = tvshowObj.getData();
		List<String> episodes = new ArrayList<String>();

		for (api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data data : listOfData) {

			episodes.add(data.getTitle());
		}

		return episodes;
	}

	public static api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data getEpisodeObj(String tvShowName, String seasonNumber,
			String episodeName) throws Exception {

		TVShowEpisodeModel tvshowObj = getObjEpisodesForASeason(tvShowName, seasonNumber);

		List<api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data> listOfData = tvshowObj.getData();
		api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data dataobj = new TVShowEpisodeModel().new Data();
		for (api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data data : listOfData) {

			if (data.getTitle().contains(episodeName)) {
				dataobj = data;
				break;
			}
		}

		return dataobj;
	}

	public static void main(String[] args) throws Exception {
		ConfigDetails.country = "SG";

		// System.out.println(getListOfEpisodes("Supergirl","2"));
		//
		// api.pojo.DiscoveryFeed.TVShowEpisodeModel.Data b =
		// getEpisodeObj("Supergirl","2","Welcome To Earth");
		// List<String> tvShowContent =
		// DiscoverFeedController.getSpotlightContentList();
		// System.out.println(getSpotlightContentList());
		// System.out.println(getSpotlightContentList().size());

		// System.out.println(ContentDetailsController.getContentdetailsObject("").getData().getContent_level());

		System.out.println(getSVODNonR21MovieList(false));

	}

}
