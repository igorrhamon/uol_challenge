package br.com.uol;

import br.com.uol.init.ImportHeroisInit;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class VingadoresApplication implements QuarkusApplication {
    private final ImportHeroisInit importHeroisInit;

    public VingadoresApplication(ImportHeroisInit importHeroisInit) {
        this.importHeroisInit = importHeroisInit;
    }

    @Override
    public int run(String... args) {
        this.importHeroisInit.startup ();

        Quarkus.waitForExit ();
        return 0;
    }
}
