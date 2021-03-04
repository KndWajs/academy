package com.example.demo.batch;

import com.example.demo.Coffee;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public class SomeReader implements ItemReader<Coffee> {

    private LinkedBlockingQueue<Coffee> queue;

    @StepScope
    public SomeReader() {
    }

    @AfterStep
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public Coffee read() throws Exception {
        if (this.queue == null) {
            this.queue = new LinkedBlockingQueue<>(readAll());
        }

        return this.queue.poll();
    }

    private Collection<Coffee> readAll() {
        return Arrays.asList(
                new Coffee("SomeBrand", "SomeOrigin", "SomeCharacteristics"),
                new Coffee("SomeBrand2", "SomeOrigin2", "SomeCharacteristics2"));
    }
}
