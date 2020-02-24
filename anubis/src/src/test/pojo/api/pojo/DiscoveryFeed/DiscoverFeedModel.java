package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFeedModel {
	
	 private String row_id;
	 private String row_name;
	 private String region;
	 private float order;
	 private String type;
	 List<Data> data ;
	 ContentDetails contentdetails;
	 
 

	 public String getRow_id() {
			return row_id;
		}
	 
		public void setRow_id(String row_id) {
			this.row_id = row_id;
		}

		public String getRow_name() {
			return row_name;
		}

		public void setRow_name(String row_name) {
			this.row_name = row_name;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public float getOrder() {
			return order;
		}

		public void setOrder(float order) {
			this.order = order;
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public List<Data> getData() {
			return data;
		}
		public void setData(List<Data> discoverfeedChildDatalist) {
			this.data = discoverfeedChildDatalist;
		}


	public  class Data{
		 
		 private String id;
		 private String title;
		 private String isPremium;
		 private boolean avod;
		 private String adLink;
		 private float adFreqCap;
		 private String provider;
		 private String catchUpCollectionId = null;
		 private String as;
		 private String region;
		 ArrayList < Object > images = new ArrayList < Object > ();
		 private String running_time;
		 private String availability;
		 private float content_level;
		 private boolean pilot;
		 ArrayList < Object > ImagesObject = new ArrayList < Object > ();
		 Object Meta ;
		 Object languages ;
		 Object audios ;
		 private String running_time_player;
		 private String running_time_friendly;
		 private String name;
		 
		 
		 
		 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public boolean isPremium() {
			return Boolean.parseBoolean(isPremium);
		}
		public void setPremium(String string) {
			this.isPremium = string;
		}
		public boolean isAvod() {
			return avod;
		}
		public void setAvod(boolean avod) {
			this.avod = avod;
		}
		public String getAdLink() {
			return adLink;
		}
		public void setAdLink(String adLink) {
			this.adLink = adLink;
		}
		public float getAdFreqCap() {
			return adFreqCap;
		}
		public void setAdFreqCap(float adFreqCap) {
			this.adFreqCap = adFreqCap;
		}
		public String getProvider() {
			return provider;
		}
		public void setProvider(String provider) {
			this.provider = provider;
		}
		public String getCatchUpCollectionId() {
			return catchUpCollectionId;
		}
		public void setCatchUpCollectionId(String catchUpCollectionId) {
			this.catchUpCollectionId = catchUpCollectionId;
		}
		public String getAs() {
			return as;
		}
		public void setAs(String as) {
			this.as = as;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public ArrayList<Object> getImages() {
			return images;
		}
		public void setImages(ArrayList<Object> images) {
			this.images = images;
		}
		public String getRunning_time() {
			return running_time;
		}
		public void setRunning_time(String running_time) {
			this.running_time = running_time;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}
		public float getContent_level() {
			return content_level;
		}
		public void setContent_level(float f) {
			this.content_level = f;
		}
		public boolean isPilot() {
			return pilot;
		}
		public void setPilot(boolean pilot) {
			this.pilot = pilot;
		}
		public ArrayList<Object> getImagesObject() {
			return ImagesObject;
		}
		public void setImagesObject(ArrayList<Object> imagesObject) {
			ImagesObject = imagesObject;
		}
		public Object getMeta() {
			return Meta;
		}
		public void setMeta(Object meta) {
			Meta = meta;
		}
		public Object getLanguages() {
			return languages;
		}
		public void setLanguages(Object object) {
			this.languages = object;
		}
		public Object getAudios() {
			return audios;
		}
		public void setAudios(Object audios) {
			this.audios = audios;
		}
		public String getRunning_time_player() {
			return running_time_player;
		}
		public void setRunning_time_player(String running_time_player) {
			this.running_time_player = running_time_player;
		}
		public String getRunning_time_friendly() {
			return running_time_friendly;
		}
		public void setRunning_time_friendly(String running_time_friendly) {
			this.running_time_friendly = running_time_friendly;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	}


	public static class ContentDetails{
	
		private String title;
		private float running_time;
		private String availability;
		private float content_level;
		private String ageRating;
		private String releaseYear;
		private String skip_update_image;
		private List<String> languages;
		private List<String> audios;
		private String running_time_player;
		private String running_time_friendly;
		private String pilot;
		private String id;
		private String as;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public float getRunning_time() {
			return running_time;
		}
		public void setRunning_time(float f) {
			this.running_time = f;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}
		public float getContent_level() {
			return content_level;
		}
		public void setContent_level(float f) {
			this.content_level = f;
		}
		public String getAgeRating() {
			return ageRating;
		}
		public void setAgeRating(String ageRating) {
			this.ageRating = ageRating;
		}
		public String getReleaseYear() {
			return releaseYear;
		}
		public void setReleaseYear(String releaseYear) {
			this.releaseYear = releaseYear;
		}
		public String getSkip_update_image() {
			return skip_update_image;
		}
		public void setSkip_update_image(String skip_update_image) {
			this.skip_update_image = skip_update_image;
		}
		public List<String> getLanguages() {
			return languages;
		}
		public void setLanguages(List<String> languages) {
			this.languages = languages;
		}
		public List<String> getAudios() {
			return audios;
		}
		public void setAudios(List<String> audios) {
			this.audios = audios;
		}
		public String getRunning_time_player() {
			return running_time_player;
		}
		public void setRunning_time_player(String running_time_player) {
			this.running_time_player = running_time_player;
		}
		public String getRunning_time_friendly() {
			return running_time_friendly;
		}
		public void setRunning_time_friendly(String running_time_friendly) {
			this.running_time_friendly = running_time_friendly;
		}
		public String getPilot() {
			return pilot;
		}
		public void setPilot(String pilot) {
			this.pilot = pilot;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAs() {
			return as;
		}
		public void setAs(String as) {
			this.as = as;
		}
	}


	
	
}

