/**
 * @author Ben Chadwick
 * last updated 12/22/20
 * This class represents the "space" all the game objects get rendered upon.
 */

package org.world;

import java.util.ArrayList;

public class Pane {

    // Game Object Lists
    private final static ArrayList<GameObject> GAME_OBJECTS = new ArrayList<GameObject>();
    private final static ArrayList<GameObject> NEW_GAME_OBJECTS = new ArrayList<GameObject>();
    // These fields keep track of objects that need to be removed from the game
    private static int removeGameObject = -1;

    /**
     * update() runs any changes that need to be made behind the scenes
     */
    public static void update(){

        // *NEW GAME OBJECT INSERTION*

        if(!NEW_GAME_OBJECTS.isEmpty()) {
            GAME_OBJECTS.addAll(NEW_GAME_OBJECTS);
            NEW_GAME_OBJECTS.clear();;
        }

        // *UPDATING OBJECTS*

        for(GameObject obj: GAME_OBJECTS){
            obj.update();
        }

        // *GAME OBJECT DEREFERENCE*

        if(removeGameObject != -1){
            GAME_OBJECTS.remove(removeGameObject);
            removeGameObject = -1;
        }
    }

    /**
     * render() sends the graphic representations to the window
     */
    public static void render(){
        for(GameObject obj : GAME_OBJECTS){
            obj.render();
        }
    }
}