package model;

import java.util.ArrayList;

import main.SnakeGameController;

public class Foods {
	private ArrayList<Box> foods;
	private SnakeGameController controller;
	public ArrayList<Box> getFoods() {
		return foods;
	}
	
	public Foods(SnakeGameController controller){
		this.controller = controller;
		this.foods = new ArrayList<Box>();
	}

	public void addRandom() {
		// TODO Auto-generated method stub
		Box newFood;
		ArrayList<Box> availableBoxes = controller.getAllBoxes();
		availableBoxes.removeAll(controller.getSnake().getBoxes());
		if(availableBoxes.size() == 0) return;
		newFood = availableBoxes.get((int)(Math.random() * availableBoxes.size()));
		this.foods.add(newFood);
	}

}
