/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

public class Usuario extends Pessoa {

    private String timeTorcedor;

    public Usuario(String timeTorcedor, String nomePessoa, String emailPessoa, String senhaPessoa, String cpfPessoa, int idadePessoa) {
        super(nomePessoa, emailPessoa, senhaPessoa, cpfPessoa, idadePessoa);
        this.timeTorcedor = timeTorcedor;
    }

    public String getTimeTorcedor() {
        return timeTorcedor;
    }

    public void setTimeTorcedor(String timeTorcedor) {
        this.timeTorcedor = timeTorcedor;
    }
    
}
