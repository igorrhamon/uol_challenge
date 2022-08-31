package br.com.uol;


import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuakusAppMain {

    public static void main(String[] args) {
        Quarkus.run (VingadoresApplication.class, args);
    }
}
