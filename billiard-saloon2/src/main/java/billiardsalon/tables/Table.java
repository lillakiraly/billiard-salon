package billiardsalon.tables;


import billiardsalon.menuitems.MenuItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Table {
    private BigDecimal hourlyPricing;
    protected LocalDateTime startTime;
    protected boolean isReserved;
    private List<MenuItem> orders;

    public Table(BigDecimal hourlyPricing) {
        this.hourlyPricing = hourlyPricing;
        isReserved = false;
        orders = new ArrayList<>();
    }

    public boolean isReserved() {
        return isReserved;
    }

    private BigDecimal getRoundedUpHourFromSecond(Duration duration) {
        BigDecimal hour = BigDecimal.valueOf(duration.getSeconds())
                .divide(BigDecimal.valueOf(3600), RoundingMode.CEILING);
//        int hour = (int) Math.ceil(duration.getSeconds() / (double) 3600);
        return hour;
    }

    public void orderMenuItem(MenuItem menuItem) {
        orders.add(menuItem);
    }


    public BigDecimal payForTable() {
        BigDecimal total = getTotalUnpaidConsumption();
        LocalDateTime endTime = LocalDateTime.now();
        Duration timeSpent = Duration.between(startTime, endTime);
        total = total.add(getRoundedUpHourFromSecond(timeSpent).multiply(hourlyPricing));
        isReserved = false;
        startTime = null;
        orders.clear();
        return total;
    }


    public BigDecimal payForMenuItem(UUID id) {
        Optional<MenuItem> order = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
        if (order.isPresent()) {
            orders.remove(order.get());
            return order.get().getPrice();
        } else {
            throw new NullPointerException();
        }
    }

    public abstract void reserveTable();


    public BigDecimal getTotalUnpaidConsumption() {
        return orders.stream().map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // other implementation -> .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        return "\nTable{" +
                "hourlyPricing=" + hourlyPricing +
                ", startTime=" + startTime +
                ", isReserved=" + isReserved +
                ", orders=" + orders +
                "}\n";
    }
}
