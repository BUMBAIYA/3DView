package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		Loader loader = new Loader();
		
		
		float[] vertices = {
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0
		};
		
		int[] indices = {
			0, 1, 3,
			3, 1, 2
		};
		
		float[] textureCoordinates = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		RawModel rawModel = loader.loadToVAO(vertices, indices, textureCoordinates);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(rawModel, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -1), 0, 0, 0, 0.5f);
		
		while (!Display.isCloseRequested()) {
			entity.increasePosition(0, 0, 0.001f);
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}

}
