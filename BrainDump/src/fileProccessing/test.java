package fileProccessing;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		try {
			archiver.extract(new File("/home/frostpmr/Downloads/small_demo_266.tgz"),new File ("/home/frostpmr/Downloads/braindump"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}