package dev.springsolver.springbatch;

public class NasdaqTotalView {

    Integer soupPartition;
    Integer soupSequence;
    String msgType;
    Integer symbolLocate;
    Long uniqueTimestamp;
    Integer orderId;
    String side;
    Integer quantity;
    String symbol;
    Integer price;
    String mpid;

    public NasdaqTotalView() {

    }

    public NasdaqTotalView(Integer soupPartition, Integer soupSequence, String msgType, Integer symbolLocate,
                           Long uniqueTimestamp, Integer orderId, String side, Integer quantity, String symbol,
                           Integer price, String mpid) {

    }

    public NasdaqTotalView(String replace) {
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

    public String setMsgType(String msgType) {
        this.msgType = msgType;
        return msgType;
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

    public NasdaqTotalView(Integer soupPartition, Integer soupSequence, String msgType,
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
