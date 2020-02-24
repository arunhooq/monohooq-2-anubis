package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;

public class ContentDetailsModel {

	Data DataObject;
	Meta MetaObject ;

	public Meta getMeta() {
		return MetaObject;
	}

	public void setMeta(Meta meta) {
		this.MetaObject = meta;
	}

	public Data getData() {
		return DataObject;
	}

	public void setData(Data dataObject) {
		this.DataObject = dataObject;
	}

	public class Data {
		private String id;
		private String parent_id = null;
		private String title;
		private String description;
		private String short_description;
		private String as;
		private String region;
		List<String> languages = new ArrayList<String>() ;
		List<String> audios = new ArrayList<String>() ;
		private boolean streamable;
		private boolean downloadable;
		private String running_time = null;
		ArrayList<Object> images = new ArrayList<Object>();
		ArrayList<Object> tags = new ArrayList<Object>();
		ArrayList<Object> people = new ArrayList<Object>();
		private String seasons;
		//Meta meta;
		Quickplay QuickplayObject;
		private String expires_on;
		private String created_at;
		private String updated_at;
		private String availability;
		private String reference_id;
		private String tmdb_id = null;
		private String broadcast_date = null;
		private String asset_id;
		private String content_level;
		private String lid;
		public Trailers TrailersObject;
		ArrayList<Object> campaigns = new ArrayList<Object>();
		private boolean pilot;
		String seasonList ;
		private boolean avod;
		private String adLink ;
		private String adFreqCap ;
		private String adChannelId;
		private String running_time_player;
		private String running_time_friendly;

		// Getter Methods

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

		public ArrayList<Object> getImages() {
			return images;
		}

		public void setImages(ArrayList<Object> images) {
			this.images = images;
		}

		public ArrayList<Object> getTags() {
			return tags;
		}

		public void setTags(ArrayList<Object> tags) {
			this.tags = tags;
		}

		public ArrayList<Object> getPeople() {
			return people;
		}

		public void setPeople(ArrayList<Object> people) {
			this.people = people;
		}

		public Meta getMetaObject() {
			return MetaObject;
		}

		public void setMetaObject(Meta metaObject) {
			MetaObject = metaObject;
		}

		public Quickplay getQuickplayObject() {
			return QuickplayObject;
		}

		public void setQuickplayObject(Quickplay quickplayObject) {
			QuickplayObject = quickplayObject;
		}

		public Trailers getTrailersObject() {
			return TrailersObject;
		}

		public void setTrailersObject(Trailers trailersObject) {
			TrailersObject = trailersObject;
		}

		public ArrayList<Object> getCampaigns() {
			return campaigns;
		}

		public void setCampaigns(ArrayList<Object> campaigns) {
			this.campaigns = campaigns;
		}

		public String getSeasonList() {
			return seasonList;
		}

		public void setSeasonList(String seasonList) {
			this.seasonList = seasonList;
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

		public String getAdFreqCap() {
			return adFreqCap;
		}

		public void setAdFreqCap(String adFreqCap) {
			this.adFreqCap = adFreqCap;
		}

		public String getAdChannelId() {
			return adChannelId;
		}

		public void setAdChannelId(String adChannelId) {
			this.adChannelId = adChannelId;
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

		public String getId() {
			return id;
		}

		public String getParent_id() {
			return parent_id;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getShort_description() {
			return short_description;
		}

		public String getAs() {
			return as;
		}

		public String getRegion() {
			return region;
		}

		public boolean getStreamable() {
			return streamable;
		}

		public boolean getDownloadable() {
			return downloadable;
		}

		public String getRunning_time() {
			return running_time;
		}

		public String getSeasons() {
			return seasons;
		}

//		public Meta getMeta() {
//			return meta;
//		}

		public Quickplay getQuickplay() {
			return QuickplayObject;
		}

		public String getExpires_on() {
			return expires_on;
		}

		public String getCreated_at() {
			return created_at;
		}

		public String getUpdated_at() {
			return updated_at;
		}

		public String getAvailability() {
			return availability;
		}

		public String getReference_id() {
			return reference_id;
		}

		public String getTmdb_id() {
			return tmdb_id;
		}

		public String getBroadcast_date() {
			return broadcast_date;
		}

		public String getAsset_id() {
			return asset_id;
		}

		public String getContent_level() {
			return content_level;
		}

		public String getLid() {
			return lid;
		}

		public Trailers getTrailers() {
			return TrailersObject;
		}

		public boolean getPilot() {
			return pilot;
		}

		// Setter Methods

		public void setId(String id) {
			this.id = id;
		}

		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setShort_description(String short_description) {
			this.short_description = short_description;
		}

		public void setAs(String as) {
			this.as = as;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public void setStreamable(boolean streamable) {
			this.streamable = streamable;
		}

		public void setDownloadable(boolean downloadable) {
			this.downloadable = downloadable;
		}

		public void setRunning_time(String running_time) {
			this.running_time = running_time;
		}

		public void setSeasons(String seasons) {
			this.seasons = seasons;
		}

//		public void setMeta(Meta metaObject) {
//			this.meta = metaObject;
//		}

		public void setQuickplay(Quickplay quickplayObject) {
			this.QuickplayObject = quickplayObject;
		}

		public void setExpires_on(String expires_on) {
			this.expires_on = expires_on;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public void setUpdated_at(String updated_at) {
			this.updated_at = updated_at;
		}

		public void setAvailability(String availability) {
			this.availability = availability;
		}

		public void setReference_id(String reference_id) {
			this.reference_id = reference_id;
		}

		public void setTmdb_id(String tmdb_id) {
			this.tmdb_id = tmdb_id;
		}

		public void setBroadcast_date(String broadcast_date) {
			this.broadcast_date = broadcast_date;
		}

		public void setAsset_id(String asset_id) {
			this.asset_id = asset_id;
		}

		public void setContent_level(String content_level) {
			this.content_level = content_level;
		}

		public void setLid(String lid) {
			this.lid = lid;
		}

		public void setTrailers(Trailers trailersObject) {
			this.TrailersObject = trailersObject;
		}

		public void setPilot(boolean pilot) {
			this.pilot = pilot;
		}
	}
	
	public class Meta {
		public  String ageRating;
		private String releaseYear;
		private boolean skip_update_image;

		// Getter Methods

		public String getAgeRating() {
			return ageRating;
		}

		public String getReleaseYear() {
			return releaseYear;
		}

		public boolean getSkip_update_image() {
			return skip_update_image;
		}

		// Setter Methods

		public void setAgeRating(String ageRating) {
			this.ageRating = ageRating;
		}

		public void setReleaseYear(String string) {
			this.releaseYear = string;
		}

		public void setSkip_update_image(boolean skip_update_image) {
			this.skip_update_image = skip_update_image;
		}
	}

	public class Trailers {

	}

	public class Quickplay {
		private String id;

		// Getter Methods

		public String getId() {
			return id;
		}

		// Setter Methods

		public void setId(String id) {
			this.id = id;
		}
	}

	

}
