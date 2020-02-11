package projetKNX;

import java.lang.module.ResolutionException;
import java.net.InetSocketAddress;

import tuwien.auto.calimero.CloseEvent;
import tuwien.auto.calimero.FrameEvent;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.cemi.CEMILData;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.NetworkLinkListener;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class Chenillard {
	
	public static  InetSocketAddress localIP = new InetSocketAddress("192.168.1.107", 0);
	public static  InetSocketAddress destIP = new InetSocketAddress("192.168.1.202", 3671);
	public static  ProcessCommunicator pc;
	public static  KNXNetworkLinkIP netLinkIp;
	
	public Chenillard(InetSocketAddress local, InetSocketAddress dest)  throws KNXException, InterruptedException, ResolutionException{
		this.localIP = local;
		this.destIP = dest;
		this.netLinkIp = KNXNetworkLinkIP.newTunnelingLink(localIP, destIP, false, new TPSettings());
		this.pc = new ProcessCommunicatorImpl(netLinkIp);
		
		pc.write(new GroupAddress("0/0/1"), false);
		pc.write(new GroupAddress("0/0/2"), false);		
		pc.write(new GroupAddress("0/0/3"), false);
		pc.write(new GroupAddress("0/0/4"), false);
	}
	
	
	public void createListener() throws KNXException, InterruptedException, ResolutionException{
		this.netLinkIp.addLinkListener( new NetworkLinkListener(){
			
				
				public void confirmation(FrameEvent arg0) {
				}
	
				public void indication(FrameEvent arg0) {
					String dest = ((CEMILData)arg0.getFrame()).getDestination().toString();
					
					System.out.println(dest);
					
					if(dest.equals("1/0/1")) {
						try {
							while(! dest.equals("1/0/2")) {
							guirlande();
							}
							
							pc.write(new GroupAddress("0/0/1"), false);
							pc.write(new GroupAddress("0/0/2"), false);
							pc.write(new GroupAddress("0/0/3"), false);
							pc.write(new GroupAddress("0/0/4"), false);
							
						} catch (ResolutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (KNXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
					}
					
				
					
					
					
				}
	
				public void linkClosed(CloseEvent arg0) {
				}
				
			});
			
		}
		
	
	
	
	
	public  void guirlande() throws KNXException, InterruptedException, ResolutionException{
		long time = 0;
     
		
		while(time <= 2) {
			this.pc.write(new GroupAddress("0/0/1"), true);
	        Thread.sleep(500);
			pc.write(new GroupAddress("0/0/1"), false);
			pc.write(new GroupAddress("0/0/2"), true);
			Thread.sleep(500);
			pc.write(new GroupAddress("0/0/2"), false);
			pc.write(new GroupAddress("0/0/3"), true);
			Thread.sleep(500);
			pc.write(new GroupAddress("0/0/3"), false);
			pc.write(new GroupAddress("0/0/4"), true);
			Thread.sleep(500);
			pc.write(new GroupAddress("0/0/4"), false);

			
			time ++;
		}
	}
	
	
	
	public static void main(String[] args) throws ResolutionException, KNXException, InterruptedException {
		Chenillard ch = new Chenillard(localIP, destIP);
		ch.createListener();
		Thread.sleep(20000);
		pc.close();
		netLinkIp.close();

		
	}

}
