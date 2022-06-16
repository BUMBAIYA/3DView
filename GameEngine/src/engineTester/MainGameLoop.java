package engineTester;

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
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel rawModel = OBJLoader.loadObjModel("tree", loader);
		TexturedModel texturedModel = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("tree")));
		
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(0.35f);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -10), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		
		while (!Display.isCloseRequested()) {
			camera.move();
		
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}

}
