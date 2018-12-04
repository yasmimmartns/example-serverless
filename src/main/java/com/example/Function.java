package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.microsoft.azure.functions.annotation.*;
import lombok.Data;
import com.microsoft.azure.functions.*;

public class Function {

    @Data
    class Cidade{
        private Long id;
        private String nome;
        private Estado estado;

        public Cidade (Long id, String nome, Estado estado){
            this.setId(id);
            this.setNome(nome);
            this.setEstado(estado);
        }
    }

    @Data
    class Estado{
        private Long id;
        private String nome;

        public Estado (Long id, String nome){
            this.setId(id);
            this.setNome(nome);
        }
    }

    @FunctionName ("funcaocriarcidade")
    public Cidade criar (
        @HttpTrigger (
            name = "restcriarcidade", 
            methods = {HttpMethod.POST}, 
            route = "cidade") Cidade c) {
                
        c.setId(new Long(1));
        return c;
    }

    @FunctionName ("funcaolercidade")
    public List<Cidade> ler(
        @HttpTrigger (
            name = "restlercidade", 
            methods = {HttpMethod.GET}, 
            route = "cidade") String x) {

        return Stream.of(
            new Cidade(1L, "Americana", new Estado(1L, "São Paulo")),
            new Cidade(2L, "Londrina", new Estado(2L, "Paraná")),
            new Cidade(3L, "Belo Horizonte", new Estado(3L, "Minas Gerais"))
        ).collect(Collectors.toList());
    }

    @FunctionName ("funcaoalterarcidade")
    public Cidade alterar (
        @HttpTrigger (
            name = "restalterarcidade",
            methods = {HttpMethod.PUT},
            route = "cidade"
        )
        Cidade c) {
        c.setNome(c.getNome());

        return c;
    }

    @FunctionName ("funcaoapagarcidade")
    public int apagar (
        @HttpTrigger (
            name = "restapagarcidade",
            methods = {HttpMethod.DELETE},
            route = "cidade/{id}"
        )
        @BindingName ("id") Long id) {
        System.out.println(String.format("Id: %d", id));

        return 200;
    }    
}
