package warcraftProjectile;

import warcraftMain.Position;
import warcraftMonster.*;

import java.util.ArrayList;

import warcraftMain.*;


public class Arrow extends Projectile{

	public Arrow (Position p , Monster cible) {
		super(2, (float)0.01, true, p, cible, (float)0.03);
	}
	
	public  void draw() {
		StdDraw.picture(p.getX(), p.getY(), "images/Arrow.png", size , size);
	}
	
	//ameliore les caracteristiques du projectile
	public void upgrade (){
		level++;
		damage += 1;
	}

	public void update(ArrayList <Monster> monsters){
		move();
		hitbox.move(p);
		draw();
		
		//Verifie si le projectile a toucher sa cible 
		if(hitbox.hit(target.getHitbox())){
			target.hit(damage);
			hit = true;
		} 
	}
	
	

}
