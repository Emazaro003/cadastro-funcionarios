package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = funcionariosFactory();;

        //Remover o Joâo
        funcionarios.remove(1);

        System.out.println("Exibindo Funcionarios:");
        System.out.println();
        funcionarios.forEach(Funcionario::mostraInformacoes);
        System.out.println();


        //Atualizando os salarios em 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
            BigDecimal novoSalario = salarioAtual.add(aumento);
            funcionario.setSalario(novoSalario);
        }

        //Agrupar funcionarios por função
        Map<Funcao, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Exibir os funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, listaDeFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaDeFuncionarios.forEach(Funcionario::mostraInformacoes);
        });
        System.out.println();

        // Filtrar funcionários que fazem aniversário nos meses 10 e 12
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> {
                    int mesAniversario = funcionario.getDataNascimento().getMonthValue();
                    return mesAniversario == 10 || mesAniversario == 12;
                })
                .toList();

        // Exibir os funcionários filtrados
        System.out.println();
        System.out.println("Funcionarios que fazem aniversário em outubro e dezembro");
        aniversariantes.forEach(Funcionario::mostraInformacoes);
        System.out.println();


        //Funcionario com a maior idade
        List<Integer> idades = funcionarios.stream()
                .map(f -> Period.between(f.getDataNascimento(), LocalDate.now()).getYears())
                .toList();

        Integer maiorIdade = idades.stream().max(Integer::compareTo).orElseThrow();

        Funcionario funcionarioMaisVelho = funcionarios.stream().filter(f -> Period.between(f.getDataNascimento(), LocalDate.now()).getYears() == maiorIdade).findFirst().orElseThrow();

        System.out.println();
        System.out.println("Funcionario com a maior idade:" + funcionarioMaisVelho.getNome() + " " + maiorIdade);
        System.out.println();

        List<Funcionario> funcionariosOrdemAlfabetica = funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .toList();

        System.out.println("Exibindo Funcionarios ordem alfabetica:");
        System.out.println();
        funcionarios.forEach(Funcionario::mostraInformacoes);
        System.out.println();

        // Soma dos salarios dos funcionarios
        System.out.println();
        System.out.println("Soma dos salarios" + funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println();

        System.out.println("Salarios minimos por funcionario");
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(new BigDecimal("1212.00"), 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + ": " + salariosMinimos);
        });


    }

    public static List<Funcionario> funcionariosFactory(){
        List<Funcionario> funcionarios = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Adicionando funcionarios na ordem
        funcionarios.add(new Funcionario("Maria",
                LocalDate.parse("2000-10-18", formato),
                Funcao.OPERADOR,
                new BigDecimal("2009.44")));

        funcionarios.add(new Funcionario("João",
                LocalDate.parse("1990-12-05", formato),
                Funcao.OPERADOR,
                new BigDecimal("2284.38")));

        funcionarios.add(new Funcionario("Caio",
                LocalDate.parse("1961-05-02", formato),
                Funcao.COORDENADOR,
                new BigDecimal("9836.14")));

        funcionarios.add(new Funcionario("Miguel",
                LocalDate.parse("1988-10-14", formato),
                Funcao.DIRETOR,
                new BigDecimal("19119.88")));

        funcionarios.add(new Funcionario("Alice",
                LocalDate.parse("1995-01-05", formato),
                Funcao.RECEPCIONISTA,
                new BigDecimal("2234.68")));

        funcionarios.add(new Funcionario("Heitor",
                LocalDate.parse("1999-11-19", formato),
                Funcao.OPERADOR,
                new BigDecimal("1582.72")));

        funcionarios.add(new Funcionario("Arthur",
                LocalDate.parse("1993-03-31", formato),
                Funcao.CONTADOR,
                new BigDecimal("4071.84")));

        funcionarios.add(new Funcionario("Laura",
                LocalDate.parse("1994-07-08", formato),
                Funcao.GERENTE,
                new BigDecimal("3017.45")));

        funcionarios.add(new Funcionario("Heloísa",
                LocalDate.parse("2003-05-24", formato),
                Funcao.ELETRICISTA,
                new BigDecimal("1606.85")));

        funcionarios.add(new Funcionario("Helena",
                LocalDate.parse("1996-09-02", formato),
                Funcao.GERENTE,
                new BigDecimal("2799.93")));
        return funcionarios;
    }
}