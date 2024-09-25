package br.com.subsistema.factory;

import br.com.subsistema.imposto.*;
import br.com.subsistema.model.Item;

public class ImpostoFactory {

    public static Imposto getImposto(TipoImposto tipoImposto){
        switch (tipoImposto){
            case TIPOISS:
                return new ISS();
            case TIPOICMS:
                return new ICMS();
            case TIPOIPI:
                return new IPI();
            case TIPOPIS:
                return new PIS();
        }
        return null;
    }
}
