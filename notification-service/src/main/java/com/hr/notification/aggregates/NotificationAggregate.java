package com.hr.notification.aggregates;

import com.hr.common.commands.SendNotificationCommand;
import com.hr.common.events.NotificationSentEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class NotificationAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationAggregate.class);
    @AggregateIdentifier
    private String notificationUid;
    public NotificationAggregate(){
    }
    @CommandHandler
    public NotificationAggregate(final SendNotificationCommand command) {
        LOGGER.debug("Notification sent");
        AggregateLifecycle.apply(new NotificationSentEvent(command.getOrderCode()));
    }
    @EventSourcingHandler
    public void on(final NotificationSentEvent event) {
        LOGGER.debug("Handling notification sent event. Setting order code : "+event.getOrderCode());
        this.notificationUid = "NOTIFICATION-"+event.getOrderCode();
    }
}
