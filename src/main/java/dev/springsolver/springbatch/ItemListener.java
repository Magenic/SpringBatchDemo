package dev.springsolver.springbatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemListener extends ItemListenerSupport {

    private static Log logger = LogFactory.getLog("item.error");
    private Throwable ItemReaderException;

    public void onReadError() throws Exception  {
            logger.error("ItemReader failed");
            throw new Exception();

        }

        public void onItemProcessorError(Exception ex) {
            logger.error("Encountered error during processing", ex);
        }

        public void onWriteError(Exception ex, List items) {

            logger.error("Encountered error on write", ex);
        }
    }

