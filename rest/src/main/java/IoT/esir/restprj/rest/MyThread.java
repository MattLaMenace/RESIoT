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

public class MyThread extends Thread {

	// vitesse du chenillard
	private int vitesse = 1000;

	// boolean qui définit si on continue le thread ou pas
	private boolean go = true;

	// Process communicator
	private ProcessCommunicator pc;

	// Tableau qui regroupe les actions a effectuer
	private ArrayList<Integer> actions = new ArrayList<Integer>();

	public MyThread(ProcessCommunicator pc) {
		this.pc = pc;
	}

	public void run() {
		//Tant que l'on veut que notre Thread tourne
		while (go) {
		//On parcours le tableau d'actions pour savoir quoi effectuer
			for (Integer action : actions) {
				switch (action) {
				case 1:
					//Si le tableau est non vide, on éteind les leds avant de continuer
					if (!actions.isEmpty()) {
						try {
							eteindre();
							chenillardV1();
						} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException
								| InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
					//On réalise un chenillard
							chenillardV1();
						} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException
								| InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;

				case 2:
					//Si le tableau est non vide, on éteind les leds avant de continuer
					if (!actions.isEmpty()) {
						try {
							eteindre();
							chenillardV2();
						} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException
								| InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
					//On réalise le chenillard dans l'autre sens 
							chenillardV2();
						} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException
								| InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;

				case 3:
					try {
						//On éteind les leds
						eteindre();
					} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 4:
					//On modifie la vitesse du chenillard
					vitesse -= 100;
					try {

						chenillardV1();

					} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException
							| InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	//On ajoute une action au tableau
	public void addAction(int commande) {
		actions.add(commande);
	}


	//On arrête le thread et réinitialise le tableau
	public void myStop() {
		actions = new ArrayList<Integer>();
		go = false;
	}

	public void chenillardV1()
			throws KNXTimeoutException, KNXLinkClosedException, KNXFormatException, InterruptedException {
		pc.write(new GroupAddress("0/0/1"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/1"), false);
		pc.write(new GroupAddress("0/0/2"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/2"), false);
		pc.write(new GroupAddress("0/0/3"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/3"), false);
		pc.write(new GroupAddress("0/0/4"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/4"), false);
		Thread.sleep(vitesse);
	}

	public void chenillardV2()
			throws KNXTimeoutException, KNXLinkClosedException, KNXFormatException, InterruptedException {
		pc.write(new GroupAddress("0/0/4"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/4"), false);
		pc.write(new GroupAddress("0/0/3"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/3"), false);
		pc.write(new GroupAddress("0/0/2"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/2"), false);
		pc.write(new GroupAddress("0/0/1"), true);
		Thread.sleep(vitesse);
		pc.write(new GroupAddress("0/0/1"), false);
		Thread.sleep(vitesse);
	}

	public void eteindre() throws KNXTimeoutException, KNXLinkClosedException, KNXFormatException {
		pc.write(new GroupAddress("0/0/1"), false);
		pc.write(new GroupAddress("0/0/2"), false);
		pc.write(new GroupAddress("0/0/3"), false);
		pc.write(new GroupAddress("0/0/4"), false);
	}

}