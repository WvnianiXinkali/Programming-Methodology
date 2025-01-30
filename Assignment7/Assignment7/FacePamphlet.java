/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	
	private JLabel name;
	private JButton add;
	private JButton delete;
	private JButton lookUp;
	private JButton changeStatus;
	private JButton changePicture;
	private JButton addFriend;
	private JTextField fieldName;
	private JTextField fieldChangeStatus;
	private JTextField fieldChangePicture;
	private JTextField fieldAddFriend;
	
	private FacePamphletDatabase DataBase = new FacePamphletDatabase();
	private FacePamphletProfile currentProfile;
	private FacePamphletCanvas canvas;

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		name = new JLabel("Name");
		add(name, NORTH);
		
		fieldName = new JTextField(TEXT_FIELD_SIZE);
		add(fieldName, NORTH);

		add = new JButton("Add");
		add(add, NORTH);

		delete = new JButton("Delete");
		add(delete, NORTH);

		lookUp = new JButton("Lookup");
		add(lookUp, NORTH);

		
		fieldChangeStatus = new JTextField(TEXT_FIELD_SIZE);
		add(fieldChangeStatus, WEST);
		changeStatus = new JButton("Change Status");
		add(changeStatus, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		fieldChangePicture = new JTextField(TEXT_FIELD_SIZE);
		add(fieldChangePicture, WEST);
		changePicture = new JButton("Change Picture");
		add(changePicture, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		fieldAddFriend = new JTextField(TEXT_FIELD_SIZE);
		add(fieldAddFriend, WEST);
		addFriend = new JButton("Add Friend");
		add(addFriend, WEST);

		
		fieldName.addActionListener(this);
		fieldChangeStatus.addActionListener(this);
		fieldChangePicture.addActionListener(this);
		fieldAddFriend.addActionListener(this);
		
		
		fieldChangeStatus.setActionCommand("Change Status");
		fieldChangePicture.setActionCommand("Change Picture");
		fieldAddFriend.setActionCommand("Add Friend");
		
		addActionListeners();
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fieldChangeStatus || e.getSource() == changeStatus && fieldChangeStatus.getText() != "") {
			if(currentProfile == null) {
				canvas.removeAll();
				canvas.showMessage("Please select a profile to change Status");
			} else {
				String status = fieldChangeStatus.getText();
				currentProfile.setStatus(status);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Status updated to " + status);
			}
		}
		if(e.getSource() == fieldChangePicture || e.getSource() == changePicture && fieldChangePicture.getText() != "") {
			if(currentProfile == null) {
				canvas.removeAll();
				canvas.showMessage("Please select a profile to change picture");
			} else {
				GImage image = null;
				try {
				image = new GImage(fieldChangePicture.getText());
				} catch (ErrorException ex) {
					image = null;
				}
				if(image != null) {
					currentProfile.setImage(image);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Picture updated" );
				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Unable to open image file" );
				}
			}
		}
		if (e.getSource() == fieldAddFriend || e.getSource() == addFriend) {
			if (currentProfile == null) {
				canvas.removeAll();
				canvas.showMessage("Please select a profile to add a friend");
			} else {
				if (!currentProfile.getName().equals(fieldAddFriend.getText())) {
					if (DataBase.containsProfile(fieldAddFriend.getText())) {
						if (currentProfile.addFriend(fieldAddFriend.getText())) {
							DataBase.getProfile(fieldAddFriend.getText()).addFriend(currentProfile.getName());
							canvas.displayProfile(currentProfile);
							canvas.showMessage(fieldAddFriend.getText() + " added as a friend ");
						} else {
							canvas.displayProfile(currentProfile);
							canvas.showMessage(fieldAddFriend.getText() + " is already a friend");
						}
					} else {
						canvas.displayProfile(currentProfile);
						canvas.showMessage(fieldAddFriend.getText() + " doesn't exist");
					}
				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage("You cannot add yourself as a friend");
				}
			}
		}
		if(e.getSource() == add && !fieldName.getText().equals("")) {
			if(DataBase.containsProfile(fieldName.getText())) {
				FacePamphletProfile profile = new FacePamphletProfile(fieldName.getText());
				currentProfile = profile;
				DataBase.deleteProfile(profile.getName());
				DataBase.addProfile(profile);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("New profile created ");
			} else {
				FacePamphletProfile profile = new FacePamphletProfile(fieldName.getText());
				currentProfile = profile;
				DataBase.addProfile(profile);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("New profile created ");
			}
		}
		if(e.getSource() == delete && !fieldName.getText().equals("")) {
			if(DataBase.containsProfile(fieldName.getText())) {
				DataBase.deleteProfile(fieldName.getText());
				canvas.removeAll();
				canvas.showMessage("Profile of " + fieldName.getText() + " deleted");
				currentProfile = null;
			} else {
				canvas.removeAll();
				canvas.showMessage("A profile with the name " + fieldName.getText() + " does not exist");
				currentProfile = null;
			}
		}
		if(e.getSource() == lookUp && !fieldName.getText().equals("")) {
			if(DataBase.containsProfile(fieldName.getText())) {
				currentProfile = DataBase.getProfile(fieldName.getText());
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Displaying " + currentProfile.getName());
			} else {
				canvas.removeAll();
				canvas.showMessage("A profile with the name " + fieldName.getText() + " does not exist");
				currentProfile = null;
			}
		}
		
	}

}
