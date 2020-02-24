package api.pojo.DiscoveryFeed;

import java.util.List;

public class RelatedTitlesModel {

	List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public class Data {

		private String id;
		private String title;
		private String as;
		private Object images;
		private Object runningTime;
		private String availability;
		private String contentLevel;
		private Boolean streamable;
		private String publishedAt;
		private Integer dispOrder;
		private List<String> audios = null;
		private List<String> languages = null;
		private Integer seasons;
		private Object meta;

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

		public String getAs() {
			return as;
		}

		public void setAs(String as) {
			this.as = as;
		}

		public Object getImages() {
			return images;
		}

		public void setImages(Object images) {
			this.images = images;
		}

		public Object getRunningTime() {
			return runningTime;
		}

		public void setRunningTime(Object runningTime) {
			this.runningTime = runningTime;
		}

		public String getAvailability() {
			return availability;
		}

		public void setAvailability(String availability) {
			this.availability = availability;
		}

		public String getContentLevel() {
			return contentLevel;
		}

		public void setContentLevel(String contentLevel) {
			this.contentLevel = contentLevel;
		}

		public Boolean getStreamable() {
			return streamable;
		}

		public void setStreamable(Boolean streamable) {
			this.streamable = streamable;
		}

		public String getPublishedAt() {
			return publishedAt;
		}

		public void setPublishedAt(String publishedAt) {
			this.publishedAt = publishedAt;
		}

		public Integer getDispOrder() {
			return dispOrder;
		}

		public void setDispOrder(Integer dispOrder) {
			this.dispOrder = dispOrder;
		}

		public List<String> getAudios() {
			return audios;
		}

		public void setAudios(List<String> audios) {
			this.audios = audios;
		}

		public List<String> getLanguages() {
			return languages;
		}

		public void setLanguages(List<String> languages) {
			this.languages = languages;
		}

		public Integer getSeasons() {
			return seasons;
		}

		public void setSeasons(Integer seasons) {
			this.seasons = seasons;
		}

		public Object getMeta() {
			return meta;
		}

		public void setMeta(Object meta) {
			this.meta = meta;
		}


	}

}
