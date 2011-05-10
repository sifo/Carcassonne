package org.gla.carcassonne.ui;

public enum Position {
	verticalTopLeft, horizontalTopLeft, centerLeft, verticalBottomLeft, horizontalBottomLeft,
	topMiddle, centerMiddle, bottomMiddle, 
	verticalTopRight, horizontalTopRight, centerRight, horizontalBottomRight, verticalBottomRight;
	
	public static void showPosition(Position position){
		switch (position) {
		case verticalTopLeft:
			System.out.println("verticalTopleft");
			break;
		case horizontalTopLeft:
			System.out.println("horizontalTopLeft");
			break;
		case centerLeft:
			System.out.println("centerLeft");
			break;
		case verticalBottomLeft:
			System.out.println("verticalBottomLeft");
			break;
		case horizontalBottomLeft:
			System.out.println("horizontalBottomLeft");
			break;
		case topMiddle:
			System.out.println("topMiddle");
			break;
		case centerMiddle:
			System.out.println("centerMiddle");
			break;
		case bottomMiddle:
			System.out.println("bottomMiddle");
			break;
		case verticalTopRight:
			System.out.println("verticalTopRight");
			break;
		case horizontalTopRight:
			System.out.println("horizontalTopRight");
			break;
		case centerRight:
			System.out.println("centerRight");
			break;
		case horizontalBottomRight:
			System.out.println("horizontalBottomRight");
			break;
		default:
			System.out.println("verticalBottomRight");
			break;
		}
	}
	
	public static Position getTopPosition(Position position){
		if(position == verticalTopLeft)
			return horizontalTopLeft;
		else if(position == horizontalTopLeft)
			return horizontalBottomLeft;
		else if(position == topMiddle)
			return bottomMiddle;
		else if(position == horizontalTopRight)
			return horizontalBottomRight;
		else if(position == verticalTopRight)
			return horizontalTopRight;
		else if(position == centerLeft)
			return verticalTopLeft;
		else if(position == centerMiddle)
			return topMiddle;
		else if(position == centerRight)
			return verticalTopRight;
		else if(position == verticalBottomLeft)
			return centerLeft;
		else if(position == horizontalBottomLeft)
			return verticalBottomLeft;
		else if(position == bottomMiddle)
			return centerMiddle;
		else if(position == horizontalBottomRight)
			return verticalBottomRight;
		else
			return centerRight;
	}

	public static Position getBottomPosition(Position position){
		if(position == verticalTopLeft)
			return centerLeft;
		else if(position == horizontalTopLeft)
			return verticalTopLeft;
		else if(position == topMiddle)
			return centerMiddle;
		else if(position == horizontalTopRight)
			return verticalTopRight;
		else if(position == verticalTopRight)
			return centerRight;
		else if(position == centerLeft)
			return verticalBottomLeft;
		else if(position == centerMiddle)
			return bottomMiddle;
		else if(position == centerRight)
			return verticalBottomRight;
		else if(position == verticalBottomLeft)
			return horizontalBottomLeft;
		else if(position == horizontalBottomLeft)
			return horizontalTopLeft;
		else if(position == bottomMiddle)
			return topMiddle;
		else if(position == horizontalBottomRight)
			return horizontalTopRight;
		else
			return horizontalBottomRight;
	}

	public static Position getLeftPosition(Position position){
		if(position == verticalTopLeft)
			return verticalTopRight;
		else if(position == horizontalTopLeft)
			return verticalTopLeft;
		else if(position == topMiddle)
			return horizontalTopLeft;
		else if(position == horizontalTopRight)
			return topMiddle;
		else if(position == verticalTopRight)
			return horizontalTopRight;
		else if(position == centerLeft)
			return centerRight;
		else if(position == centerMiddle)
			return centerLeft;
		else if(position == centerRight)
			return centerMiddle;
		else if(position == verticalBottomLeft)
			return verticalBottomRight;
		else if(position == horizontalBottomLeft)
			return verticalBottomLeft;
		else if(position == bottomMiddle)
			return horizontalBottomLeft;
		else if(position == horizontalBottomRight)
			return bottomMiddle;
		else
			return horizontalBottomRight;
	}
	
