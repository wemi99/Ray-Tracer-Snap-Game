# Ray-Tracer-Snap-Game
(Java) The high-level idea of this project is a dual 2D/3D game where the player character has a camera. I modeled a room/scene in 3D space, including walls, light sources, ceilings, floors, monsters, etc. I then rendered the scene in a simple, pixelated, 2D top-down view. The interesting part comes from when the character takes a picture. This picture is rendered in a 3D style using a ray tracer. This is done asynchronously to the actual game, meaning the player can still move around and take additional pictures while previous pictures are rendering. These pictures are automatically stored in an album and time marked.

Images
Example images are shown below. The first two show the 2D view along with an example photo
from the game. The last image comes from the development process and demonstrates some of
our ray tracing features, including shadows and mirroring.

Features
Ray Tracing: For our ray tracing, we implemented the functionality we learned in the main
course about ray tracing, but implemented in Java instead of Python. We have all the components
expected of a ray tracer, including diffuse lighting, specular lighting, ambient lighting, and
mirror lighting. The programmer can explicitly decide where to place all the lights. We’ve also
implemented frustum culling to make the ray tracing faster. Lastly, we have a model for the
player which will cast a shadow along with all the other objects in the scene.
Picture Capturing: When the character in the 2D grid is facing a certain direction, pressing the
spacebar uses the ray tracer to capture the moment in 3D. This photo is then saved in the
“pictures” folder in our project file, automatically named using the date and time. All of this is
done asynchronously using Java threads. This means we can render high quality images while
the user is still playing the game, allowing them to see all their images when they close the game.
Because of this asynchronous nature, we have to copy all of our triangle data before taking the
picture.
2D Grid: This game follows the format of a character moving around a 2D grid. The WSAD
keys are used to move the character around the 2D grid and the arrow keys are used to change
the view of the character’s camera. The camera’s outline will show in what direction the camera
is pointing. The character will not be able to leave the game area.
Blender Objects: For our ray tracing, we also incorporated blender objects to make the photos
captured more intricate and interesting. For example, we created the star. The charmander on the
rock was found online. We imported it into blender, converted it into a mesh, used the decimate
modifier to decrease the number of triangles, and exported it as an .obj file. We also build a .obj
reader from scratch for our project, which is able to read the vertex and face data and render it in
our program.
Misc: We created functionality to allow the charmander on the rock model to spin. We can see
this happening in real time in the 2D view: the charmander PNG will spin. This will also happen
to the 3D model in the ray tracing view.
