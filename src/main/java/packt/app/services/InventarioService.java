package packt.app.services;

import javafx.stage.StageStyle;
import javafx.stage.Window;
import packt.app.MainConfig.controlers.outher.editar.EditarInventario;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.views.ModalDialog;
import packt.app.views.config.ViewConfig;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarioService {
    private static final Logger LOGGER = Logger.getLogger(InventarioService.class.getName());

    private InventarioService() {
        // Construtor privado para evitar instanciação
    }

    /**
     * Abre o diálogo de edição e retorna se os dados foram alterados
     *
     * @param item Item a ser editado
     * @param owner Janela pai para o modal
     * @return true se os dados foram salvos, false caso contrário
     */
    public static boolean editarItemComRetorno(Module_Inventario item, Window owner) {
        try {
            String title = "Editar Item - " + item.getCodDep();

            Optional<Boolean> result = ModalDialog.showModalWithResult(
                    ViewConfig.Editar.INVENTARIO,
                    owner,
                    title,
                    StageStyle.UTILITY,
                    controller -> ((EditarInventario) controller).setItem(item),
                    EditarInventario::foiSalvo
            );

            return result.orElse(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao editar item do inventário: " + item.getCodDep(), e);
            return false;
        }
    }

    /**
     * Versão simplificada sem retorno
     *
     * @param item Item a ser editado
     * @param owner Janela pai para o modal
     */
    public static void editarItem(Module_Inventario item, Window owner) {
        try {
            String title = "Editar Item - " + item.getCodDep();

            ModalDialog.showModalWithResult(
                    ViewConfig.Editar.INVENTARIO,
                    owner,
                    title,
                    StageStyle.UTILITY,
                    controller -> ((EditarInventario) controller).setItem(item),
                    null
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao abrir editor de inventário: " + item.getCodDep(), e);
        }
    }
}