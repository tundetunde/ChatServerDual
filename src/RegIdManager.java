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
		BufferedReader br = new BufferedReader(new FileReader(REG_ID_STORE));
		String regId = "";
		Set<String> regIdSet = new HashSet<String>();
		while ((regId = br.readLine()) != null) {
			regIdSet.add(regId);
		}
		br.close();
		return regIdSet;
	}
	
	public static void createFile(){
		/*PrintWriter writer;
		try {
			writer = new PrintWriter(REG_ID_STORE, "UTF-8");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		File file = new File(REG_ID_STORE);
		BufferedWriter output;
        try {
        	output = new BufferedWriter(new FileWriter(file));
			output.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
