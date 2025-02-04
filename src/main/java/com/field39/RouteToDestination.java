package com.field39;

import java.io.Serializable;

import org.jpos.core.*;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.ContextConstants;
import org.jpos.transaction.TransactionParticipant;

public class RouteToDestination implements TransactionParticipant, Configurable {
	Configuration cfg;

	@Override
	public int prepare(long id, Serializable context) {
		Context ctx = (Context) context;
		ISOMsg m = (ISOMsg) ctx.get(ContextConstants.REQUEST.toString());
//		if (m != null && (m.hasField(2) || m.hasField(35))) {
//			try {
//				Card card = Card.builder().isomsg(m).build();
//				String s = cfg.get("bin." + card.getBin(), null);
//				if (s != null) {
//					ctx.put(ContextConstants.DESTINATION.toString(), s);
//				}
//			} catch (InvalidCardException ignore) {
//				// use default destination
//				System.out.println("just defaults to jpos-AUTORESPONDER mux");
//			}
//		}
		
		String s = cfg.get("dest", null);
		if (s != null) {
			ctx.put(ContextConstants.DESTINATION.toString(), s);
		}
		
		return PREPARED | NO_JOIN | READONLY;
	}

	public void setConfiguration(Configuration cfg) {
		this.cfg = cfg;
	}	
	
	//code here
	
}