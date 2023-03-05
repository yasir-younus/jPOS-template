package com.field39;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class EchoRequest {

	public static void main(String[] args) throws IOException, ISOException {
		
		GenericPackager packager = new GenericPackager("src/dist/cfg/packager/isoebcdic-field39.xml");
		
		ISOMsg message = new ISOMsg();
		
		message.setPackager(packager);
		message.setMTI("0800");
		message.set(11, "123456");
		message.set(41, "00000001");
		message.set(70, "301");
		
		ASCIIChannel channel = new ASCIIChannel("localhost", 7000, packager);
		
		System.out.println(ISOUtil.hexdump(message.getBytes()));
		
		channel.connect();
		channel.send(message);
		

	}

}
