# 3DView

3D OpenGL Renderer using LWJGL2

## Features ✨

- Skybox
- Water Rendering
- Intance Rendering
- Physical based Lighting
- Model loader (only OBJ for now)

## Jar and Natives included in repo in Lib directory or Download [LWJGL2](https://legacy.lwjgl.org/download.php.html)

Version 2.9.3 of Light Weight Java Game Library has a modular design for build artifacts. For convenience you can use the LWJGL build configurator. There you can choose if you want to download a ZIP Bundle or if you want to use a build tool. You could also browse through the artifacts at the bottom of the configurator and download each artifact manually.
This tutorial was made with the LWJGL release version 3.1.5 and the modules for GLFW, OpenGL and STB.

After downloading LWJGL as ZIP bundle or by using your build tool you will have multiple *.jar files. The lwjgl artifact is the core module, the binding artifacts have a lwjgl-\<binding> naming scheme. Optionally you may also have differenct classifiers like sources for the source code, javadoc for the documentation or natives-\<platform> for the native library files

## Setting up your favorite IDE

Folow this step after downloading ZIP bundle
Important

### Eclipse

1. Go to Window -> Preferences
2. Now choose Java -> Build Path -> User Libraries and click on New...
3. Type any name for the library like "LWJGL3"
4. Select the newly made library
5. Click on Add external JARs... and add all *.jar files from the place where you extracted the ZIP Bundle, without the artifacts with sources or javadoc in its name
6. (Optional) For each \*.jar file (excluding the natives classified files) click on Source attachement -> Edit... and add the External location for the corresponding *-sources.jar file

### IntelliJ IDEA

1. Create a new Java project via File -> New Project
2. In your main menu select File -> Project Structure
3. Select the Libraries category
4. Click on the green + -> Java
5. Select the folder where you extracted the ZIP Bundle
6. Confirm that the library will be added to your project

## License

Licensed under MIT license.

---
