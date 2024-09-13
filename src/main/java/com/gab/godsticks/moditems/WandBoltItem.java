package com.gab.godsticks.moditems;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WandBoltItem extends Item {
    public WandBoltItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            castLightning(world, player);
            player.getItemCooldownManager().set(this, 40);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    private void castLightning(World world, PlayerEntity player) {
        // Ottieni le coordinate del blocco che il giocatore sta guardando
        HitResult hitResult = player.raycast(100, 0.0F, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos targetPos = blockHitResult.getBlockPos();

            // Evoca un fulmine nella posizione mirata
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(targetPos));
            world.spawnEntity(lightning);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Giustizia gli infedeli.").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        tooltip.add(new LiteralText("Il potere divino è la tue mani!").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        tooltip.add(new LiteralText("Cooldown: 2 secondi"));
    }
}