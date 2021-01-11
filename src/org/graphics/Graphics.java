package org.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import org.resource.ImageResource;

import java.awt.*;

public class Graphics {

    // Color values
    private static float red = 255;
    private static float green = 255;
    private static float blue = 255;
    private static float alpha = 1;

    private static float rotation = 0;
    private static GL2 gl = EventListener.gl;
    private static TextRenderer renderer;

    public static void drawImage(ImageResource image, float x, float y, float width, float height){


        Texture tex = image.getTexture();

        if(tex != null){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, tex.getTextureObject());
        }

        gl.glTranslatef(x,y,0);



        /*gl.glColor4f(red, green, blue, alpha);*/
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2d(0,1);
        gl.glVertex2f(-width / 2, -height / 2);
        gl.glTexCoord2d(1,1);
        gl.glVertex2f(width / 2, -height / 2);
        gl.glTexCoord2d(1,0);
        gl.glVertex2f(width / 2, height / 2);
        gl.glTexCoord2d(0,0);
        gl.glVertex2f(-width / 2, height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
        /*gl.glRotatef(-rotation, 0,0,1);*/
        gl.glTranslatef(-x,-y,0);

    }

    public static void fillRect(float x, float y, float width, float height){
        GL2 gl = EventListener.gl;
        setColor(red,green,blue,0);
        gl.glTranslatef(x,y,0);
        gl.glRotatef(rotation,0,0,1);


        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-width / 2, -height / 2);
            gl.glVertex2f(width / 2, -height / 2);
            gl.glVertex2f(width / 2, height / 2);
            gl.glVertex2f(-width / 2, height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glRotatef(-rotation, 0,0,1);
        gl.glTranslatef(-x,-y,0);

    }

    public static void setColor(float r, float g, float b, float a){
        red = Math.max(0, Math.min(255, r));
        green = Math.max(0, Math.min(255, g));
        blue = Math.max(0, Math.min(255, b));
        alpha = Math.max(0, Math.min(255, a));
    }

    public static void printText(){
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 12));
        renderer.beginRendering(50, 50);
        // optionally set the color
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw("Text to draw", 0, 0);
        // ... more draw commands, color changes, etc.
        renderer.endRendering();
    }

    public static void setRotation(float r){
        rotation = r;
    }

    public static void drawStar(float x, float y, float width, float height){
        GL2 gl = EventListener.gl;

        gl.glTranslatef(x,y,0);
        gl.glRotatef(rotation,0,0,1);

        gl.glColor4f(red, green, blue, alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-width / 2, -height / 2);
        gl.glVertex2f(width / 2, -height / 2);
        gl.glVertex2f(width / 2, height / 2);
        gl.glVertex2f(-width / 2, height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glRotatef(-rotation, 0,0,1);
        gl.glTranslatef(-x,-y,0);

    }


}
