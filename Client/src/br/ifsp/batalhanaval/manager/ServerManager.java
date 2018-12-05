package br.ifsp.batalhanaval.manager;

public class ServerManager {

	int port;
	String ip;

	private static ServerManager instance;

	private ServerManager() {
		port = 7777;
		ip = "localhost";
	}

	public static ServerManager getInstance() {
		if (instance == null) {
			instance = new ServerManager();
		}

		return instance;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
