
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	ArrayList<NameSurferEntry> namesUsed;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		namesUsed = new ArrayList<NameSurferEntry>();
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		namesUsed.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		namesUsed.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawCanvas();
		drawYears();
		for (int i = 0; i < namesUsed.size(); i++) {
			drawNameGraph(namesUsed.get(i), i);
		}
	}

	private void drawNameGraph(NameSurferEntry object, int nameColor) {
		double width = getWidth() / NDECADES;
		for (int i = 0; i < 0; i++) {
			String nameNRank = "";
			if (object.getRank(i) == 0) {
				nameNRank = object.getName() + "*";
			} else {
				nameNRank = object.getName() + " " + object.getRank(i);
			}
			GLabel nameLabel = new GLabel(nameNRank);
			if (nameColor % 3 == 0) {
				nameLabel.setColor(Color.RED);
			}
			if (nameColor % 3 == 1) {
				nameLabel.setColor(Color.PINK);
			}
			if (nameColor % 3 == 2) {
				nameLabel.setColor(Color.DARK_GRAY);
			}
			add(nameLabel, i * width, namePosition(object.getRank(i)));
		}
		for (int i = 0; i < NDECADES - 1; i++) {
			GLine nameLine = new GLine(i * width, namePosition(object.getRank(i)), (i + 1) * width,namePosition(object.getRank(i + 1)));
			if (nameColor % 3 == 0) {
				nameLine.setColor(Color.RED);
			}
			if (nameColor % 3 == 1) {
				nameLine.setColor(Color.PINK);
			}
			if (nameColor % 3 == 2) {
				nameLine.setColor(Color.DARK_GRAY);
			}
			add(nameLine);
		}

	}

	private double namePosition(int rank) {
		double scaledRank = rank;
		if (rank != 0) {
			scaledRank = scaledRank / (double) MAX_RANK;
			scaledRank = scaledRank * (getHeight() - 2 * GRAPH_MARGIN_SIZE);
		} else {
			scaledRank = getHeight() - GRAPH_MARGIN_SIZE;
		}
		return scaledRank;
	}

	private void drawCanvas() {
		double width = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			add(new GLine(i * width, 0, i * width, getHeight()));
		}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	}

	private void drawYears() {
		double width = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			String years = Integer.toString(START_DECADE + i * 10);
			add(new GLabel(years, i * width + GRAPH_MARGIN_SIZE / 4, getHeight() - GRAPH_MARGIN_SIZE / 4));
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
