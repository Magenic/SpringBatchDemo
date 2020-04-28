package dev.springsolver.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;


public class JsonItemProcessor implements ItemProcessor<NasdaqTotalView, NasdaqTotalView> {

    private static final Logger log = LoggerFactory.getLogger(JsonItemProcessor.class);

    @Override
    public NasdaqTotalView process(final NasdaqTotalView nasdaqTotalView) throws Exception {
        final String msgType = nasdaqTotalView.getMsgType().toLowerCase();

        final NasdaqTotalView transformedNasdaqTotalView = new NasdaqTotalView();

        log.info("Converting (" + nasdaqTotalView + ") into (" + transformedNasdaqTotalView + ")");

        return transformedNasdaqTotalView;
    }
}
