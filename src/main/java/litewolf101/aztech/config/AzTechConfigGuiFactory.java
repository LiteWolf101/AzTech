package litewolf101.aztech.config;

import litewolf101.aztech.proxy.CommonProxy;
import litewolf101.aztech.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by LiteWolf101 on Feb /26/2019
 */
public class AzTechConfigGuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new AztechConfigGuiScreen(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	public static class AztechConfigGuiScreen extends GuiConfig {

		public AztechConfigGuiScreen(GuiScreen parent) {
			super(parent, getConfigElements(), Reference.MODID, true, true, "AzTech Configuration");
		}

		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();

			//list.add(new DummyConfigElement.DummyCategoryElement("general", "aztech.category.general.name", GeneralClass.class));
			//list.add(new DummyConfigElement.DummyCategoryElement("dimension", "aztech.category.dimension.name", DimensionClass.class));

			return list;
		}

		public static class GeneralClass extends GuiConfigEntries.CategoryEntry {

			public GeneralClass(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
				super(owningScreen, owningEntryList, configElement);
			}

			@Override
			protected GuiScreen buildChildScreen() {
				return new GuiConfig(this.owningScreen, (new ConfigElement(CommonProxy.getConfig().getCategory(AzTechConfig.CATEGORY_GENERAL))).getChildElements(), Reference.MODID, AzTechConfig.CATEGORY_GENERAL, true, true, "AzTech General Configuration");
			}

		}

		public static class DimensionClass extends GuiConfigEntries.CategoryEntry {

			public DimensionClass(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
				super(owningScreen, owningEntryList, configElement);
			}

			@Override
			protected GuiScreen buildChildScreen() {
				return new GuiConfig(this.owningScreen, (new ConfigElement(CommonProxy.getConfig().getCategory(AzTechConfig.CATEGORY_DIMENSION))).getChildElements(), Reference.MODID, AzTechConfig.CATEGORY_DIMENSION, true, true, "AzTech Dimension Configuration");
			}

		}

	}

}
