package dk.BdCC.bankaccountsystem;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class BASApp {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}