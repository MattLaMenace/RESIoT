package projetKNX;

import java.lang.module.ResolutionException;
import java.net.InetSocketAddress;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class Chenillard {
	
	public static InetSocketAddress localIP = new InetSocketAddress("192.168.1.113", 0);
	public static InetSocketAddress destIP = new InetSocketAddress("192.168.1.201", 3671);
	
	
	public static void createConnexion() throws KNXException, InterruptedException, ResolutionException{
		
		KNXNetworkLinkIP netLinkIp = KNXNetworkLinkIP.newTunnelingLink(localIP, destIP, false, new TPSettings());
		ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIp);
		
		pc.write(new GroupAddress("0/0/2"), !pc.readBool(new GroupAddress("0/0/2")));
//		pc.write(new GroupAddress("0/0/2"), false);
//		pc.write(new GroupAddress("0/0/3"), false);
//		pc.write(new GroupAddress("0/0/4"), false);
		
//		
//		long time = 0;
//        
//		
//		while(time <= 10) {
//			pc.write(new GroupAddress("0/0/1"), true);
//	        Thread.sleep(1000);
//			pc.write(new GroupAddress("0/0/1"), false);
//			pc.write(new GroupAddress("0/0/2"), true);
//			Thread.sleep(1000);
//			pc.write(new GroupAddress("0/0/2"), false);
//			pc.write(new GroupAddress("0/0/3"), true);
//			Thread.sleep(1000);
//			pc.write(new GroupAddress("0/0/3"), false);
//			pc.write(new GroupAddress("0/0/4"), true);
//			time ++;
//
//			
//		}
		
		pc.close();
		netLinkIp.close();
	}	
	
	public void main(String[] args) throws KNXException, InterruptedException, ResolutionException{
		createConnexion();
	}

}
