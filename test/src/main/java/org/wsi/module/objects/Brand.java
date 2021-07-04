package org.wsi.module.objects;

public class Brand {
	private String name;
	private boolean live;
	private boolean bopis_enabled;
	
	public Brand(String name, boolean live, boolean bopis_enabled){
		this.name = name;
		this.live = live;
		this.bopis_enabled = bopis_enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isBopisEnabled() {
		return bopis_enabled;
	}

	public void setBopisEnabled(boolean bopis_enabled) {
		this.bopis_enabled = bopis_enabled;
	}
	
	@Override
	public String toString(){
		return "[" + name + ", " + live + ", " + bopis_enabled + "]";
	}
	
}
