package billiardsalon.tables;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pool extends Table {
    private static final BigDecimal HOURLY_PRICING = BigDecimal.valueOf(1200);

    public Pool() {
        super(HOURLY_PRICING);
    }

    @Override
    public void reserveTable() {
        startTime = LocalDateTime.now();
        isReserved = true;
    }
}
