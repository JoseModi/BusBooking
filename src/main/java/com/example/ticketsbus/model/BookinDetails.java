package com.example.ticketsbus.model;

import com.example.ticketsbus.util.ConstantUtil;
import com.itextpdf.kernel.color.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookinDetails extends com.codingerror.model.AddressDetails {
    private String billingInfoText= ConstantUtil.BILLING_INFO;
    private String shippingInfoText=ConstantUtil.SHIPPING_INFO;
    private String billingCompanyText=ConstantUtil.BILLING_COMPANY;
    private String billingCompany=ConstantUtil.EMPTY;
    private String billingNameText=ConstantUtil.BILLING_NAME;
    private String billingName=ConstantUtil.EMPTY;
    private String billingAddressText=ConstantUtil.BILLING_ADDRESS;
    private String billingAddress=ConstantUtil.EMPTY;
    private String billingEmailText=ConstantUtil.BILLING_EMAIL;
    private String billingEmail=ConstantUtil.EMPTY;
    
    private String shippingNameText=ConstantUtil.SHIPPING_NAME;
    private String shippingName=ConstantUtil.EMPTY;
    private String shippingAddressText=ConstantUtil.SHIPPING_ADDRESS;
    private String shippingAddress=ConstantUtil.EMPTY;
    private Color borderColor=Color.GRAY;

    public BookinDetails setBillingInfoText(String billingInfoText) {
        this.billingInfoText = billingInfoText;
        return this;
    }

    public BookinDetails setShippingInfoText(String shippingInfoText) {
        this.shippingInfoText = shippingInfoText;
    return this;
    }

    public BookinDetails setBillingCompanyText(String billingCompanyText) {
        this.billingCompanyText = billingCompanyText;
    return this;
    }

    public BookinDetails setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
    return this;
    }

    public BookinDetails setBillingNameText(String billingNameText) {
        this.billingNameText = billingNameText;
    return this;
    }


    public BookinDetails setBillingName(String billingName) {
        this.billingName = billingName;
    return this;
    }

    public BookinDetails setBillingAddressText(String billingAddressText) {
        this.billingAddressText = billingAddressText;
    return this;
    }

    public BookinDetails setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    return this;
    }

    public BookinDetails setBillingEmailText(String billingEmailText) {
        this.billingEmailText = billingEmailText;
    return this;
    }

    public BookinDetails setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    return this;
    }

    public BookinDetails setShippingNameText(String shippingNameText) {
        this.shippingNameText = shippingNameText;
    return this;
    }

    public BookinDetails setShippingName(String shippingName) {
        this.shippingName = shippingName;
    return this;
    }

    public BookinDetails setShippingAddressText(String shippingAddressText) {
        this.shippingAddressText = shippingAddressText;
    return this;
    }

    public BookinDetails setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    return this;
    }

    public BookinDetails setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    return this;
    }

    public BookinDetails build() {
        return this;
    }

    }
