package com.rflpazini.xgroup.service;

import com.rflpazini.xgroup.adapter.StatsAdapter;
import com.rflpazini.xgroup.repository.MutantRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsTest {

  @Autowired StatsService statsService;

  @Test
  public void shouldCalculateRatio() {
    long mutant = 40;
    long humans = 100;
    double expected = 0.4;

    Assert.assertEquals(statsService.calculateRatio(mutant, humans), expected, 0.001);
  }

  @Test
  public void shouldNotDevideByZero() {
    long mutant = 40;
    long humans = 0;
    double expected = 0;

    Assert.assertEquals(expected, statsService.calculateRatio(mutant, humans), 0.001);
  }

  @Test
  public void shouldCalculateStats() {
    long mutants = 1;
    long humans = 1;
    double ratio = 1.0;

    StatsAdapter statsAdapterExpec = new StatsAdapter();
    statsAdapterExpec.setCount_human_dna(humans);
    statsAdapterExpec.setCount_mutant_dna(mutants);
    statsAdapterExpec.setRatio(ratio);

    MutantRepository mockMutantRepository = Mockito.spy(MutantRepository.class);
    Mockito.when(mockMutantRepository.countMutantsBy(1)).thenReturn(mutants);
    Mockito.when(mockMutantRepository.count()).thenReturn(mutants + humans);

    StatsAdapter actual = statsService.countMutants();

    Assert.assertEquals(statsAdapterExpec.getCount_human_dna(), actual.getCount_human_dna());
    Assert.assertEquals(statsAdapterExpec.getCount_mutant_dna(), actual.getCount_mutant_dna());
    Assert.assertEquals(statsAdapterExpec.getRatio(), actual.getRatio(), 0.001);
  }
}
