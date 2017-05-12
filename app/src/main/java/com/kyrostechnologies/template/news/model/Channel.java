package com.kyrostechnologies.template.news.model;

import java.io.Serializable;

public class Channel implements Serializable {
	private String name;
	private String color;
	private int icon;

	public Channel(String name, String color, int icon) {
		this.name = name;
		this.icon = icon;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
