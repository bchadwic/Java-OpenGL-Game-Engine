package org.engine;

import com.jogamp.newt.event.KeyEvent;
import org.graphics.Renderer;
import org.input.KeyInput;
import org.world.Pane;

/**
 * GameLoop ensures that the loop runs at a consistent frame rate
 */
public class GameLoop {

    private static boolean running = false;

    private static int updates = 0;
    private static final int MAX_UPDATES = 5;

    private static long lastUpdateTime = 0;

    private static final int TARGET_FPS = 100;
    private static final int NANO_SECOND = 1000000000;
    private static final int TARGET_TIME = NANO_SECOND / TARGET_FPS;

    public static void start() {
        Thread thread = new Thread(() -> {
            running = true;

            lastUpdateTime = System.nanoTime();

            int enemyCount = 0;
            int row = 4;
            int col = -3;
            int fps = 0;
            long lastFpsCheck = System.nanoTime();

            while (running) {

                if (KeyInput.getKey(KeyEvent.VK_ESCAPE))
                    running = false;

                long currentTime = System.nanoTime();
                updates = 0;
                while (currentTime - lastUpdateTime >= TARGET_TIME) {
                    Pane.update();
                    lastUpdateTime += TARGET_TIME;
                    updates++;

                    if (updates > MAX_UPDATES) {
                        break;
                    }
                }

                Renderer.render();

                fps++;
                if (System.nanoTime() >= lastFpsCheck + 1000000000) {
                    System.out.println(fps);
                    fps = 0;
                    lastFpsCheck = System.nanoTime();
                }

                long timeTaken = System.nanoTime() - currentTime;
                if (TARGET_TIME > timeTaken) {
                    try {
                        Thread.sleep((TARGET_TIME - timeTaken) / 1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.setName("GameLoop");
        thread.start();
    }

    public static float updateDelta() {
        return 1.0f / NANO_SECOND * TARGET_TIME;
    }
}
