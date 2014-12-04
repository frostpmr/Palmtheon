import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

public class FileProcessor {

	public void fileExtractor(){
		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		try {
			archiver.extract(new File("/home/frostpmr/Downloads/small_demo_266.tgz"),new File ("/home/frostpmr/Downloads/braindump"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void useByfferedFileWriter(String content, String filePath) {
		File file = new File(filePath);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			            // Write the lines one by one
				String line = content;
				line += System.getProperty("line.separator");
			    bufferedWriter.write(line);
			 
				                // alternatively add bufferedWriter.newLine() to change line
			
			 
		} catch (IOException e) {
	    System.err.println("Error writing the file : ");
		e.printStackTrace();
	       } finally {
				 
	    	   if (bufferedWriter != null && fileWriter != null) {
	    		   try {
	    			   bufferedWriter.close();
				       fileWriter.close();
				   } catch (IOException e) {
				     e.printStackTrace();
				     }
			   }
		     }
			 
	 }
	
	public void jsonWriter(JSONObject obj,String output) {
		
		String jsonOut = output.concat(".json");
		
		try {
			 
			FileWriter file = new FileWriter(jsonOut);
			file.write(obj.toString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		System.out.print(obj.toString());
	}
//	public void avro() throws IOException{
//		Schema schema = new Schema.Parser().parse(new File("/home/frostpmr/Downloads/braindump/user.avro"));
//		GenericRecord user1 = new GenericData.Record(schema);
//		user1.put("name", "Alyssa");
//		user1.put("favorite_number", 256);
//		// Leave favorite color null
//
//		GenericRecord user2 = new GenericData.Record(schema);
//		user2.put("name", "Ben");
//		user2.put("favorite_number", 7);
//		user2.put("favorite_color", "red");
//		 
//		// Serialize user1 and user2 to disk
//		File file = new File("/home/frostpmr/Downloads/braindump/user.avro");
//		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
//		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
//		dataFileWriter.create(schema, file);
//		dataFileWriter.append(user1);
//		dataFileWriter.append(user2);
//		dataFileWriter.close();
//	}
	}


