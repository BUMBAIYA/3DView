package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 5, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			position.y += 0.25f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			position.y -= 0.25f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			yaw -= 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
			yaw += 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			pitch += 0.5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			pitch -= 0.5f;
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getRoll() {
		return roll;
	}
	

}
