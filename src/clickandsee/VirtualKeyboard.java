/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickandsee;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author quochung
 */
public class VirtualKeyboard extends Robot {
     public VirtualKeyboard() throws AWTException
    {
        super();
    }

    public void pressKeys(String keysCombination) throws IllegalArgumentException
    {
            for (String key : keysCombination.split("\\+"))
            {
                try
                {   //System.out.println("Ban vua nhan: "+key);
                    this.keyPress((int) KeyEvent.class.getField("VK_" 
                            + key.toUpperCase()).getInt(null));

                } catch (IllegalAccessException e)
                {
                    e.printStackTrace();

                }catch(NoSuchFieldException e )
                {
                    throw new IllegalArgumentException(key.toUpperCase()+" is invalid key\n"+"VK_"+key.toUpperCase() + " is not defined in java.awt.event.KeyEvent");
                }


            }


    }

}
