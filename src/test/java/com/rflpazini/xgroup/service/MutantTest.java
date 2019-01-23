package com.rflpazini.xgroup.service;

import com.rflpazini.xgroup.model.Mutant;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantTest {
  @Autowired private MutantService mutantService;

  private List<String> mutantDnaList;

  private List<String> humanDnaList;

  private List<String> notSavedDnaList;

  @Before
  public void setup() {
    this.mutantDnaList = new ArrayList<>();
    this.mutantDnaList.add("ATGCGA");
    this.mutantDnaList.add("CAGTGC");
    this.mutantDnaList.add("TTATGT");
    this.mutantDnaList.add("AGAAGG");
    this.mutantDnaList.add("CCCCTA");
    this.mutantDnaList.add("TCACTG");

    this.humanDnaList = new ArrayList<>();
    this.humanDnaList.add("ATGCGA");
    this.humanDnaList.add("CAGTGC");
    this.humanDnaList.add("TTATTT");
    this.humanDnaList.add("AGACGG");
    this.humanDnaList.add("GCGTCA");
    this.humanDnaList.add("TCACTG");

    this.notSavedDnaList = new ArrayList<>();
    this.notSavedDnaList.add("ATGCGA");
    this.notSavedDnaList.add("CAGTAA");
    this.notSavedDnaList.add("TTATTT");
    this.notSavedDnaList.add("AGACGG");
    this.notSavedDnaList.add("GCGTCA");
    this.notSavedDnaList.add("TCACTG");
  }

  @Test
  public void checkConvertDnaListToArray() {
    List<String> mockList = new ArrayList<>();
    mockList.add("first");
    mockList.add("second");

    Assert.assertThat(
        mutantService.convertDnaListToArray(mockList), Matchers.instanceOf(String[].class));
  }

  @Test
  public void checkConvertDnaArrayTo2DArray() {
    String[] mock2DArray = {"a", "b", "c", "d"};

    Assert.assertThat(
        mutantService.convertDnaArrayTo2DArray(mock2DArray), Matchers.instanceOf(String[][].class));
  }

  @Test
  public void checkIfDnaWasStoredOnDb() {
    Mutant mockMutant = new Mutant();
    mockMutant.setDna(this.mutantDnaList);

    MutantService mockMutantService = Mockito.spy(MutantService.class);
    Mockito.doReturn(mockMutant).when(mockMutantService).checkIfDnaWasStoredOnDb(mockMutant);

    Assert.assertThat(
        mutantService.checkIfDnaWasStoredOnDb(mockMutant), Matchers.instanceOf(Mutant.class));
  }

  @Test
  public void checkIfDnaWasNotStoredOnDb() {
    Mutant mockMutant = new Mutant();
    mockMutant.setDna(this.notSavedDnaList);

    MutantService mockMutantService = Mockito.spy(MutantService.class);
    Mockito.doReturn(null).when(mockMutantService).checkIfDnaWasStoredOnDb(mockMutant);

    Assert.assertNull(mutantService.checkIfDnaWasStoredOnDb(mockMutant));
  }

  @Test
  public void shouldValidateMutantDna() {
    String[] dna = mutantService.convertDnaListToArray(this.mutantDnaList);
    String[][] mockDna = mutantService.convertDnaArrayTo2DArray(dna);

    MutantService mockMutantService = Mockito.spy(MutantService.class);
    Mockito.when(mockMutantService.searchMutantGenes(mockDna)).thenReturn(true);

    Assert.assertTrue(mutantService.searchMutantGenes(mockDna));
  }

  @Test
  public void shouldNotValidateMutantDna() {
    String[] dna = mutantService.convertDnaListToArray(this.humanDnaList);
    String[][] mockDna = mutantService.convertDnaArrayTo2DArray(dna);

    MutantService mockMutantService = Mockito.spy(MutantService.class);
    Mockito.when(mockMutantService.searchMutantGenes(mockDna)).thenReturn(false);

    Assert.assertFalse(mutantService.searchMutantGenes(mockDna));
  }
}
