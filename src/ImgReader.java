import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImgReader {

	//Class fields
	private File[] images; 
	private BufferedImage buffImgOne, buffImgTwo;			
	private int heightOne, heightTwo, widthOne, widthTwo;
	
	
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
			//Error
			JOptionPane.showMessageDialog(null,
				    e1.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		//Selected image #2
		try {
			
			this.buffImgTwo = ImageIO.read(images[selectedTwo]);
		} 
		catch (IOException e) {
			//Error
			JOptionPane.showMessageDialog(null,
				    e.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public BufferedImage getBuffImage(int index){
		
		try{
			if(index == 1){
			
				return this.buffImgOne;
			}
			else{
			
				return this.buffImgTwo;
			}
		}
		catch (Exception ex){
			//Error
			JOptionPane.showMessageDialog(null,
				    ex.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
				return null;
			}
	}
	
	//identifies larger image
	private BufferedImage getLargerImg(){
		
		if((buffImgOne.getWidth() + buffImgOne.getHeight()) >= (buffImgTwo.getWidth() + buffImgTwo.getHeight())){
			
			return buffImgOne;
		}
		else{
			
			return buffImgTwo;
		}
	}
	
	//identifies smaller image
	private BufferedImage getSmallerImg(){
		
		if((buffImgOne.getWidth() + buffImgOne.getHeight()) < (buffImgTwo.getWidth() + buffImgTwo.getHeight())){
			
			return buffImgOne;
		}
		else{
			
			return buffImgTwo;
		}
	}
	
	
	public double imageCompare(){
		
		BufferedImage one = getLargerImg();
		BufferedImage two = getSmallerImg();
		
		long diff = 0;
		
		int width = one.getWidth();
		int height = one.getHeight();
		
		//JOptionPane.showMessageDialog(null, String.format("width: %d, height: %d", width, height)); DEBUG ONLY
		
		try{
			for(int y = 0; y < height; y++){
			
				for(int x = 0; x < width; x++){
				
					System.out.println(String.format("X:%d Y:%d", x,y));
					
					int rgbOne = one.getRGB(x, y); //Get pixels color
					int rgbTwo = two.getRGB(x, y); //Get pixels color
				
					int r1 = (rgbOne >> 16) & 0xff;
					int g1 = (rgbOne >> 8) & 0xff;
					int b1 = (rgbOne) & 0xff;
				
					int r2 = (rgbTwo >> 16) & 0xff;
					int g2 = (rgbTwo >> 8) & 0xff;
					int b2 = (rgbTwo) & 0xff;
				
					diff += Math.abs(r1 - r2) + Math.abs(g1 - g2) +  Math.abs(b1 - b2); //Calculate difference between colors
			
				}			
			}
		} 
		catch(Exception ex){
			
			JOptionPane.showMessageDialog(null,
				    ex.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		/*
		 * n - holds information how many times script added differences of colors of every pixel to diff. 
		 * Color value is between 0 and 255 for RGB.
		 * n times 255 is the maximum difference possible. That is if all colors had a difference of 255. 
		 * diff is the actual difference. To get percentage you have to divide actual with total and multiply with 100.0
		 */
		
		double n = one.getWidth() * one.getHeight() * 3;
		double percent = (diff/ n / 255.0) * 100.0;
		return percent;
	}
	
	public int getImgWidth(int i){
		
		if(i == 1){
			return buffImgOne.getWidth();
		}
		else{
			return buffImgTwo.getWidth();
		}
	}
	
	public int getImgHeight(int i){
		
		if(i == 1){
			return buffImgOne.getHeight();
		}
		else{
			return buffImgTwo.getHeight();
		}
	}
	
	//ImageReader
}
