package com.hr.order.aggregate;

import com.hr.common.commands.CreateOrderCommand;
import com.hr.common.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class OrderAggregate {
    private static final Logger LOG = LoggerFactory.getLogger(OrderAggregate.class);
    @AggregateIdentifier
    private String orderUid;
    public OrderAggregate(){
    }
    @CommandHandler
    public OrderAggregate(final CreateOrderCommand command) {
        LOG.debug("Create order");
        AggregateLifecycle.apply(new OrderCreatedEvent(command.getOrderCode(), command.getPaymentInfo(), command.getUserEmail()));
    }

    @EventSourcingHandler
    public void on(final OrderCreatedEvent event) {
        LOG.debug("Handling order created event. Setting order code : "+event.getOrderCode());
        this.orderUid = "ORDER-"+event.getOrderCode();
    }
}
