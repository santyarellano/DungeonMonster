package Classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements KeyListener, ActionListener {
	// Instance variables
	private boolean isPlaying, inFloor;
	private Timer timer;
	private final int MILISECS = 10;
	private final int IMAGE_CORRECTION_H = 28;
	private final int IMAGE_CORRECTION_V = 3;
	private final int INI_PLAYER_HEALTH = 3;
	private final int INI_ENEMY_HEALTH = 10;
	private int scr_size;
	private final int divi = 10;
	private BufferedImage im;
	private int leftKey, rightKey, jumpKey;
	private int enemySpd;
	private Player player;
	private Enemy enemy;
	private Poison poison;
	private Walls walls;
	private Titles titles;
	private int tileSize;
	private Color bgColor;
	private Tools tools;
	private int counter = 0, shootFre = 1000;
	private int monHsp = 2;
	private AudioInputStream mainMenuAudio;
	private AudioInputStream battleAudio;
	private AudioInputStream poisonAudio;
	private AudioInputStream mHitAudio;
	private AudioInputStream pHitAudio;
	private Clip mainMenuClip;
	private Clip battleClip;
	private Clip poisonClip;
	private Clip mHitClip;
	private Clip pHitClip;
	private boolean musicBegan = false, fxBegan = false;
	private int[] scn = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 0, 0, 0, 0, 3, 0, 1, 1,
			0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	// ------------------------ MAP CODES ----------------------------------
	/*
	 * 0 = null 1 = wall 2 = player start point 3 = enemies 4 = civilian 5 =
	 * elevator
	 */
	// ------------------------ MAP CODES ----------------------------------

	// ----------------------- CONSTRUCTOR -----------------------------
	public MainCanvas(int scr_size) {
		super();
		setFocusable(true);
		this.scr_size = scr_size;
		isPlaying = false;
		inFloor = false;
		leftKey = 0;
		rightKey = 0;
		jumpKey = 0;
		tileSize = scr_size / divi;
		bgColor = new Color(79, 79, 79);
		im = new BufferedImage(scr_size, scr_size, BufferedImage.TYPE_INT_RGB);
		walls = new Walls();
		tools = new Tools();
		poison = new Poison(0, 0, tileSize * 2, tileSize * 2);
		titles = new Titles(0, 0, scr_size, scr_size);
		enemySpd = 2;
		// Create objects
		for (int i = 0; i < divi; i++) {
			for (int j = 0; j < divi; j++) {
				int arrPos = (i * divi) + j;
				switch (scn[arrPos]) {
					case 0:
						break;
					case 1:
						int wallX = j * tileSize;
						int wallY = i * tileSize;
						walls.addWall(wallX, wallY, tileSize);
						break;
					case 2:
						int x = j * tileSize;
						int y = i * tileSize;
						player = new Player(x, y, tileSize, tileSize, INI_PLAYER_HEALTH, 0, true, true, "Player");
						break;
					case 3:
						int eX = j * tileSize;
						int eY = i * tileSize;
						enemy = new Enemy(eX, eY, tileSize * 2, tileSize * 2, INI_ENEMY_HEALTH);
						break;
					default:
						break;
				}
			}
		}

		// Audio
		File bgMusic = new File("../Sounds/bgMusic.wav");
		File battleMusic = new File("../Sounds/battleMusic.wav");
		File poisonFX = new File("../Sounds/poison.wav");
		File mHitFX = new File("../Sounds/mHit.wav");
		File pHitFX = new File("../Sounds/pHit.wav");
		try {
			mainMenuAudio = AudioSystem.getAudioInputStream(bgMusic);
			battleAudio = AudioSystem.getAudioInputStream(battleMusic);
			poisonAudio = AudioSystem.getAudioInputStream(poisonFX);
			mHitAudio = AudioSystem.getAudioInputStream(mHitFX);
			pHitAudio = AudioSystem.getAudioInputStream(pHitFX);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			mainMenuClip = AudioSystem.getClip();
			battleClip = AudioSystem.getClip();
			poisonClip = AudioSystem.getClip();
			mHitClip = AudioSystem.getClip();
			pHitClip = AudioSystem.getClip();

			mainMenuClip.open(mainMenuAudio);
			battleClip.open(battleAudio);
			poisonClip.open(poisonAudio);
			mHitClip.open(mHitAudio);
			pHitClip.open(pHitAudio);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addKeyListener(this);
		timer = new Timer(MILISECS, this);
		timer.start();
	}
	// ----------------------- CONSTRUCTOR -----------------------------

	// ------------------------------ METHODS --------------------------
	public void update() {
		if (isPlaying) {
			// ------------------------ PLAYER ---------------------------
			int hsp = rightKey - leftKey;
			hsp *= 2;
			player.setHsp(hsp);
			if (jumpKey == 1 && inFloor) {
				player.jump();
				inFloor = false;
				player.setJumping(true);
			} else {
				player.stopJump();
			}

			// ------------- Player Collitions -----------
			// Horizontal
			if (player.getxPos() + hsp + IMAGE_CORRECTION_H < tileSize) {
				player.setHsp(0);
			} else if (player.getxPos() + hsp - IMAGE_CORRECTION_H > tileSize * (divi - 1)) {
				player.setHsp(0);
			}
			// Vertical
			if (player.getyPos() + player.getVsp() - IMAGE_CORRECTION_V > tileSize * (divi - 2)) {
				player.setVsp(0);
				inFloor = true;
				player.setJumping(false);
			}
			// ------------- Player Collitions -----------
			player.run();
			// ------------------------ PLAYER ---------------------------

			// ------------------------ ENEMY ---------------------------
			// Enemy Collitions
			// Horizontal
			if (enemy.getxPos() + enemy.getHsp() < tileSize) {
				enemy.setHsp(enemy.getHsp() * -1);
			} else if (enemy.getxPos() + enemy.getHsp() + enemy.getWidth() > (tileSize * (divi - 1))) {
				enemy.setHsp(enemy.getHsp() * -1);
			}

			// Vertical
			if (enemy.getyPos() + enemy.getVsp() + enemy.getHeight() > tileSize * (divi - 1)) {
				enemy.setVsp(0);
				enemy.setInFloor(true);
				enemy.setJumping(false);
			}

			enemy.run();
			// ------------------------ ENEMY ---------------------------

			// ---------------------- POISON --------------------------
			counter++;
			if (enemy.inFloor) {
				if (counter == shootFre) {
					counter = 0;
					if (!enemy.isShooting()) {
						Random r = new Random();
						enemy.setShooting(true);
						enemy.setHsp(0);
						// Shooting direction
						int xPower = 0;
						while (xPower < 5) {
							xPower = r.nextInt(20);
						}
						int yPower = 25;
						if (player.xPos > enemy.getxPos()) {
							poison.create(enemy.xPos, enemy.yPos - (enemy.height / 2) + 10, xPower, -yPower,
									tileSize * 2, tileSize * 2);
						} else {
							poison.create(enemy.xPos, enemy.yPos - (enemy.height / 2) + 10, -xPower, -yPower,
									tileSize * 2, tileSize * 2);
						}

						// Sound FX
						poisonClip.setFramePosition(0);
						poisonClip.start();

					}
				}
			}

			if (poison.isExists()) {
				// Vertical collitions
				if (poison.getyPos() + poison.getHeight() + poison.getVsp() > tileSize * (divi - 1)) {
					poison.setVsp(poison.getVsp() * -1);
					poison.setVsp(poison.getVsp() / 1.2);
				}

				// Horizontal collition
				if (poison.getxPos() + poison.getHsp() < tileSize) {
					poison.setHsp(poison.getHsp() * -1);
				} else if (poison.getxPos() + poison.getWidth() + poison.getHsp() > (tileSize * (divi - 1))) {
					poison.setHsp(poison.getHsp() * -1);
				}

				// Other collitions
				int eJump = -10;
				// Player
				if (player.getWidth() > 0) {
					if (poison.getxPos() + poison.getHsp() < player.getxPos() + player.getWidth()) {
						if (poison.getxPos() + poison.getHsp() > player.getxPos()) {
							if (poison.getyPos() + poison.getHeight() + poison.getVsp() > player.getyPos()) {
								if (poison.getyPos() + poison.getHeight() + poison.getVsp() < player.getyPos()
										+ player.getHeight()) {
									hitPlayer(eJump);
								}
							}
						}
					} else if (poison.getxPos() + poison.getWidth() + poison.getHsp() > player.getxPos()) {
						if (poison.getxPos() + poison.getWidth() + poison.getHsp() < player.getxPos()
								+ player.getWidth()) {
							if (poison.getyPos() + poison.getHeight() + poison.getVsp() > player.getyPos()) {
								if (poison.getyPos() + poison.getHeight() + poison.getVsp() < player.getyPos()
										+ player.getHeight()) {
									hitPlayer(eJump);
								}
							}
						}
					}
				} else if (player.getWidth() < 0) {
					if (poison.getxPos() + poison.getHsp() < player.getxPos()) {
						if (poison.getxPos() + poison.getHsp() > player.getxPos() + player.getWidth()) {
							if (poison.getyPos() + poison.getHeight() + poison.getVsp() > player.getyPos()) {
								if (poison.getyPos() + poison.getHeight() + poison.getVsp() < player.getyPos()
										+ player.getHeight()) {
									hitPlayer(eJump);
								}
							}
						}
					} else if (poison.getxPos() + poison.getWidth() + poison.getHsp() > player.getxPos()
							+ player.getWidth()) {
						if (poison.getxPos() + poison.getWidth() + poison.getHsp() < player.getxPos()) {
							if (poison.getyPos() + poison.getHeight() + poison.getVsp() > player.getyPos()) {
								if (poison.getyPos() + poison.getHeight() + poison.getVsp() < player.getyPos()
										+ player.getHeight()) {
									hitPlayer(eJump);
								}
							}
						}
					}
				}

				// Enemy
				if (tools.place_meeting(poison.getxPos() + poison.getHsp(), poison.getyPos(), enemy)) {
					poison.setVisible(false);
					poison.setExists(false);
					enemy.setVsp(eJump);
					enemy.setInFloor(false);
					enemy.getHit();
					// Sound FX
					mHitClip.setFramePosition(0);
					mHitClip.start();
				} else if (tools.place_meeting(poison.getxPos() + poison.getWidth() + poison.getHsp(), poison.getyPos(),
						enemy)) {
					poison.setVisible(false);
					poison.setExists(false);
					enemy.setVsp(eJump);
					enemy.setInFloor(false);
					enemy.getHit();
					// Sound FX
					mHitClip.setFramePosition(0);
					mHitClip.start();
				}
			}

			if (enemy.isShooting() && poison.isExists()) {
				enemy.setShooting(false);
				if (shootFre > 500) {
					shootFre -= 100;
				}
				Random r = new Random();
				int dir = r.nextInt(1);
				if (dir == 0) {
					enemy.setHsp(monHsp);
				} else {
					enemy.setHsp(-monHsp);
				}

				monHsp++;
			}

			poison.update();
			// ---------------------- POISON --------------------------

			if (player.getHealth() == 0) {
				loose();
			} else if (enemy.getHealth() == 0) {
				win();
			}
		} else {
			// Background Music
			if (!mainMenuClip.isRunning()) {
				mainMenuClip.setFramePosition(0);
				mainMenuClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}

		repaint();
	}

	public void hitPlayer(int eJump) {
		poison.setVisible(false);
		poison.setExists(false);
		player.getHit();
		player.setVsp(eJump);
		player.setInFloor(false);
		// Sound FX
		pHitClip.setFramePosition(0);
		pHitClip.start();
	}

	public void win() {
		isPlaying = false;
		titles.setState(2);
		// Music
		if (battleClip.isRunning()) {
			battleClip.stop();
			mainMenuClip.setFramePosition(0);
			mainMenuClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void loose() {
		isPlaying = false;
		titles.setState(3);
		// Music
		if (battleClip.isRunning()) {
			battleClip.stop();
			mainMenuClip.setFramePosition(0);
			mainMenuClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void paintComponent(Graphics g) {
		Graphics graph = im.createGraphics();

		if (isPlaying) {
			graph.setColor(bgColor);
			graph.fillRect(0, 0, scr_size, scr_size);

			walls.paint(graph);
			enemy.paint(graph);
			player.paint(graph);

			if (poison.isVisible) {
				poison.paint(graph);
			}
		} else {
			titles.paint(graph);
		}

		g.drawImage(im, 0, 0, null);
	}
	// ------------------------------ METHODS --------------------------

	// ------------------------ KEYS -----------------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_A:
				leftKey = 1;
				break;
			case KeyEvent.VK_D:
				rightKey = 1;
				break;
			case KeyEvent.VK_SPACE:
				jumpKey = 1;
				break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_A:
				leftKey = 0;
				break;
			case KeyEvent.VK_D:
				rightKey = 0;
				break;
			case KeyEvent.VK_SPACE:
				jumpKey = 0;
				break;
		}

		if (!isPlaying) {
			if (key == KeyEvent.VK_ENTER) {
				isPlaying = true;
				player.setHealth(INI_PLAYER_HEALTH);
				enemy.setHealth(INI_ENEMY_HEALTH);
				// Music
				if (mainMenuClip.isRunning()) {
					mainMenuClip.stop();
					battleClip.setFramePosition(0);
					battleClip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	// ------------------------ KEYS -----------------------------------

	// ------------------------- TIME ---------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
	// ------------------------- TIME ---------------------------------

}
