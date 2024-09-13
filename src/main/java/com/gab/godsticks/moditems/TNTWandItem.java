package com.gab.godsticks.moditems;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TNTWandItem extends Item {

    public TNTWandItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            launchTNT(world, player);
            player.getItemCooldownManager().set(this, 20);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Questa lancia TNT veramente arrabbiata.").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        tooltip.add(new LiteralText("Fai esplodere tutto ma NON abusarne!").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        tooltip.add(new LiteralText("Cooldown: 1 secondo"));
    }

    private void launchTNT(World world, PlayerEntity player) {
        // Ottieni la posizione del giocatore
        Vec3d playerPos = player.getEyePos();

        // Ottieni la direzione in cui il giocatore sta guardando
        Vec3d lookDir = player.getRotationVec(1.0F);

        // Calcola la posizione iniziale della TNT
        Vec3d tntPos = playerPos.add(lookDir.multiply(1.5));

        // Crea un'entità TNT
        TntEntity tntEntity = new TntEntity(world, tntPos.x, tntPos.y, tntPos.z, player);

        // Imposta la velocità della TNT nella direzione in cui il giocatore guarda
        tntEntity.setVelocity(lookDir.multiply(1.5));

        // Spawna la TNT nel mondo
        world.spawnEntity(tntEntity);

        // Imposta il timer per l'esplosione (20 tick = 1 secondo)
        tntEntity.setFuse(60);
    }
}