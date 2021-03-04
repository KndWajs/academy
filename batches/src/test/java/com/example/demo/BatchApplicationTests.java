package com.example.demo;

import com.example.demo.batch.BatchConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.AssertFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@EnableBatchProcessing
public class BatchApplicationTests {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private Job changeCaffeeNames;

    private static final String EXPECTED_OUTPUT = "target/classes/coffee-list-expected-output.csv";
    private static final String TEST_OUTPUT = "target/classes/coffee-list-output.csv";

    @BeforeEach
    public void clearJobExecutions() {
//		this.jobLauncherTestUtils = new JobLauncherTestUtils();
    }

    @Test
    public void testBatch() throws Exception {
        // given
        FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
        FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

        // when
        JobExecution jobExecution = jobLauncher.run(changeCaffeeNames, defaultJobParameters());
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        // then
        assertThat(actualJobInstance.getJobName()).isEqualTo(BatchConfiguration.JOB_NAME);
        assertThat(actualJobExitStatus.getExitCode()).isEqualTo("COMPLETED");
        AssertFile.assertFileEquals(expectedResult, actualResult);
    }

    private JobParameters defaultJobParameters() {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
//		paramsBuilder.addString("file.input", "bla");
//		paramsBuilder.addString("file.output", "bla");
        return paramsBuilder.toJobParameters();
    }

}
