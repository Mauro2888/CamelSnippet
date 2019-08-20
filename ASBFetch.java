package main;


import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;

import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.implementation.QueueDescription;
import com.microsoft.windowsazure.services.servicebus.implementation.TopicDescription;
import com.microsoft.windowsazure.services.servicebus.models.*;
import com.sun.xml.bind.v2.TODO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.*;

public class Main {

	private final static String connectionString = "";

	public static void main(String[] args) throws Exception {

		//System.out.println(messageCount("queueTest"));
		getQueues(connectionToAsb()).forEach(item->System.out.println(item));
		getQueues(connectionToAsb()).forEach(item -> System.out.println(item));

	}

	/**
	 * This method retrive all the queues from ASB
	 * @param serviceBusContract Service manager connection
	 * @return list of queues
	 */
	private static List<String> getQueues(ServiceBusContract serviceBusContract) {
		int size = 0;
		List<String> exisingQueues = new ArrayList<>();
		try {
			while (exisingQueues.size() == size){
				ListQueuesOptions listQueuesOptions = new ListQueuesOptions().setSkip(size);
				ListQueuesResult queueList2 = serviceBusContract.listQueues(listQueuesOptions);
				for (QueueInfo queueInfo : queueList2.getItems()) {
					exisingQueues.add("Queue " + queueInfo.getUri().getPath() + "  Count:" + queueInfo.getMessageCount()
							+ " Active: " + queueInfo.getCountDetails().getActiveMessageCount() + " DeadLetter " + queueInfo.getCountDetails().getDeadLetterMessageCount());
				}
				size += 100;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return exisingQueues;
	}

	/**
	 * Get messageCount for single queue
	 *
	 * @param queueName queue
	 * @return result
	 * @throws Exception ex
	 */
	private static long singleQueuesCount(String queueName) throws Exception {
		return connectionToAsb().getQueue(queueName).getValue().getMessageCount();
	}

	/**
	 * make connection
	 *
	 * @return service reference
	 */
	private static ServiceBusContract connectionToAsb() {
		Configuration configuration = new Configuration();
		ServiceBusConfiguration.configureWithConnectionString(null, configuration, connectionString);
		return ServiceBusService.create(configuration);
	}

	/**
	 * This method retrive all topics from ASB
	 * @param serviceBusContract connector
	 * @throws ServiceException topic Path
	 */
	private static void getTopics(ServiceBusContract serviceBusContract) throws ServiceException {
		for (TopicInfo i : serviceBusContract.listTopics().getItems()) {
			System.out.println(i.getPath());
		}
	}

}


