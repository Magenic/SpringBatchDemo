package dev.springsolver.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;


public class JsonItemProcessor implements ItemProcessor<NasdaqTotalView, NasdaqTotalView> {

    private static final Logger log = LoggerFactory.getLogger(JsonItemProcessor.class);


    @Override
    public NasdaqTotalView process(final NasdaqTotalView nasdaqTotalView) throws Exception {
        final Integer soupPartition = nasdaqTotalView.getSoupPartition();
        final Integer symbolLocate = nasdaqTotalView.getSymbolLocate();
        final Long uniqueTimestamp = nasdaqTotalView.getUniqueTimestamp();
        final Integer soupSequence = nasdaqTotalView.getSoupSequence();
        final String msgType = nasdaqTotalView.getMsgType();
        final Integer orderId = nasdaqTotalView.getOrderId();
        final String side = nasdaqTotalView.getSide();
        final String symbol = nasdaqTotalView.getSymbol();
        final Integer quantity = nasdaqTotalView.getQuantity();
        final Integer price = nasdaqTotalView.getPrice();
        final String mpid = nasdaqTotalView.getMpid();

        if(nasdaqTotalView.getMsgType() == null) {

            System.out.println("msgType is null - not going to transform");
        }

        else
            log.info("Current read" + nasdaqTotalView);
            msgType.replace("F", "G");
            nasdaqTotalView.setMsgType();
            System.out.println("Attempted to replace F with G, result: " + nasdaqTotalView.getMsgType() + " value");

            final NasdaqTotalView newNasdaqTotalView = new NasdaqTotalView(soupSequence,
                    symbolLocate,
                    soupPartition,
                    uniqueTimestamp,
                    msgType,
                    orderId,
                    side,
                    symbol,
                    quantity,
                    price,
                    mpid);

            log.info("Returning");
            return newNasdaqTotalView;
        }
    }