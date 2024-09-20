package com.hr.payment.aggregates;

import com.hr.common.commands.ProcessPaymentCommand;
import com.hr.common.events.PaymentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class PaymentAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAggregate.class);
    @AggregateIdentifier
    private String paymentUid;

    public PaymentAggregate() {

    }

    @CommandHandler
    public PaymentAggregate(final ProcessPaymentCommand command) {
        LOGGER.debug("Payment processing is completed");
        AggregateLifecycle.apply(new PaymentProcessedEvent(command.getOrderCode(), command.getUserEmail()));
    }

    @EventSourcingHandler
    public void on(final PaymentProcessedEvent event) {
        LOGGER.debug("Handling payment processed event. Setting order code : " + event.getOrderCode());
        this.paymentUid = "PAYMENT-" + event.getOrderCode();
    }
}
