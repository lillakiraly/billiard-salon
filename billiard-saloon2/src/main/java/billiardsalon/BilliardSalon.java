package billiardsalon;

import billiardsalon.menuitems.MenuItem;
import billiardsalon.tables.Table;

import java.math.BigDecimal;
import java.util.*;

public class BilliardSalon {
    private BigDecimal income;

    private List<Table> tables;
    public BilliardSalon(Table... tables) {
        this.tables = new ArrayList<>(Arrays.asList(tables));
        income = BigDecimal.ZERO;
    }

    public <T extends Table> void reserveTable(Class<T> tableType) {
        tables.stream().filter(table -> !table.isReserved() && tableType.isInstance(table))
                .findFirst().ifPresent(Table::reserveTable);
    }

    public Table getTableWithHighestTotal() {
        return Collections.max(tables, Comparator.comparing(Table::getTotalUnpaidConsumption));
    }

    public void payForTable(Table table) {
        income = income.add(table.payForTable());
    }

    public void payForMenuItemOfTable(Table table, UUID menuItemId) {
        income = income.add(table.payForMenuItem(menuItemId));
    }

    public BigDecimal getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "BilliardSalon{" +
                "tables=" + tables +
                '}';
    }
}
