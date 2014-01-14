

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;

public class Window{


	private Wordstractor wordstractor;
	private File selectedFile;
	private String fileString;
	private JFrame frame;
	private JTextField textField;
	private JTextArea bigTextField;
	private JTextField searchField;
	private JLabel lblAnalytics; // Label ANALYTICS
	private JButton originalFileButton; 
	private JButton searchButton; // search button
	private JButton openButton; // 
	private JButton listWords;
	private JButton btnListWordsAZ; // sorting button 
	private JButton btnListWordsZA;
	private JButton btnExtract;
	private JButton ocButton;
	private JButton lengthButton;
	private JScrollPane scrollPane;
	private JLabel totalWords; 
	private JButton surpriseButton;

	/**
	 * Create the application.
	 */
	public Window() {
		setup();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setup() {
		frame = new JFrame(); // FRAME
		frame.setTitle("Text Analyzer"); // FRAME's Title
		frame.getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		frame.setBounds(100, 100, 743, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField(); // Chosen-file text field
		textField.setBounds(18, 22, 204, 28);
		textField.setForeground(Color.GRAY);
		textField.setEditable(false);
		textField.setText("Choose file...");
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		openButton = new JButton("Open");
		openButton.setBounds(220, 23, 117, 29);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(openButton);

		surpriseButton = new JButton("*");
		surpriseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				surprise();
			}
		});
		surpriseButton.setBounds(0, 444, 19, 16);
		frame.getContentPane().add(surpriseButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 97, 534, 356);
		frame.getContentPane().add(scrollPane);

		bigTextField = new JTextArea();
		bigTextField.setEditable(true);
		scrollPane.setViewportView(bigTextField);
		bigTextField.setLineWrap(true);

