import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImgReader {

	//Class fields
	private BufferedImage buffImgOne;
	private BufferedImage buffImgTwo;		
	private File[] images; 
	
	
	//Constructor
	public ImgReader(ArrayList<String> imgName, int selectedIndx, int selectedIndx2) {
		
		try{
			
			int length = imgName.size();
			images = new File[length];
			int i = 0;
				
			for(String name : imgName){
			
				images[i] = new File(name);
				//System.out.println(images[i]); //Debug
				i++;
			}
		
			setSelectedImages(selectedIndx, selectedIndx2);
		}
		catch(Exception ex){
			//Error
			JOptionPane.showMessageDialog(null,
				    ex.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	//Load selected images
	private void setSelectedImages(int selectedOne, int selectedTwo){
		
		//Selected image #1
		try {
			
			this.buffImgOne = ImageIO.read(images[selectedOne]);
		} 
		catch (IOException e1) {

			e1.printStackTrace();
		}
		
		//Selected image #2
		try {
			
			this.buffImgTwo = ImageIO.read(images[selectedTwo]);
		} 
		catch (IOException e) {
			
 			e.printStackTrace();
		}
	}
	
	
	//ImageReader
}
