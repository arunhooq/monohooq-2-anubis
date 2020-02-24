package partner.utils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.automation.testengine.TestUtilities;

public class TestData
{
	public static Map<String, Object> getTitles(String env, String country)
	{
		try
		{
			if(env.toLowerCase().equals("nightly"))
			{
				switch(country.toLowerCase())
				{
				    case "id":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"56118cf1-6929-4d56-83dd-753280883ecc"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"4111ea20-d7ca-4620-9e8e-1ff937b07f8a"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"9e1b5785-5721-47b5-984d-e7546a868fd0"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"2ae839d4-6b6a-4565-bbba-afe335eff3d6"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"91d11d8f-97ae-472a-92a6-0a441cfcd8e8"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"0fb729bb-12f1-4a01-98da-f21f980d0dc4"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"9c4bc9bc-cf76-4536-af1e-030ffb42203f"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"638e744a-559a-4f0c-8d77-80d363e56557"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"af4ce0a3-d393-4693-9cc2-9fdac2e98480"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"68b99197-9310-442f-a4c3-21476f251e82"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"ef05449f-ca37-40d1-be1f-16e8aa78c209"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"57bbe5f0-75ff-46dc-87f7-7f45e85b0c3e")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

				    case "in":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"0001c1be-5eaf-4245-98a2-e77d73444abb"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"467698d0-39c9-4a39-9a9a-a776765b7a0a"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,""),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"3300a6bd-8206-4f10-bf83-ff504d9595a8"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"80e7fa93-abaa-4b98-a89e-55766b0a6220"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"8c57d9d1-d7d9-4b80-a60c-b5d74efddf56"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"1d72746f-4b34-43d8-967b-9a0aa0296d23"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"f8ee5e1c-72c2-4f5a-b92d-4072f284c338"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"034f15fd-daeb-43c1-aa49-481da9ecfe69"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"f9d11d1c-d64f-423a-bb35-1a8f16726108"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,""),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

				    case "sg":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"1375c542-dce3-4182-8d64-d94a624dd246"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"5f8e6bb1-594c-4883-823a-1680559538f9"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,""),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"81abf7d6-cc87-457d-9006-3d446a9448de"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"d47a758a-7cf8-4cba-a71c-c6fb65512290"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"651877c3-37a8-4971-af5c-ef22f84a72f8"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,""),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"6eb5d6df-2f1a-45c1-b1c7-e14946cad342"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"8385924a-48cb-4923-8969-e1e4d63e6d04"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,"666001c9-6eb2-4410-8e3f-7b0e1a0d9fe4"),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"8385924a-48cb-4923-8969-e1e4d63e6d04"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"91f8d748-f4ef-45ed-8739-596a98f75497"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

				    case "th":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"c6abbd53-fda8-436b-987f-ec7bd9b90dc8"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"e04d8476-a247-446f-bd7f-e96ec357a272"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,""),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"4d6a664c-39ac-489f-8905-1846dd3108e5"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"85e2fb10-8246-40c2-80e0-e53d74bbf317"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"b3ab50a6-9787-446b-aa43-4c16f69c99ef"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"03e878a1-74b1-4090-90c3-4674fa15bab8"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"d397cc5e-814f-445d-9d4b-00c58a56080f"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"c45925ec-3ec3-4c70-a794-42887d305d98"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"854a66be-62f4-4862-a314-81eb60061ddb"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"84f96567-0127-402a-8128-977b372e818b"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

				    case "ph":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"3b9ec6ca-51ff-4222-90fa-9cfba997f6ca"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"8d373214-eb63-414c-8100-5ce08c40cb83"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,""),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"877899a5-ddd7-4728-a986-959d3ec1ff7c"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"1a5ababb-dd2c-4cd9-bdc8-08d2eaaf439d"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"da76f1f3-adcc-4d99-bcae-e602c18faa27"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"945ea37f-ed5d-45a3-b7bf-958de86d6fa1"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"c76daf31-a273-43f1-9603-d76c8411b4d9"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"fcbf90f0-4658-4bc5-acc7-3365a3b87774"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"70640b5f-a88f-43ea-a2d8-971680a39f9c"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"d700428b-843b-440d-bb56-12e4b188bee8"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
				}
			} else if(env.toLowerCase().equals("sandbox"))
			{
				switch(country.toLowerCase())
				{
					case "id":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"43e041fd-7be3-488e-9d96-4b904cbef1d2"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"84537a19-ff49-4651-8669-be187ffab434"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"0257f379-a1d9-4273-8be5-9de51c7c2d4b"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"3d166ba3-98ac-4aa3-8124-cdefafca3d1f"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"2dabaa4d-d35f-4819-a49b-a185f90954ac"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"3d166ba3-98ac-4aa3-8124-cdefafca3d1f"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"9c428bd8-f814-487b-8ab2-96c4d91bca26"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"3c9d2001-40b2-4d86-9cd9-aeb6e441c9fa"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"72a6de55-e3c0-4ee0-a304-6dc40b323fdc"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"8609c27f-b339-4927-9d8d-20b6ece836b4"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"5fdf2136-9171-4d94-9f7a-ad6e2b30ad6a"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"cc167a24-29ab-4c23-9207-a1d8f7b432ee")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

					case "in":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"4208bd8d-0c88-4ea5-b7be-abbfd13e3bc1"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"02ef8fd9-1cf0-4f74-a223-898b4d7e5367"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"3ef1b756-411c-418f-8f6d-423b265a48a5"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"13e76d7e-2b19-4215-b3db-a83f4224d729"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"1046255f-f295-421f-95cd-6e6e15e97b4b"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"4208bd8d-0c88-4ea5-b7be-abbfd13e3bc1"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"0032f341-bb62-4c44-9cbe-f9d0df4f05d0"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"023cf1b7-4736-401c-9a5d-ab7adabe536d"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"011104d9-689d-4218-97ab-40af605596e5"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"f5b8ab2d-3c7d-4479-8609-a5e086ea8399"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,""),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

					case "sg":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"e2876652-d023-4d0d-8281-e72e7a1aba77"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"a74769a4-6399-45cc-9d25-705c9e18e167"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"431190ca-e088-49e1-8168-c6b5fc8926f9"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"b96f70b6-68f6-45d4-a903-5ac43905e3e9"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"6368a77b-f27c-4ee0-b280-2ae38d97656b"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"b94f97d8-f52f-42bc-9207-42c292521f2e"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"3132ee74-5b6b-4da0-8e0c-e09e0bcbd0f7"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,""),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"3b324a36-bd33-4a2f-b489-df04564de06e"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,"25293c31-d977-4633-9d2e-c50427da42b1"),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"2acc829e-fbca-4ee2-adb7-c5790707e646"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"7b45e2eb-6d23-4d3a-9f07-36632f1ccfeb"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"4b2b553d-69aa-471c-8d0a-5836fc03a43b")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

					case "th":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"5120211f-1731-47c0-af1d-68112e97da85"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"45881065-bc50-4083-b796-ea93ffbdf2f7"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"00396153-081a-4a2e-a620-ef5d46d57d7e"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"1ec097d0-dfff-4ce4-8013-a7c2352d27c9"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"30c659e8-2204-43b9-b523-ec48555406d4"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"5120211f-1731-47c0-af1d-68112e97da85"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"5acdfe2e-a293-4ff2-871f-9ea93897c2d3"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"ff0d3551-6bcf-4561-a49a-1b6b2be8af56"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"095edc41-deda-487e-a3c7-636e411ef0d8"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"115cbec5-094c-455b-becb-ddd2e1c7ec7f"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,"bdf55753-3fa6-4136-9eea-728f37d48595"),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

					case "ph":
				    	return Stream.of(
			    			new AbstractMap.SimpleEntry<>(Constants.DOWNLOADABLE,"de8a8ba2-a840-43e7-bebf-f143931aea2e"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_DOWNLOADABLE,"87acfc7d-1482-4143-aafd-a770498e4ec6"),
			    			new AbstractMap.SimpleEntry<>(Constants.NON_STREAMABLE,"0052eb95-ec77-490f-ac62-936f46b3b8b4"),
			    			new AbstractMap.SimpleEntry<>(Constants.AVOD,"2bf35552-2af2-4215-9d06-e66da1e66565"),
			    			new AbstractMap.SimpleEntry<>(Constants.TVOD,"ae2815a6-98d4-4ac5-9283-d0474e1130d7"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_10,"de8a8ba2-a840-43e7-bebf-f143931aea2e"),
			    			new AbstractMap.SimpleEntry<>(Constants.MOVIE_LVL_30,"b0c9b260-38b8-467c-b549-0e21e67d5861"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_10,"bd1ba5df-583b-4c83-8601-7740b6bbaaac"),
			    			new AbstractMap.SimpleEntry<>(Constants.SERIES_LVL_30,"158b2337-c937-4414-baa6-f7bcf63d569d"),
			    			new AbstractMap.SimpleEntry<>(Constants.R21,""),
			    			new AbstractMap.SimpleEntry<>(Constants.DISNEY,"b50eb701-75a3-4cbc-85b1-ff6f8c5c3f6b"),
			    			new AbstractMap.SimpleEntry<>(Constants.FREE_TV,""),
			    			new AbstractMap.SimpleEntry<>(Constants.PAY_TV,"")
				    	)
				    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
				}
			}
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}

	public static Map<String, Object> getUserAccounts(String partner)
	{
		try
		{
			switch(partner.toLowerCase())
			{
			    case "evergenthooq":
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.LAPSED,"evpartnerlapsed@yopmail.com"),
		    			new AbstractMap.SimpleEntry<>(Constants.PREMIUM,"evpartnerpremier@yopmail.com")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			    case "evergentlesspartner":
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.LAPSED,"evlesspartnerlapsed@yopmail.com"),
		    			new AbstractMap.SimpleEntry<>(Constants.PREMIUM,"evlesspartnerpremium@yopmail.com")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}

	public static Map<String, Object> getTVStreamDetail(String titleID)
	{
		try
		{
			switch(titleID)
			{
			    case Constants.ARIRANG_TV_CHANNEL:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.ID,"ef05449f-ca37-40d1-be1f-16e8aa78c209"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGO_URL,"https://commodusprod.hooq.tv/cdnimagesprod/assets/feed/842d3536fdf6.png"),
		    			new AbstractMap.SimpleEntry<>(Constants.TITLE,"Arirang TV"),
		    			new AbstractMap.SimpleEntry<>(Constants.DESCRIPTION, Constants.EMPTY_STRING),
		    			new AbstractMap.SimpleEntry<>(Constants.IS_PREMIUM, false),
		    			new AbstractMap.SimpleEntry<>(Constants.LABEL, Constants.BRIGHTCOVE),
		    			new AbstractMap.SimpleEntry<>(Constants.STREAM_URL,"https://livetvhooq.akamaized.net/2d3daea33e7849f4bcd48bb6396cbf2b/eu-central-1/5493668622001/playlist.m3u8?hdnts=st=1579512475~exp=1579512595~acl=/*~id=279e318e-10f4-4183-88e0-1843cd754b36~data=61.247.28.49~hmac=facbbb1c98f8dd35e92642317e1be10fd037fc8f652daacf57a56652a94ba13e")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			    case Constants.MNC_TV_CHANNEL:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.ID,"5fdf2136-9171-4d94-9f7a-ad6e2b30ad6a"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGO_URL,"https://assets.hooq.tv/feed/c994d4ebded6.png"),
		    			new AbstractMap.SimpleEntry<>(Constants.TITLE,"MNC Channel"),
		    			new AbstractMap.SimpleEntry<>(Constants.DESCRIPTION, Constants.EMPTY_STRING),
		    			new AbstractMap.SimpleEntry<>(Constants.IS_PREMIUM, false),
		    			new AbstractMap.SimpleEntry<>(Constants.LABEL, Constants.BRIGHTCOVE),
		    			new AbstractMap.SimpleEntry<>(Constants.STREAM_URL,"https://livetvhooq.akamaized.net/8fdf70fb516444ad9287403092f2dce8/ap-southeast-1/5493668622001/playlist.m3u8?hdnts=st=1579856388~exp=1579856508~acl=/*~id=5ac5471f-2387-487e-89b6-b1ee8ab337ec~data=61.247.28.49~hmac=dc268de64d872d18f186fe2a641ef125deaf478970f1e8ee92dd7442787ac43f")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}

	public static Map<String, String> getUserDetails(String user)
	{
		try
		{
			switch(user)
			{
			    case Constants.EXISTING_EMAIL:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"19080104051826564657960"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"190801040518152"),
		    			new AbstractMap.SimpleEntry<>(Constants.CID,"65363649"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"ContactUserName")
			    	)
	    			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			    case Constants.EXISTING_PHONENUMBER:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"19091810302052864837760"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"190918103020402"),
		    			new AbstractMap.SimpleEntry<>(Constants.CID,"65543599"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"AlternateUserName")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			    case Constants.EXISTING_CP_ID:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"20012407455975165346760"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"1579851906"),
		    			new AbstractMap.SimpleEntry<>(Constants.CID,"66052949"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"cpCustomerID")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//			    This 2 Users Below for same cpCustomer ID but different BU
			    case Constants.HOOQINDO1:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"76887987yui787879"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"1"),
		    			new AbstractMap.SimpleEntry<>(Constants.CID,"64572977"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"cpCustomerID")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			    case Constants.HOOQIND1:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"1"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"1"),
		    			new AbstractMap.SimpleEntry<>(Constants.CID,"51052"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"cpCustomerID")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//				    This 2 Users Below for Visitor Evless User
			    case Constants.NIGHTLY + Constants.VISITOR:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"31bb4732-3c4c-4248-82e8-5fe33c359459"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"1579851906"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"visitor")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			    case Constants.SANDBOX + Constants.VISITOR:
			    	return Stream.of(
		    			new AbstractMap.SimpleEntry<>(Constants.SID,"6fecdf75-4545-4422-a174-646fb06135a9"),
		    			new AbstractMap.SimpleEntry<>(Constants.UID,"1579851906"),
		    			new AbstractMap.SimpleEntry<>(Constants.LOGIN_TYPE,"visitor")
			    	)
			    	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		}catch (Exception e) {
			TestUtilities.logReportFailure(e);
		}
		return null;
	}
}
