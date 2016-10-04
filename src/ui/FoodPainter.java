package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.Box;

public class FoodPainter extends NonGridPainters {
	private ArrayList<Box> foods;

	public FoodPainter(ArrayList<Box> foods) {
		super();
		this.foods = foods;
	}
	
	public void paintFoods(Graphics g){
		for(Box food : foods){
			paintFoods(food, g);
		}
	}

	private void paintFoods(Box food, Graphics g) {
		// TODO Auto-generated method stub
		super.paintBox(food);
		g.setColor(Color.GREEN);
		g.fillRect(atX + width / 4, atY + height / 4, width / 2, height / 2);
		
	}
}
