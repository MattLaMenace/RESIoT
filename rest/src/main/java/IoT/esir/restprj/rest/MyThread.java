package IoT.esir.restprj.rest;

import java.lang.module.ResolutionException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

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

public  class MyThread extends Thread {

	// vitesse du chenillard
	private int vitesse;

	// boolean qui définit si on continue le thread ou pas
	private boolean go;

	// Process communicator
	private ProcessCommunicator pc;

	GroupAddress led1 = new GroupAddress("0/0/1");
	GroupAddress led2 = new GroupAddress("0/0/2");
	GroupAddress led3 = new GroupAddress("0/0/3");
	GroupAddress led4 = new GroupAddress("0/0/4");
	
	private Connection co;


	public MyThread(int vit) throws KNXException, InterruptedException {
		this.co = new Connection();
		this.pc = co.getPC();
		this.vitesse = vit;
		this.go = true;
		
	}
	
	public int getVitesse(){
		return this.vitesse;
	}
	
	public void setVitesse(int vit){
		this.vitesse=vit;
	}
	
	public void accelerer() {
		setVitesse(this.vitesse-100);
	}
	
	public void decelerer() {
		setVitesse(this.vitesse+100);
	}
	
	public void allumage(GroupAddress led, int vitesse)throws KNXException, InterruptedException{
		pc.write(led, true);		
		Thread.sleep(vitesse);  // on attend 500ms
		pc.write(led, false);
	}

	//On arrête le thread et réinitialise le tableau
	public void myStop() throws KNXTimeoutException, KNXLinkClosedException, KNXFormatException {
		go = false;
		eteindre();
	}

	public void chenillardV1() throws InterruptedException, KNXException {
		while(go){
				allumage(led1, this.vitesse);
				allumage(led2, this.vitesse);
				allumage(led3, this.vitesse);
				allumage(led4, this.vitesse);
		}
	}

	public void chenillardV2() throws InterruptedException, KNXException {
		while(go){
				allumage(led4, this.vitesse);
				allumage(led3, this.vitesse);
				allumage(led2, this.vitesse);
				allumage(led1, this.vitesse);
		}
	}
	
	public void chenillardV3() throws InterruptedException, KNXException {
		while(go){
				allumage(led1, this.vitesse);
				allumage(led3, this.vitesse);
				allumage(led2, this.vitesse);
				allumage(led4, this.vitesse);
		}
	}
	
	public void chenillardV4() throws InterruptedException, KNXException {
		while(go){
				allumage(led3, this.vitesse);
				allumage(led1, this.vitesse);
				allumage(led4, this.vitesse);
				allumage(led2, this.vitesse);
		}
	}
	
	public void allumer() throws InterruptedException, KNXException {
		pc.write(led1, true);
		pc.write(led2, true);
		pc.write(led3, true);
		pc.write(led4, true);
	}

	public void eteindre() throws KNXTimeoutException, KNXLinkClosedException, KNXFormatException {
		pc.write(led1, false);
		pc.write(led2, false);
		pc.write(led3, false);
		pc.write(led4, false);
	}
	
	// on ferme la connexion KNX
		public void close(){
			co.close();
		}
	
	

}