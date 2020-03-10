package projetKNX;

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
	public ProcessCommunicator pc;
	public KNXNetworkLinkIP netLinkIp;
	public boolean stop = false;
	public MyThread m;

	public Connection() throws KNXException, InterruptedException, ResolutionException {
		this.netLinkIp = KNXNetworkLinkIP.newTunnelingLink(localIP, destIP, false, new TPSettings());
		this.pc = new ProcessCommunicatorImpl(netLinkIp);
		m = new MyThread(this.pc);
		m.start();
		
		
		this.netLinkIp.addLinkListener(new NetworkLinkListener() {
			
			public void confirmation(FrameEvent arg0) {
			}

			public void indication(FrameEvent arg0) {
				String dest = ((CEMILData) arg0.getFrame()).getDestination().toString();

				if (dest.equals("1/0/1")) {
					m.myStop();
					m=new MyThread(pc);
					m.addAction(1);
					m.start();
				}

				if (dest.equals("1/0/2")) {
					m.myStop();
					m=new MyThread(pc);
					m.addAction(2);
					m.start();


				}
				
				if (dest.equals("1/0/3")) {
					m.myStop();
					m=new MyThread(pc);
					m.addAction(3);	
					m.start();

				}
				
				if (dest.equals("1/0/4")) {
					m.myStop();
					m=new MyThread(pc);
					m.addAction(4);	
					m.start();

				}
			}

			public void linkClosed(CloseEvent arg0) {
			}

		});

	}

	
}
