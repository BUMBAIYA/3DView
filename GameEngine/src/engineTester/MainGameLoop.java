package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel rawModel = OBJLoader.loadObjModel("rainbowCube", loader);
		TexturedModel texturedModel = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("rainbow")));
		
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(0.35f);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0, -5, -25), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		
		List<Entity> allCubes = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0; i < 500; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * 100 - 50;
			allCubes.add(new Entity(texturedModel, new Vector3f(x, y, z), random.nextFloat() * 180f, random.nextFloat() * 180f, 0f, 1f));
		}
		
		while (!Display.isCloseRequested()) {
			entity.increaseRotation(0, 0.15f, 0);
			camera.move();
		
			for (Entity cube: allCubes) {
				renderer.processEntity(cube);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}

}
