package com.hr.order.saga;

import com.hr.common.commands.ProcessPaymentCommand;
import com.hr.common.commands.SendNotificationCommand;
import com.hr.common.events.NotificationSentEvent;
import com.hr.common.events.OrderCreatedEvent;
import com.hr.common.events.PaymentProcessedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderSaga {

	private static final Logger LOG = LoggerFactory.getLogger(OrderSaga.class);

	@Autowired
	private CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderCode")
	public void handle(final OrderCreatedEvent event) {
		LOG.debug("------- SAGA - Start -------");
		LOG.debug("Order is placed. Now process the payment");
		commandGateway.send(new ProcessPaymentCommand(event.getOrderCode(), event.getPaymentInfo(), event.getUserEmail()));
	}

	@SagaEventHandler(associationProperty = "orderCode")
	public void handle(final PaymentProcessedEvent event) {
		LOG.debug("Payment is processed. Now send notification");
		commandGateway.send(new SendNotificationCommand(event.getOrderCode(), event.getUserEmail()));
	}

	@SagaEventHandler(associationProperty = "orderCode")
	@EndSaga
	public void handle(final NotificationSentEvent event) {
		LOG.debug("Notification is sent");
		LOG.debug("------- SAGA - End -------");
	}


}
