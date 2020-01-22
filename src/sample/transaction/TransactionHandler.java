package sample.transaction;

import javafx.concurrent.Task;

import java.util.concurrent.ThreadLocalRandom;

// 4 kasy - kazda oddzielny watek?
public class TransactionHandler {
    Task<Void> cashRegister = new Task<Void>() {
        @Override
        protected Void call() throws Exception {


            //region proper thread.sleep()
            try {
                Thread.sleep(100);
            } catch (InterruptedException interrupted) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
//                    break;
                }
            }
            //endregion
            return null;
        }
    };

    /**
     * Returns a random number between min and max (both inclusive)
     */
    public int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
