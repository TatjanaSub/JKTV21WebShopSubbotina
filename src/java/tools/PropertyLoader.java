/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ResourceBundle;

/**
 *
 * @author pupil
 */
public class PropertyLoader {
    private final static ResourceBundle resource = ResourceBundle.getBundle("properties.pathToView");
    public static String getPath(String key){
        return resource.getString(key);
    }
}
