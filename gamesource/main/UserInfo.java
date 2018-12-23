package gamesource.main;
import java.io.Serializable;
import java.util.ArrayList;

import gamesource.collidables.Enemy;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = -2451521736177262405L;
	
	public int movementCode;
	public int bulletCode = 0;
	public double x0, y0, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8, x9, y9, x10, y10, x11, y11, x12, y12, x13, y13, x14, y14;
	public float rotation0, rotation1, rotation2, rotation3, rotation4, rotation5, rotation6, rotation7, rotation8, rotation9, rotation10, rotation11, rotation12, rotation13, rotation14;
	public boolean DP0,DP1,DP2,DP3,DP4,DP5,DP6,DP7,DP8,DP9,DP10,DP11,DP12,DP13,DP14, eliminateClientPlayer, eliminateHostPlayer;
	public double mov;
	public int enemyCounter;
	
	public UserInfo(int movementCode, int bulletcode) {
		this.movementCode = movementCode;
		this.bulletCode = bulletcode;
	}
}
