/*
 * File: HangmanLexicon.java 
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;
import acm.program.*;

public class HangmanLexicon {
	
	private ArrayList <String> wordsToGuess = new ArrayList<String>(); 
	private int count = 0;
	
	public HangmanLexicon() { 
		BufferedReader rd;
		 try {
			  rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		 } catch(Exception e) {
			 return;
		 }
		 
		 try {
			 while(true) {
				 String word = rd.readLine();
				 if(word == null) {
					 break;
				 }
				 count++;
				 wordsToGuess.add(word);
			 }
			 rd.close();
		 } catch(Exception e) {
			 return;
		 }
	} 

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return count;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordsToGuess.get(index);
	};
}
