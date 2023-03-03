/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

/**
 *
 * @author User-Pc
 */
public class MyGeneric <T> {
    
      T obj;
    
    MyGeneric(T obj){
        this.obj=obj;
    }
    
     public T getObj() {
        return obj;
    }
}
