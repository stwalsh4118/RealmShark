package potato.control;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import potato.model.DataModel;
import potato.view.RenderViewer;
import util.FocusedWindow;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseController implements NativeMouseWheelListener {

    DataModel model;
    RenderViewer renderer;
    ServerSynch server;

    public MouseController(DataModel model, RenderViewer renderer, ServerSynch serverHTTP) {
        this.model = model;
        this.renderer = renderer;
        this.server = serverHTTP;
        try {
            globalKeyMouseRegister();
            System.out.println("clearconsole");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void globalKeyMouseRegister() throws AWTException {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeMouseWheelListener(this);
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        if ((e.getModifiers() % 512) == 0) {
//            if (FocusedWindow.getWindowFocus().equals("RotMG Exalt.exe")) {
                model.editZoom(e.getWheelRotation());
//            }
        }
    }
}
