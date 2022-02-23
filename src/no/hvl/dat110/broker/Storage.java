package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		// See ClientSession class
		
		ClientSession session = new ClientSession(user, connection); // dette objektet skal inn i hashmappen av aktive sessions
		
		clients.put(user, session); //setter det inn i listen av aktive clients (hashmappet). Dvs den er nå registert som en aktiv session hos brokeren
		
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void removeClientSession(String user) {

		// TODO: disconnet the client (user) 
		// and remove client session for user from the storage
		
		ClientSession session = clients.get(user);
		
		//disconneter og fjerner fra hashmappen med aktive sessions/brukere.
		session.disconnect();
		clients.remove(user, session);
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage
		Set<String> set = ConcurrentHashMap.newKeySet(); //nytt set med strings som lager plass til de users som subber på denne topic?
		subscriptions.put(topic, set);

		//throw new UnsupportedOperationException(TODO.method());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage

		subscriptions.remove(topic);
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		
		/**
		 * henter ut topicen i hashmappet, og legger til useren i settet av string(users/subscribers) mappet til denne keyen (topic). key = topic, value = subscriber
		 */
		subscriptions.get(topic).add(user);
		
	
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic
		/**
		 * henter ut topic, og fjerne deretter brukeren fra settet som ligger som values på den topicen (value= en subscriber)
		 */
		
		subscriptions.get(topic).remove(user);

		//throw new UnsupportedOperationException(TODO.method());
	}
}
