package billiardsalon.menuitems;

import java.math.BigDecimal;
import java.util.UUID;

public class MenuItem {
    private final UUID id;
    private String name;
    private BigDecimal price;
    private MenuItemType type;

    public MenuItem(UUID id, String name, BigDecimal price, MenuItemType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String
    toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
