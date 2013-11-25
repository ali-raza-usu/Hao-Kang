package utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Assert;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import utilities.IMessage;



public class Encoder {
	static Logger logger = Logger.getLogger(Encoder.class);
	public static byte[] encode(IMessage _data)
	{
		try{
				ByteArrayOutputStream bStream = new ByteArrayOutputStream();
				ObjectOutputStream objOutput = new ObjectOutputStream(bStream);
				objOutput.writeObject(_data);
				objOutput.flush();		
				byte[] data = bStream.toByteArray();
				objOutput.close();
				bStream.close();
			return data;
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(ExceptionUtils.getStackTrace(e)); 
			return null;
		}
	}
	
	
	public static IMessage decode(byte[] _bytes)
	{
		try{		
			if(_bytes == null || _bytes.length == 0)
				return null;
			ByteArrayInputStream bInputS = new ByteArrayInputStream(_bytes);
			ObjectInputStream oIs = new ObjectInputStream(bInputS);
			IMessage msg = (IMessage) oIs.readObject();			
			oIs.close();
			bInputS.close();
			return msg;
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(ExceptionUtils.getStackTrace(e)); 
			return null;
		}
	}

	
}
