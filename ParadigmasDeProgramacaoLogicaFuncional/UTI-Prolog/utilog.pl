% Alunos:
% Lucas Beluomini 120111 </br>
% Arthur Martins Maciel 120119

% Curso:
% Ciência da Computação (UEM)

% Diciplina: 
% Paradigmas de Programação Lógica e Funcional  </br>
% 2 Trabalho Prático

:- initialization(main).

main :-
    normalizar_pacientes,
    write('Bem vindo ao sistema de diagnostico de doencas!'), nl,
    write('Digite 1 para cadastrar um paciente'), nl,
    write('Digite 2 para listar todos os pacientes'), nl,
    write('Digite 3 para buscar um paciente'), nl,
    write('Digite 4 para alterar um paciente'), nl,
    write('Digite 5 para excluir um paciente'), nl,
    write('Digite 6 para iniciar o diagnostico'), nl,
    write('Digite 7 para sair'), nl,
    read(Option),
    (Option = 1 -> cadastrar_paciente, main ; true),
    (Option = 2 -> listar_pacientes, main ; true),
    (Option = 3 -> buscar_paciente, main ; true),
    (Option = 4 -> alterar_paciente, main ; true),
    (Option = 5 -> excluir_paciente, main ; true),
    (Option = 6 -> iniciar_diagnostico, main ; true),
    (Option = 7 -> write('Obrigado por utilizar o sistema!'), retractall(paciente(_, _, _)) ; true).

%__________________________________ NORMALIZAR PACIENTE _________________________________________

normalizar_pacientes :- 
    read_file_to_string('pacientes.txt', Conteudo, []),
    atomic_list_concat(Linhas, '\n', Conteudo),
    cadastrar_pacientes(Linhas).

cadastrar_pacientes([]).
cadastrar_pacientes([Line|Rest]) :-
    % Verifica se a linha não está vazia
    string_length(Line, Length),
    Length > 0,
    % Divide a linha em uma lista de valores
    split_string(Line, ",", "", [Nome, IdadeStr, Sexo]),
    % Converte a idade para um número inteiro
    atom_number(IdadeStr, Idade),
    % Cadastra o paciente
    assertz(paciente(Nome, Idade, Sexo)),
    % Chama recursivamente para cadastrar os demais pacientes
    cadastrar_pacientes(Rest).
cadastrar_pacientes([_|Rest]) :-
    % Se a linha estiver vazia, chama recursivamente para cadastrar os demais pacientes
    cadastrar_pacientes(Rest).
    
%__________________________________ CADASTRAR PACIENTE _________________________________________

cadastrar_paciente :-
    open('pacientes.txt', append, Stream),
    write('Digite o nome do paciente ("Fulano".): '), read(Nome),
    write('Digite a idade do paciente (18.): '), read(Idade),
    write('Digite o sexo do paciente ("M".): '), read(Sexo),
    format(Stream, '~n~w,~w,~w', [Nome, Idade, Sexo]),
    assert(paciente(Nome, Idade, Sexo)),
    write('Paciente cadastrado com sucesso!'), nl,
    close(Stream).

%__________________________________ LISTAR PACIENTES _________________________________________

listar_pacientes :-
    read_file_to_string('pacientes.txt', Conteudo, []),
    atomic_list_concat(Linhas, '\n', Conteudo),
    write('Pacientes cadastrados:'), nl,
    escrever_linhas(Linhas),
    write('--------------------------------\n'), nl.

escrever_linhas([]).
escrever_linhas([Linha|Linhas]) :-
    write(Linha), nl,
    escrever_linhas(Linhas).

%__________________________________ CONSULTAR PACIENTE _________________________________________

buscar_paciente :-
    write('Digite o nome do paciente ("Fulano".): '), read(Nome),
    read_file_to_string('pacientes.txt', Conteudo, []),
    atomic_list_concat(Linhas, '\n', Conteudo),
    % Percorre cada linha e verifica se não está vazia
    (   member(Line, Linhas),
        string_length(Line, Length),
        Length > 0,
        % Divide a linha em uma lista de valores
        split_string(Line, ",", "", [NomePaciente, IdadeStr, Sexo]),
        % Converte a idade para um número inteiro
        atom_number(IdadeStr, Idade),
        % Verifica se o nome corresponde ao paciente que se quer consultar
        NomePaciente = Nome,
        % Imprime os dados do paciente encontrado
        format("Paciente encontrado: ~nNome: ~w, ~nIdade: ~d, ~nSexo: ~w~n", [Nome, Idade, Sexo]),
        ! % Para o loop após encontrar o primeiro paciente
    ;   % Caso contrário, imprime mensagem informando que o paciente não foi encontrado
        format("Paciente ~w não encontrado~n", [Nome])
    ).

