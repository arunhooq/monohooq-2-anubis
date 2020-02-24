package partner.utils;

public class Resources {

//	Resources BasePath List

	public static final String USER_HEALTH_CHECK			= "/health/user";
	public static final String USER							= "/2.0/user";
	public static final String KASHMIR						= "/2.0/config";
	public static final String PLAY							= "/2.0/play/";
	public static final String DISCOVER						= "/v1.5/discover";
	public static final String VISITOR_ACTIVATE				= "/2.0/visitor/activate";
	public static final String SIGNIN						= USER + "/signin";
	public static final String SIGNOUT						= USER + "/signout";
	public static final String TOKEN						= USER + "/token/";
	public static final String AUTHCODE						= TOKEN + "authcode";
	public static final String REFRESH_TOKEN				= TOKEN + "refresh";
	public static final String TVOD							= USER + "/tvod/";
	public static final String TVOD_STATUS					= TVOD + "status/";
	public static final String PURCHASE_TVOD				= TVOD + "purchase";
	public static final String SUBSCRIPTION_STATUS			= USER + "/subscriptions";
	public static final String ELIGIBILITY					= USER + "/eligible";
	public static final String UPDATE_SUBSCRIPTION			= USER + "/activate";
	public static final String DELETE_DEVICES				= USER + "/devices";
	public static final String REDIRECT						= USER + "/redirect/";
	public static final String REDIRECT_AUTO_LOGIN			= REDIRECT + "autologin/";
	public static final String REDIRECT_PLAYBACK			= REDIRECT + "play/";
	public static final String INVALIDATE_PARTNER_CONFIG	= USER + "/config/invalidate";
	public static final String LIVE_TV						= PLAY + "tv/";
	public static final String DISCOVER_TITLE_DETAILS		= DISCOVER + "/titles/";
	public static final String MJOLNIR						= "/eligible";
}
