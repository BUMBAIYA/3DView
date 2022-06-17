package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("Grassy2"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		
		ModelData flowerData = OBJFileLoader.loadOBJ("flower");
		RawModel flowerModel = loader.loadToVAO(flowerData.getVertices(), flowerData.getTextureCoords(), flowerData.getNormals(), flowerData.getIndices());
		
		ModelData grassData = OBJFileLoader.loadOBJ("grassModel");
		RawModel grassModel = loader.loadToVAO(grassData.getVertices(), grassData.getTextureCoords(), grassData.getNormals(), grassData.getIndices());
		
		ModelData fernData = OBJFileLoader.loadOBJ("fern");
		RawModel fernModel = loader.loadToVAO(fernData.getVertices(), fernData.getTextureCoords(), fernData.getNormals(), fernData.getIndices());
		
		TexturedModel flower = new TexturedModel(flowerModel, new ModelTexture(loader.loadTexture("flower")));
		TexturedModel grass = new TexturedModel(grassModel, new ModelTexture(loader.loadTexture("grassTexture")));
		TexturedModel fern = new TexturedModel(fernModel, new ModelTexture(loader.loadTexture("fern")));
		flower.getTexture().setHasTransparency(true);
		flower.getTexture().setUseFakeLighting(true);
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTransparency(true);
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0; i < 400; i++) {
			entities.add(new Entity(flower, new Vector3f(random.nextFloat() * 800 -400, 0, random.nextFloat() * -600), 0, random.nextFloat() * 180, 0, random.nextFloat() * 2));
			entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 -400, 0, random.nextFloat() * -600), 0, 0, 0, 1));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 -400, 0, random.nextFloat() * -600), 0, random.nextFloat() * 180, 0, 0.6f));
		}
		
		Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap);
		Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap);
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		
		ModelData playerData = OBJFileLoader.loadOBJ("bunny");
		RawModel playerModel = loader.loadToVAO(playerData.getVertices(), playerData.getTextureCoords(), playerData.getNormals(), playerData.getIndices());
		TexturedModel playerTexturedModel = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("yellow")));
		
		Player player = new Player(playerTexturedModel, new Vector3f(100, 0, -50), 0, 0, 0, 1);
		
		while (!Display.isCloseRequested()) {
			camera.move();
			player.move();
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			for (Entity entity: entities) {
				renderer.processEntity(entity);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}

}
