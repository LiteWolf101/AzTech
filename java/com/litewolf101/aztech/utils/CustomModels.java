package com.litewolf101.aztech.utils;

//@Mod.EventBusSubscriber(modid = AzTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomModels {

    /*@SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {
        try {
            IUnbakedModel model = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("aztech:block/ancient_rock.obj"));

            if (model instanceof OBJModel) {
                IBakedModel bakedModel = model.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(), new BasicState(model.getDefaultState(), false), DefaultVertexFormats.BLOCK);
                event.getModelRegistry().put(new ModelResourceLocation("aztech:ancient_rock", ""), bakedModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onPreTextureStitch(TextureStitchEvent.Pre event) {
        event.addSprite (
                ResourceLocation.tryCreate("aztech:block/ancient_rock")
        );
    }*/
}
