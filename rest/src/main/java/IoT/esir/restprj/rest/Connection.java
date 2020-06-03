package IoT.esir.restprj.rest;



import java.io.FileNotFoundException;
import java.lang.module.ResolutionException;
import java.net.InetSocketAddress;

import tuwien.auto.calimero.CloseEvent;
import tuwien.auto.calimero.FrameEvent;
import tuwien.auto.calimero.GroupAddress;
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

public class Connection {

	public InetSocketAddress localIP = new InetSocketAddress("192.168.1.104", 0);
	public InetSocketAddress destIP = new InetSocketAddress("192.168.1.202", 3671);
	public static ProcessCommunicator pc;
	public KNXNetworkLinkIP netLinkIp;
	public static  MyThread m;

	public Connection() throws  KNXException, InterruptedException {
		
		

		this.netLinkIp = KNXNetworkLinkIP.newTunnelingLink(localIP, destIP, false, new TPSettings());
		pc = new ProcessCommunicatorImpl(netLinkIp);
		
		
		
		this.netLinkIp.addLinkListener(new NetworkLinkListener() {
			
			public void confirmation(FrameEvent arg0) {
			}

			public void indication(FrameEvent arg0)  {

				String dest = ((CEMILData) arg0.getFrame()).getDestination().toString();

				if (dest.equals("1/0/1")) {
					
					try {
						m.myStop();
						m=new MyThread(800);
						m.chenillardV1();
					} catch (KNXException | InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (dest.equals("1/0/2")) {
					
					try {
						m.myStop();
						m=new MyThread(800);
						m.chenillardV2();
					} catch (KNXException | InterruptedException e) {
						e.printStackTrace();
					}


				}
				
				if (dest.equals("1/0/3")) {
					
					try {
						m.myStop();
						m.eteindre();
					} catch (KNXException e) {
						e.printStackTrace();
					}

				}
				
				if (dest.equals("1/0/4")) {
					
					m.accelerer();
				}
			}

			public void linkClosed(CloseEvent arg0) {
			}

		});

	}

	public ProcessCommunicator getPC() {
		return pc;
	}
	
	public void close (){
		netLinkIp.close();
		pc.close();	
	}
	
	
	


}