		listWords = new JButton("List words");
		listWords.setBounds(564, 149, 117, 29);
		listWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});

		frame.getContentPane().add(listWords);

		lblAnalytics = new JLabel("Analytics"); // Analytics Label
		lblAnalytics.setBackground(Color.WHITE);
		lblAnalytics.setBounds(589, 54, 117, 36);
		lblAnalytics.setFont(new Font("Synchro LET", Font.BOLD, 20));

		frame.getContentPane().add(lblAnalytics);

		originalFileButton = new JButton("Show Original File");
		originalFileButton.setBounds(395, 23, 157, 29);
		originalFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(originalFileButton);

		searchField = new JTextField();
		searchField.setText("Search...");
		searchField.setForeground(Color.GRAY);
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(searchField.getText().isEmpty()) // if empty
					searchField.setText("Search..."); // set this text
				searchField.setForeground(Color.GRAY); // color
			}
			public void focusGained(FocusEvent arg0) {
				if(searchField.getText().equals("Search...")) // if has this text
					searchField.setText(""); // then empty it the field
				searchField.setForeground(Color.BLACK); // color
			}
			
		});
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchMethod();
				if(e.getKeyCode()==10) // if press ENTER
					bigTextField.requestFocus(); // get Focus
			}
		});
		searchField.setBounds(564, 384, 157, 28);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);

		searchButton = new JButton("Search");
		searchButton.setBounds(589, 424, 117, 29);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(searchButton);

		btnListWordsAZ = new JButton("List words za-ZA-90");
		btnListWordsAZ.setBounds(564, 231, 169, 29);
		btnListWordsAZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(btnListWordsAZ);

		btnListWordsZA = new JButton("List words 09-AZ-az");
		btnListWordsZA.setBounds(564, 190, 169, 29);
		btnListWordsZA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(btnListWordsZA);

		JLabel lblNewLabel = new JLabel("or");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(202, 69, 24, 16);
		frame.getContentPane().add(lblNewLabel);

		btnExtract = new JButton("Extract");
		btnExtract.setBounds(220, 62, 117, 29);
		btnExtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(btnExtract);

		JLabel lblFromBelow = new JLabel("from below");
		lblFromBelow.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFromBelow.setBounds(337, 69, 134, 16);
		frame.getContentPane().add(lblFromBelow);

		ocButton = new JButton("List b/o occurrence");
		ocButton.setBounds(564, 272, 153, 29);
		ocButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(ocButton);

		lengthButton = new JButton("List b/o word length");
		lengthButton.setBounds(564, 313, 157, 29);
		lengthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		frame.getContentPane().add(lengthButton);

		totalWords= new JLabel("Word count: ");
		totalWords.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		totalWords.setBounds(573, 99, 160, 28);
		frame.getContentPane().add(totalWords);
		
		bigTextField.requestFocus(); // big text field requests focus, (CALLING THIS METHOD AT THE END HELPS WITH THE SEARCH FUNCTION (HIGHLIGHTING))
	}

	public void action(ActionEvent e) {
		Object action = e.getSource();
		if(action==openButton) {
			openButton();
			bigTextField.requestFocus();
		} else if(action==btnExtract) {
			extract();
		} else if(action==searchButton) { // SEARCH
			searchMethod();
			bigTextField.requestFocus();
		} else if(action==originalFileButton&&wordstractor!=null) {
			setBigText(fileString);
		} else if(action==listWords&&wordstractor!=null) {
			setBigText(wordstractor.getList().toString()); // get the processed text representation of the list. 
		} else if(action==btnListWordsAZ&&wordstractor!=null) {
			setBigText(wordstractor.getMergeList().toString()); // get the processed text representation of the sorted list.
		} else if(action==btnListWordsZA&&wordstractor!=null) {
			setBigText(wordstractor.getReverseMergeList().toString()); // get the processed text representation of the reverse sorted list.
		} else if(action==ocButton&&wordstractor!=null) {
			setBigText(wordstractor.getOccurrenceList().toString()); // get the processed text representation of the list based on word occurrence.
		} else if(action==lengthButton&&wordstractor!=null) {
			setBigText(wordstractor.getLengthList().toString()); // // get the processed text representation of the list based on word length.
		}
	}

	/**
	 * This method takes a string and sets it in the big text box. 
	 * @param string
	 */
	public void setBigText(String string) {
		bigTextField.setText(string);
	}

	/**
	 * Extract text from the big text box
	 * Initializes the wordstractor to analyze that text
	 * and then portray the result back again in the big text box 
	 */
	public void extract() {
		fileString = bigTextField.getText(); // store the text in the big text field in a String variable
		wordstractor = new Wordstractor(fileString); // pass it to the word extractor
		textField.setText("(Extracted)"); // set text to the upper text field
		setBigText(fileString); // portray the extracted text in the big text box
		totalWords.setText("Word count: "+wordstractor.wordCount()); // portray the number of words in the text
	}

	
	/**
	 * Open files and set the name of the file
	 * in the chosen-file box 
	 */
	public void openButton() {
		openFile();
		if(selectedFile!=null) {
			try {
				fileString = fileToString(selectedFile); // set text
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	
			try {
				if(selectedFile!=null)
					wordstractor = new Wordstractor(fileToString(selectedFile)); // create wordstractor
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			totalWords.setText("Word count: "+wordstractor.wordCount()); // number of nodes in the list held by the wordstractor 
			setBigText(fileString); // set fileString in the big text box
		}
	}

	public void openFile() {
		selectedFile=null;

		JFileChooser fileChooser = new JFileChooser(); // File chooser
		fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory()); 
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter( // Restrict files only to .txt
				".txt files", "txt");
		fileChooser.setFileFilter(filter);


		try {
			int code = fileChooser.showOpenDialog(null);
			if (code == JFileChooser.APPROVE_OPTION) { // Choosing the file
				selectedFile = fileChooser.getSelectedFile();
			} 
		} catch (Exception f) {
			f.printStackTrace();
		}
		if(selectedFile!=null)
			textField.setText(selectedFile.getName()); // set name of the file in the chosen-file text field
	}

	/**
	 * This method takes a text File and converts into a multi-line String.
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public String fileToString(File file) throws FileNotFoundException {
		String str = "";
		Scanner reader = new Scanner(file);
		while(reader.hasNextLine()){ // if there is another line to be read
			str = str+"\n" + reader.nextLine(); 
		}
		return str;
	}

	/**
	 * This method gets text from search field and if it exits then
	 * it selects that text represantion in the big text box. 
	 */
	public void searchMethod() {
		String searchWord = searchField.getText();
		if(bigTextField.getText().contains(searchWord)) {
			int start = bigTextField.getText().indexOf(searchWord);
				bigTextField.select(start, start + searchWord.length());
		}
	}

	/*
	 * This method....... !!!!!
	 */
	public void surprise() {
		JOptionPane.showMessageDialog(null,"SURPRISE !!!");
		EasterEgg launch = new EasterEgg();
	}

} // End of class
