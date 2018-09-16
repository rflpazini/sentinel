package com.rflpazini.xgroup.adapter;

public class MutantAdapter {
    private Long id;

    private int type;

    private String[] dna;

    private String dnaJson;

    public Long getId() {
        return id;
    }

    public MutantAdapter setId(Long id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public String getDnaJson() {
        return dnaJson;
    }

    public void setDnaJson(String dnaJson) {
        this.dnaJson = dnaJson;
    }
}
