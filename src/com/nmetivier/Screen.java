package com.nmetivier;

public class Screen {
	private int width, height;
	
	public int[] pixels;
	
	int time = 0, counter = 0;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
	}
	
	public void clear() {
		for (int index = 0; index < pixels.length; index++) {
			pixels[index] = 0;
		}
	}
	
	public void render() {
		counter++;
		if (counter%100 == 0) {
			time++;
		}
		for (int y = 0; y < height; y++) {

			for (int x = 0; x < height; x++) {
				pixels[time+time*width] = 0xff00ff;
			}
		}
	}
}
