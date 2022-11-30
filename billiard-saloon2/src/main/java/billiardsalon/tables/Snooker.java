package billiardsalon.tables;

import java.math.BigDecimal;

public class Snooker extends TableAfter6 {
    private static final BigDecimal HOURLY_PRICING = BigDecimal.valueOf(1600);

    public Snooker() {
        super(HOURLY_PRICING);
    }
}
