/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

public class Admin extends Pessoa {

    private String funcao;

    public Admin(String funcao, String nomePessoa, String emailPessoa, String senhaPessoa, String cpfPessoa, int idadePessoa) {
        super(nomePessoa, emailPessoa, senhaPessoa, cpfPessoa, idadePessoa);
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
}
