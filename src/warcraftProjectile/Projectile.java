package warcraftProjectile;

import warcraftHitbox.ProjectileHitbox;
import warcraftMain.Position;
import warcraftMonster.*;


public abstract class Projectile {

	protected Position p;						//Position du projectile à l'instant t  
	protected int damage ;						//Degat du projectile
	protected float speed ;						//Vitesse du projectile 
	protected boolean aerialTarget ;			//Attaque les cibles volantes 
	protected int level ;						//Niveau des projectiles (le premier niveau est le niveau 1)
	protected ProjectileHitbox hitbox ;			//Hitbox
	protected Monster target ;					//Cible du projectile  
	protected Boolean hit = false; 				//Le projectile atteint sa cible 
	protected float size;
	
	/*
	 * GETTERS AND SETTERS
	 */
	public Position getP() {
		return p;
	}
	public void setP(Position p) {
		this.p = p;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public boolean isAerialTarget() {
		return aerialTarget;
	}
	public void setAerialTarget(boolean aerialTarget) {
		this.aerialTarget = aerialTarget;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public ProjectileHitbox getHitbox() {
		return hitbox;
	}
	public void setHitbox(ProjectileHitbox hitbox) {
		this.hitbox = hitbox;
	}
	public Monster getTarget() {
		return target;
	}
	public void setTarget(Monster cible) {
		this.target = cible;
	}
	public Boolean getHit() {
		return hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}


	public Projectile(int damage, float speed, boolean aerialTarget, Position p, Monster target, float size) {
		this.damage = damage;
		this.speed = speed;
		this.aerialTarget = aerialTarget ;
		this.p = p ;
		this.target = target;
		this.level = 1;
		this.size = size;
		this.hitbox = new ProjectileHitbox(p, size); 
	}
	
	//Calcul de la prochaine position et deplacement jusqua la prochaine position 
	public void move(){
		if(target.getP().getX() > p.getX()){
			//losque le monstre est en haut a droite de la tour  
			if(target.getP().getY() > p.getY()) {
				float AB = Math.abs(target.getP().getX() - p.getX());
				float BC = Math.abs(target.getP().getY() - p.getY());

				//distance entre la tour et le monstre 
				float distance = (float) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );

				//deplace le projectile
				this.p.setX( p.getX() + (speed/distance) * AB);
				this.p.setY( p.getY() + (speed/distance) * BC);
			}	
			//losque le monstre est en bas a droite de la tour 
			else {
				float AB = Math.abs(target.getP().getX() - p.getX());
				float BC = Math.abs( p.getY() - target.getP().getY());
				float distance = (float)Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				this.p.setX( p.getX() + (speed/distance) * AB);
				this.p.setY( p.getY() - (speed/distance) * BC);
			}
		}
		else {
			//losque le monstre est en haut a gauche de la tour 
			if(target.getP().getY() > p.getY()) {
				float AB = Math.abs(p.getX() - target.getP().getX());
				float BC = Math.abs(target.getP().getY() - p.getY());
				float distance = (float) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				this.p.setX( p.getX() - (speed/distance) * AB);
				this.p.setY( p.getY() + (speed/distance) * BC);
			}
			else{
				//losque le monstre est en bas a gauche de la tour 
				float AB = Math.abs(p.getX() - target.getP().getX());
				float BC = Math.abs( p.getY() - target.getP().getY());
				float distance = (float) Math.sqrt ( Math.pow(AB, 2) + Math.pow(BC, 2) );
				this.p.setX( p.getX() - (speed/distance) * AB);
				this.p.setY( p.getY() - (speed/distance) * BC);
			}
		}

		hitbox.move(p);		
	}

	public abstract void draw() ;

	public abstract void upgrade();

	public abstract void update();

}
