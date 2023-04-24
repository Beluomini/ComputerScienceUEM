/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

public class Pessoa {

    private String nomePessoa;
    private String emailPessoa;
    private String senhaPessoa;
    private String cpfPessoa;       
    private int idadePessoa;

    public Pessoa(String nomePessoa, String emailPessoa, String senhaPessoa, String cpfPessoa, int idadePessoa) {
        this.nomePessoa = nomePessoa;
        this.emailPessoa = emailPessoa;
        this.senhaPessoa = senhaPessoa;
        this.cpfPessoa = cpfPessoa;
        this.idadePessoa = idadePessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public int getIdadePessoa() {
        return idadePessoa;
    }

    public void setIdadePessoa(int idadePessoa) {
        this.idadePessoa = idadePessoa;
    }
    
}
