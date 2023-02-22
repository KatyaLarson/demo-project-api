package com.demo.jmeter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
public class JemeterTest {

    @Test
    @Tag("smoke")
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(10, 2,
                        httpSampler("https://myservice")
                )
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

}
