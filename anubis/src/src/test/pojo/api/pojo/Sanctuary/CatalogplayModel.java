package api.pojo.Sanctuary;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class CatalogplayModel 
{
	
		 private String streamId;
		 private Raw RawObject;
		 private boolean isTrial;
		 private float duration;
		 private Download DownloadObject;
		 private float trialDuration;
		 private Quickplay QuickplayObject;
		 private String requestId;
		 private float now;
		 private boolean fromChromecast;
		 private Hooq HooqObject;
		 private Links LinksObject;
		 private String id;
		 private float position;
		 private String lastDevice;
	
		 
		 // Getter Methods 

		 public String getStreamId() {
		  return streamId;
		 }

		 public Raw getRaw() {
		  return RawObject;
		 }

		 public boolean getIsTrial() {
		  return isTrial;
		 }

		 public float getDuration() {
		  return duration;
		 }

		 public Download getDownload() {
		  return DownloadObject;
		 }

		 public float getTrialDuration() {
		  return trialDuration;
		 }

		 public Quickplay getQuickplay() {
		  return QuickplayObject;
		 }

		 public String getRequestId() {
		  return requestId;
		 }

		 public float getNow() {
		  return now;
		 }

		 public boolean getFromChromecast() {
		  return fromChromecast;
		 }

		 public Hooq getHooq() {
		  return HooqObject;
		 }

		 public Links getLinks() {
		  return LinksObject;
		 }

		 public String getId() {
		  return id;
		 }

		 public float getPosition() {
		  return position;
		 }

		 public String getLastDevice() {
		  return lastDevice;
		 }

		 // Setter Methods 

		 public void setStreamId(String streamId) {
		  this.streamId = streamId;
		 }

		 public void setRaw(Raw rawObject) {
		  this.RawObject = rawObject;
		 }

		 public void setIsTrial(boolean isTrial) {
		  this.isTrial = isTrial;
		 }

		 public void setDuration(float duration) {
		  this.duration = duration;
		 }

		 public void setDownload(Download downloadObject) {
		  this.DownloadObject = downloadObject;
		 }

		 public void setTrialDuration(float trialDuration) {
		  this.trialDuration = trialDuration;
		 }

		 public void setQuickplay(Quickplay quickplayObject) {
		  this.QuickplayObject = quickplayObject;
		 }

		 public void setRequestId(String requestId) {
		  this.requestId = requestId;
		 }

		 public void setNow(float now) {
		  this.now = now;
		 }

		 public void setFromChromecast(boolean fromChromecast) {
		  this.fromChromecast = fromChromecast;
		 }

		 public void setHooq(Hooq hooqObject) {
		  this.HooqObject = hooqObject;
		 }

		 public void setLinks(Links linksObject) {
		  this.LinksObject = linksObject;
		 }

		 public void setId(String id) {
		  this.id = id;
		 }

		 public void setPosition(float position) {
		  this.position = position;
		 }

		 public void setLastDevice(String lastDevice) {
		  this.lastDevice = lastDevice;
		 }
		

		public class Links 
		{
		 private String self;
	
	
		 // Getter Methods 
	
		 public String getSelf() 
		 {
		  return self;
		 }
	
		 // Setter Methods 
	
		 public void setSelf(String self) 
		 {
		  this.self = self;
		 }
		 
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class Hooq {
		 private String hmac;


		 // Getter Methods 

		 public String getHmac() {
		  return hmac;
		 }

		 // Setter Methods 

		 public void setHmac(String hmac) {
		  this.hmac = hmac;
		 }
		}
		
		public class Quickplay {
		 private String resourceId;
		 private String session;
		 private String assetId;
		 private String hmac;
		 private String channelPartnerID;
		 private String countryOfLogin;
		 private String expiry;
		 private String travel;
		 private String countryOfRegistration;


		 // Getter Methods 

		 public String getResourceId() {
		  return resourceId;
		 }

		 public String getSession() {
		  return session;
		 }

		 public String getAssetId() {
		  return assetId;
		 }

		 public String getHmac() {
		  return hmac;
		 }

		 public String getChannelPartnerID() {
		  return channelPartnerID;
		 }

		 public String getCountryOfLogin() {
		  return countryOfLogin;
		 }

		 public String getExpiry() {
		  return expiry;
		 }

		 public String getTravel() {
		  return travel;
		 }

		 public String getCountryOfRegistration() {
		  return countryOfRegistration;
		 }

		 // Setter Methods 

		 public void setResourceId(String resourceId) {
		  this.resourceId = resourceId;
		 }

		 public void setSession(String session) {
		  this.session = session;
		 }

		 public void setAssetId(String assetId) {
		  this.assetId = assetId;
		 }

		 public void setHmac(String hmac) {
		  this.hmac = hmac;
		 }

		 public void setChannelPartnerID(String channelPartnerID) {
		  this.channelPartnerID = channelPartnerID;
		 }

		 public void setCountryOfLogin(String countryOfLogin) {
		  this.countryOfLogin = countryOfLogin;
		 }

		 public void setExpiry(String expiry) {
		  this.expiry = expiry;
		 }

		 public void setTravel(String travel) {
		  this.travel = travel;
		 }

		 public void setCountryOfRegistration(String countryOfRegistration) {
		  this.countryOfRegistration = countryOfRegistration;
		 }
		}
		
		
		public class Download {
		 private boolean downloadAble;
		 private float maxDownload;
		 private boolean canDownload;
		 private float queueCounter;
		 private float downloadCount;


		 // Getter Methods 

		 public boolean getDownloadAble() {
		  return downloadAble;
		 }

		 public float getMaxDownload() {
		  return maxDownload;
		 }

		 public boolean getCanDownload() {
		  return canDownload;
		 }

		 public float getQueueCounter() {
		  return queueCounter;
		 }

		 public float getDownloadCount() {
		  return downloadCount;
		 }

		 // Setter Methods 

		 public void setDownloadAble(boolean downloadAble) {
		  this.downloadAble = downloadAble;
		 }

		 public void setMaxDownload(float maxDownload) {
		  this.maxDownload = maxDownload;
		 }

		 public void setCanDownload(boolean canDownload) {
		  this.canDownload = canDownload;
		 }

		 public void setQueueCounter(float queueCounter) {
		  this.queueCounter = queueCounter;
		 }

		 public void setDownloadCount(float downloadCount) {
		  this.downloadCount = downloadCount;
		 }
		}
		
		
		public class Raw 
		{
		 private String thumbnail;
	
		 ArrayList < poster_sources > poster_sources = new ArrayList < poster_sources > ();
		 private String reference_id;
		
		 ArrayList < Object > sources = new ArrayList < Object > ();
		
		 ArrayList < Object > thumbnail_sources = new ArrayList < Object > ();
		 Custom_fields Custom_fieldsObject;
		 private boolean offline_enabled;
		 private String link = null;
		 private String created_at;
		 private String description = null;
		 private String long_description = null;
		
		 ArrayList < Object > cue_points = new ArrayList < Object > ();
	
		 ArrayList < Object > tags = new ArrayList < Object > ();
		 private float duration;
		 private String economics;
		 private String account_id;
		 private String updated_at;
		 
		 ArrayList < Object > text_tracks = new ArrayList < Object > ();
		 private String name;
		 private String id;
		 private String published_at;
		 private String ad_keys = null;
		 private String poster;


		 // Getter Methods 

		 public String getThumbnail() {
		  return thumbnail;
		 }

		 public String getReference_id() {
		  return reference_id;
		 }

		 public Custom_fields getCustom_fields() {
		  return Custom_fieldsObject;
		 }

		 public boolean getOffline_enabled() {
		  return offline_enabled;
		 }

		 public String getLink() {
		  return link;
		 }

		 public String getCreated_at() {
		  return created_at;
		 }

		 public String getDescription() {
		  return description;
		 }

		 public String getLong_description() {
		  return long_description;
		 }

		 public float getDuration() {
		  return duration;
		 }

		 public String getEconomics() {
		  return economics;
		 }

		 public String getAccount_id() {
		  return account_id;
		 }

		 public String getUpdated_at() {
		  return updated_at;
		 }

		 public String getName() {
		  return name;
		 }

		 public String getId() {
		  return id;
		 }

		 public String getPublished_at() {
		  return published_at;
		 }

		 public String getAd_keys() {
		  return ad_keys;
		 }

		 public String getPoster() {
		  return poster;
		 }

		 // Setter Methods 

		 public void setThumbnail(String thumbnail) {
		  this.thumbnail = thumbnail;
		 }

		 public void setReference_id(String reference_id) {
		  this.reference_id = reference_id;
		 }

		 public void setCustom_fields(Custom_fields custom_fieldsObject) {
		  this.Custom_fieldsObject = custom_fieldsObject;
		 }

		 public void setOffline_enabled(boolean offline_enabled) {
		  this.offline_enabled = offline_enabled;
		 }

		 public void setLink(String link) {
		  this.link = link;
		 }

		 public void setCreated_at(String created_at) {
		  this.created_at = created_at;
		 }

		 public void setDescription(String description) {
		  this.description = description;
		 }

		 public void setLong_description(String long_description) {
		  this.long_description = long_description;
		 }

		 public void setDuration(float duration) {
		  this.duration = duration;
		 }

		 public void setEconomics(String economics) {
		  this.economics = economics;
		 }

		 public void setAccount_id(String account_id) {
		  this.account_id = account_id;
		 }

		 public void setUpdated_at(String updated_at) {
		  this.updated_at = updated_at;
		 }

		 public void setName(String name) {
		  this.name = name;
		 }

		 public void setId(String id) {
		  this.id = id;
		 }

		 public void setPublished_at(String published_at) {
		  this.published_at = published_at;
		 }

		 public void setAd_keys(String ad_keys) {
		  this.ad_keys = ad_keys;
		 }

		 public void setPoster(String poster) {
		  this.poster = poster;
		 }
		}
	
		public class Custom_fields {
		 private String source_url;


		 // Getter Methods 

		 public String getSource_url() {
		  return source_url;
		 }

		 // Setter Methods 

		 public void setSource_url(String source_url) {
		  this.source_url = source_url;	 		 
		 }
		 
		
		 
		}
		
		 class poster_sources
		 {
			 private String published_at; 
			 private String reference_id;
			 
			 public String getpublished_at() {
				  return published_at;
				 }
			 
			 public void setStreamId(String streamId) {
				  this.published_at = streamId;
				 }
			 
			 public String getreference_id() {
				  return reference_id;
				 }
			 
			 public void setreference_id(String streamId) {
				  this.reference_id = streamId;
				 }
		 }

}
