# include <stdio.h>
# include <string.h>
# include <time.h>

int multiplicacaoBinario(int decimal1, int decimal2, int *produto){
    // Inicializacao de variaveis
    int binario1[TAM_BINARIO] = {0};
    int binario2[TAM_BINARIO] = {0};
    int aux[TAM_BINARIO] = {0};
    zerarBinario(produto, TAM_BINARIO*2);
    int carry = 0;
    int posicao = TAM_BINARIO-1;

    // Converte os numeros decimais para binario e transforma em positivos
    decimalBinarioVetor(decimal1, binario1, TAM_BINARIO);
    decimalBinarioVetor(decimal2, binario2, TAM_BINARIO);
    binario1[TAM_BINARIO-1] = 0;
    binario2[TAM_BINARIO-1] = 0;

    // Laco de repeticao que fara um ciclo para cada bit do binario
    while(posicao != 0){
        // Imprime informações do ciclo
        printf("\n----------------- CICLO %d ------------------\n", TAM_BINARIO-posicao);
        printf("\nC = %d | A = ", carry);
        imprimeBinario(aux, TAM_BINARIO-1);
        printf(" | Q = ");
        imprimeBinario(binario1, TAM_BINARIO-1);
        printf(" | M = ");
        imprimeBinario(binario2, TAM_BINARIO-1);

        if(binario1[0] == 1){ // Se o bit menos significativo for 1
            if(binarioVetorDecimal(aux, TAM_BINARIO) + binarioVetorDecimal(binario2, TAM_BINARIO) > 32767){
                // Se o resultado for maior que o numero maximo para um binario, o carry sera 1
                carry = 1;
            }
            // Faz a soma dos binarios A com M e atribui para A (aux)
            somaBinario(binarioVetorDecimal(aux, TAM_BINARIO), binarioVetorDecimal(binario2, TAM_BINARIO), aux);

            //Imprime os dados novos
            printf("\nC = %d | A = ", carry);
            imprimeBinario(aux, TAM_BINARIO-1);
            printf(" | Q = ");
            imprimeBinario(binario1, TAM_BINARIO-1);
            printf(" | M = ");
            imprimeBinario(binario2, TAM_BINARIO-1);

        }

        // Processo de move para a direita
        // Move os valores de Q 1 para a direita
        for(int i = 0; i < TAM_BINARIO-1; i++){
            binario1[i] = binario1[i+1];
        }
        // O primeiro bit de Q recebe o ultimo de A
        binario1[TAM_BINARIO-2] = aux[0];
        // Move os valores de A 1 para a direita
        for(int i = 0; i < TAM_BINARIO-1; i++){
            aux[i] = aux[i+1];
        }
        // O primeiro bit de A recebe o carry
        aux[TAM_BINARIO-2] = carry;
        // O carry recebe 0
        carry = 0;
        // Decrementa o contador para ir para o proximo ciclo
        posicao--;
        printf("\n---------------------------------------------\n", posicao+1);
    }

    // Une os valores de A e Q para o produto
    for(int i = 0; i < TAM_BINARIO*2; i++){
        if(i < TAM_BINARIO-1){
            produto[i] = binario1[i];
        }else{
            produto[i] = aux[i+1-TAM_BINARIO];
        }
    }

    // Trata o sinal do produto
    if((decimal1 < 0 && decimal2 > 0) || (decimal1 > 0 && decimal2 < 0)){
        // Se os numeros tiverem sinais diferentes, o sinal do produto sera negativo
        produto[TAM_BINARIO*2-1] = 1;
    }else{
        // Se os numeros tiverem sinais iguais, o sinal do produto sera positivo
        produto[TAM_BINARIO*2-1] = 0;
    }

    // Retorna o produto em decimal
    return binarioVetorDecimal(produto, TAM_BINARIO*2);
}