package org.graphics;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import org.input.KeyInput;
import org.input.MouseInput;

import java.awt.*;

public class Renderer {

    private static GLWindow window = null;
    private static GLProfile profile = null;
    public static int windowWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    public static int windowHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    public static int displayWidth = 480;
    public static int displayHeight = 580;
    public static float unitsWide = 10;


    public static void init() {
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setSize(displayWidth, displayHeight);

        window.addGLEventListener(new EventListener());
        window.addMouseListener(new MouseInput());
        window.addKeyListener(new KeyInput());
        /*window.setProperty("jnlp.newt.window.icons", "/res/ship.png");*/
        window.setTitle("Galaga");
        window.setPosition((int)(windowWidth * .3),(int)(windowHeight * .1));
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.setUndecorated(true);
        /*FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();*/

        window.setFullscreen(false);
        window.setVisible(true);
        window.requestFocus();
        window.setResizable(false);

    }

    public static void render(){
        if(window == null){
            return;
        }
        window.display();
    }

    public static int getDisplayWidth(){
        return window.getWidth();
    }

    public static int getDisplayHeight(){
        return window.getHeight();
    }

    public static GLProfile getProfile(){
        return profile;
    }

}
