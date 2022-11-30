package billiardsalon.tables;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class TableAfter6 extends Table {

    public TableAfter6(BigDecimal hourlyPricing) {
        super(hourlyPricing);
    }

    @Override
    public void reserveTable() {
      if (LocalDateTime.now().getHour() >= 18) {
          startTime = LocalDateTime.now();
          isReserved = true;
      }
    }
}
