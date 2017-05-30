package br.pro.ramon.tomfish.clients;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class HelloClient {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Seu nome: ");
        String nome = console.nextLine();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/TomFish/webresources/hello/{nome}");
        Response response = target.resolveTemplate("nome", nome).request().get();
        String msg = response.readEntity(String.class);
        System.out.println(msg);
    }

}