%__________________________________ ALTERAR PACIENTE _________________________________________

alterar_paciente :-
    write('Digite o nome do paciente ("Fulano".): '), read(Nome),
    read_file_to_string('pacientes.txt', Conteudo, []),
    atomic_list_concat(Linhas, '\n', Conteudo),
    % Percorre cada linha e verifica se não está vazia
    exclude(remover_linha(Nome), Linhas, NewLines),
    % Escreve a lista de linhas atualizada de volta para o arquivo
    atomic_list_concat(NewLines, "\n", NewFileString),
    open('pacientes.txt', write, Out),
    write(Out, NewFileString),
    close(Out),
    retract(paciente(Nome, Idade, Sexo)),
    open('pacientes.txt', append, Stream),
    write('Digite o novo nome do paciente ("Fulano".): '), read(Nome),
    write('Digite a nova idade do paciente (18.): '), read(Idade),
    write('Digite o novo sexo do paciente ("M".): '), read(Sexo),
    format(Stream, '~n~w,~w,~w', [Nome, Idade, Sexo]),
    assert(paciente(Nome, Idade, Sexo)),
    write('Paciente alterado com sucesso!'), nl,
    close(Stream).


%__________________________________ EXCLUIR PACIENTE _________________________________________

excluir_paciente :-
    write('Digite o nome do paciente ("Fulano".): '), read(Nome),
    read_file_to_string('pacientes.txt', Conteudo, []),
    atomic_list_concat(Linhas, '\n', Conteudo),
    % Percorre cada linha e verifica se não está vazia
    exclude(remover_linha(Nome), Linhas, NewLines),
    % Escreve a lista de linhas atualizada de volta para o arquivo
    atomic_list_concat(NewLines, "\n", NewFileString),
    open('pacientes.txt', write, Out),
    write(Out, NewFileString),
    close(Out),
    retract(paciente(Nome, _, _)).

% Remove uma linha se o nome do paciente corresponder
remover_linha(Nome, Line) :-
    % Verifica se a linha não está vazia
    string_length(Line, Length),
    Length > 0,
    % Divide a linha em uma lista de valores
    split_string(Line, ",", "", [NomePaciente|_]),
    % Remove a linha se o nome corresponder
    NomePaciente = Nome.
    
%__________________________________ INICIAR DIAGNÓSTICO _________________________________________

% Função principal de diagnóstico
iniciar_diagnostico :-
    write('Digite o nome do paciente a ser diagnosticado: '),
    read(Nome),
    verificar_paciente(Nome),
    write('----------- Consulta -----------'), nl,
    findall(Sintoma, sintoma(Sintoma), Sintomas),
    pedir_sintomas(Sintomas, SintomasSelecionados),
    calcular_probabilidades([gripe, dengue, covid, sarampo, rinite, pneumonia, hepatite, meningite, enxaqueca, asma, hipertensao, diabetes, dermatite], SintomasSelecionados, 1, Probs),
    mostrar_diagnostico(Probs).

% Verifica se o paciente está cadastrado
verificar_paciente(Nome) :-
    paciente(Nome, _, _).
verificar_paciente(Nome) :-
    \+ paciente(Nome, _, _),
    write('Paciente nao encontrado.'), nl,
    fail.

% Pede os sintomas do paciente
pedir_sintomas([], []).
pedir_sintomas([Sintoma | Sintomas], [Sintoma | SintomasSelecionados]) :-
    write('O paciente apresenta o sintoma '), write(Sintoma), write('? (s/n)'),
    read(Resposta),
    (Resposta = 's' ; Resposta = 'S'),
    pedir_sintomas(Sintomas, SintomasSelecionados).
pedir_sintomas([_ | Sintomas], SintomasSelecionados) :-
    pedir_sintomas(Sintomas, SintomasSelecionados).

% Calcula a probabilidade de cada doença
calcular_probabilidades([], _, _, []).
calcular_probabilidades([Doenca | Doencas], SintomasPaciente, ProbabilidadeAnterior, [ProbabilidadeAtual-Doenca | Probs]) :-
    doenca(Doenca, SintomasDoenca, ProbabilidadeDoenca),
    intersection(SintomasPaciente, SintomasDoenca, SintomasEmComum),
    length(SintomasEmComum, QuantidadeSintomas),
    ProbabilidadeAtual is (ProbabilidadeDoenca * (ProbabilidadeAnterior + 0.1) * QuantidadeSintomas) * 100 / 3,
    calcular_probabilidades(Doencas, SintomasPaciente, ProbabilidadeAnterior, Probs).

