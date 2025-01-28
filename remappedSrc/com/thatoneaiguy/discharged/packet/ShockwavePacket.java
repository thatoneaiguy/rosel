package com.thatoneaiguy.discharged.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public record ShockwavePacket(Vec3d position) implements Packet<ClientPlayPacketListener> {
	public static final Identifier ID = new Identifier("discharged", "shockwave");

	public ShockwavePacket(Vec3d position) {
		this.position = position;
	}

	public void write(PacketByteBuf buf) {
		buf.writeDouble(this.position.x);
		buf.writeDouble(this.position.y);
		buf.writeDouble(this.position.z);
	}

	public void apply(ClientPlayPacketListener listener) {
	}

	public Vec3d position() {
		return this.position;
	}
}
