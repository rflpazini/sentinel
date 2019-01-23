package com.rflpazini.xgroup.service;

import com.rflpazini.xgroup.adapter.StatsAdapter;
import com.rflpazini.xgroup.repository.MutantRepository;
import com.rflpazini.xgroup.utils.Constants;
import java.text.DecimalFormat;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
  Logger logger = Logger.getLogger(StatsService.class.getName());

  @Autowired MutantRepository repository;

  @Transactional
  public StatsAdapter countMutants() {
    StatsAdapter statsAdapter = new StatsAdapter();

    try {
      Long mutantsTotal = repository.countMutantsBy(Constants.MUTANT_TYPE);
      Long humansTotal = repository.count() - mutantsTotal;
      double ratio = calculateRatio(mutantsTotal, humansTotal);

      statsAdapter.setCount_mutant_dna(mutantsTotal);
      statsAdapter.setCount_human_dna(humansTotal);
      statsAdapter.setRatio(ratio);
    } catch (Exception e) {
      statsAdapter.setCount_mutant_dna((long) 0);
      statsAdapter.setCount_human_dna((long) 0);
      statsAdapter.setRatio(0);

      System.out.println(e);
    }

    return statsAdapter;
  }

  public double calculateRatio(Long mutants, Long humans) {
    if (humans == 0) {
      return 0;
    }

    double ratio = (double) mutants / humans;
    DecimalFormat df = new DecimalFormat("#.##");

    return Double.valueOf(df.format(ratio));
  }
}
