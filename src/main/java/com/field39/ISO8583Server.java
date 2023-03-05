package com.field39;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class ISO8583Server implements ISORequestListener {

	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 7000;
		
		try {
			ISOPackager packager = new GenericPackager("isoebcdic-field39.xml");
			ASCIIChannel channel = new ASCIIChannel(host, port, packager);
			
			ISOServer server = new ISOServer(port, channel, null);
			
			server.addISORequestListener(new ISO8583Server());
			
			System.out.println("Running Field39 ISO8583...");
			new Thread(server).start();
			
			
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean process(ISOSource source, ISOMsg m) {

		try {
			System.out.print("Incoming message: " + ((BaseChannel) source).getSocket().getInetAddress());
			

			handleMessage(source, m);
			//LogISOMsg(m);
			
		}
		catch(Exception ex) {
			
		}
		
		return true;

	}
	
	private void handleMessage(ISOSource source, ISOMsg m) throws ISOException {
		
	}

}
