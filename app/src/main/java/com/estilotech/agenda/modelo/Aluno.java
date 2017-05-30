package com.estilotech.agenda.modelo;

import java.io.Serializable;

/**
 * Created by vinic on 21/05/2017.
 */

public class Aluno implements Serializable {

    public Aluno() {
        super();
    }
    public Aluno(Long id, String nome, String endereco, String telefone, String site, Double nota) {

        this.id = id ;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.site = site;
        this.nota = nota;

    }

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        if(!site.startsWith("http://")){
            site = "http://" + site;
        }
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}
