# include <stdio.h>
# include <string.h>
# include <time.h>

int divisaoBinario(int decimal1, int decimal2, int *quociente, int *resto){
    // Inicialização de variáveis
    int binario1[TAM_BINARIO] = {0};
    int binario2[TAM_BINARIO] = {0};
    zerarBinario(quociente, TAM_BINARIO);
    zerarBinario(resto, TAM_BINARIO);
    
    // Conversão de decimal para binário
    decimalBinarioVetor(decimal1, binario1, TAM_BINARIO);
    decimalBinarioVetor(decimal2, binario2, TAM_BINARIO);

    // Verifica se o primeiro valor é maior que o segundo
    if(abs(decimal1) < abs(decimal2)){
        // Se o segundo for maior nao tem como dividir e o resto é o primeiro
        decimalBinarioVetor(decimal1, resto, TAM_BINARIO);
    }else if(decimal2 == 0){
        // Se o segundo valor for zero, não é possivel fazer a operacao
        return 0;   
    }else{
        // Se o segundo nao for maior que o primeiro e diferente de 0

        if((decimal1 < 0 && decimal2 > 0) || (decimal1 > 0 && decimal2 < 0)){
            // Se os sinais forem diferentes, o resultado sera negativo
            quociente[TAM_BINARIO-1] = 1;
        }

        binario1[TAM_BINARIO-1] = 0;
        binario2[TAM_BINARIO-1] = 0;
        // O resto recebe o valor total a ser dividido
        copiaBinario(resto, binario1);

        // Contador que vera quantas subtracoes parciais serao possiveis de fazer
        int quocienteDecimal = 0;

        // Loop que faz as subtracoes parciais enquanto o resto for maior ou igual que o segundo valor
        while(verificaIgualMaior(resto, binario2) == 1){ // Verifica se o resto é maior ou igual ao segundo valor
            // Subtrai e atualiza a variavel resto com a resposta
            subtracaoBinario(binarioVetorDecimal(resto, TAM_BINARIO), binarioVetorDecimal(binario2, TAM_BINARIO), resto);
            // Incrementa o contador
            quocienteDecimal++;
        }
        
        if(decimal1 < 0){
            // Se o primeiro valor for negativo, o resto sera negativo
            resto[TAM_BINARIO-1] = 1;
        }
        // Tranforma o quociente decimal para binario
        decimalBinarioVetor(quocienteDecimal, quociente, TAM_BINARIO);

    }

    // Retorna o quociente em decimal
    return binarioVetorDecimal(quociente, TAM_BINARIO);


}