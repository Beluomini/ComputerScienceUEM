# include <stdio.h>
# include <string.h>
# include <time.h>

#define TAM_BINARIO 16

// Funcao que zera um vetor que representa um binario
int zerarBinario(int *binario, int tamanho){
    // Passa por todos os elementos do vetor e tranforma em 0
    for(int i = 0; i < tamanho; i++){
        binario[i] = 0;
    }
    return 0;
}

// Funcao que transforma um numero decimal em binario
int decimalBinarioVetor(int decimal, int *binario, int tamanho){
    if(decimal < 0){ 
        // Se o numero for negativo acrescenta 1 a posicao do sinal magnitude
        binario[tamanho-1] = 1;
        decimal = decimal * -1;
    }else if(decimal == 0){
        // Se o numero for 0, retorna 0 e zera o binario
        zerarBinario(binario, tamanho);
        return 0;
    }

    int resto = decimal;
    int posicao = 0;

    while(resto > 1){
        // Enquanto o resto for maior que 1, o numero eh dividido por 2
        binario[posicao] = resto % 2;
        // O resto dessa divisao eh o valor do binario na posicao atual
        resto = resto / 2;
        posicao++;
    }
    binario[posicao] = resto;

    // Por fim o binario é escrito de manira inversa, com o sinal magnitude no final (ultima posicao)
    return 0;
}

// Funcao que tranforma um binario em forma de vetor para decimal
int binarioVetorDecimal(int *binario, int tamanho){
    int posicao = 0;
    int decimal = 0;

    for(int i = 0; i < tamanho-1; i++){
        // Passa por todos os elementos do vetor
        if(binario[i] == 1){
            // Se o elemento for 1, o decimal eh incrementado por 2^posicao
            decimal = decimal + pow(2, i);
        }
    }
    
    // Por fim verifica o sinal magnitude para definir se o numero eh negativo ou nao
    if(binario[tamanho-1] == 1){
        decimal = decimal * -1;
    }
    
    // Retorna o numero decimal
    return decimal;
}

// Funcao que verifica se o binario1 eh maior ou igual ao binario2
int verificaIgualMaior(int *binario1, int *binario2){
    int maior = 0;
    for(int i = 14; i >= 0; i--){
        // Passa por todos os elementos do binario do mais significativo ao menos significativo (com excecao do sinal magnitude)
        if(binario1[i] > binario2[i]){
            // Se tiver um bit mais significativo ja é maior
            return 1;
        }else if(binario1[i] == binario2[i]){
            // Se o bit for igual, continua verificando, mas com a verificacao de igualdade ativa
            maior = 1;
        }else{
            // Se tiver um bit mais significativo no segundo numro o primeiro ja eh menor
            return 0;
        }
    }
    // Retorna 1 se o primeiro numero for maior ou igual ao segundo e 0 se for menor
    return maior;
}

// Funcao que copia pro binario1 o binario2
int copiaBinario(int *binario1, int *binario2){
    for(int i = 0; i < 16; i++){
        binario1[i] = binario2[i];
    }
    return 0;
}

// Funcao para imprimir um binario com o sinal magnitude incluido
int imprimeBinario(int *binario, int tamanho){
    for(int i = tamanho-1; i >= 0; i--){
        printf(" %d", binario[i]);
    }
}

// Funcao para imprimir um binario com o sinal magnitude destacado
int imprimeBinarioSM(int *binario, int tamanho){
    for(int i = tamanho-1; i >= 0; i--){
        if(i == tamanho-1){
            printf("(%d)", binario[i]);
        }else{
            printf(" %d", binario[i]);
        }
    }
}