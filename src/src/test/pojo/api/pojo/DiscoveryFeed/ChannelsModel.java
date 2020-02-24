package api.pojo.DiscoveryFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChannelsModel {

	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public class Data {

		private String rowId;
		private String rowName;
		private String region;
		private int order;
		private String type;

		private List<Datum> datum;

		public String getRowId() {
			return rowId;
		}

		public void setRowId(String rowId) {
			this.rowId = rowId;
		}

		public String getRowName() {
			return rowName;
		}

		public void setRowName(String rowName) {
			this.rowName = rowName;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<Datum> getData() {
			return datum;
		}

		public void setData(List<Datum> data) {
			this.datum = data;
		}

	}

	public class Datum {
		private Object images;

		private String adFreqCap;

		private String provider;

		private String adLink;

		private String avod;

		private String id;

		private String isPremium;

		private String title;

		public Object getImages() {
			return images;
		}

		public void setImages(Object images) {
			this.images = images;
		}

		public String getAdFreqCap() {
			return adFreqCap;
		}

		public void setAdFreqCap(String adFreqCap) {
			this.adFreqCap = adFreqCap;
		}

		public String getProvider() {
			return provider;
		}

		public void setProvider(String provider) {
			this.provider = provider;
		}

		public String getAdLink() {
			return adLink;
		}

		public void setAdLink(String adLink) {
			this.adLink = adLink;
		}

		public String getAvod() {
			return avod;
		}

		public void setAvod(String avod) {
			this.avod = avod;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getIsPremium() {
			return isPremium;
		}

		public void setIsPremium(String isPremium) {
			this.isPremium = isPremium;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
}
