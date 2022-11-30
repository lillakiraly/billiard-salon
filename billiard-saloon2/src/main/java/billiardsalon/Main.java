package billiardsalon;

import billiardsalon.menuitems.MenuItem;
import billiardsalon.menuitems.MenuItemType;
import billiardsalon.tables.Pool;
import billiardsalon.tables.Rex;
import billiardsalon.tables.Snooker;
import billiardsalon.tables.Table;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Table pool = new Pool();
        Table pool2 = new Pool();
        Table rex = new Rex();
        Table snooker = new Snooker();

        BilliardSalon billiardSalon = new BilliardSalon(pool, pool2, new Table(BigDecimal.ONE) {
            @Override
            public void reserveTable() {
            }
        }, rex, snooker);
        System.out.println(billiardSalon);

        pool.reserveTable();
        pool2.reserveTable();
        rex.reserveTable();
        snooker.reserveTable();

        System.out.println(billiardSalon);

        pool.orderMenuItem(new MenuItem(UUID.randomUUID(), "Fries", BigDecimal.valueOf(100), MenuItemType.FOOD));
        pool2.orderMenuItem(new MenuItem(UUID.randomUUID(), "Fries", BigDecimal.valueOf(100), MenuItemType.FOOD));
        pool2.orderMenuItem(new MenuItem(UUID.randomUUID(), "Triple Sec", BigDecimal.valueOf(10000), MenuItemType.BEVERAGE));

        System.out.println(billiardSalon);

        System.out.println(billiardSalon.getTableWithHighestTotal());
        System.out.println(billiardSalon.getIncome());


//        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS).execute(() -> {
//            billiardSalon.payForTable(pool2);
//        });
//
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                billiardSalon.payForTable(pool2);
//            }
//        }, 300000);

        Thread.sleep(6000);

        billiardSalon.payForTable(pool2);
        System.out.println(billiardSalon.getIncome());

    }
}
