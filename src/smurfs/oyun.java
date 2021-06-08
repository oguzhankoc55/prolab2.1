
package smurfs;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;

public class oyun extends JFrame {
	altin altin = new altin();
	mantar mantar = new mantar();
	private JPanel contentPane;
	static int x, y, satir, sutun, key = 0, hiz = 0, gold, mntr, yon_x, yon_y;
	static obje[][] birim;
	static oyuncu kahraman = new oyuncu();
	static ArrayList<oyuncu> oyuncular = new ArrayList<oyuncu>();
	static ArrayList<dusman> karakterler = new ArrayList<dusman>();
	static ArrayList<JLabel> dusman_kutu = new ArrayList<JLabel>();
	static String str;
	static char[][] temp;
	static int dusman_sayisi = 0;
	static String[][] dusman_karakter = new String[2][2];
	static int i = 0, j, l = 0, k = 0;
	static int[][] dizi;
	static int[][] map;
	static String adres = "C:\\Users\\Oðuzhan Koç\\OneDrive\\Masaüstü\\prolab2.1\\src\\";
	static JLabel dusman, dusman1;
	static JLabel scor_degisen = new JLabel();
	static JLabel bitis_ekrani;
	static JLabel oyuncu;

	public static void main(String[] args) {
		dosya_oku();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					oyun frame = new oyun();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public oyun() {
		gozlukluSirin s1 = new gozlukluSirin();
		oyuncular.add(s1);
		tembelSirin s2 = new tembelSirin();
		oyuncular.add(s2);
		kahraman.setX(6);
		kahraman.setY(5);
		kahraman.setScor(20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		oyuncu = new JLabel();
		oyuncu.setBounds(100 + (kahraman.getX() * 50), 50 + (kahraman.getY() * 50), 50, 50);
		oyuncu.setVisible(false);
		contentPane.add(oyuncu);
		JLabel baslangýc_ekran = new JLabel("");
		baslangýc_ekran.setBackground(Color.WHITE);
		baslangýc_ekran.setBounds(350, 250, 300, 200);
		contentPane.add(baslangýc_ekran);
		bitis_ekrani = new JLabel("oyun sonlandi");
		bitis_ekrani.setFont(new Font("Tahoma", Font.PLAIN, 34));
		bitis_ekrani.setBounds(50, 25, 900, 650);
		bitis_ekrani.setVisible(false);
		contentPane.add(bitis_ekrani);
		scor_degisen.setBounds(850, 150, 150, 40);
		scor_degisen.setVisible(false);
		contentPane.add(scor_degisen);
		JLabel scor_baslýk = new JLabel("oyuncu scor");
		scor_baslýk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scor_baslýk.setBounds(840, 100, 150, 40);
		scor_baslýk.setVisible(false);
		contentPane.add(scor_baslýk);
		JLabel karakter_secme = new JLabel("karakterini sec");
		karakter_secme.setFont(new Font("Tahoma", Font.PLAIN, 34));
		karakter_secme.setBounds(400, 177, 250, 100);
		contentPane.add(karakter_secme);
		JLabel tembel_yazý = new JLabel("Tembel Sirin(T'ye t\u0131klay\u0131n\u0131z) ");
		tembel_yazý.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel gozluklu_yazý = new JLabel("Gozluklu Sirin(G'ye t\u0131klay\u0131n\u0131z) ");
		gozluklu_yazý.setFont(new Font("Tahoma", Font.PLAIN, 18));
		gozluklu_yazý.setBounds(250, 300, 250, 76);
		contentPane.add(gozluklu_yazý);
		tembel_yazý.setBounds(550, 300, 250, 76);
		contentPane.add(tembel_yazý);
		JLabel gozluklu_resim = new JLabel("");
		gozluklu_resim.setIcon(new ImageIcon(adres + "bs_gozluklu.jpg"));
		gozluklu_resim.setBounds(325, 380, 98, 150);
		contentPane.add(gozluklu_resim);
		JLabel tembel_resim = new JLabel("");
		tembel_resim.setIcon(new ImageIcon(adres + "bs_tembel.jpg"));
		tembel_resim.setBounds(600, 380, 150, 150);
		contentPane.add(tembel_resim);
		dusman1 = new JLabel("");

		dusman1.setVisible(false);
		contentPane.add(dusman1);
		dusman = new JLabel("");
		dusman.setVisible(false);
		contentPane.add(dusman);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

					if (hiz == 2 && kahraman.getY() == 7 && kahraman.getX() == 11) {
						kahraman.setX(12);
						reset_map();
					} else if ((birim[kahraman.getY()][kahraman.getX() + hiz].isDuvar() == false
							|| oyuncular.get(key).getId() == 1)) {

						if (birim[kahraman.getY()][kahraman.getX() + 1].isDuvar() == false) {
							int a = hiz;
							gold = 0;
							mntr = 0;
							if (oyuncular.get(key).getId() == 1
									&& (birim[kahraman.getY()][kahraman.getX() + hiz].isDuvar() == true)) {
								hiz = 1;

							}
							if (birim[kahraman.getY()][kahraman.getX() + 1].isGold() == true) {
								gold = 1;
								yon_x = -1;
								yon_y = 0;
							}
							if (birim[kahraman.getY()][kahraman.getX() + 1].isMantar() == true) {
								mntr = 1;
								yon_x = -1;
								yon_y = 0;
							}
							x = (int) oyuncu.getBounds().getX();
							y = (int) oyuncu.getBounds().getY();
							if (x < 840)
								x += 50 * hiz;
							kahraman.setX(hiz + kahraman.getX());
							hiz = a;
							oyuncu.setBounds(x, y, 50, 50);
							reset_map();
						}
					}

				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT
						&& (birim[kahraman.getY()][kahraman.getX() - hiz].isDuvar() == false
								|| oyuncular.get(key).getId() == 1)) {
					if (birim[kahraman.getY()][kahraman.getX() - 1].isDuvar() == false) {
						int a = hiz;
						gold = 0;
						mntr = 0;
						if (oyuncular.get(key).getId() == 1
								&& (birim[kahraman.getY()][kahraman.getX() - hiz].isDuvar() == true)) {
							hiz = 1;

						}
						if (birim[kahraman.getY()][kahraman.getX() - 1].isGold() == true) {
							gold = 1;
							yon_x = 1;
							yon_y = 0;
						}
						if (birim[kahraman.getY()][kahraman.getX() - 1].isMantar() == true) {
							mntr = 1;
							yon_x = 1;
							yon_y = 0;
						}
						x = (int) oyuncu.getBounds().getX();
						y = (int) oyuncu.getBounds().getY();
						if (x > 0)
							x -= 50 * hiz;
						oyuncu.setBounds(x, y, 50, 50);
						kahraman.setX(kahraman.getX() - hiz);
						hiz = a;
						reset_map();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP
						&& (birim[kahraman.getY() - hiz][kahraman.getX()].isDuvar() == false
								|| oyuncular.get(key).getId() == 1)) {
					if (birim[kahraman.getY() - 1][kahraman.getX()].isDuvar() == false) {
						int a = hiz;
						gold = 0;
						mntr = 0;
						if (oyuncular.get(key).getId() == 1
								&& (birim[kahraman.getY() - hiz][kahraman.getX()].isDuvar() == true)) {
							hiz = 1;

						}
						if (birim[kahraman.getY() - 1][kahraman.getX()].isMantar() == true) {
							mntr = 1;
							yon_x = 0;
							yon_y = 1;
						}
						if (birim[kahraman.getY() - 1][kahraman.getX()].isGold() == true) {
							gold = 1;
							yon_x = 0;
							yon_y = 1;
						}
						x = (int) oyuncu.getBounds().getX();
						y = (int) oyuncu.getBounds().getY();
						if (y > 0)
							y -= 50 * hiz;
						oyuncu.setBounds(x, y, 50, 50);
						kahraman.setY(kahraman.getY() - hiz);
						hiz = a;
						reset_map();
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN
						&& (birim[kahraman.getY() + hiz][kahraman.getX()].isDuvar() == false
								|| oyuncular.get(key).getId() == 1)) {
					if (birim[kahraman.getY() + 1][kahraman.getX()].isDuvar() == false) {
						int a = hiz;
						gold = 0;
						mntr = 0;
						if (oyuncular.get(key).getId() == 1
								&& (birim[kahraman.getY() + hiz][kahraman.getX()].isDuvar() == true)) {
							hiz = 1;

						}
						if (birim[kahraman.getY() + 1][kahraman.getX()].isGold() == true) {
							gold = 1;
							yon_x = 0;
							yon_y = -1;
						}
						if (birim[kahraman.getY() + 1][kahraman.getX()].isMantar() == true) {
							mntr = 1;
							yon_x = 0;
							yon_y = -1;
						}
						x = (int) oyuncu.getBounds().getX();
						y = (int) oyuncu.getBounds().getY();
						if (y < 700)
							y += 50 * hiz;
						oyuncu.setBounds(x, y, 50, 50);
						kahraman.setY(kahraman.getY() + hiz);
						hiz = a;
						reset_map();
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_G) {
					key = 0;
					gozlukluSirin kahraman = new gozlukluSirin();
					kahraman.setId(1);
					hiz = kahraman.getHiz();
					oyuncu.setVisible(true);
					baslangýc_ekran.setVisible(false);
					scor_degisen.setVisible(true);
					scor_baslýk.setVisible(true);
					dusman.setVisible(true);
					oyuncu.setVisible(true);
					baslangýc_ekran.setVisible(false);
					scor_degisen.setVisible(true);
					scor_baslýk.setVisible(true);
					dusman.setVisible(true);
					gozluklu_resim.setVisible(false);
					tembel_resim.setVisible(false);
					tembel_yazý.setVisible(false);
					gozluklu_yazý.setVisible(false);
					karakter_secme.setVisible(false);
					oyuncu.setIcon(new ImageIcon(adres + "gozluklu_50.jpg"));
					dusman_konumlandýr();
					Button_týklandý();
					Timer myTimer = new Timer();
					TimerTask gorev = new TimerTask() {
						public void run() {
							run_map();
						}
					};
					myTimer.schedule(gorev, 0, 7000);
				}
				if (e.getKeyCode() == KeyEvent.VK_T) {
					key = 1;
					tembelSirin kahraman = new tembelSirin();
					kahraman.setId(2);
					hiz = kahraman.getHiz();
					oyuncu.setVisible(true);
					baslangýc_ekran.setVisible(false);
					scor_degisen.setVisible(true);
					scor_baslýk.setVisible(true);
					dusman.setVisible(true);
					oyuncu.setVisible(true);
					baslangýc_ekran.setVisible(false);
					scor_degisen.setVisible(true);
					scor_baslýk.setVisible(true);
					dusman.setVisible(true);
					gozluklu_resim.setVisible(false);
					tembel_resim.setVisible(false);
					tembel_yazý.setVisible(false);
					gozluklu_yazý.setVisible(false);
					karakter_secme.setVisible(false);
					oyuncu.setIcon(new ImageIcon(adres + "tembel_50.jpg"));
					dusman_konumlandýr();
					Button_týklandý();
					Timer myTimer = new Timer();
					TimerTask gorev = new TimerTask() {
						public void run() {
							run_map();
						}
					};
					myTimer.schedule(gorev, 0, 7000);
				}
			}
		});
	}

	public void reset_map() {

		String scorumuz = Integer.toString(kahraman.getScor().puanGoster());
		scor_degisen.setText(scorumuz);
		karakterHareketEttir();
		karsýlasma_ara();
		enKisaYoluHesapla();
		deleteImage_new();
		yolciz();
		altin.setterGold(birim, satir, sutun, adres);
		mantar.setterMantar(birim, satir, sutun, adres);
		giris_resim_ayarla();
	}

	public void run_map() {
		deleteImage();
		mantar.generateMantar(birim, satir, sutun);
		altin.generateGold(birim, satir, sutun);
		yolciz();
		altin.setterGold(birim, satir, sutun, adres);
		mantar.setterMantar(birim, satir, sutun, adres);
		giris_resim_ayarla();
	}


	public void giris_resim_ayarla() {
		birim[5][0].setIcon(new ImageIcon(adres + "c_50.jpg"));
		birim[10][3].setIcon(new ImageIcon(adres + "d_50.jpg"));
		birim[0][10].setIcon(new ImageIcon(adres + "b_50.jpg"));
		birim[0][3].setIcon(new ImageIcon(adres + "a_50.jpg"));
		birim[7][12].setIcon(new ImageIcon(adres + "sirine_50.jpg"));
		birim[5][0].setDuvar(true);
		birim[10][3].setDuvar(true);
		birim[0][10].setDuvar(true);
		birim[0][3].setDuvar(true);
	}

	public void Button_týklandý() {
		birim = new obje[satir][sutun];
		int[][] dizi = map;
		for (int i = 0; i < satir; i++) {
			for (int j = 0; j < sutun; j++) {
				birim[i][j] = new obje();
				birim[i][j].setBounds(100 + (j * 50), 50 + (i * 50), 50, 50);
				if (dizi[i][j] == 0) {
					birim[i][j].setDuvar(true);
					birim[i][j].setIcon(new ImageIcon(adres + "black_50.jpg"));
				}
				if (dizi[i][j] == 1) {
					birim[i][j].setDuvar(false);
					birim[i][j].setIcon(new ImageIcon(adres + "white_50.jpg"));
				}
				contentPane.add(birim[i][j]);
				contentPane.repaint();
			}
		}
		enKisaYoluHesapla();
		yolciz();
	}

	public static void dosya_oku() {
		String dosyaYolu = adres + "Harita.txt";
		try {
			File dosya = new File(dosyaYolu);
			BufferedReader oku = new BufferedReader(new InputStreamReader(new FileInputStream(dosya), "UTF8"));
			map = new int[11][13];
			String[] temp;
			int i = 0, j = 0, a = 0;
			while ((str = oku.readLine()) != null) {
				if (str.contains(",")) {
					dusman_sayisi++;
					str = str.replace("Karakter:", "");
					str = str.replace("Kapi:", "");
					temp = str.split(",");
					dusman_karakter[a][0] = temp[0];
					dusman_karakter[a][1] = temp[1];
					for (i = 0; i < dusman_karakter.length - 1; i++) {
					}
					a++;
				}
				if (str.charAt(0) == '0' || str.charAt(0) == '1') {
					j = 0;
					temp = str.split("\t");
					for (int k = 0; k < temp.length; k++, j++) {
						if (temp[k].equals("0")) {
							map[i - 1][j] = 0;
						} else if (temp[k].equals("1")) {
							map[i - 1][j] = 1;
						}
					}
					i++;
				}
			}
			satir = map.length;
			sutun = map[0].length;
			oku.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteImage_new() {
		for (int i = 0; i < satir; i++) {
			for (int j = 0; j < sutun; j++) {
				if (birim[i][j].isMantar() == true) {
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
				if (birim[i][j].isGold() == true) {
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
				if (birim[i][j].isDuvar() != true) {
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
			}
		}
	}

	public void deleteImage() {
		for (int i = 0; i < satir; i++) {
			for (int j = 0; j < sutun; j++) {
				if (birim[i][j].isMantar() == true) {
					birim[i][j].setMantar(false);
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
				if (birim[i][j].isGold() == true) {
					birim[i][j].setGold(false);
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
				if (birim[i][j].isDuvar() != true) {
					birim[i][j].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
				}
			}
		}
	}

	public void karsýlasma_ara() {
		if (birim[kahraman.getY()][kahraman.getX()].isMantar()) {
			kahraman.setScor(kahraman.getScor().puanGoster() + 50);
			birim[kahraman.getY()][kahraman.getX()].setMantar(false);
			birim[kahraman.getY()][kahraman.getX()].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
		}
		if (birim[kahraman.getY()][kahraman.getX()].isGold()) {
			kahraman.setScor(kahraman.getScor().puanGoster() + 5);
			birim[kahraman.getY()][kahraman.getX()].setGold(false);
			birim[kahraman.getY()][kahraman.getX()].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
		}

		if (hiz == 2 && gold == 1) {
			kahraman.setScor(kahraman.getScor().puanGoster() + 5);
			birim[kahraman.getY() + yon_y][kahraman.getX() + yon_x].setGold(false);
			birim[kahraman.getY() + yon_y][kahraman.getX() + yon_x].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
		}

		if (hiz == 2 && mntr == 1) {
			kahraman.setScor(kahraman.getScor().puanGoster() + 50);
			birim[kahraman.getY() + yon_y][kahraman.getX() + yon_x].setMantar(false);
			birim[kahraman.getY() + yon_y][kahraman.getX() + yon_x].setIcon(new ImageIcon(adres + "\\white_50.jpg"));
		}

		if (karakterler.get(0).mesafe_kontrol()) {
			if (karakterler.get(0).getId() == 1) {
				kahraman.setScor(kahraman.getScor().puanGoster() - 5);
			} else {
				kahraman.setScor(kahraman.getScor().puanGoster() - 15);
			}

			if (dusman_karakter[0][1].contains("A")) {
				karakterler.get(0).setX(3);
				karakterler.get(0).setY(0);
				dusman_kutu.get(0).setBounds(100 + (50 * karakterler.get(0).getX()),
						50 + (50 * karakterler.get(0).getY()), 50, 50);
			}
			if (dusman_karakter[0][1].contains("B")) {
				karakterler.get(0).setX(10);
				karakterler.get(0).setY(0);
				dusman_kutu.get(0).setBounds(100 + (50 * karakterler.get(0).getX()),
						50 + (50 * karakterler.get(0).getY()), 50, 50);
			}
			if (dusman_karakter[0][1].contains("C")) {
				karakterler.get(0).setX(0);
				karakterler.get(0).setY(5);
				dusman_kutu.get(0).setBounds(100 + (50 * karakterler.get(0).getX()),
						50 + (50 * karakterler.get(0).getY()), 50, 50);
			}
			if (dusman_karakter[0][1].contains("D")) {
				karakterler.get(0).setX(3);
				karakterler.get(0).setY(10);
				dusman_kutu.get(0).setBounds(100 + (50 * karakterler.get(0).getX()),
						50 + (50 * karakterler.get(0).getY()), 50, 50);
			}
		}

		if (dusman_sayisi == 2) {
			if (karakterler.get(1).mesafe_kontrol()) {
				if (karakterler.get(1).getId() == 1) {
					kahraman.setScor(kahraman.getScor().puanGoster() - 5);
				} else {
					kahraman.setScor(kahraman.getScor().puanGoster() - 15);
				}

				if (dusman_karakter[1][1].contains("A")) {
					karakterler.get(1).setX(3);
					karakterler.get(1).setY(0);
					dusman_kutu.get(1).setBounds(100 + (50 * karakterler.get(1).getX()),
							50 + (50 * karakterler.get(1).getY()), 50, 50);
				}
				if (dusman_karakter[1][1].contains("B")) {
					karakterler.get(1).setX(10);
					karakterler.get(1).setY(0);
					dusman_kutu.get(1).setBounds(100 + (50 * karakterler.get(1).getX()),
							50 + (50 * karakterler.get(1).getY()), 50, 50);
				}
				if (dusman_karakter[1][1].contains("C")) {
					karakterler.get(1).setX(0);
					karakterler.get(1).setY(5);
					dusman_kutu.get(1).setBounds(100 + (50 * karakterler.get(1).getX()),
							50 + (50 * karakterler.get(1).getY()), 50, 50);
				}
				if (dusman_karakter[1][1].contains("D")) {
					karakterler.get(1).setX(3);
					karakterler.get(1).setY(10);
					dusman_kutu.get(1).setBounds(100 + (50 * karakterler.get(1).getX()),
							50 + (50 * karakterler.get(1).getY()), 50, 50);
				}

			}
		}

		if (kahraman.getScor().puanGoster() < 0) {
			bitis_ekrani.setVisible(true);
			bitis_ekrani.setIcon(new ImageIcon(adres + "lose1.jpg"));
			oyuncu.setVisible(false);

		}
		if (kahraman.getX() == 12 && kahraman.getY() == 7) {
			bitis_ekrani.setVisible(true);
			bitis_ekrani.setIcon(new ImageIcon(adres + "win1.jpg"));
			oyuncu.setVisible(false);

		}

	}

	public void enKisaYoluHesapla() {
		for (int i = 0; i < karakterler.size(); i++) {

			karakterler.get(i).en_kisa_yol(kahraman.getX(), kahraman.getY(), map);
		}

	}

	public void yolciz() {
		for (int i = 0; i < karakterler.size(); i++) {
			if (karakterler.get(i).getEn_kisa_yol() != null) {

				for (int j = 0; j < karakterler.get(i).getEn_kisa_yol().size(); j += 2) {
					int x = karakterler.get(i).getEn_kisa_yol().get(j);
					int y = karakterler.get(i).getEn_kisa_yol().get(j + 1);

					birim[x][y].setIcon(new ImageIcon(adres + "green_50.jpg"));
				}
			}
		}
	}

	public void karakterHareketEttir() {

		for (int i = 0; i < karakterler.size(); i++) {
			if (karakterler.get(i).getEn_kisa_yol() != null) {
				if (karakterler.get(i) instanceof gargamel) {
					dusman_kutu.get(i).setBounds(100 + (50 * karakterler.get(i).getX()),
							50 + (50 * karakterler.get(i).getY()), 50, 50);
					karakterler.get(i).karakteri_hareket_ettir();
				}
				karakterler.get(i).karakteri_hareket_ettir();
				dusman_kutu.get(i).setBounds(100 + (50 * karakterler.get(i).getX()),
						50 + (50 * karakterler.get(i).getY()), 50, 50);
			}

		}
	}

	public void dusman_konumlandýr() {

		karakterler = new ArrayList<dusman>();
		dusman yeni_gargamel = new gargamel();
		dusman yeni_azman = new azman();

		if (dusman_karakter[0][0].contains("Gargamel")) {

			dusman.setIcon(new ImageIcon(adres + "gargamel_50.jpg"));
			karakterler.add(yeni_gargamel);
		}
		if (dusman_karakter[0][0].contains("Azman")) {

			dusman.setIcon(new ImageIcon(adres + "azman_50.jpg"));
			karakterler.add(yeni_azman);
		}
		dusman_kutu.add(dusman);

		if (dusman_karakter[0][1].contains("A")) {

			dusman.setBounds(100 + (50 * 3), 50, 50, 50);
			karakterler.get(0).setX(3);
			karakterler.get(0).setY(0);
		}

		if (dusman_karakter[0][1].contains("B")) {

			dusman.setBounds(100 + (50 * 10), 50, 50, 50);
			karakterler.get(0).setX(10);
			karakterler.get(0).setY(0);
		}

		if (dusman_karakter[0][1].contains("C")) {

			dusman.setBounds(100, 50 + (50 * 5), 50, 50);
			karakterler.get(0).setX(0);
			karakterler.get(0).setY(5);
		}

		if (dusman_karakter[0][1].contains("D")) {

			dusman.setBounds(100 + (50 * 3), 50 + (50 * 10), 50, 50);
			karakterler.get(0).setX(3);
			karakterler.get(0).setY(10);
		}

		if (dusman_sayisi == 2) {
			dusman1.setVisible(true);

			if (dusman_karakter[1][0].contains("Gargamel")) {

				dusman1.setIcon(new ImageIcon(adres + "gargamel_50.jpg"));
				karakterler.add(yeni_gargamel);
			}
			if (dusman_karakter[1][0].contains("Azman")) {

				dusman1.setIcon(new ImageIcon(adres + "azman_50.jpg"));
				karakterler.add(yeni_azman);
			}
			dusman_kutu.add(dusman1);

			if (dusman_karakter[1][1].contains("A")) {

				dusman1.setBounds(100 + (50 * 3), 50, 50, 50);
				karakterler.get(1).setX(3);
				karakterler.get(1).setY(0);
			}
			if (dusman_karakter[1][1].contains("B")) {

				dusman1.setBounds(100 + (50 * 10), 50, 50, 50);
				karakterler.get(1).setX(10);
				karakterler.get(1).setY(0);
			}
			if (dusman_karakter[1][1].contains("C")) {

				dusman1.setBounds(100, 50 + (50 * 5), 50, 50);
				karakterler.get(1).setX(0);
				karakterler.get(1).setY(5);
			}
			if (dusman_karakter[1][1].contains("D")) {

				dusman1.setBounds(100 + (50 * 3), 50 + (50 * 10), 50, 50);
				karakterler.get(1).setX(3);
				karakterler.get(1).setY(10);
			}

		}

	}

}