package main;

     import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.JsonEncoder;

     public class idk{
     String schema = "test";




     public static byte[] jsonToAvro(String json, String schemaStr) throws IOException {
         InputStream input = null;
         GenericDatumWriter<Object> writer = null;
         Encoder encoder = null;
         ByteArrayOutputStream output = null;
         try {
             Schema schema = new Schema.Parser().parse(new File("/home/frostpmr/Downloads/braindump/copy.json"));
             DatumReader<Object> reader = new GenericDatumReader<Object>(schema);
             input = new ByteArrayInputStream(json.getBytes());
             output = new ByteArrayOutputStream();
             DataInputStream din = new DataInputStream(input);
             writer = new GenericDatumWriter<Object>(schema);
             Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
             encoder = EncoderFactory.get().binaryEncoder(output, null);
             File file = new File("/home/frostpmr/Downloads/braindump/pedigrees2.avro");
             Object datum;
             
             while (true) {
                 try {
                     datum = reader.read(null, decoder);
                 } catch (EOFException eofe) {
                     break;
                 }
                 
                 writer.write(datum, encoder);
                 System.out.println(datum);
             }
             encoder.flush();
             return output.toByteArray();
         } finally {
             try { input.close(); } catch (Exception e) {}
         }
     }

     }
     
     
     
     
     
     
// 	maplist.add(pedigreeRecordMap);
//	//System.out.println(maplist);
//	for (Map<String, Object> map : maplist) {
//		GenericRecord rec = new GenericData.Record(schema);
//	    for (Map.Entry<String, Object> entry : map.entrySet()) {
//	        
//	        rec.put(entry.getKey(), entry.getValue());
//	        recordList.add(rec);
//	    }
//	}
//	}