package com.flavourheights.service;

import com.flavourheights.model.Order;
import com.flavourheights.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

	public PaymentResponse createPaymentLink(Order order) throws StripeException;
	
	
}
