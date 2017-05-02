import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DirectoryReader {

	private static Path currentRelativePath;
	private static String rootFolder;
	private static File[] files;
	private static ArrayList<String> imagesPath;
	private static String[] imagesName;
	private static ArrayList<String> extentions;
	
	//Constructor
	DirectoryReader(){
			
		currentRelativePath = Paths.get(""); //relative path
		rootFolder = currentRelativePath.toAbsolutePath().toString(); //absolute path
		files = new File(rootFolder).listFiles(); //Get all file names
		imagesPath = new ArrayList<String>();
		setExtentions();
		filterImages();
		imagesName = new String[imagesPath.size()];
		clearImgName();	
	}
	
	//Display all file names from the folder
	private void displayFileNames(){
		
		for(File file : files){
			System.out.println(file);
		}
	}
	
	//Create list of file/picture extensions
	private void setExtentions(){
		
		extentions = new ArrayList<String>();
		extentions.add("jpg");
		extentions.add("jpeg");
		extentions.add("gif");
		extentions.add("png");
		extentions.add("bmp");
	}
	
	//METHODS
	//Filter files according to file-extension
	private void filterImages(){
		
		String fileName;
		
		try{
		
			for(File file : files){
					
				if(file.isFile()){
				
					fileName = file.toString();
				
					for(String extention : extentions){
					
						if(fileName.contains(extention)){
							
							//System.out.println("Match found: " + fileName); //Debug only		
							imagesPath.add(fileName);
						}
					}				
				}		
			}
		}
		catch(Exception ex){
			
			//System.out.println(ex.getCause());
			//System.out.println(ex.toString());
			//System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null,
				    ex.getMessage(),
				    "General error",
				    JOptionPane.ERROR_MESSAGE);
		}
		//filterImages
	}
	
	//Filter path for image name
	private void clearImgName(){
		
		String imgName;
		try{
			int i = 0;
			for(String imgPath : imagesPath){
			
				imgName = imgPath.toString();
				imgName = imgName.replace(rootFolder, "");
				imgName = imgName.replace("\\", "");
				//System.out.println("IMG name: " + imgName); //Debug only		
				imagesName[i] = imgName;
				i++;
			}
		}
		catch(Exception ex){
			
			System.out.println(ex.getCause());
			System.out.println(ex.toString());
			System.out.println(ex.getMessage());
		}
	}
	
	
	//GETTERS
	public String getCurrentDirectory(){
		//Root folder
		return this.rootFolder;
	}
	
	public ArrayList<String> getImgPath(){
		//Image array
		return this.imagesPath;
	}
	
	public String[] getImgName(){
		//Image array
		return this.imagesName;
	}
	
	//DirectoryReader
}
