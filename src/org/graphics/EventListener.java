package org.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import org.resource.ImageResource;
import org.world.Pane;

public class EventListener implements GLEventListener {

    public static GL2 gl = null;
    public static ImageResource image = null;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0,0,0,1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.setSwapInterval(0);


    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        Pane.render();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float unitsTall = Renderer.getDisplayHeight() / (Renderer.getDisplayWidth() / Renderer.unitsWide);
        gl.glOrtho(-Renderer.unitsWide/2, Renderer.unitsWide/2, -unitsTall/2, unitsTall/2, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }
}
