import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//class fields
	private JTextField textResult;
	private JComboBox<Object[]> comboImageOne, comboImageTwo;
	static int indxOne,indxTwo;
	private static ArrayList<String> imagesPath;
	private JTextField textImageAsize;
	private JTextField textImageBsize;
	
	//main method
	public static void main(String[] args) {
		
		DirectoryReader imgFinder = new DirectoryReader(); //search for images
		String[] images = imgFinder.getImgName(); //generate array with the name of all images
		
		GUI guiClient = new GUI(images); //Run GUI client
		guiClient.setImagessPath(imgFinder.getImgPath());
		guiClient.calcImgSize();
		
	}
	
	//Constructor
	public GUI(String[] images) {
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button
		this.setSize(440,270); //Window size
		setResizable(false); //Not re-sizable
		setTitle("Image analysis"); //Title
		getContentPane().setLayout(null);
		
		setLables(); //Create labels
		setCombos(images);
		setCompareBtn(); //Compare button 
		setCloseBtn(); //Close button
		setResetBtn(); //Create Reset button
		setTxtField(); //Create text field
		
		this.setVisible(true);
	}
	
	//Methods
	
	//Set labels
	private void setLables(){
		
		JLabel lblSelectImageOne = new JLabel("Source image:");
		lblSelectImageOne.setBounds(36, 26, 133, 14);
		getContentPane().add(lblSelectImageOne);
		
		JLabel lblSelectImageTwo = new JLabel("Image to compare:");
		lblSelectImageTwo.setBounds(260, 26, 159, 14);
		getContentPane().add(lblSelectImageTwo);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setBounds(208, 54, 21, 14);
		getContentPane().add(lblVs);
	}
	
	//Set combo-boxes
	public void setCombos(String[] images){
		
		//String[] list = (String[]) arrayList.toArray();
		
		comboImageOne = new JComboBox<Object[]>();
		comboImageOne.setModel(new DefaultComboBoxModel(images));
		comboImageOne.addActionListener(new FirstCombo());
		comboImageOne.setBounds(36, 51, 135, 20);
		getContentPane().add(comboImageOne);
		
		comboImageTwo = new JComboBox<Object[]>();
		comboImageTwo.setModel(new DefaultComboBoxModel(images));
		comboImageTwo.addActionListener(new SecondCombo());
		comboImageTwo.setBounds(260, 51, 135, 20);
		getContentPane().add(comboImageTwo);
		
	}
	
	//Calculate selected images dimentions and display them
	private void calcImgSize(){
		//Create ImageReader object + pass selected images and images path
		ImgReader myReader = new ImgReader(imagesPath, getSelectedIndx(1), getSelectedIndx(2));	
		
		textImageAsize.setText("(h/w): " + myReader.getImgHeight(1) + " x " + myReader.getImgWidth(1));
		textImageBsize.setText("(h/w): " + myReader.getImgHeight(2) + " x " + myReader.getImgWidth(2));
	}
	
	//Create Compare button
	private void setCompareBtn(){
		
		JButton btnCompare = new JButton("Compare");
		
		//Compare button event handler
		btnCompare.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Create ImageReader object + pass selected images and images path
				ImgReader myReader = new ImgReader(imagesPath, getSelectedIndx(1), getSelectedIndx(2));	
				
				//calcImgSize();			
				textResult.setText("Please wait while calculating the result...");

				double diffPercent = myReader.imageCompare();
				
				textResult.setText(String.format("Percantage of diference between two pictures: %.2f", diffPercent));
			}
		});
		btnCompare.setBounds(169, 171, 89, 23);
		getContentPane().add(btnCompare);
		
		//setCompareBtn
	}
	
	//Create Close button
	private void setCloseBtn(){
		JButton btnClose = new JButton("Close");
		//Event handler
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		btnClose.setBounds(306, 171, 89, 23);
		getContentPane().add(btnClose);
		
		//setCloseBtn
	}
	
	//Create Reset button
	private void setResetBtn(){
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			
			//Reset button event handler
			public void actionPerformed(ActionEvent e) {
				
				textImageAsize.setText("Image size: ?");
				textImageBsize.setText("Image size: ?");
				
				comboImageOne.setSelectedIndex(0);
				comboImageTwo.setSelectedIndex(0);
				
				textResult.setText("Please select images and click on \"Compare\" button");
			}
		});
		btnReset.setBounds(36, 171, 89, 23);
		getContentPane().add(btnReset);
	}
	
	//Create text field
	private void setTxtField(){
		
		textResult = new JTextField();
		textResult.setEditable(false);
		textResult.setBounds(36, 119, 359, 29);
		textResult.setText("Please select images and click on \"Compare\" button");
		getContentPane().add(textResult);
		textResult.setColumns(10);
		
		textImageAsize = new JTextField();
		textImageAsize.setEditable(false);
		textImageAsize.setText("Image size: ?");
		textImageAsize.setBounds(36, 82, 133, 20);
		getContentPane().add(textImageAsize);
		textImageAsize.setColumns(10);
		
		textImageBsize = new JTextField();
		textImageBsize.setEditable(false);
		textImageBsize.setText("Image size: ?");
		textImageBsize.setColumns(10);
		textImageBsize.setBounds(260, 82, 133, 20);
		getContentPane().add(textImageBsize);
		
		JLabel lblNewLabel = new JLabel("*Program limitation: max picture size allowed: 700 x 700");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(36, 216, 359, 14);
		getContentPane().add(lblNewLabel);
		
		//setTxtField
	}
	
	//Return selected index
	public int getSelectedIndx(int combo){
		
		if(combo == 1){
			
			GUI.indxOne = comboImageOne.getSelectedIndex();
			//System.out.println(indxOne); //DEBUG ONLY
			return GUI.indxOne;
		}
		else{
			
			GUI.indxTwo = comboImageTwo.getSelectedIndex();
			//System.out.println(indxTwo); //DEBUG ONLY
			return GUI.indxTwo;
		}
		
	}
	
	//Event handler
	private class FirstCombo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			indxOne = comboImageOne.getSelectedIndex();
			//System.out.println(indxOne); //DEBUG ONLY
			
			calcImgSize();
		}

		//FirstCombo
	}
	
	//Event handler
	private class SecondCombo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			indxTwo = comboImageTwo.getSelectedIndex();
			//System.out.println(indxTwo); //DEBUG ONLY
			
			calcImgSize();
		}
		
		//SecondCombo
	}
	
	
	//
	public void setImagessPath(ArrayList<String> path){
		
		this.imagesPath = path;
	}
}
