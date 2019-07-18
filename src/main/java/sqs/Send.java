package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import java.util.Date;
import java.util.List;

public class Send {

    public static void main(String[] args) {

        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        String[] queueUrl = {System.getenv("QueueA"), System.getenv("QueueB"), System.getenv("QueueC")};

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 5; j++) {
                SendMessageRequest send_msg_request = new SendMessageRequest()
                        .withQueueUrl(queueUrl[i])
                        .withMessageBody("hello world no:" + j)
                        .withDelaySeconds(5);
                sqs.sendMessage(send_msg_request);
            }
        }
    }
}
