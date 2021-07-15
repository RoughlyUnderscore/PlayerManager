# PlayerManager

This plugin will save a huge ton of information about each player in a respective file.

Commands:
/loadFromFile <playerName> <fileName (optional)>
Permission: pmanager.loadfromfile

Gets player from the first argument and overwrites their current data from a file. If the second argument is not present, it will take the data from last saved file of the player from the first argument. Otherwise, it will load the data of a player in the second argument. Note that the second argument must be a player, who has a file with their UUID saved in the folder!

For example:
/loadFromFile Roughly_ TheCaptainSleepy
Will take Roughly_ and set (most of) his current properties to TheCaptainSleepy properties, such as gamemode, inventory, whitelist status, op status, etc.

Config:
```
# Delay (in ticks), after which the data for all online players will be renewed.
renewTime: 20
lang: 'en'
# Selected from below options.
en:
  could-not-save-file: '&cCould not save file &6%name% &c!'
  no-permissions: '&cInsufficient permissions!'
  too-little-arguments: '&cToo little arguments for command &6%command%&c! Usage: &b%usage%'
  too-many-arguments: '&cToo many arguments for command &6%command%&c! Usage: &b%usage%'
  cant-console: '&cYou can''t use this from console!'
  no-such-player: '&cThere''s no player &6%player% &conline!'
  no-player-data: '&cThere''s no file that stores data of player &6%player%&c!'
ru:
  could-not-save-file: '&cНе получилось сохранить &6%name% &c!'
  no-permissions: '&cНедостаточно прав!'
  too-little-arguments: '&cСлишком мало аргументов для команды &6%command%&c! Использование: &b%usage%'
  too-many-arguments: '&cСлишком много аргументов для команды &6%command%&c! Использование: &b%usage%'
  cant-console: '&cЭта команда не может быть использована консолью!'
  no-such-player: '&cИгрок &6%player% &cне в сети или не найден!'
  no-player-data: '&cНе найдено файла, хранящего данные игрока &6%player%&c!'
```

Example of a file:
```
UUID: 9cc124ee-6b8e-4346-88aa-4a98c8b73d01
Name: Roughly_
Whitelisted: false
Swimming: false
OP: false
Online: true
Invulnerable: false
Glowing: false
Dead: false
Banned: false
Blocking: false
Last-Damage: 0.0
Main-Hand: RIGHT
IP: 127.0.0.1
Sleeping: false
Sneaking: false
Sprinting: false
Flying: false
Total-Exp: 0
Full-Levels: 0
Health: 20.0
Location:
  X: -371.30000001192093
  Y: 72.0
  Z: -212.69999998807907
  Yaw: 252.42673
  Pitch: 66.08695
  World: world
Inventory:
  N0:
    ==: org.bukkit.inventory.ItemStack
    v: 1519
    type: DIRT
```

For support, join this discord: https://discord.gg/bBge7bj3ra
I won't provide support in the replies.
