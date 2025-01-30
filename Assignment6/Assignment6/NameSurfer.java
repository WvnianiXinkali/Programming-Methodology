/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JTextField nameSpace;
	private String name = "";
	private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	
	private NameSurferGraph graph;
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		add(graph);
		nameSpace = new JTextField(20);
		add(new JLabel("Name:"),SOUTH);
		add(nameSpace,SOUTH);
		add(new JButton("Graph"),SOUTH);
		add(new JButton("Clear"),SOUTH);
		addActionListeners();
		nameSpace.addActionListener(this);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (e.getSource() == nameSpace) {
			NameSurferEntry entry = dataBase.findEntry(nameSpace.getText());
			graph.addEntry(entry);
			graph.update();
		}
		if (cmd.equals("Graph")){
			NameSurferEntry entry = dataBase.findEntry(nameSpace.getText());
			graph.addEntry(entry);
			graph.update();
		}
		if (cmd.equals("Clear")) {
			graph.clear();
		}
	}

}
