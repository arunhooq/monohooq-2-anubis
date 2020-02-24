package api.pojo.DiscoveryFeed;

import java.util.List;

public class TVShowEpisodeModel {

	private List<Data> data;

	private Object meta;

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Data> getData() {
		return this.data;
	}

	public class Data {
		private String id;

		private String parent_id;

		private String title;

		private String description;

		private String short_description;

		private String as;

		private String region;

		private List<String> languages;

		private List<String> audios;

		private boolean streamable;

		private boolean downloadable;

		private int running_time;

		private List<Object> images;

		private List<Object> tags;

		private List<Object> people;

		private int season;

		private int episode;

		private Object meta;

		private Object quickplay;

		private String expires_on;

		private String created_at;

		private String updated_at;

		private String availability;

		private String reference_id;

		private String tmdb_id;

		private String broadcast_date;

		private String asset_id;

		private int content_level;

		private String lid;

		private Object brightcove;

		private boolean avod;

		private String adLink;

		private String adFreqCap;

		private String adChannelId;

		private boolean pilot;

		private String running_time_player;

		private String running_time_friendly;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}

		public String getParent_id() {
			return this.parent_id;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDescription() {
			return this.description;
		}

		public void setShort_description(String short_description) {
			this.short_description = short_description;
		}

		public String getShort_description() {
			return this.short_description;
		}

		public void setAs(String as) {
			this.as = as;
		}

		public String getAs() {
			return this.as;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getRegion() {
			return this.region;
		}

		public void setLanguages(List<String> languages) {
			this.languages = languages;
		}

		public List<String> getLanguages() {
			return this.languages;
		}

		public void setAudios(List<String> audios) {
			this.audios = audios;
		}

		public List<String> getAudios() {
			return this.audios;
		}

		public void setStreamable(boolean streamable) {
			this.streamable = streamable;
		}

		public boolean getStreamable() {
			return this.streamable;
		}

		public void setDownloadable(boolean downloadable) {
			this.downloadable = downloadable;
		}

		public boolean getDownloadable() {
			return this.downloadable;
		}

		public void setRunning_time(int running_time) {
			this.running_time = running_time;
		}

		public int getRunning_time() {
			return this.running_time;
		}

		public void setImages(List<Object> images) {
			this.images = images;
		}

		public List<Object> getImages() {
			return this.images;
		}

		public void setTags(List<Object> tags) {
			this.tags = tags;
		}

		public List<Object> getTags() {
			return this.tags;
		}

		public void setPeople(List<Object> people) {
			this.people = people;
		}

		public List<Object> getPeople() {
			return this.people;
		}

		public void setSeason(int season) {
			this.season = season;
		}

		public int getSeason() {
			return this.season;
		}

		public void setEpisode(int episode) {
			this.episode = episode;
		}

		public int getEpisode() {
			return this.episode;
		}

		public void setMeta(Object meta) {
			this.meta = meta;
		}

		public Object getMeta() {
			return this.meta;
		}

		public void setQuickplay(Object quickplay) {
			this.quickplay = quickplay;
		}

		public Object getQuickplay() {
			return this.quickplay;
		}

		public void setExpires_on(String expires_on) {
			this.expires_on = expires_on;
		}

		public String getExpires_on() {
			return this.expires_on;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getCreated_at() {
			return this.created_at;
		}

		public void setUpdated_at(String updated_at) {
			this.updated_at = updated_at;
		}

		public String getUpdated_at() {
			return this.updated_at;
		}

		public void setAvailability(String availability) {
			this.availability = availability;
		}

		public String getAvailability() {
			return this.availability;
		}

		public void setReference_id(String reference_id) {
			this.reference_id = reference_id;
		}

		public String getReference_id() {
			return this.reference_id;
		}

		public void setTmdb_id(String tmdb_id) {
			this.tmdb_id = tmdb_id;
		}

		public String getTmdb_id() {
			return this.tmdb_id;
		}

		public void setBroadcast_date(String broadcast_date) {
			this.broadcast_date = broadcast_date;
		}

		public String getBroadcast_date() {
			return this.broadcast_date;
		}

		public void setAsset_id(String asset_id) {
			this.asset_id = asset_id;
		}

		public String getAsset_id() {
			return this.asset_id;
		}

		public void setContent_level(int content_level) {
			this.content_level = content_level;
		}

		public int getContent_level() {
			return this.content_level;
		}

		public void setLid(String lid) {
			this.lid = lid;
		}

		public String getLid() {
			return this.lid;
		}

		public void setBrightcove(Object brightcove) {
			this.brightcove = brightcove;
		}

		public Object getBrightcove() {
			return this.brightcove;
		}

		public void setAvod(boolean avod) {
			this.avod = avod;
		}

		public boolean getAvod() {
			return this.avod;
		}

		public void setAdLink(String adLink) {
			this.adLink = adLink;
		}

		public String getAdLink() {
			return this.adLink;
		}

		public void setAdFreqCap(String adFreqCap) {
			this.adFreqCap = adFreqCap;
		}

		public String getAdFreqCap() {
			return this.adFreqCap;
		}

		public void setAdChannelId(String adChannelId) {
			this.adChannelId = adChannelId;
		}

		public String getAdChannelId() {
			return this.adChannelId;
		}

		public void setPilot(boolean pilot) {
			this.pilot = pilot;
		}

		public boolean getPilot() {
			return this.pilot;
		}

		public void setRunning_time_player(String running_time_player) {
			this.running_time_player = running_time_player;
		}

		public String getRunning_time_player() {
			return this.running_time_player;
		}

		public void setRunning_time_friendly(String running_time_friendly) {
			this.running_time_friendly = running_time_friendly;
		}

		public String getRunning_time_friendly() {
			return this.running_time_friendly;
		}
	}

}
