<div align="center">
  <img src="https://img.shields.io/badge/Mod_Loader-Fabric-black?style=for-the-badge&labelColor=black&color=%231bd96a" title="Mod Loader" alt="Mod Loader">
  <img src="https://img.shields.io/modrinth/game-versions/sv9FlLct?style=for-the-badge&labelColor=black&color=%231bd96a" title="Game Versions" alt="Game Versions">
</div>

<div align="center">
  <h1>Description</h1>
</div>

This mod makes item frames easier to build with by allowing you to toggle the visibility of item frames! This is normally possible only through commands, however this mod provides an easier, more survival-friendly, method of creating and managing invisible item frames.


### As a side note: 
As this mod is just an interface for vanilla features, if you want your invisible item frames to go back to normal **after** the mod is uninstalled, you'll have to use this command: 

`/execute at @e run data merge entity @e[distance=1, limit=1, type=minecraft:item_frame] {Invisible:0b}`

This will make all item frames in the immediate area visible again.

<div align="center">
  <h1>Features</h1>
</div>

- Toggle item frame visibility by sneak + right-click with an empty hand. (Server Only)

- Hold an item frame to make surrounding item frames visible. (Client Only)

- All item frames will be invisible by default on servers without the mod, this can be changed via config. (Client Only)

If you come across any issues, post an issue on the github repo and I'll get to it as soon as I can.
