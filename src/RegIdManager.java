import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class RegIdManager {
	static final String REG_ID_STORE = "GCMRegId.txt";
	public static void writeToFile(String regId) throws IOException {
		Set<String> regIdSet = null;
		createFile();
		try{
			regIdSet = readFromFile();
		}catch(FileNotFoundException e){
			createFile();
		}catch(IOException e){
			createFile();
		}
		

		if (!regIdSet.contains(regId)) {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(REG_ID_STORE, true)));
			out.println(regId);
			out.close();
		}
	}

	public static Set<String> readFromFile() throws IOException, FileNotFoundException{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(REG_ID_STORE));
		}catch(FileNotFoundException e){
			createFile();
			br = new BufferedReader(new FileReader(REG_ID_STORE));
		}
		
		String regId = "";
		Set<String> regIdSet = new HashSet<String>();
		while ((regId = br.readLine()) != null) {
			regIdSet.add(regId);
		}
		br.close();
		return regIdSet;
	}
	
	public static void createFile(){
		 try {
	            // Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter(REG_ID_STORE);
	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);
	            
	            bufferedWriter.write("RegistrationFile");
	            // Always close files.
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '" + REG_ID_STORE + "'");
	            // ex.printStackTrace();
	        }
		
	}
}
