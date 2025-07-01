package com.flavourheights.service;

import com.flavourheights.model.Order;
import com.flavourheights.response.PaymentResponse;

public interface PaymentService {

	public PaymentResponse createPaymentLink(Order order);
}
