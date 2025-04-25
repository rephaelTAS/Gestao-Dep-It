package packt.app.services;


import javafx.stage.Window;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.app.views.FXMLManager;
import packt.app.views.ModalDialog;
import packt.app.views.config.ViewConfig;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class InventarioService {
    private static Function<String, Object> controllerProvider = FXMLManager::getController;

    // Para testes
    public static void setControllerProvider(Function<String, Object> provider) {
        controllerProvider = provider;
    }

    public static void editarItem(Module_Inventario item, Window owner, Consumer<Boolean> callback) {
        boolean resultado = editarItemComRetorno(item, owner);
        if (callback != null) {
            callback.accept(resultado);
        }
    }

    public static boolean editarItemComRetorno(Module_Inventario item, Window owner) {
        try {
            Optional<Boolean> result = ModalDialog.showModalWithResult(...);
            return result.orElse(false);
        } catch (Exception e) {
            new Notificacao().showError("Falha ao editar item");
            LOGGER.error("Erro na edição", e);
            return false;
        }
    }

}