% Ordena as doenças em ordem decrescente de probabilidade
ordenar_probabilidades(Probs, ProbsOrdenadas) :-
    keysort(Probs, ProbsOrdenadasInvertidas),
    reverse(ProbsOrdenadasInvertidas, ProbsOrdenadas).

% Mostra o diagnóstico
mostrar_diagnostico(Probs) :-
    write('Diagnostico do paciente:'), nl,
    ordenar_probabilidades(Probs, ProbsOrdenadas),

    nth1(1, ProbsOrdenadas, Prob1-Doenca1),
    nth1(2, ProbsOrdenadas, Prob2-Doenca2),
    nth1(3, ProbsOrdenadas, Prob3-Doenca3),

    % write('Probabilidade 1 '), write(Prob1), nl,
    % write('Probabilidade 2 '), write(Prob2), nl,
    % write('Probabilidade 3 '), write(Prob3), nl,

    % write('Probabilidade total '), write(Prob1+Prob2+Prob3), nl,

    write('1. '), write(Doenca1), write(' (probabilidade: '), format("~2f", [Prob1 * 100 / (Prob1 + Prob2 + Prob3)]), write('%)'), nl,
    write('2. '), write(Doenca2), write(' (probabilidade: '), format("~2f", [Prob2 * 100 / (Prob1 + Prob2 + Prob3)]), write('%)'), nl,
    write('3. '), write(Doenca3), write(' (probabilidade: '), format("~2f", [Prob3 * 100 / (Prob1 + Prob2 + Prob3)]), write('%)'), nl.

%__________________________________ CADASTRAR DOENÇAS _________________________________________

% Lista de doenças com sintomas e probabilidades
doenca(gripe, [febre, dor_de_cabeca, tosse], 0.8).
doenca(dengue, [febre, dor_nas_articulacoes, dor_de_cabeca], 0.7).
doenca(covid, [febre, tosse, falta_de_ar], 0.9).
doenca(sarampo, [febre, vermelhidao_na_pele, tosse], 0.6).
doenca(rinite, [coriza, espirros, coceira_no_nariz], 0.4).
doenca(pneumonia, [febre, tosse, dor_no_peito], 0.8).
doenca(hepatite, [amarelao_na_pele, dor_de_barriga, cansaco], 0.5).
doenca(meningite, [febre, dor_de_cabeca, rigidez_no_pescoco], 0.7).
doenca(enxaqueca, [dor_de_cabeca, sensibilidade_a_luz, sensibilidade_ao_som], 0.3).
doenca(asma, [tosse, falta_de_ar, chiado_no_peito], 0.6).
doenca(hipertensao, [dor_de_cabeca, tontura, dor_no_peito], 0.5).
doenca(diabetes, [fome_constante, sede_constante, cansaco], 0.7).
doenca(dermatite, [coceira_na_pele, vermelhidao_na_pele, bolhas_na_pele], 0.4).
 
%__________________________________ CADASTRAR SINTOMAS _________________________________________

% Lista de sintomas com perguntas
sintoma(febre).
sintoma(dor_de_cabeca).
sintoma(tosse).
sintoma(dor_nas_articulacoes).
sintoma(coriza).
sintoma(espirros).
sintoma(coceira_no_nariz).
sintoma(coceira_na_pele).
sintoma(dor_no_peito).
sintoma(amarelao_na_pele).
sintoma(vermelhidao_na_pele).
sintoma(dor_de_barriga).
sintoma(rigidez_no_pescoco).
sintoma(sensibilidade_a_luz).
sintoma(sensibilidade_ao_som).
sintoma(chiado_no_peito).
sintoma(falta_de_ar).
sintoma(tontura).
sintoma(cansaco).
sintoma(fome_constante).
sintoma(sede_constante).
sintoma(bolhas_na_pele).

%__________________________________ TESTES UNITÁRIOS _________________________________________
% :- use_module(library(plunit)).
% :- begin_tests(doencas).

%     test(gripe, probabilidade == 0.8) :- 
%         doenca(gripe, [febre, dor_de_cabeca, tosse], probabilidade).
%     test(dengue, probabilidade == 0.7) :- 
%         doenca(dengue, [febre, dor_nas_articulacoes, dor_de_cabeca], probabilidade).
%     test(covid, probabilidade == 0.9) :- 
%         doenca(covid, [febre, tosse, falta_de_ar], probabilidade).
%     test(gripe, [fail]) :- 
%         doenca(gripe, [febre, dor_nas_articulacoes, tosse], 0.5).

% :- end_tests(doencas).

% run_tests.