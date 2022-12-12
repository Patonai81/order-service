package hu.webuni.orderservice.model;

public enum OrderStatus {

    PENDING("P"), CONFIRMED("C"), DECLINED("D"),DELIVERED("O"),SHIPMENT_FAILED("F");

    private String code;

    private OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}