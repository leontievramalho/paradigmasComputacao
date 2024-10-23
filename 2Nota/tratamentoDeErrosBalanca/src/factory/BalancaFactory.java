package factory;

import enums.TipoBalanca;
import excecoes.BalanceTypeException;
import interfaces.IBalanca;
import models.Produto;
import services.FilizolaSmart;
import services.ToledoMGV6;
import services.UranoIntegra;

public class BalancaFactory {
    public static IBalanca<Produto> getBalanca(TipoBalanca tipo) {
        try {
            switch (tipo) {
                case FINIZOLA_SMART:
                    return new FilizolaSmart();
                case TOLEDO_MGV6:
                    return new ToledoMGV6();
                case URANO_INTEGRA:
                    return new UranoIntegra();
                default:
                    throw new BalanceTypeException("Tipo de balança não disponível");
            }
        } catch (BalanceTypeException bte){
            System.err.println("Erro ao selecionar balança: " + bte.getMessage());
            return null;
        }catch (EnumConstantNotPresentException ecnpe){
            System.err.println("Erro de Enum: " + ecnpe.getMessage());
            return null;
        }
    }
}

