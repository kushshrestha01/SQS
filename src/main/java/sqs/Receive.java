package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.Date;
import java.util.List;

public class Receive {

    public static void main(String[] args) {

        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        String[] queueUrl = {System.getenv("QueueA"), System.getenv("QueueB"), System.getenv("QueueC")};


        // receive messages from the queue

        for (int i = 0; i < 3; i++) {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl[i]).withAttributeNames("All");
            receiveMessageRequest.setMaxNumberOfMessages(10);
            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            // delete messages from the queue
            for (Message m : messages) {
                System.out.println(m.getBody());
                sqs.deleteMessage(queueUrl[i], m.getReceiptHandle());
            }
        }
    }
}
