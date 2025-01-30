/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel displayMsg = new GLabel(msg);
		displayMsg.setFont(MESSAGE_FONT);
		add(displayMsg,getWidth()/2 - displayMsg.getWidth()/2,getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		GLabel displayName = new GLabel(profile.getName());
		displayName.setFont(PROFILE_NAME_FONT);
		displayName.setColor(Color.BLUE);
		add(displayName,LEFT_MARGIN,displayName.getAscent() + TOP_MARGIN);
		if(profile.getImage() == null) {
			GRect image = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
			add(image,LEFT_MARGIN,displayName.getAscent() + TOP_MARGIN + IMAGE_MARGIN);
			GLabel imageString = new GLabel("No Image");
			imageString.setFont(PROFILE_IMAGE_FONT);
			add(imageString,LEFT_MARGIN + IMAGE_WIDTH/2 - imageString.getWidth()/2,displayName.getAscent() + TOP_MARGIN + IMAGE_MARGIN + imageString.getHeight()/2 + IMAGE_HEIGHT/2);
		} else {
			GImage Photo = new GImage(profile.getImage().getImage());
			Photo.scale(IMAGE_WIDTH/Photo.getWidth(), IMAGE_HEIGHT/Photo.getHeight());
			add(Photo,LEFT_MARGIN,displayName.getAscent() + TOP_MARGIN + IMAGE_MARGIN);
		}
		if(profile.getStatus().isEmpty()) {
			GLabel currentStatus = new GLabel("No current status");
			currentStatus.setFont(PROFILE_STATUS_FONT);
			add(currentStatus,LEFT_MARGIN,displayName.getAscent() + TOP_MARGIN + STATUS_MARGIN + currentStatus.getHeight() + IMAGE_HEIGHT);
		} else {
			GLabel currentStatus = new GLabel(profile.getName() + " is " + profile.getStatus());
			currentStatus.setFont(PROFILE_STATUS_FONT);
			add(currentStatus,LEFT_MARGIN,displayName.getAscent() + TOP_MARGIN + STATUS_MARGIN + currentStatus.getHeight() + IMAGE_HEIGHT);
		}
		GLabel FriendsLabel = new GLabel("Friends:");
		FriendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(FriendsLabel,getWidth()/2,displayName.getAscent() + TOP_MARGIN + IMAGE_MARGIN);
		Iterator<String> it = profile.getFriends();
		int i = 1;
		while(it.hasNext()) {
			GLabel Friends = new GLabel(it.next());
			Friends.setFont(PROFILE_FRIEND_FONT);
			add(Friends,getWidth()/2,displayName.getAscent() + TOP_MARGIN + IMAGE_MARGIN + i*FriendsLabel.getAscent());
			i++;
		}
	}

	
}
