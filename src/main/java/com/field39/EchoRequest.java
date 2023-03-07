package com.field39;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.BCDChannel;
import org.jpos.iso.channel.NACChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.iso.packager.XMLPackager;

public class EchoRequest {

	final static String env = "local";
//	final static String env = "remote";
	final static String rhost = "146.190.206.247";
	final static String lhost = "127.0.0.1";
	final static int rport = 4446;
	final static int lport = 10000;

	public static void main(String[] args) throws IOException, ISOException {
		
		GenericPackager packagerEBCDIC = new GenericPackager("src/dist/cfg/packager/isoebcdic-field39.xml");
		ISO87BPackager packagerISO87 = new ISO87BPackager();
		XMLPackager xmlPackager = new XMLPackager();
	
		
		ISOMsg ISOMsgEBCDIC = new ISOMsg();
		ISOMsgEBCDIC = prepareEchoMsg(packagerEBCDIC, ISOMsgEBCDIC);
		
		
		ISOMsg ISOMsgASCII = new ISOMsg();
		ISOMsgASCII = prepareEchoMsg(packagerISO87, ISOMsgASCII);
		
		
		ISOMsg ISOMsgXML = new ISOMsg();
		ISOMsgXML = prepareEchoMsg(xmlPackager, ISOMsgXML);
		
		
		//System.out.println(ISOUtil.hexdump(ISOMsgEBCDIC.getBytes()));
//		print("EBCDIC presentation", ISOMsgEBCDIC);
        
//        System.out.println("Sending ebcdic message to localhost:9000\n\n");
		print("EBCDIC presentation", ISOMsgEBCDIC);
//		EBCDICChannel channelEBCDIC = new EBCDICChannel(getHost(), getPort(), packagerEBCDIC);
//        channelEBCDIC.connect();
//		channelEBCDIC.send(ISOMsgEBCDIC);
        
		
//		print("ASCII presentation", ISOMsgASCII);		
////        System.out.println("Sending ascii message to localhost:8000");
//        ASCIIChannel channelASCII = new ASCIIChannel(getHost(), getPort(), packagerISO87);
//        channelASCII.connect();
//		channelASCII.send(ISOMsgASCII);
		
//		print("XML based packager", ISOMsgXML);
////		System.out.println("Sending XML based message to");
//        XMLChannel channelXML = new XMLChannel(getHost(), getPort(), xmlPackager);
//        channelXML.connect();
//		channelXML.send(ISOMsgXML);
		

	}
	
	private static String getHost() {
		return (env.equals("remote")) ? rhost : lhost;
	}
	
	private static int getPort() {
		return (env.equals("remote")) ? rport : lport;
	}
	
	private static void print(String message, ISOMsg m) throws ISOException {
		byte[] data = m.pack();
		short messageLength = (short)data.length;
		System.out.println("MLI" + messageLength);
		System.out.println(message);
        System.out.println(ISOUtil.hexdump(data));
	}
	
	private static ISOMsg prepareEchoMsg(ISOPackager p, ISOMsg m) throws ISOException {
		
		m.setPackager(p);
		m.setMTI("0800");
		m.set(11, "123456");
		m.set(41, "00000001");
		m.set(70, "301");
		
		return m;
		
	}

}
