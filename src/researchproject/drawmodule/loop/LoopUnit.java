package researchproject.drawmodule.loop;

public class LoopUnit {
	public LoopUnit(int z, int y, int x) {
		super();
		this.z = z;
		this.y = y;
		this.x = x;
	}
	/**
	 *  massArray[z][y][x]
	 */
	private int z;

	/**
	 * massArray[z][y][x]
	 */
	private int y;
	/**
	 * massArray[z][y][x]
	 */
	private int x;
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}


}
