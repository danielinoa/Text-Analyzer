

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Wordstractor implements Comparable{

	private List list;
	private MergeSortList mergeList;
	private MergeSortList reverseMergeList;
	private MergeSortList occurrenceList;
	private MergeSortList lengthList;
	private String originalString;
	private String cleanOriginalString;
	private int wordCount;
	
	
	// Constructor
	public Wordstractor() {
		list = new List();
		mergeList = new MergeSortList();
	}

	// Main Constructor
	public Wordstractor(String str){
		initializeLists();
		
		originalString = str;
		cleanOriginalString = deletter(originalString); // we clean String from punctuations
		
		list.setRoot(extract(cleanOriginalString)); // extract all the from the "cleanOriginalString" words and put them into individual nodes
		
		mergeList.setRoot(extract(cleanOriginalString)); // extract all the from the "cleanOriginalString" words and put them into individual nodes
		mergeList.sort(); // sorts the list based on alphabetical order
		
		reverseMergeList.setRoot(extract(cleanOriginalString)); // extract all the from the "cleanOriginalString" words and put them into individual nodes
		reverseMergeList.reverseSort(); // sorts the list based on reverse alphabetical order
		
		occurrenceList.setRoot(extract(cleanOriginalString)); // extract all the from the "cleanOriginalString" words and put them into individual nodes
		((OccurrenceList) occurrenceList).regenerateOnOccurrence(); // regenerates the list without repeating words
		occurrenceList.sort(); // sorts the list based on occurrence
		
		lengthList.setRoot(extract(cleanOriginalString)); // extract all the from the "cleanOriginalString" words and put them into individual nodes
		lengthList.sort(); // sorts the list based on word length
	}

	/**
	 * This method initializes all the list to be used
	 */
	public void initializeLists() {
		list = new List();
		mergeList = new MergeSortList();
		reverseMergeList = new MergeSortList();
		occurrenceList = new OccurrenceList();
		lengthList = new LengthList();
	}
	
	/**
	 * Takes individual words out of a single String text and
	 * then place each word into a linked list;
	 * @param text
	 */
	public Node extract(String text) {
		List tempList = new List();
		
		String singleWord="";
		while(!text.isEmpty()||singleWord.contains(" ")) {
			if(text.contains(" ")) {
				singleWord = text.substring(0, text.indexOf(" ")).trim(); // trimming the single extracted word.
				tempList.addToLast(new String(singleWord)); // adding word to list.
				text = text.substring(text.indexOf(" ")).trim(); // we get rid of the extracted word and trim the whole thing.
			} else { // when there is only one word in the text (text without spaces)
				tempList.addToLast(text);
				text=""; // "text" becomes empty
			}
		}
		wordCount = tempList.getWordCount(); // get the total number of Nodes(words)
		return tempList.getRoot(); // return the head of the generated List
	}

	/**
	 * This method returns list
	 * @return
	 */
	public List getList() {
		return list;
	}
	
	/**
	 * This method returns mergeList
	 * @return
	 */
	public List getMergeList() {
		return mergeList;
	}

	/**
	 * This method returns reverseMergeList
	 * @return
	 */
	public List getReverseMergeList() {
		return reverseMergeList;
	}
	
	/**
	 * This method returns occurrenceList
	 * @return
	 */
	public List getOccurrenceList() {
		return occurrenceList;
	}
	
	/**
	 * This method returns lengthList
	 * @return
	 */
	public List getLengthList() {
		return lengthList;
	}
	
	/**
	 * This method returns the number of words in list
	 * @return wordCount
	 */
	public int wordCount(){
		return wordCount;
	}

	/**
	 * Clears a string from punctuation signs and returns the clean copy.
	 * @param str
	 * @return
	 */
	public String deletter(String str){
		String temp="";
		for(int i=0;i<str.length();i++){
			String cl = ""+str.charAt(i); 
			if(!cl.equals(",")&&!cl.equals(".")&&!cl.equals("(")&&!cl.equals(")")&&!cl.equals("\"")&& // if the character pointed is NOT 
					!cl.equals("-")&&!cl.equals(":")&&!cl.equals("'")&&!cl.equals("`")&&!cl.equals(";") // one of these...
					&&!cl.equals("[")&&!cl.equals("]")&&!cl.equals("{")&&!cl.equals("}")&&!cl.equals(">")
					&&!cl.equals("<")&&!cl.equals("?")&&!cl.equals("!")){
				temp=temp+cl; // ... then add it to the String.
			} else {
				temp=temp+" "; // otherwise add a space-character instead
			}
		}
		return temp.trim(); // returns it trimmed
	}

	@Override
	public int compareTo(Object someObject) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
