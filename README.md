# AnnoyanceFix StationAPI Edition for Minecraft Beta 1.7.3

![stationapi-banner](https://github.com/telvarost/AnnoyanceFix-StationAPI/assets/25235249/f6331228-f1df-4e48-b33d-e94b46dcc703)

**If you're looking for the ModLoader edition, see this repository:** https://github.com/2zqa/AnnoyanceFix

**If you're looking for skin fixes and inventory fixes:** https://modrinth.com/mod/mojangfix-stationapi-edition

**This version of the mod now supports multiplayer!**
- Use GlassConfigAPI version 2.0+ to set up the config.
- The mod showcase video has also been updated: https://www.youtube.com/watch?v=h-NnpTYiuZc

The aim of this mod is to fix (major) annoyances or backport useful features that do not affect gameplay. List of fixes:

- Pickaxes are also effective against: furnaces, cobblestone stairs, bricks, redstone ore, iron doors, rails, dispensers, stone pressure plates, and monster spawners
- Axes are also effective against: crafting tables, wooden slabs (experimental), wooden stairs, fences, wooden doors, ladders, signs, pumpkins, wooden pressure plates, jukeboxes, and noteblocks
- Fences are placeable like normal and bounding box was minimized when appropriate
- Stairs drop themselves
- Bookshelves drop three books
- Pigs drop their saddles if they have a saddle equipped
- Boats drop themselves
- Boats don't break due to high velocity (or drop boat, or vanilla, toggle with config)
- Never fall through boats anymore when getting out
- Logout/login with vehicle (no longer exit vehicle upon logout)
- Pick block (middle mouse button click) works for blocks that are in the player's inventory, but not in their hotbar
- Pick block also works on: paintings, minecarts (all three types), wooden doors, iron doors, signs, crops, redstone repeaters, redstone wire, beds, piston heads and cake
- Add missing wooden items as fuel sources in furnace: wooden tools, wooden doors, boats, bowls, signs, and ladders
- Repair armor and tools
- Flint and steel does not lose durability if fire fails to ignite
- Flowing lava now disappears when lava source block is removed
- Chicken bounding box was increased to modern Minecraft bounding box size
- Water spring propagation has been fixed: https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1283147-water-spring-propagation-fix
- Also: almost all fixes are toggleable!
  - Note: There is currently a bug with GlassConfigAPI where integer settings need to be saved twice

To use pick block: Look at something and click your scroll wheel. If it's in your inventory, it will automatically be selected.
- On multiplayer pick block can only search the hotbar for the item

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/babric/prism-instance
2. Install Java 17, set the instance to use it, and disable compatibility checks on the instance: https://adoptium.net/temurin/releases/
3. Add StationAPI to the mod folder for the instance: https://modrinth.com/mod/stationapi
4. (Optional) Add Mod Menu to the mod folder for the instance: https://modrinth.com/mod/modmenu-beta
5. (Optional) Add GlassConfigAPI 2.0+ to the mod folder for the instance: https://modrinth.com/mod/glass-config-api
6. Add this mod to the mod folder for the instance: https://github.com/telvarost/AnnoyanceFix-StationAPI/releases
7. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/AnnoyanceFix-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
