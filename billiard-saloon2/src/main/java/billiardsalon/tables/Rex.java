package billiardsalon.tables;

import java.math.BigDecimal;

public class Rex extends TableAfter6 {
    private static final BigDecimal HOURLY_PRICING = BigDecimal.valueOf(800);

    public Rex() {
        super(HOURLY_PRICING);
    }
}
