package doggytalents.client.entity.render.layer.accessory;

import com.mojang.blaze3d.matrix.MatrixStack;

import doggytalents.api.client.render.IAccessoryRenderer;
import doggytalents.api.registry.AccessoryInstance;
import doggytalents.client.entity.model.DogModel;
import doggytalents.common.entity.DogEntity;
import doggytalents.common.entity.accessory.DyeableAccessory.DyeableAccessoryInstance;
import doggytalents.common.util.Util;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class LeatherArmorAccessoryRenderer implements IAccessoryRenderer {

    private final EntityModel<DogEntity> model;
    private ResourceLocation texture;

    public LeatherArmorAccessoryRenderer(ResourceLocation textureIn) {
        this.model = new DogModel(0.4F);
        this.texture = textureIn;
    }

    @Override
    public void render(LayerRenderer<DogEntity, DogModel> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, DogEntity dog, AccessoryInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(dog.isTamed() && !dog.isInvisible()) {
            DyeableAccessoryInstance armorInstance = data.cast(DyeableAccessoryInstance.class);
            float[] color = Util.rgbIntToFloatArray(armorInstance.getColor());

            layer.getEntityModel().copyModelAttributesTo(this.model);
            this.model.setLivingAnimations(dog, limbSwing, limbSwingAmount, partialTicks);
            this.model.setRotationAngles(dog, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            LayerRenderer.renderCutoutModel(this.model, this.getTexture(dog, data), matrixStackIn, bufferIn, packedLightIn, dog, color[0], color[1], color[2]);
        }
    }

    public ResourceLocation getTexture(DogEntity dog, AccessoryInstance data) {
        return this.texture;
    }
}
