package com.mylld.amazon.locker.request;

public class PackageDetails {

    private String packageName;
    private String recipientName;
    private String packageSize;
    private String recipientPhoneNo;
    private String recipientAddress;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getRecipientPhoneNo() {
        return recipientPhoneNo;
    }

    public void setRecipientPhoneNo(String recipientPhoneNo) {
        this.recipientPhoneNo = recipientPhoneNo;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
}
