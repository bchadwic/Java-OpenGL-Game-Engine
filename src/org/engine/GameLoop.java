package org.engine;

import com.jogamp.newt.event.KeyEvent;
import org.graphics.Renderer;
import org.input.KeyInput;
import org.world.Pane;

public class GameLoop {

    private static boolean running = false;
    private static boolean paused = false;

    private static int updates = 0;
    private static final int MAX_UPDATES = 5;

    private static long lastUpdateTime = 0;

    private static final int targetFPS = 100;
    private static final int targetTime = 1000000000 / targetFPS;

    public static void start() {
        Thread thread = new Thread(() -> {
            running = true;
            paused = false;

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
                while (currentTime - lastUpdateTime >= targetTime) {
                    Pane.update();
                    lastUpdateTime += targetTime;
                    updates++;

                    if (updates > MAX_UPDATES) {
                        break;
                    }
                }

                Renderer.render();

                fps++;
                if (System.nanoTime() >= lastFpsCheck + 1000000000) {
                    /*System.out.println(fps);*/
                    fps = 0;
                    lastFpsCheck = System.nanoTime();
                }

                long timeTaken = System.nanoTime() - currentTime;
                if (targetTime > timeTaken) {
                    try {
                        Thread.sleep((targetTime - timeTaken) / 1000000);
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
        return 1.0f / 1000000000 * targetTime;
    }
}
