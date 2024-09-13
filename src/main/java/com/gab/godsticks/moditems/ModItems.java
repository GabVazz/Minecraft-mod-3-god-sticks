package com.gab.godsticks.moditems;

import com.gab.godsticks.GodSticks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item LIGHTNING_STICK = registraItem("lightning-stick", new WandBoltItem(new FabricItemSettings().rarity(Rarity.RARE).maxDamage(50).group(ItemGroup.COMBAT)));
    public static final Item TNT_STICK = registraItem("tnt-stick", new TNTWandItem(new FabricItemSettings().rarity(Rarity.RARE).maxDamage(50).group(ItemGroup.COMBAT))) ;


    //lo registro qui dentro
    private static Item registraItem(String nome, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(GodSticks.MOD_ID, nome), item);
    }

    //registra tutti gli item definiti sopra in game
    public static void registraModItems() {
        System.out.println("Sto registrando i mod items per il seguente ID: " + GodSticks.MOD_ID);
    }
}
