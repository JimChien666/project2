package com.iii.eeit124.util;

import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.InvoiceObj;

public class PaymentCall {
	public static String genAioCheckOutALL(UUID uid, String buyerId, String total, String productName, String quantity, String price, String buyerName, String buyerAddress, String buyerTel, String date){
		
		AllInOne all = new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		InvoiceObj invoice = new InvoiceObj();
		System.out.println(uid);
		obj.setMerchantTradeNo(uid.toString().replaceAll("-", "").substring(0, 20));
		obj.setMerchantTradeDate(date);
		obj.setTotalAmount(total);
		obj.setTradeDesc("test Description");
		obj.setItemName(productName);
		obj.setReturnURL("https://f9bf69860689.ngrok.io/team6/TestApi");
		obj.setClientBackURL("http://localhost:8080/team6/goOrderSuccessPage");
		obj.setNeedExtraPaidInfo("Y");
		obj.setStoreExpireDate("3");
		obj.setInvoiceMark("N");
		obj.setChooseSubPayment("Credit");
		invoice.setRelateNumber(uid.toString().replaceAll("-", "").substring(0, 20));
		invoice.setCustomerID(buyerId);
		invoice.setCarruerType("1");
		invoice.setTaxType("1");
		invoice.setCarruerNum("");
		invoice.setDonation("0");
		invoice.setLoveCode("X123456");
		invoice.setPrint("0");
		invoice.setCustomerName(buyerName);
		invoice.setCustomerAddr(buyerAddress);
		invoice.setCustomerPhone(buyerTel);
		invoice.setDelayDay("1");
		invoice.setInvType("07");
		invoice.setInvoiceItemName("test");
		invoice.setInvoiceItemCount(quantity);
		invoice.setInvoiceItemWord("入");
		invoice.setInvoiceItemPrice(price);
		invoice.setInvoiceItemTaxType("1");
		String form = all.aioCheckOut(obj, invoice);
		return form;
	}
}
