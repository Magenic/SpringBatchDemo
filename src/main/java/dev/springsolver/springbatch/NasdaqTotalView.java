package dev.springsolver.springbatch;

public class NasdaqTotalView {
    private Integer soupPartition;
    private Integer soupSequence;
    private String msgType;
    private Integer symbolLocate;
    private Integer uniqueTimestamp;
    private Integer orderId;
    private String side;
    private Integer quantity;
    private String symbol;
    private Integer price;
    private String mpid;

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

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getSymbolLocate() {
        return symbolLocate;
    }

    public void setSymbolLocate(Integer symbolLocate) {
        this.symbolLocate = symbolLocate;
    }

    public Integer getUniqueTimestamp() {
        return uniqueTimestamp;
    }

    public void setUniqueTimestamp(Integer uniqueTimestamp) {
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

    public NasdaqTotalView() {}

    public NasdaqTotalView(Integer rowId, Integer soupPartition, Integer soupSequence, String msgType,
                           Integer symbolLocate, Integer uniqueTimestamp, Integer orderId, String side,
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
