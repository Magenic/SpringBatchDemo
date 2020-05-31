package dev.springsolver.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class NasdaqItemProcessorTest {

    @Autowired
    private ItemProcessor<NasdaqTotalView, NasdaqTotalView> nasdaqTotalViewItemProcessor;

    @Test
    void testNasdaqItemProcessor() throws Exception {

    }
}