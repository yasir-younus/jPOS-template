package com.field39;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.iso.packager.XMLPackager;

public class EchoRequest {

	public static void main(String[] args) throws IOException, ISOException {
		
		GenericPackager packagerEBCDIC = new GenericPackager("src/dist/cfg/packager/isoebcdic-field39.xml");
		ISO87BPackager packagerISO87 = new ISO87BPackager();
		XMLPackager xmlPackager = new XMLPackager();
	
		
		ISOMsg ISOMsgEBCDIC = new ISOMsg();
		ISOMsgEBCDIC = prepareISOMsg(packagerEBCDIC, ISOMsgEBCDIC);
		
		
		ISOMsg ISOMsgB = new ISOMsg();
		ISOMsgB = prepareISOMsg(packagerISO87, ISOMsgB);
		
		
		ISOMsg ISOMsgXML = new ISOMsg();
		ISOMsgXML = prepareISOMsg(xmlPackager, ISOMsgXML);
		
		
		//System.out.println(ISOUtil.hexdump(ISOMsgEBCDIC.getBytes()));
		print("EBCDIC presentation", ISOMsgEBCDIC);
        
//        System.out.println("Sending ebcdic message to localhost:9000\n\n");
//		ASCIIChannel channelEBCDIC = new ASCIIChannel("localhost", 9000, packagerEBCDIC);
//        channelEBCDIC.connect();
//		channelEBCDIC.send(ISOMsgEBCDIC);
        
		print("ASCII presentation", ISOMsgB);
		
//        System.out.println("Sending ascii message to localhost:8000");
//        ASCIIChannel channelASCII = new ASCIIChannel("localhost", 8000, packagerISO87);
//        channelASCII.connect();
//		channelASCII.send(ISOMsgXML);
		
		print("XML based packager", ISOMsgXML);
		System.out.println("Sending XML based message to localhost:8000");
//        XMLChannel channelASCII = new XMLChannel("localhost", 8000, xmlPackager);
//        channelASCII.connect();
//		channelASCII.send(ISOMsgXML);
		
		
		
		

	}
	
	private static void print(String message, ISOMsg m) throws ISOException {
		byte[] data = m.pack();
		short messageLength = (short)data.length;
		System.out.println("MLI" + messageLength);
		System.out.println(message);
        System.out.println(ISOUtil.hexdump(data));
	}
	
	private static ISOMsg prepareISOMsg(ISOPackager p, ISOMsg m) throws ISOException {
		
		m.setPackager(p);
		m.setMTI("0800");
		m.set(11, "123456");
		m.set(41, "00000001");
		m.set(70, "301");
		
		return m;
		
	}

}
