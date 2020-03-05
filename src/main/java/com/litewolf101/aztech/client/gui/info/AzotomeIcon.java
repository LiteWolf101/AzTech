package com.litewolf101.aztech.client.gui.info;

import com.litewolf101.aztech.client.gui.AzotomeScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AzotomeIcon extends Screen {
    private ITextComponent title;
    private ITextComponent description;
    private ItemStack iconItem;
    private int x, y;
    private ITextComponent lore;
    private AzotomeIcon parent;
    private AzotomeScreen.CategoryType categoryType;

    public AzotomeIcon(ITextComponent title, ITextComponent desc, ItemStack icon, int x, int y, ITextComponent lore_translation, AzotomeIcon parent, AzotomeScreen.CategoryType type) {
        super(title);
        this.title = title;
        this.description = desc;
        this.iconItem = icon;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.lore = lore_translation;
        this.categoryType = type;
    }

    @Override
    public void init() {
        /*addButton(new ButtonAzotomeIcon(this.x, this.y, 32, 32, this.title.toString(), button -> {
            Minecraft.getInstance().displayGuiScreen(new InfoPage(title, lore));
        }));*/
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
    }

    public String getName(){
        return this.title.getFormattedText();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public AzotomeIcon getParent() {
        if (this.parent != null) {
            if (this.parent.getCategory() != this.getCategory()) {
                throw new IllegalArgumentException("Cannot parent icons between categories! {" + this.getName() + ":" + this.getCategory() + "->" + this.parent.getName() + ":" + this.parent.getCategory() + "}");
            }
        }
        return this.parent;
    }

    public AzotomeScreen.CategoryType getCategory(){
        return this.categoryType;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
