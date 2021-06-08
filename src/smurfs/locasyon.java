
package smurfs;

public abstract class locasyon {
	int x, y;

	public locasyon() {

	}

	public locasyon(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}

}
