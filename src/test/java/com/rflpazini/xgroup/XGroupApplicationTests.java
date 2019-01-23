package com.rflpazini.xgroup;

import static org.assertj.core.api.Assertions.assertThat;

import com.rflpazini.xgroup.controller.MutantController;
import com.rflpazini.xgroup.controller.SentinelController;
import com.rflpazini.xgroup.controller.StatsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XGroupApplicationTests {
  @Autowired private SentinelController sentinelController;

  @Autowired private MutantController mutantController;

  @Autowired private StatsController statsController;

  @Test
  public void contextLoads() {
    assertThat(sentinelController).isNotNull();
    assertThat(mutantController).isNotNull();
    assertThat(statsController).isNotNull();
  }
}
