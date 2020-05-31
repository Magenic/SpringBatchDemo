package dev.springsolver.springbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class NasdaqItemProcessor implements ItemProcessor<NasdaqTotalView, NasdaqTotalView> {
    private Log logger = LogFactory.getLog(NasdaqItemProcessor.class);

    @Override
    public NasdaqTotalView process(NasdaqTotalView nasdaqTotalView) throws Exception {
        if (nasdaqTotalView.getSoupSequence() == 123) {
            try {
                logger.info("Record located: " + nasdaqTotalView);
                String msgType = nasdaqTotalView.setMsgType("Booya");
                logger.info("Value of msgType: " + msgType + " Check transform: " + nasdaqTotalView.getMsgType());
            }
            catch(Exception ex) {
                logger.info("Unable to process msgType");
                logger.trace("Trace: " + nasdaqTotalView);
                throw new Exception("Item processor is out of gas.");
            }
        }
        return nasdaqTotalView;
    }
}

