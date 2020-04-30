package dev.springsolver.springbatch;

import java.sql.Date;

public class NasdaqTotalView {

    private Integer soupSequence;
    private Integer symbolLocate;
    private Integer soupPartition;
    private Long uniqueTimestamp;
    private String msgType;
    private Integer orderId;
    private String side;
    private String symbol;
    private Integer quantity;
    private Integer price;
    private String mpid;
    

    public NasdaqTotalView() {

    }

    public NasdaqTotalView(Integer soupSequence, Integer symbolLocate, Integer soupPartition, Long uniqueTimestamp, String msgType, Integer orderId, String side, String symbol, Integer quantity, Integer price, String mpid) {
    }


    public Integer getSoupPartition() {
        return soupPartition;
    }

    public void setSoupPartition(Integer soupPartition) {
        this.soupPartition = soupPartition;
    }

    public Integer getSoupSequence() {
        return soupSequence;
    }

    public void setSoupSequence(Integer soupSequence) {
        this.soupSequence = soupSequence;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType() {
        this.msgType = msgType;
    }

    public Integer getSymbolLocate() {
        return symbolLocate;
    }

    public void setSymbolLocate(Integer symbolLocate) {
        this.symbolLocate = symbolLocate;
    }

    public Long getUniqueTimestamp() {
        return uniqueTimestamp;
    }

    public void setUniqueTimestamp(Long uniqueTimestamp) {
        this.uniqueTimestamp = uniqueTimestamp;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }

    public NasdaqTotalView(int record_id, int soup_partition, int soup_sequence, String msg_type, int symbol_locate, Date unique_timestamp, int order_id, String side, int quantity, String mpid, String symbol, int price) {}

    public NasdaqTotalView(Integer rowId, Integer soupPartition, Integer soupSequence, String msgType,
                           Integer symbolLocate, Long uniqueTimestamp, Integer orderId, String side,
                           Integer quantity, String mpid, String symbol, Integer price) {
        this.soupPartition = soupPartition;
        this.soupSequence = soupSequence;
        this.msgType = msgType;
        this.symbolLocate = symbolLocate;
        this.uniqueTimestamp = uniqueTimestamp;
        this.orderId = orderId;
        this.side = side;
        this.quantity = quantity;
        this.symbol = symbol;
        this.price = price;
        this.mpid = mpid;
    }

    @Override
    public String toString() {
        return "soupPartition: " + soupPartition + ", soupSequence: " + soupSequence + ", msgType: " + msgType +
                ", symbolLocate: " + symbolLocate + ", uniqueTimestamp: " + uniqueTimestamp + ", orderId: " + orderId + ", side: " + side + ", quantity: " + quantity + ", symbol: " + symbol + ", price: " + price + ", mpid: " + mpid;
    }
}
