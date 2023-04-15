
package net.mcreator.megamodnostalgy.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.megamodnostalgy.world.inventory.SkillsMenu;
import net.mcreator.megamodnostalgy.network.SkillsButtonMessage;
import net.mcreator.megamodnostalgy.MegamodNostalgyMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class SkillsScreen extends AbstractContainerScreen<SkillsMenu> {
	private final static HashMap<String, Object> guistate = SkillsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_biegikakdebil;
	Button button_prygaikakdebil;
	Button button_budflieshiemkakdebil;
	Button button_riegienkakudebila;

	public SkillsScreen(SkillsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("megamod_nostalgy:textures/screens/skills.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.megamod_nostalgy.skills.label_skilly"), 62, 9, -12829636);
		this.font.draw(poseStack, new TranslatableComponent("gui.megamod_nostalgy.skills.label_1alm"), 129, 28, -12829636);
		this.font.draw(poseStack, new TranslatableComponent("gui.megamod_nostalgy.skills.label_1alm1"), 137, 54, -12829636);
		this.font.draw(poseStack, new TranslatableComponent("gui.megamod_nostalgy.skills.label_1alm2"), 149, 81, -12829636);
		this.font.draw(poseStack, new TranslatableComponent("gui.megamod_nostalgy.skills.label_1alm3"), 143, 108, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		button_biegikakdebil = new Button(this.leftPos + 35, this.topPos + 24, 88, 20, new TranslatableComponent("gui.megamod_nostalgy.skills.button_biegikakdebil"), e -> {
			if (true) {
				MegamodNostalgyMod.PACKET_HANDLER.sendToServer(new SkillsButtonMessage(0, x, y, z));
				SkillsButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_biegikakdebil", button_biegikakdebil);
		this.addRenderableWidget(button_biegikakdebil);
		button_prygaikakdebil = new Button(this.leftPos + 32, this.topPos + 51, 98, 20, new TranslatableComponent("gui.megamod_nostalgy.skills.button_prygaikakdebil"), e -> {
			if (true) {
				MegamodNostalgyMod.PACKET_HANDLER.sendToServer(new SkillsButtonMessage(1, x, y, z));
				SkillsButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_prygaikakdebil", button_prygaikakdebil);
		this.addRenderableWidget(button_prygaikakdebil);
		button_budflieshiemkakdebil = new Button(this.leftPos + 27, this.topPos + 77, 119, 20, new TranslatableComponent("gui.megamod_nostalgy.skills.button_budflieshiemkakdebil"), e -> {
			if (true) {
				MegamodNostalgyMod.PACKET_HANDLER.sendToServer(new SkillsButtonMessage(2, x, y, z));
				SkillsButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_budflieshiemkakdebil", button_budflieshiemkakdebil);
		this.addRenderableWidget(button_budflieshiemkakdebil);
		button_riegienkakudebila = new Button(this.leftPos + 34, this.topPos + 103, 103, 20, new TranslatableComponent("gui.megamod_nostalgy.skills.button_riegienkakudebila"), e -> {
			if (true) {
				MegamodNostalgyMod.PACKET_HANDLER.sendToServer(new SkillsButtonMessage(3, x, y, z));
				SkillsButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_riegienkakudebila", button_riegienkakudebila);
		this.addRenderableWidget(button_riegienkakudebila);
	}
}
