package com.rflpazini.xgroup.adapter;

public class StatsAdapter {
  private Long count_mutant_dna;

  private Long count_human_dna;

  private double ratio;

  public Long getCount_mutant_dna() {
    return count_mutant_dna;
  }

  public void setCount_mutant_dna(Long count_mutant_dna) {
    this.count_mutant_dna = count_mutant_dna;
  }

  public Long getCount_human_dna() {
    return count_human_dna;
  }

  public void setCount_human_dna(Long count_human_dna) {
    this.count_human_dna = count_human_dna;
  }

  public double getRatio() {
    return ratio;
  }

  public void setRatio(double ratio) {
    this.ratio = ratio;
  }
}
