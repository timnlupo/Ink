import java.io.*;

public class Input {
	
	private static String fileName;
	
    public Input(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public static String readFile() {
		
		String line = null;
		String input = null;
	
        try {
        	
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                input += " " + line + " ";
            }
            
            bufferedReader.close(); 
        }
        
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } 
        catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        } 
        catch (NullPointerException ex) {
        	System.out.println("No filename provided!");
        }
        
        return input;
	}
}