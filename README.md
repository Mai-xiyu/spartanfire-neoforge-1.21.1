# Spartan Weaponry: Ice and Fire

NeoForge 1.21.1 port of SpartanFire for:

- Minecraft 1.21.1
- NeoForge 21.1.x
- Ice and Fire Community Edition by IAFEnvoy
- Spartan Weaponry Unofficial

This repository is a porting workspace derived from KreloX/SpartanFire 1.20.1.

## Build

```powershell
.\gradlew.bat build
```

For local composite builds, create an ignored `local.properties`:

```properties
localIceAndFireCePath=../IceAndFire-CE
localSpartanWeaponryPath=../spartanweaponry-neoforge
```

The current port was verified locally with:

- `com.iafenvoy.iceandfire:iceandfire:2.0-beta.17`
- `org.xiyu.spartanweaponryunofficial:spartan_weaponry_unofficial:1.2.0`

## Status

`gradlew.bat build` passes locally. Myrmex materials are retained with fallback stats because the inspected Ice and Fire CE source does not currently expose the old myrmex items/materials used by the original Forge 1.20.1 addon.

## Credits

Original SpartanFire by KreloX. Continuation of Spartan and Fire by ChaosBuffalo.
