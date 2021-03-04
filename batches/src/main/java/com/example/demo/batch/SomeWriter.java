package com.example.demo.batch;

import com.example.demo.Coffee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SomeWriter implements ItemWriter<Coffee> {

    @StepScope
    public SomeWriter() {
    }

//  @BeforeStep
//  public void beforeStep(StepExecution stepExecution) {
//
//    this.stepExecution = stepExecution;
//    readJobParameters(stepExecution);
//  }


    @Override
    public void write(List<? extends Coffee> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
