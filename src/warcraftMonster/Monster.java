package warcraftMonster;

import warcraftMain.Position;
import warcraftHitbox.MonsterHitbox;

public abstract class Monster {
	// Position du monstre à l'instant t
	protected Position p;
	// Vitesse du monstre
	protected double speed;
	// Position du monstre à l'instant t+1
	protected Position nextP;
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	protected boolean reached;
	// Compteur de déplacement pour savoir si le monstre à atteint le chateau du joueur
	protected int checkpoint = 0;
	
	//Taille de la Hitbox
	protected double size;
	
	//Hitbox
	protected MonsterHitbox hitbox;
	
	public Position getP() {
		return p;
	}
	public void setP(Position p) {
		this.p = p;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Position getNextP() {
		return nextP;
	}
	public void setNextP(Position nextP) {
		this.nextP = nextP;
	}
	public boolean isReached() {
		return reached;
	}
	public void setReached(boolean reached) {
		this.reached = reached;
	}
	public int getCheckpoint() {
		return checkpoint;
	}
	public void setCheckpoint(int checkpoint) {
		this.checkpoint = checkpoint;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public MonsterHitbox getHitbox() {
		return hitbox;
	}
	public void setHitbox(MonsterHitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	public Monster(Position p, double size , double speed) {
		this.p = p;
		this.size = size; 
		this.speed = speed;
		this.nextP = new Position(p);
		this.hitbox = new MonsterHitbox(p, size);
	}
	
	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.getX() - p.getX();
		double dy = nextP.getY() - p.getY();
		if (dy + dx != 0){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.setX(p.getX() +  ratioX * speed);
			p.setY(p.getY() +  ratioY * speed);
			hitbox.move(p);
		}
	}

	public void update() {
		move();
		draw();
		checkpoint++;
	}
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();

	
}
