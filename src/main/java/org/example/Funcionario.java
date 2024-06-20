package org.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {

    private Funcao funcao;
    private BigDecimal salario;

    public Funcionario(String nome, LocalDate dataNascimento, Funcao funcao, BigDecimal salario) {
        super(nome, dataNascimento);
        this.funcao = funcao;
        this.salario = salario;
    }

    public void mostraInformacoes(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        System.out.println("Nome: " + getNome());
        System.out.println("Data de nascimento: " + getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Salario: R$" + decimalFormat.format(getSalario()));
        System.out.println("Funçao: " + funcao);
        System.out.println();
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
