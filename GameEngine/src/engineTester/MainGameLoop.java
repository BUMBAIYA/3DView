package engineTester;

import org.lwjgl.opengl.Display;

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
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
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
		
		while (!Display.isCloseRequested()) {
			
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}

}
