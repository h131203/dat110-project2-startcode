package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start

		// create a client object and use it to

		// - connect to the broker - user "sensor" as the user name
		// - publish the temperature(s)
		// - disconnect from the broker

		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT); //oppretter en klient
		client.connect(); //kobler til brokeren
		
		//ber temperatursensoren lese av temp og publisere dette 10 ganger, med 1 sek mellomrom.
		for (int i=0; i< COUNT; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int temp = sn.read(); //henter temperatur
			
			client.publish("temperature", "DISPLAY: "+temp);
			System.out.println("....READING:" + temp);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		client.disconnect(); //kobler av igjen
		
		// TODO - end

		System.out.println("Temperature device stopping ... ");

		
	}
}
