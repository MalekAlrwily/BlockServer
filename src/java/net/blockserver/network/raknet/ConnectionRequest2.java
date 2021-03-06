package net.blockserver.network.raknet;

import net.blockserver.Server;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class ConnectionRequest2 extends BaseLoginPacket {
	private Server server;
	private DatagramPacket packet;
	
	protected ByteBuffer buffer;
	private byte PID;
	@SuppressWarnings("unused")
	private byte[] MAGIC, securityCookie;
	public short mtuSize;
	public long clientID;
	
	public ConnectionRequest2(DatagramPacket packet, Server server){
		this.server = server;
		this.packet = packet;
		
		buffer = ByteBuffer.wrap(packet.getData());
		PID = buffer.get();
		MAGIC = buffer.get(new byte[16]).array();
		securityCookie = buffer.get(new byte[4]).array();
		mtuSize = buffer.getShort();
		clientID = buffer.getLong();
	}
	
	public ByteBuffer getResponse(){
		ByteBuffer response = ByteBuffer.allocate(30);
		byte packetID = (byte) 0x08;
		byte[] magic = this.getMAGIC();
		long serverID = server.getServerID();
		short clientPort = (short) packet.getPort();
		short mtu = mtuSize;
		byte security = 0;
		
		response.put(packetID);
		response.put(magic);
		response.putLong(serverID);
		response.putShort(clientPort); // who did this...
		response.putShort(mtu);
		response.put(security);
		
		return response;
		
	}
	
	public ByteBuffer getBuffer(){
		return ByteBuffer.wrap(packet.getData());
	}
	
	public byte getPID(){
		return PID;
	}
	
	public DatagramPacket getPacket(){
		return packet;
	}

}
