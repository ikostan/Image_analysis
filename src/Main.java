
public class Main {

	public static void main(String[] args) {
		
		//new GUI(); 
		
		DirectoryReader imgFinder = new DirectoryReader(); //search for images
		GUI guiClient = new GUI(); //Run GUI client
		guiClient.setCombos(imgFinder.getImgName());
		
	}
	
	//Test
}
