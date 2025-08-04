package shcm.shsupercm.fabric.citresewn.defaults.cit.builtin.conditions;

import io.shcm.shsupercm.fabric.fletchingtable.api.Entrypoint;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import shcm.shsupercm.fabric.citresewn.api.CITConditionContainer;
import shcm.shsupercm.fabric.citresewn.cit.CITCondition;
import shcm.shsupercm.fabric.citresewn.cit.CITContext;
import shcm.shsupercm.fabric.citresewn.cit.CITParsingException;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyGroup;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyKey;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyValue;

import java.util.Arrays;

public class ItemCondition extends CITCondition {
    @Entrypoint(CITConditionContainer.ENTRYPOINT)
    public static final CITConditionContainer<ItemCondition> CONTAINER = new CITConditionContainer<>(ItemCondition.class, ItemCondition::new,
            "items", "matchItems");

    public Item[] items = new Item[0];

    public ItemCondition() {}

    public ItemCondition(Item... items) {
        this.items = items;
    }

    @Override
    public void load(PropertyKey key, PropertyValue value, PropertyGroup properties) throws CITParsingException {
        if (value.value().equals("*")) {
            this.items = Registries.ITEM.stream().toArray(Item[]::new);
            return;
        }

        String[] itemNames = value.value().split(" ");
        this.items = new Item[itemNames.length];
        
        for (int i = 0; i < itemNames.length; i++) {
            Identifier itemId = Identifier.tryParse(itemNames[i]);
            if (itemId == null || !Registries.ITEM.containsId(itemId))
                throw new CITParsingException("Unknown item: " + itemNames[i], properties, value.position());
            this.items[i] = Registries.ITEM.get(itemId);
        }
    }

    @Override
    public boolean test(CITContext context) {
        return Arrays.asList(items).contains(context.stack.getItem());
    }
}
