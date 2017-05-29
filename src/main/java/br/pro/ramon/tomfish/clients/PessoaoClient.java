package br.pro.ramon.tomfish.clients;

import br.pro.ramon.tomfish.models.Pessoa;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class PessoaoClient {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.print("Peso: ");
        String peso = console.nextLine();
        System.out.print("Altura: ");
        String altura = console.nextLine();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8084/TomFish/webresources/pessoa/xml");
        Response response = target
                .queryParam("nome", nome)
                .queryParam("peso", peso)
                .queryParam("altura", altura)
                .request().get();
        Pessoa pessoa = response.readEntity(Pessoa.class);
        System.out.println("xml: " + pessoa.getImc().getCategoria());

        target = client.target("http://localhost:8084/TomFish/webresources/pessoa/json");
        response = target
                .queryParam("nome", nome)
                .queryParam("peso", peso)
                .queryParam("altura", altura)
                .request().get();
        pessoa = response.readEntity(Pessoa.class);
        System.out.println("json: " + pessoa.getImc().getCategoria());
    }

}
