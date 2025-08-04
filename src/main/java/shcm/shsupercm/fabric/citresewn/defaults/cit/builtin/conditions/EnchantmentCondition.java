package shcm.shsupercm.fabric.citresewn.defaults.cit.builtin.conditions;

import io.shcm.shsupercm.fabric.fletchingtable.api.Entrypoint;
import net.minecraft.util.Identifier;
import shcm.shsupercm.fabric.citresewn.api.CITConditionContainer;
import shcm.shsupercm.fabric.citresewn.cit.CITCondition;
import shcm.shsupercm.fabric.citresewn.cit.CITContext;
import shcm.shsupercm.fabric.citresewn.cit.builtin.conditions.IdentifierCondition;
import shcm.shsupercm.fabric.citresewn.cit.builtin.conditions.ListCondition;
import shcm.shsupercm.fabric.citresewn.mixin.pack.format.PropertyGroup;
import shcm.shsupercm.fabric.citresewn.mixin.pack.format.PropertyKey;
import shcm.shsupercm.fabric.citresewn.mixin.pack.format.PropertyValue;
import shcm.shsupercm.fabric.citresewn.cit.CITParsingException;

import java.util.Set;

public class EnchantmentCondition extends ListCondition<EnchantmentCondition.EnchantmentCondition> {
    @Entrypoint(CITConditionContainer.ENTRYPOINT)
    public static final CITConditionContainer<EnchantmentCondition> CONTAINER = new CITConditionContainer<>(EnchantmentCondition.class, EnchantmentCondition::new,
            "enchantments", "enchantmentIDs");

    public EnchantmentCondition() {
        super(EnchantmentCondition.class, EnchantmentCondition::new);
    }

    public Identifier[] getEnchantments() {
        Identifier[] enchantments = new Identifier[this.conditions.length];

        for (int i = 0; i < this.conditions.length; i++)
            enchantments[i] = this.conditions[i].getValue(null);

        return enchantments;
    }

    @Override
    public Set<Class<? extends CITCondition>> siblingConditions() {
        return Set.of(ConditionEnchantmentLevels.class);
    }

    protected static class EnchantmentCondition extends IdentifierCondition {
        @Override
        public boolean test(CITContext context) {
            return context.enchantments().containsKey(this.value);
        }

        @Override
        protected Identifier getValue(CITContext context) {
            return this.value;
        }
    }
}
