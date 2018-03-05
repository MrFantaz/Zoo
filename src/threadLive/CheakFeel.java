package threadLive;

import animals.Animal;
import main.ExtensibleCage;
import sun.plugin.javascript.navig.Link;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CheakFeel extends Thread {
    private static CheakFeel instance;
    private static Map<String, ExtensibleCage<? extends Animal>> cages;
    public boolean isRun;
    private CheakFeel(){

    }
    public static CheakFeel getCheakFeeel(){
      synchronized (CheakFeel.class) {
          if (instance == null) {
              instance = new CheakFeel();
          }
          return instance;
      }
    }

    public void stopWork(boolean run) {
        isRun = run;
    }

    public static void setCages(Map<String, ExtensibleCage<? extends Animal>> cages) {
        CheakFeel.cages = cages;

    }
     public void running(Map<String, ExtensibleCage<? extends Animal>> cages){
        setCages(cages);
        isRun=true;
        start();

     }
    @Override
    public void run() {
        long clocer = System.currentTimeMillis();
        while (isRun){
            if((clocer+1000)<System.currentTimeMillis()) {
                clocer=System.currentTimeMillis();
                StringBuilder sb = new StringBuilder();

                Set<String> keys = cages.keySet();
                for (String key : keys) {
                    for (Animal animal:cages.get(key).getCage()) {
                     animal.setFill(animal.getFill()-1);
                      if(animal.getFill()<=0){
                          animal.dea();
                      }
                    }


                    sb.append(cages.get(key).toString());

                }
                System.out.println(sb.toString());
            }
        }

    }

}
