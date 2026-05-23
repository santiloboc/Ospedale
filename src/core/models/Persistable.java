/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models;

import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public interface Persistable {

    public abstract HashMap<String, Object> serialize();

}
