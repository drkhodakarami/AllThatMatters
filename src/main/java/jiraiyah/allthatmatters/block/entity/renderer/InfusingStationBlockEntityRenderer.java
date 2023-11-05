package jiraiyah.allthatmatters.block.entity.renderer;

import jiraiyah.allthatmatters.block.custom.InfusingStationBlock;
import jiraiyah.allthatmatters.block.entity.custom.InfusingStationBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class InfusingStationBlockEntityRenderer implements BlockEntityRenderer<InfusingStationBlockEntity>
{
    public InfusingStationBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {}

    @Override
    public void render(InfusingStationBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        matrices.push();
        {
            matrices.translate(0.5f, 0.75f, 0.5f);
            matrices.scale(0.35f, 0.35f, 0.35f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));

            switch (entity.getCachedState().get(InfusingStationBlock.FACING))
            {
                case NORTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
                case EAST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));
                case SOUTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0));
                case WEST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));
            }

            itemRenderer.renderItem(stack, ModelTransformationMode.GUI,
                    getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV,
                    matrices, vertexConsumers, entity.getWorld(), 1);
        }
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos)
    {
        int blight = world.getLightLevel(LightType.BLOCK, pos);
        int slight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(blight, slight);
    }
}