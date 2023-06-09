
package net.mcreator.megamodnostalgy.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.megamodnostalgy.world.inventory.SkillsMenu;
import net.mcreator.megamodnostalgy.procedures.Buton4Procedure;
import net.mcreator.megamodnostalgy.procedures.Buton3Procedure;
import net.mcreator.megamodnostalgy.procedures.Buton2Procedure;
import net.mcreator.megamodnostalgy.procedures.Buton1Procedure;
import net.mcreator.megamodnostalgy.MegamodNostalgyMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillsButtonMessage {
	private final int buttonID, x, y, z;

	public SkillsButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SkillsButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SkillsButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SkillsButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = SkillsMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			Buton1Procedure.execute(entity);
		}
		if (buttonID == 1) {

			Buton2Procedure.execute(entity);
		}
		if (buttonID == 2) {

			Buton3Procedure.execute(entity);
		}
		if (buttonID == 3) {

			Buton4Procedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MegamodNostalgyMod.addNetworkMessage(SkillsButtonMessage.class, SkillsButtonMessage::buffer, SkillsButtonMessage::new, SkillsButtonMessage::handler);
	}
}
