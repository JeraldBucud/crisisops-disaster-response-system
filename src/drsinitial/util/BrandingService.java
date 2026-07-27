package drsinitial.util;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Labeled;

/**
 * Applies portfolio branding to legacy JavaFX views without changing their
 * controller paths, package names, or existing layout structure.
 */
public final class BrandingService {

    private BrandingService() {
    }

    /**
     * Replaces legacy product labels with CrisisOps branding throughout a
     * loaded JavaFX scene graph.
     *
     * @param root root node of the loaded view
     */
    public static void applyCrisisOpsBranding(Parent root) {
        updateNode(root);
    }

    private static void updateNode(Node node) {
        if (node instanceof Labeled labeled) {
            labeled.setText(rebrandText(labeled.getText()));
        }

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                updateNode(child);
            }
        }
    }

    private static String rebrandText(String text) {
        if (text == null || text.isBlank()) {
            return text;
        }

        return text
                .replace("DRS-Enhanced Disaster Response System", "CrisisOps")
                .replace("Disaster Response System",
                        "Emergency Operations and Disaster Response")
                .replace("Enhanced Prototype", "Portfolio Edition")
                .replace("DRS-Enhanced", "CrisisOps")
                .replace("DRS", "CrisisOps");
    }
}
