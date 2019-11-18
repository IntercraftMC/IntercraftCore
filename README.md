# Intercraft Core
Core mod for InterCraft 1.14.4 modpack.


### Todo List
- Missing pieces
    - [x] Metals (ex. copper, tin) :heavy_check_mark:
    - [ ] Minerals (ex. saltpeter, salt) :heavy_check_mark:
    - [x] Rubber
    - [ ] [Electrical Components](https://github.com/IntercraftMC/IntercraftCore/issues/6)
- Machines
    - [ ] PCB Work Table (PCB crafting tree step 1)
    - [ ] Soldering Station (PCB crafting tree step 2)
    - [ ] PCB Factory line (PCB crafting tree step 1 & 2 automated with program)
    - [ ] (Placeable) PCB (logic gates)
    
    *Can be configurated however you want to make 1x1 logic gates (AND, NAND, OR, XOR, etc). Can be used in crafting ex. [OpenComputers](https://minecraft.curseforge.com/projects/opencomputers).*
- Gen.
    - [x] Ores ([hard](https://minecraft.curseforge.com/projects/harder-ores)) :heavy_check_mark:
    
    *Ores which depending on the concentration of metal inside it will vary the break time of the block and amount of ore chunks dropped.*
    - [ ] Ore Gen.
    
    *Ores spawning after the vanilla ore generation algorithm but change the `density` blockstate depending on the chunk's metal concentration of that particular ore type (copper, lead, silver, iron, etc).*
    - [ ] Oil
    - [ ] Minerals :heavy_check_mark:
- Compatibility
    - [ ] [OpenComputers](https://minecraft.curseforge.com/projects/opencomputers) :heavy_check_mark:
    - [ ] [Galacticraft](https://micdoodle8.com/mods/galacticraft)
    - [ ] [Project Red](https://projectredwiki.com/wiki/Main_Page)
- Energy
    - [ ] Cables
    
    *Regular old cables taking up space. But multiple cables (and pipes) can take up the same space like [EnderIO](https://minecraft.curseforge.com/projects/ender-io), also single cable lines can be dragged between connectors like [Immersive Engineering](https://minecraft.curseforge.com/projects/immersive-engineering).*
    - [ ] Custom Batteries
    
    *Batteries which are modeled on real life batteries or Galvanic [Cells](https://en.wikipedia.org/wiki/Galvanic_cell) which are then placed into battery boxes to be usable in external machines.*
    - [ ] Single Use Batteries
    - [ ] [Aquaphobic](https://en.wikipedia.org/wiki/Aquaphobia) (machines, cables without insulation, circuits, etc)
    - [ ] Generators (Water, air, geothermal, fission, steam)
    
    *I really like [ReactorCraft](https://sites.google.com/site/reikasminecraft/reactorcraft)'s way of creating multiblocks. You design the reactor yourself with pieces as indiviual blocks doing their thing. Doing that but for most of the generators where you decide how you want to build it.*
    - [ ] Unit
    
    *Custom energy unit ("ICU"?) also loosely modeled after real life (not completely as to not eat too much TPS).*

### Custom Additions

- Additions

    - [ ] Stone Statues

    *Stone statues spread around the world that were players once, petrified by a mysterious green light thousands of years ago. Frozen in time, caged in a stone prison waiting for the day to awake from their eternal slumber. [Maybe a player will wake up one day to rebuild civilization with science](https://www.youtube.com/watch?v=dQw4w9WgXcQ).*
    - [ ] Sky Block
    
    *A block that renders the current skybox of that dimension. Pretty much a copy of [OpenBlocks](https://minecraft.curseforge.com/projects/openblocks)' Sky Block, because they are cool. Can be crafted with Six (6) Sky Panels and one (1) Stone to make one Sky Block. Sky Panels can be found on the ground when the simulation is starting to crack.*
    - [ ] Glitchium

    *A super rare ore, only found one (1) at a time. Can be hooked into a OC Computer as a component. It has a single function `refine()`, when doing so will destroy the ore block and leave a ingot stack. Doing so has a chance of corrupting the computer, so doing it on a isolated network is a recommendation.*    
    - [ ] Strong Anvil
    
    *Works just like a regular Anvil except for lasting a bit longer than a regular Anvil. But has the special ability to allow Enchanting of tools beyond thirty six (36) levels. Crafted from "Glitchium" Blocks and Ingots like a regular Anvil. Possibly allowing incompatible Enchants to be combined.*
    
    - Chunkloading
        - [ ] Chunkloader
        
        *A plain old Chunkloader which loads the chunk at all time.*
        - [ ] Chunkloader (Redstone)
        
        *Same as Chunkloader, but is only active if it has a Redstone signal.*
        - [ ] Chunkloader (Timed)
        
        *Same as Chunkloader, but has a GUI where you can specify a countdown and is only active while running and above `0`.*
        - [ ] Chunkloader (Array)
        
        *Needs two of them. When linked by facing two of them and sharing one X or Z chunk coordinate will when given a Redstone pulse load all chunks in a straight line between the two points for a specified time (min 2 tick, max 100 tick). If two or more Chunkloaders fills up that criteria it will chose the closest one. Needs to re-charge after each use (600 ticks).*

    - Renewable resources Features
        - [x] Gravel + Sand
        
        *Blowing up Cobblestone has a chance of converting some of it to Gravel, and blowing up Gravel has a chance of converting to Sand.*
        - [ ] Clay
        
        *Mixing Gravel, Dirt and Water (presumably submerging 3/4 Dirt and 1/4 Gravel in a 4x4) will convert it to Clay over a span of (ex.) 30 minutes.*
        - [x] Sponge
        
        *When a regular Guardian is struck by lightning it will convert it to an Elder Guardian which can be farmed.*