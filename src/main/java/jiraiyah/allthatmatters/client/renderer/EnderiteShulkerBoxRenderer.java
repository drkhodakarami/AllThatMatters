package jiraiyah.allthatmatters.client.renderer;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class EnderiteShulkerBoxRenderer<T extends EnderiteShulkerBlockEntity> implements BlockEntityRenderer<T>
{
    private final ShulkerEntityModel<?> model;

    public EnderiteShulkerBoxRenderer(BlockEntityRendererFactory.Context ctx)
    {
        this.model = new ShulkerEntityModel<>(ctx.getLayerModelPart(EntityModelLayers.SHULKER));
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        Direction direction = Direction.UP;
        if (entity.hasWorld())
        {
            BlockState blockState = entity.getWorld().getBlockState(entity.getPos());
            if (blockState.getBlock() instanceof ShulkerBoxBlock) {
                direction = blockState.get(ShulkerBoxBlock.FACING);
            }
        }
        /*String modifier = "";
        if (entity.hasUpgrades())
        {
            entity.getUpgrades().getKeys();
            for (String upgrade : entity.getUpgrades().getKeys())
                modifier += "_" + upgrade;
        }*/
        SpriteIdentifier spriteIdentifier;
        Identifier id = ModBlocks.getTextureForShulker(entity.getColor());
        spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE, id);

        matrices.push();
        matrices.translate(0.5D, 0.5D, 0.5D);
        float g = 0.9995F;
        matrices.scale(0.9995F, 0.9995F, 0.9995F);
        matrices.multiply(direction.getRotationQuaternion());
        matrices.scale(1.0F, -1.0F, -1.0F);
        matrices.translate(0.0D, -1.0D, 0.0D);

        ModelPart modelPart = this.model.getLid();
        modelPart.setPivot(0.0F, 24.0F - entity.getAnimationProgress(tickDelta) * 0.5F * 16.0F, 0.0F);
        modelPart.yaw = 270.0F * entity.getAnimationProgress(tickDelta) * 0.017453292F;
        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutoutNoCull);
        this.model.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
    }
}