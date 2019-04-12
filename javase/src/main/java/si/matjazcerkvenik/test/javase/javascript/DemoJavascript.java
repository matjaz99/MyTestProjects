/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package si.matjazcerkvenik.test.javase.javascript;

import java.io.FileReader;
import java.io.Reader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *  To je primer uporabe javascripta iz jave
 * @author bojan
 */
public class DemoJavascript {
    private  ScriptEngineManager scriptManager; 
    private ScriptEngine engine;
    private MyContext myctx;
    
    DemoJavascript() {
        scriptManager = new ScriptEngineManager();
        engine = scriptManager.getEngineByName("JavaScript");  
        myctx = new MyContext();
        myctx.setSomeCtx(222);
    }
  
    public void doit() throws Exception {
       Alarm alarm = new Alarm();
       alarm.setTrap("to je tekst");
       Reader reader = new FileReader("./javascript-files/Parser.js");
       engine.eval(reader);
       reader.close();
       engine.put("myctx", myctx);
       Invocable inv = (Invocable) engine;
       Object result = inv.invokeFunction("parseAlarm", alarm);
       System.out.println("" + result);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DemoJavascript d = new DemoJavascript();
        d.doit();
    }
    
}
