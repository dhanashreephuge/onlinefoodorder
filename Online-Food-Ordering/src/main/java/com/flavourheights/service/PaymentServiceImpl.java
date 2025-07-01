package com.flavourheights.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flavourheights.model.Order;
import com.flavourheights.response.PaymentResponse;
import com.stripe.Stripe;
//import com.stripe.model.billingportal.Session;
import com.stripe.model.checkout.Session; // ✅ CORRECT
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.exception.StripeException;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Value("${strip.api.key}")
	private String stripeSecretKey;

	@Override
	public PaymentResponse createPaymentLink(Order order) throws StripeException {
		Stripe.apiKey = stripeSecretKey;

		SessionCreateParams params= SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:3000/payment/success"+order.getId())
				.setCancelUrl("http://localhost:3000/payment/fail")
				.addLineItem(SessionCreateParams.LineItem.builder()
						.setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("usd")
								.setUnitAmount((long) order.getTotalAmount()*100)
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("yummy food")
										.build())
								.build()
						)
						.build()
				)
				.build();
		
		Session session = Session.create(params);
		PaymentResponse res= new PaymentResponse();
		res.setPayment_url(session.getUrl());
		
		return res;
	}

}
