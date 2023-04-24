# include <stdio.h>
# include <string.h>
# include <time.h>
# include <math.h>

int subtracaoBinario(int decimal1, int decimal2, int *subtracao){
    // Inicializacao de variaveis
    int binario1[TAM_BINARIO] = {0};
    int binario2[TAM_BINARIO] = {0};
    zerarBinario(subtracao, TAM_BINARIO);
    int resto = 0;
    int posicao = 0;
    int carry = 0;

    // Se os valores tiverem o mesmo sinal sera feita uma subtracao comum
    if((decimal1 < 0 && decimal2 < 0) || (decimal1 > 0 && decimal2 > 0)){
        printf("\nSubtracao entre valores de sinais iguais %d e %d (Subtrai o maior do menor e mantem o sinal do maior)\n\n", decimal1, decimal2);

        // Supoe que o primeiro valor é o maior (nesse caso se mantera o sinal do maior)
        int maior = decimal1;
        int menor = decimal2;
        if(abs(decimal1) < abs(decimal2)){
            // Se o segundo valor for o maior, invertera o seu sinal e a resposta tera esse sinal
            printf("(maior valor esta em baixo, eh passado para cima e inverte o sinal da resposta)\n");
            maior = decimal2;
            menor = decimal1;
            if(decimal2 > 0){
                // o maior valor esta em baixo (se for positivo a resposta sera negativa e vice versa)
                subtracao[TAM_BINARIO-1] = 1;
            }
        }else{
            // o maior valor esta em cima (se for positivo a resposta sera positiva e vice versa)
            if(decimal1 < 0){
                subtracao[TAM_BINARIO-1] = 1;
            }
        }

        // Converte os numeros decimais para binario
        decimalBinarioVetor(maior, binario1, TAM_BINARIO);
        decimalBinarioVetor(menor, binario2, TAM_BINARIO);

        // Subtracao de binarios
        for(int i = 0; i < TAM_BINARIO-1; i++){ // Passa por todos os elementos do vetor
            // Binario de resposta na posicao atual = carry + binario1[i] - binario2[i]
            subtracao[i] = carry + binario1[i] - binario2[i];
            if(subtracao[i] == -1){
                // Se o valor carry + binario1[i] - binario2[i] for -1, o carry eh -1 e a resposta eh 1
                carry = -1;
                subtracao[i] = 1;
            }else if(subtracao[i] == -2){
                // Se o valor carry + binario1[i] - binario2[i] for -2, o carry eh -1 e a resposta eh 0
                carry = -1;
                subtracao[i] = 0;
            }else{
                // Se o valor carry + binario1[i] - binario2[i] for 0, o carry eh 0 e a resposta eh 0
                carry = 0;
            }
        }

        // Imprime a resposta
        imprimeBinarioSM(binario1, TAM_BINARIO);
        printf(" -> Binario 1\n - \n");
        imprimeBinarioSM(binario2, TAM_BINARIO);
        printf(" -> Binario 2\n   ");
        printf("\n -------------------------\n");
        imprimeBinarioSM(subtracao, TAM_BINARIO);
        
        // Retorna o valor em decimal
        return binarioVetorDecimal(subtracao, TAM_BINARIO);

    }else{
        // Se os valores tiverem o mesmo sinal, sera feita uma soma mantendo o sinal do primeiro
        printf("\nSubtracao entre valores de sinais diferentes %d e %d (Soma os modulos e mantem o sinal do primeiro)\n\n", decimal1, decimal2);

        decimal2 = decimal2 * -1; // Inverte o sinal do segundo valor
        // Faz uma soma de valores
        return somaBinario(decimal1, decimal2, subtracao);
    }
    
}