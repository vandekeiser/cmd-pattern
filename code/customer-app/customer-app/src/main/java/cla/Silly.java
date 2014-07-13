package cla;

public class Silly {

	public static void main(String[] args) {
		double posZero = 0.0d, negZero = -0.0d;
		System.out.println(posZero==negZero);
		System.out.println(Double.doubleToLongBits(posZero)==Double.doubleToLongBits(negZero));
		System.out.println(Double.doubleToLongBits(posZero)==Double.doubleToLongBits(negZero + posZero));
	}

}
