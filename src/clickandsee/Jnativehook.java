/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickandsee;

import com.sun.javafx.PlatformUtil;
import dictionaryen_vi.UI_anhvietController;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author quochung
 */
public class Jnativehook implements NativeKeyListener, NativeMouseListener {

    private Stage stage = new Stage();
    public static String text="";
    
    
    public Jnativehook() {

    }

    public void cancelNativeHook() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }

        } else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {

        } else if (e.getKeyCode() == NativeKeyEvent.VC_F) {

        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            if (stage == null) {
                stage = new Stage();
            }
            process();          
            windowsclickandsee();
            
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {

        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    public void Execute() {
        try {
            GlobalScreen.setEventDispatcher(new VoidDispatchService());
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Jnativehook());
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);

            logger.setUseParentHandlers(false);
            //  GlobalScreen.addNativeMouseListener(this);
            // GlobalScreen.addNativeMouseListener(new Jnativehook());
        } catch (NativeHookException ex) {
            //Logger.getLogger(Jnativehook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nme) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class VoidDispatchService extends AbstractExecutorService {

        private boolean running = false;

        public VoidDispatchService() {
            running = true;
        }

        @Override
        public void shutdown() {
            running = false;
        }

        @Override
        public List<Runnable> shutdownNow() {
            running = false;
            return new ArrayList<Runnable>(0);
        }

        @Override
        public boolean isShutdown() {
            return !running;
        }

        @Override
        public boolean isTerminated() {
            return !running;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void execute(Runnable r) {
            r.run();
        }
    }

    private void process() {
        VirtualKeyboard x = null;
        // viet cai ham nhan dien OS di
        try {
            x = new VirtualKeyboard();           
            
            if(PlatformUtil.isWindows())
                x.pressKeys("control+c");
            else if(PlatformUtil.isMac())
                x.pressKeys("meta+c");
        } catch (AWTException ex) {
            Logger.getLogger(Jnativehook.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(PlatformUtil.isWindows())
                x.keyRelease(KeyEvent.VK_CONTROL);
            else if(PlatformUtil.isMac())
                x.keyRelease(KeyEvent.VK_META);
            x.keyRelease(KeyEvent.VK_C);
        }

    }

    private void windowsclickandsee()  {

        
        PauseTransition pt = new PauseTransition(Duration.millis(350));
        pt.setOnFinished(ae -> {
            text = Clipboard.getSystemClipboard().getString().trim();
            System.out.println("Chuoi trong clipboard: " + text);

            try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionaryen_vi/UI_cnsForm.fxml"));
            Scene scene = new Scene(root);
            //stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            Point point = MouseInfo.getPointerInfo().getLocation();
            stage.setX(point.x);
            stage.setY(point.y);
            stage.setAlwaysOnTop(true);
            stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Jnativehook.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        SequentialTransition seq = new SequentialTransition(pt);
        seq.play();
  
    }

}
