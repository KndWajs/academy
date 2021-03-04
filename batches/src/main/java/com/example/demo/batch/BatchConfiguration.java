package com.example.demo.batch;

import com.example.demo.Coffee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    public static final String JOB_NAME = "changeCaffeeNames";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    @Value("${file.output}")
    private String fileOutput;

    @Bean
    @StepScope
    public FlatFileItemReader<Coffee> reader() {
        BeanWrapperFieldSetMapper<Coffee> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Coffee.class);


        return new FlatFileItemReaderBuilder<Coffee>().name("coffeeItemReader")
                .resource(new FileSystemResource(fileInput))
                .delimited()
                .names("brand", "origin", "characteristics")
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Coffee> secondReader() {
        return new SomeReader();
    }

    @Bean
    public CoffeeItemProcessor processor() {
        return new CoffeeItemProcessor();
    }

    @Bean
    @StepScope
    public JsonFileItemWriter<Coffee> writer() {
        JsonFileItemWriterBuilder<Coffee> builder = new JsonFileItemWriterBuilder<>();
        JacksonJsonObjectMarshaller<Coffee> marshaller = new JacksonJsonObjectMarshaller<>();
        return builder
                .name("coffeeItemWriter")
                .jsonObjectMarshaller(marshaller)
                .resource(new FileSystemResource(fileOutput))
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<Coffee> secondWriter() {
        return new SomeWriter();
    }

    @Bean
    public Job importUserJob(Step step1, Step step2) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(FlatFileItemReader<Coffee> reader, JsonFileItemWriter<Coffee> writer) {
        return stepBuilderFactory.get("step1")
                .<Coffee, Coffee>chunk(10)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Coffee, Coffee>chunk(10)
                .reader(secondReader())
                .processor(processor())
                .writer(secondWriter())
                .build();
    }
}