import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class GUI extends JFrame{
	
	//class fields
	private JTextField textResult;
	private JComboBox comboImageOne, comboImageTwo;
	static int indxOne,indxTwo;
	
	
	//main method
	public static void main(String[] args) {
		
		DirectoryReader imgFinder = new DirectoryReader(); //search for images
		String[] images = imgFinder.getImgName(); //generate array with the name of all images
		
		GUI guiClient = new GUI(images); //Run GUI client
		
		//Create ImageReader object + pass selected images and images path
		ImgReader myReader = new ImgReader(imgFinder.getImgPath(), guiClient.getSelectedIndx(1), guiClient.getSelectedIndx(2));	
		
	}
	
	//Constructor
	public GUI(String[] images) {
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button
		this.setSize(440,260); //Window size
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
	public void setCombos(Object[] arrayList){
		
		comboImageOne = new JComboBox();
		comboImageOne.setModel(new DefaultComboBoxModel(arrayList));
		comboImageOne.addActionListener(new FirstCombo());
		comboImageOne.setBounds(36, 51, 135, 20);
		getContentPane().add(comboImageOne);
		
		comboImageTwo = new JComboBox();
		comboImageTwo.setModel(new DefaultComboBoxModel(arrayList));
		comboImageTwo.addActionListener(new SecondCombo());
		comboImageTwo.setBounds(260, 51, 135, 20);
		getContentPane().add(comboImageTwo);
	}
	
	//Create Compare button
	private void setCompareBtn(){
		
		JButton btnCompare = new JButton("Compare");
		
		//Compare button event handler
		btnCompare.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				getSelectedIndx(1);
				getSelectedIndx(2);
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
		textResult.setBounds(36, 108, 359, 29);
		textResult.setText("Please select images and click on \"Compare\" button");
		getContentPane().add(textResult);
		textResult.setColumns(10);
		
		//setTxtField
	}
	
	//Return selected index
	public int getSelectedIndx(int combo){
		
		if(combo == 1){
			
			this.indxOne = comboImageOne.getSelectedIndex();
			//System.out.println(indxOne); //DEBUG ONLY
			return this.indxOne;
		}
		else{
			
			this.indxTwo = comboImageTwo.getSelectedIndex();
			//System.out.println(indxTwo); //DEBUG ONLY
			return this.indxTwo;
		}
		
	}
	
	//Event handler
	private class FirstCombo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			indxOne = comboImageOne.getSelectedIndex();
			//System.out.println(indxOne); //DEBUG ONLY
		}

		//FirstCombo
	}
	
	//Event handler
	private class SecondCombo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			indxTwo = comboImageTwo.getSelectedIndex();
			//System.out.println(indxTwo); //DEBUG ONLY
		}
		
		//SecondCombo
	}
	
	//End
}
