package io.github.apace100.apoli.power.factory.condition.item;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class SmeltableCondition {

    public static boolean condition(SerializableData.Instance data, ItemStack stack, Supplier<World> worldSupplier) {
        World world = worldSupplier.get();
        return world != null && world.getRecipeManager()
            .getFirstMatch(RecipeType.SMELTING, new SimpleInventory(stack), world)
            .isPresent();
    }

    public static ConditionFactory<ItemStack> getFactory(Supplier<World> world) {
        return new ConditionFactory<>(
            Apoli.identifier("smeltable"),
            new SerializableData(),
            (data, stack) -> condition(data, stack, world)
        );
    }

}