	public static Position getRightPosition(Position position){
		if(position == verticalTopLeft)
			return horizontalTopLeft;
		else if(position == horizontalTopLeft)
			return topMiddle;
		else if(position == topMiddle)
			return horizontalTopRight;
		else if(position == horizontalTopRight)
			return verticalTopRight;
		else if(position == verticalTopRight)
			return verticalTopLeft;
		else if(position == centerLeft)
			return centerMiddle;
		else if(position == centerMiddle)
			return centerRight;
		else if(position == centerRight)
			return centerLeft;
		else if(position == verticalBottomLeft)
			return horizontalBottomLeft;
		else if(position == horizontalBottomLeft)
			return bottomMiddle;
		else if(position == bottomMiddle)
			return horizontalBottomRight;
		else if(position == horizontalBottomRight)
			return verticalBottomRight;
		else
			return verticalBottomLeft;
	}

	public static Position getPositionAfterRotation(Position position, int rotation){
		switch(position){
		case verticalTopLeft:{
			if(rotation == 0) return verticalTopLeft;
			if(rotation == 90) return horizontalTopRight;
			if(rotation == 180) return verticalBottomRight;
			if(rotation == 270) return horizontalBottomLeft;
		}
		case horizontalTopLeft:{
			if(rotation == 0) return horizontalTopLeft;
			if(rotation == 90) return verticalTopRight;
			if(rotation == 180) return horizontalBottomRight;
			if(rotation == 270) return verticalBottomLeft;
		}
		case topMiddle:{
			if(rotation == 0) return topMiddle;
			if(rotation == 90) return centerRight;
			if(rotation == 180) return bottomMiddle;
			if(rotation == 270) return centerLeft;
		}
		case horizontalTopRight:{
			if(rotation == 0) return horizontalTopRight;
			if(rotation == 90) return verticalBottomRight;
			if(rotation == 180) return horizontalBottomLeft;
			if(rotation == 270) return verticalTopLeft;
		}
		case verticalTopRight:{
			if(rotation == 0) return verticalTopRight;
			if(rotation == 90) return horizontalBottomRight;
			if(rotation == 180) return verticalBottomLeft;
			if(rotation == 270) return horizontalTopLeft;
		}
		case centerLeft:{
			if(rotation == 0) return centerLeft;
			if(rotation == 90) return topMiddle;
			if(rotation == 180) return centerRight;
			if(rotation == 270) return bottomMiddle;
		}
		case centerMiddle:
			return centerMiddle;
		case centerRight:{
			if(rotation == 0) return centerRight;
			if(rotation == 90) return bottomMiddle;
			if(rotation == 180) return centerLeft;
			if(rotation == 270) return topMiddle;
		}
		case verticalBottomLeft:{
			if(rotation == 0) return verticalBottomLeft;
			if(rotation == 90) return horizontalTopLeft;
			if(rotation == 180) return verticalTopRight;
			if(rotation == 270) return horizontalBottomRight;
		}
		case horizontalBottomLeft:{
			if(rotation == 0) return horizontalBottomLeft;
			if(rotation == 90) return verticalTopLeft;
			if(rotation == 180) return horizontalTopRight;
			if(rotation == 270) return verticalBottomRight;
		}
		case bottomMiddle:{
			if(rotation == 0) return bottomMiddle;
			if(rotation == 90) return centerLeft;
			if(rotation == 180) return topMiddle;
			if(rotation == 270) return centerRight;
		}
		case horizontalBottomRight:{
			if(rotation == 0) return horizontalBottomRight;
			if(rotation == 90) return verticalBottomLeft;
			if(rotation == 180) return horizontalTopLeft;
			if(rotation == 270) return verticalTopRight;
		}
		default:
			if(rotation == 0) return verticalBottomRight;
			if(rotation == 90) return horizontalBottomLeft;
			if(rotation == 180) return verticalTopLeft;
			if(rotation == 270) return horizontalTopRight;
		}
		System.err.println("Jamais ici");
		return null;
	}
}