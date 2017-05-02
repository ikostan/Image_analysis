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
	private static int x = 0; //width starting point
	private static int y = 0; //height starting point
		
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
	
	//Return buffered image according to selected index
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
	
	//identifies larger width
	private int getMaxX(){
		
		if((buffImgOne.getWidth() - buffImgTwo.getWidth()) == 0){
			
			return buffImgOne.getWidth();
		}
		else if((buffImgOne.getWidth() - buffImgTwo.getWidth()) > 0){
			
			return buffImgOne.getWidth();
		}
		else{
			
			return buffImgTwo.getWidth();
		}
	}
	
	//identifies larger height
	private int getMaxY(){
		
		if((buffImgOne.getHeight() - buffImgTwo.getHeight()) == 0){
			
			return buffImgOne.getHeight();
		}
		else if((buffImgOne.getHeight() - buffImgTwo.getHeight()) > 0){
			
			return buffImgOne.getHeight();
		}
		else{
			
			return buffImgTwo.getHeight();
		}
	}
	
	//identifies smaller width
	private int getSmallerX(){
		
		if((buffImgOne.getWidth() - buffImgTwo.getWidth()) >= 0){
			
			return buffImgTwo.getWidth();
		}
		else{
			
			return buffImgOne.getWidth();
		}
	}
	
	//identifies smaller height
	private int getSmallerY(){
		
		if((buffImgOne.getHeight() - buffImgTwo.getHeight()) >= 0){
			
			return buffImgTwo.getHeight();
		}
		else{
			
			return buffImgOne.getHeight();
		}
	}
		
	//Calculate difference between two pixels
	private double colorDiff(int rgbOne, int rgbTwo){
		
		int r1 = (rgbOne >> 16) & 0xff; //Red color
		int g1 = (rgbOne >> 8) & 0xff; 	//Green color
		int b1 = (rgbOne) & 0xff;		//Blue color
	
		int r2 = (rgbTwo >> 16) & 0xff; //Red color
		int g2 = (rgbTwo >> 8) & 0xff;	//Green color
		int b2 = (rgbTwo) & 0xff;		//Blue color
	
		return (Math.abs(r1 - r2) + Math.abs(g1 - g2) +  Math.abs(b1 - b2)); //Calculate difference between colors
	}
	
	public double imageCompare(){
		
		long diff = 0;
		int rgbOne, rgbTwo;
		
		//buffImgOne, buffImgTwo;
		
		for(y = 0; y < buffImgOne.getHeight(); y++){
			
			for(x = 0; x < buffImgOne.getWidth(); x++){
				
				rgbOne = buffImgOne.getRGB(x, y);
				rgbTwo = buffImgTwo.getRGB(x, y);
				
				diff += colorDiff(rgbOne, rgbTwo);
			}
			
			if(y % 10 == 0){
				
				System.out.println("still looping..."); //DEBUG ONLY
			}
		}
		
		System.out.println("DONE!!!"); //DEBUG ONLY

		double n = buffImgOne.getWidth() * buffImgOne.getHeight() * 3.0 ;
		double p = (diff / n / 255.0) * 100.0; //Percentage
		p  = Double.parseDouble(String.format("%.2f", p)); //Display two decimal digits only
				
		return (p * 100.0); //Percentage		
	}
	
	//Return image width
	public int getImgWidth(int i){
		
		if(i == 1){
			return buffImgOne.getWidth();
		}
		else{
			return buffImgTwo.getWidth();
		}
	}
	
	//Return image height
	public int getImgHeight(int i){
		
		if(i == 1){
			return buffImgOne.getHeight();
		}
		else{
			return buffImgTwo.getHeight();
		}
	}
	
	//Check if pucture maz size (700 px) exceeded
	private boolean picMaxSize(BufferedImage one){
		
		boolean isTrue = false;
		int max = 700; //max picture size
		
		if(one.getWidth() > max){
			
			isTrue = true;
		}
		else if(one.getHeight() > max){
			
			isTrue = true;
		}
		
		return isTrue;
	}
	
	//ImageReader
}
