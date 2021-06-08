package smurfs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class obje extends JLabel {

	private boolean mantar, gold, duvar;

	public obje() {
		this.mantar = false;
		this.gold = false;
		this.duvar = false;
	}

	public obje(boolean mantar, boolean altin, boolean duvar) {

		this.mantar = mantar;
		this.gold = altin;
		this.duvar = duvar;
	}

	public boolean isMantar() {
		return mantar;
	}

	public void setMantar(boolean mantar) {
		this.mantar = mantar;
	}

	public boolean isGold() {
		return gold;
	}

	public void setGold(boolean gold) {
		this.gold = gold;
	}

	public boolean isDuvar() {
		return duvar;
	}

	public void setDuvar(boolean duvar) {
		this.duvar = duvar;
	}

}

class altin extends obje {

	locasyon konum;

	public altin() {
		super();
	}

	@Override
	public void setDuvar(boolean duvar) {
		// TODO Auto-generated method stub
		super.setDuvar(duvar);
	}

	public void generateGold(obje[][] birim, int satir, int sutun) {
		int i = 0;

		altin altin = new altin();
		while (i < 5) {
			int randRow = (int) (Math.random() * satir);
			int randCol = (int) (Math.random() * sutun);
			if (birim[randRow][randCol].isGold() != true && birim[randRow][randCol].isMantar() != true
					&& birim[randRow][randCol].isDuvar() != true) {
				birim[randRow][randCol].setGold(true);
				i++;
			}
		}
	}

	public void setterGold(obje[][] birim, int satir, int sutun, String adres) {
		for (int row = 0; row < satir; row++) {
			for (int col = 0; col < sutun; col++) {
				if (birim[row][col].isGold()) {
					birim[row][col].setIcon(new ImageIcon(adres + "\\gold_50.jpg"));
				}
			}
		}
	}
}

class mantar extends obje {
	locasyon konum;

	public mantar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void generateMantar(obje[][] birim, int satir, int sutun) {
		int i = 0;
		while (i < 1) {
			int randRow = (int) (Math.random() * satir);
			int randCol = (int) (Math.random() * sutun);
			if (birim[randRow][randCol].isGold() != true && birim[randRow][randCol].isMantar() != true
					&& birim[randRow][randCol].isDuvar() != true) {
				birim[randRow][randCol].setMantar(true);
				i++;
			}
		}
	}

	public void setterMantar(obje[][] birim, int satir, int sutun, String adres) {
		for (int row = 0; row < satir; row++) {
			for (int col = 0; col < sutun; col++) {
				if (birim[row][col].isMantar()) {
					birim[row][col].setIcon(new ImageIcon(adres + "\\mantar_50.jpg"));
				}
			}
		}
	}

}
