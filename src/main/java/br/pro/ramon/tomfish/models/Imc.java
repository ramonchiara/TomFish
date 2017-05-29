package br.pro.ramon.tomfish.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"peso", "altura", "valor", "categoria"})
public class Imc {

    private double peso;
    private double altura;

    protected Imc() {
    }

    public Imc(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    @XmlAttribute
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @XmlAttribute
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @XmlElement
    public double getValor() {
        return peso / (altura * altura);
    }

    @XmlElement
    public String getCategoria() {
        String categoria;

        double imc = getValor();

        if (imc < 18.5) {
            categoria = "Abaixo do Peso";
        } else if (imc < 25) {
            categoria = "Peso Normal";
        } else if (imc < 30) {
            categoria = "Acima do Peso";
        } else {
            categoria = "Obesidade";
        }

        return categoria;
    }

}
