package web.data;

import java.util.ArrayList;

public class AudioSubTitlesBean {

	public ArrayList<String> audioList = new ArrayList<String>();
	public ArrayList<String> subTitlesList = new ArrayList<String>();

	public void setAudio(ArrayList<String> audioList) {
		this.audioList = audioList;
	}

	public ArrayList<String> getAudio() {
		return audioList;
	}

	public void setSubTitle(ArrayList<String> subTitlesList) {
		this.subTitlesList = subTitlesList;
	}

	public ArrayList<String> getsubTitle() {
		return subTitlesList;
	}

}
