package br.pro.ramon.tomfish.clients;

import br.pro.ramon.tomfish.models.Pessoa;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PessoaCrudClient {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        boolean terminou = false;
        do {
            System.out.println("1 - create (FORM)");
            System.out.println("2 - create (JSON)");
            System.out.println("3 - findAll");
            System.out.println("4 - findByName");
            System.out.println("5 - update");
            System.out.println("6 - remove");
            System.out.println("7 - sair");
            System.out.print("Opção: ");
            String opcao = console.nextLine();

            switch (opcao) {
                case "1":
                    createForm();
                    break;
                case "2":
                    createJson();
                    break;
                case "3":
                    findAll();
                    break;
                case "4":
                    findByName();
                    break;
                case "5":
                    update();
                    break;
                case "6":
                    remove();
                    break;
                case "7":
                    terminou = true;
                    break;
            }

        } while (!terminou);
    }

    public static void createForm() {
        Scanner console = new Scanner(System.in);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoas");

        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.print("Peso: ");
        String peso = console.nextLine();
        System.out.print("Altura: ");
        String altura = console.nextLine();

        Form form = new Form();
        form.param("nome", nome);
        form.param("peso", peso);
        form.param("altura", altura);
        Entity<Form> entity = Entity.form(form);
        Response response = target.request().post(entity);

        int status = response.getStatus();
        switch (status) {
            case 201:
                System.out.println("Pessoa criada com sucesso!");
                break;
            case 409:
                System.out.println("Já existe uma pessoa cadastrada com esse nome!");
                break;
        }
    }

    public static void createJson() {
        Scanner console = new Scanner(System.in);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoas");

        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.print("Peso: ");
        double peso = Double.parseDouble(console.nextLine());
        System.out.print("Altura: ");
        double altura = Double.parseDouble(console.nextLine());

        Pessoa pessoa = new Pessoa(nome, peso, altura);
        Entity<Pessoa> entity = Entity.entity(pessoa, MediaType.APPLICATION_JSON);
        Response response = target.request().post(entity);

        int status = response.getStatus();
        switch (status) {
            case 201:
                System.out.println("Pessoa criada com sucesso!");
                break;
            case 409:
                System.out.println("Já existe uma pessoa cadastrada com esse nome!");
                break;
        }
    }

    public static void findAll() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoas");

        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        List<Pessoa> pessoas = response.readEntity(new GenericType<List<Pessoa>>() {
        });
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getNome() + " está " + pessoa.getImc().getCategoria());
        }
    }

    public static void findByName() {
        Scanner console = new Scanner(System.in);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoas/{pessoa}");

        System.out.print("Nome: ");
        String nome = console.nextLine();

        Response response = target.resolveTemplate("pessoa", nome).request().accept(MediaType.APPLICATION_JSON).get();
        int status = response.getStatus();
        switch (status) {
            case 200:
                Pessoa pessoa = response.readEntity(Pessoa.class);
                System.out.println(pessoa.getNome() + " está " + pessoa.getImc().getCategoria());
                break;
            case 404:
                System.out.println("Pessoa não encontrada!");
                break;
        }
    }

    public static void update() {
        Scanner console = new Scanner(System.in);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoas/{pessoa}");

        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.print("Peso: ");
        double peso = Double.parseDouble(console.nextLine());
        System.out.print("Altura: ");
        double altura = Double.parseDouble(console.nextLine());

        Pessoa pessoa = new Pessoa(nome, peso, altura);
        Entity<Pessoa> entity = Entity.entity(pessoa, MediaType.APPLICATION_JSON);
        Response response = target.request().post(entity);

        int status = response.getStatus();
        switch (status) {
            case 201:
                System.out.println("Pessoa criada com sucesso!");
                break;
            case 409:
                System.out.println("Já existe uma pessoa cadastrada com esse nome!");
                break;
        }
    }

    public static void remove() {
    }
    
}
