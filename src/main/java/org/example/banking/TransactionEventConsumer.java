package org.example.banking;

import org.example.banking.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class TransactionEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(TransactionEventConsumer.class);

    @KafkaListener(topics = "transactions", groupId = "user-mgmt-group")
    public void consume(TransactionEvent event) {
        log.info("Received transaction event: transactionId={}, amount={}, status={}",
                event.getTransactionId(), event.getAmount(), event.getStatus());

        // Future: trigger notification, update audit log, etc.
    }
}
